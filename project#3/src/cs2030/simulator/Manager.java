package cs2030.simulator;

import java.util.PriorityQueue;


public class Manager {
    /**
     * The main method to manage the system.
     * @param seed the seed for random generator
     * @param serverNum total number of human server
     * @param selfCheckoutCounterNum total number of self checkout counter
     * @param maxQ maximum queue allowed
     * @param customerNum total number of customers
     * @param arrivalRate use to calculate the arrival time of the customers
     * @param serviceRate use to calculate the service time every time a customer is served
     * @param restingRate use to calculate the resting time every time a server when into rest
     * @param restingProp probability of a server going to rest
     * @param greedyCustomerProp probability of generating a greedy customer
     */
    public static void main(int seed, int serverNum, int selfCheckoutCounterNum, int maxQ,
                            int customerNum, double arrivalRate, double serviceRate,
                            double restingRate, double restingProp, double greedyCustomerProp) {
        RandomGenerator generator =
                new RandomGenerator(seed, arrivalRate, serviceRate, restingRate);

        if ((serverNum + selfCheckoutCounterNum) == 0) { // if no counter at all
            double arrivalTime = 0;
            PriorityQueue<Event> events = new PriorityQueue<>(new EventComparator());
            for (int i = 0; i < customerNum; i++) {
                Customer c = new Customer(arrivalTime);
                arrivalTime += generator.genInterArrivalTime();
                Event e = new Event(c, c.getArrivalTime(), Event.State.ARRIVES, -1);
                events.add(e);
            }
            while (events.peek() != null) {
                Event e = events.poll();
                System.out.println(e);
                if (e.getState() == Event.State.ARRIVES) {
                    Event next =
                            new Event(e.getCustomer(), e.getEventTime(), Event.State.LEAVES, -1);
                    events.add(next);
                }
            }
            System.out.println("[" + String.format("%.3f", 0.0) + " "
                    + 0 + " " + customerNum + "]");
        } else {
            // generate servers
            int total = selfCheckoutCounterNum > 0 ? serverNum + 1 : serverNum;
            Server[] servers = new Server[total];
            for (int i = 0; i < serverNum; i++) {
                servers[i] = new HumanServer(maxQ, generator, restingProp);
            }
            if (total > serverNum) {
                servers[serverNum] = new SelfCheckoutCounter(maxQ,
                                        generator,
                                        selfCheckoutCounterNum);
            }
            // generate customers, initialize events
            double arrivalTime = 0;
            PriorityQueue<Event> events = new PriorityQueue<>(new EventComparator());
            for (int i = 0; i < customerNum; i++) {
                Customer c = generator.genCustomerType() < greedyCustomerProp
                        ? new GreedyCustomer(arrivalTime)
                        : new TypicalCustomer(arrivalTime);
                arrivalTime += generator.genInterArrivalTime();
                Event e = new Event(c, c.getArrivalTime(), Event.State.ARRIVES, -1);
                events.add(e);
            }

            // go through each events
            while (events.peek() != null) {
                Event e = events.poll();
                if (e.getState() != Event.State.SERVER_REST &&
                        e.getState() != Event.State.SERVER_BACK) {
                    if (e.getServerId() == -1 || e.getState() == Event.State.LEAVES) {
                        System.out.println(e.toString());
                    } else {
                        Server server = servers[e.getServerId() - 1];
                        System.out.println(e.toString() + server.toString() +
                                server.counterNumber(e.getCustomer()));
                    }
                }
                if (e.getServerId() == -1) { // if hasn't assigned to any server
                    boolean isAssigned = false;
                    // check if there is a server with no queue
                    for (int i = 0; i < total; i++) {
                        if (servers[i].canServe() && servers[i].canServeImmediately()) {
                            e.setServerId(i + 1);
                            isAssigned = true;
                            break;
                        }
                    }
                    if (!isAssigned) { // if all servers have queue
                        if (e.getCustomer().isGreedy()) { // if the customer is greedy
                            int minQueueId = 1;
                            // pointer to the server in the servers array that has min queue
                            int minQ = servers[0].queueSize();
                            for (int i = 0; i < total; i++) {
                                if (servers[i].canServe() && servers[i].queueSize() < minQ) {
                                    minQ = servers[i].queueSize();
                                    minQueueId = i + 1;
                                }
                            }
                            e.setServerId(minQueueId);
                            isAssigned = true;
                        } else {
                            for (int i = 0; i < total; i++) {
                                if (servers[i].canServe()) {
                                    e.setServerId(i + 1);
                                    isAssigned = true;
                                    break;
                                }
                            }
                        }
                    }
                    if (!isAssigned) {
                        e.setServerId(1); // else assign to a random server (will leave anyway)
                    }
                }
                Server s = servers[e.getServerId() - 1];
                Event next = s.serve(e);
                if (next != null) {
                    events.add(next);
                }
            }

            //print out statistics
            System.out.println("[" + String.format("%.3f", Server.getAverageWaitingTime()) +
                    " " + Server.getServedNum() + " " +
                    (Customer.total() - Server.getServedNum()) + "]");
        }
    }
}
