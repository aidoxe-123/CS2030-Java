package cs2030.simulator;

import java.util.LinkedList;
import java.util.Queue;

public class Server {
    private RandomGenerator generator;

    protected int id;
    private int capacity;
    private double restingProp;
    private boolean isResting;
    private int nSelf;
    private Customer[] counters;
    private int isChecking;
    // the waitingLine maximum length will be capacity, with the 1st element is the one being served
    Queue<Customer> waitingLine = new LinkedList<>();

    private static int totalServer = 0;
    private static int servedNum = 0;
    private static double totalWaitingTime = 0;

    /**
     * Creating a server.
     * @param capacity the maximum queue length that this server can have
     * @param generator the generator used to generate service time and resting time
     * @param restingProp the probability that this server comes to rest
     * @param nSelf the number of counters that this server have
     *              (self checking servers is considered as 1 server with multiple counters)
     */
    public Server(int capacity, RandomGenerator generator, double restingProp, int nSelf) {
        this.id = ++totalServer;
        this.capacity = capacity;
        this.generator = generator;
        this.restingProp = restingProp;
        this.isResting = false;
        this.nSelf = nSelf;
        counters = new Customer[nSelf];
        isChecking = 0;
    }

    /**
     * Generate the next event that follows an input event.
     * @param e the input event
     * @return an event that happens as the consequence of the input event
     *         if nothing happens, return null instead
     */
    public Event serve(Event e) {
        if (e.getState() == Event.State.ARRIVES) {
            if (waitingLine.size() > capacity) {
                System.out.println("error in waiting line");
            } else if (waitingLine.size() == capacity) {
                return new Event(e.getCustomer(),
                        e.getCustomer().getArrivalTime(),
                        Event.State.LEAVES,
                        id);
            } else if (isResting) {
                waitingLine.add(e.getCustomer());
                return new Event(e.getCustomer(),
                        e.getCustomer().getArrivalTime(),
                        Event.State.WAITS,
                        id);
            } else {
                if (isChecking < nSelf) {
                    for (int i = 0; i < nSelf; i++) {
                        if (counters[i] == null) {
                            counters[i] = e.getCustomer();
                            isChecking++;
                            return new Event(e.getCustomer(),
                                    e.getCustomer().getArrivalTime(),
                                    Event.State.SERVED,
                                    id);
                        }
                    }
                } else {
                    waitingLine.add(e.getCustomer());
                    return new Event(e.getCustomer(),
                            e.getCustomer().getArrivalTime(),
                            Event.State.WAITS,
                            id);
                }
            }
        } else if (e.getState() == Event.State.SERVED) {
            return new Event(e.getCustomer(),
                    e.getEventTime() + generator.genServiceTime(),
                    Event.State.DONE,
                    id);
        } else if (e.getState() == Event.State.DONE) {
            servedNum++;
            isChecking--;
            int availableCounter = -1;
            for (int i = 0; i < nSelf; i++) {
                if (counters[i] == e.getCustomer()) {
                    counters[i] = null; // when finished, the customer goes out of the line
                    availableCounter = i;
                    break;
                }
            }
            if (restingProp > 0 && generator.genRandomRest() < restingProp) {
                return new Event(e.getCustomer(), e.getEventTime(), Event.State.SERVER_REST, id);
            } else {
                if (waitingLine.peek() != null) { // if there are waiting customers
                    // that customer goes out of the line to move to the counter
                    Customer nextCustomer = waitingLine.poll();
                    counters[availableCounter] = nextCustomer;
                    isChecking++;
                    totalWaitingTime += e.getEventTime() - nextCustomer.getArrivalTime();
                    return new Event(nextCustomer,
                            e.getEventTime(),
                            Event.State.SERVED, // the next customer is served
                            id);
                } else {
                    return null;
                }
            }
        } else if (e.getState() == Event.State.SERVER_REST) {
            isResting = true;
            return new Event(e.getCustomer(),
                    e.getEventTime() + generator.genRestPeriod(),
                    Event.State.SERVER_BACK,
                    id);
        } else if (e.getState() == Event.State.SERVER_BACK) {
            isResting = false;
            if (waitingLine.peek() != null) { // if there are waiting customers
                Customer nextCustomer = waitingLine.poll();
                for (int i = 0; i < nSelf; i++) {
                    if (counters[i] == null) {
                        counters[i] = nextCustomer;
                        isChecking++;
                        break;
                    }
                }
                totalWaitingTime += e.getEventTime() - nextCustomer.getArrivalTime();
                return new Event(nextCustomer,
                        e.getEventTime(),
                        Event.State.SERVED, // the next customer is served
                        id);
            } else {
                return null;
            }
        }
        return null;
    }

    public boolean canServe() {
        return waitingLine.size() < capacity;
    }

    /**
     * Decide whether this server can serve a customer without letting that customer waiting.
     * @return true if it can serve immediately, false otherwise
     */
    public boolean canServeImmediately() {
        if (!isResting) {
            for (int i = 0; i < nSelf; i++) {
                if (counters[i] == null) {
                    return true;
                }
            }
        }
        return false;
    }

    public int queueSize() {
        return waitingLine.size();
    }

    /**
     * Return the counter number that an input customer is currently standing.
     * If the server is a human server:
     * - only has 1 counter
     * - the counter that the customer waits to be served
     *   and the counter that serve him/ her is the same
     * If the server is a self - checking counter:
     * - can have multiple counters
     * - the counter that the customer waits to be served
     *   and the counter that serve him/ her can be different
     * @param c an input customer
     * @return the id of the counter
     */
    public int counterNumber(Customer c) {
        for (int i = 0; i < nSelf; i++) {
            if (counters[i] == c) {
                return this.id + i;
            }
        }
        return this.id;
    }

    @Override
    public String toString() {
        return "server ";
    }

    public static int getServedNum() {
        return servedNum;
    }

    public static double getAverageWaitingTime() {
        return totalWaitingTime / servedNum;
    }
}