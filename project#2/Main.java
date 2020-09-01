import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Iterator;

class Main {
    /**
     * Read the input, then generate events, store those events in a priority queue.
     * Go through each event of the priority queue,
     * Print out the event and generate new ones base on that event and the server related to it
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // creating servers
        int totalServer = sc.nextInt();
        Server[] servers = new Server[totalServer];
        for (int i = 0; i < totalServer; i++) {
            servers[i] = new Server();
        }
        //reading input
        PriorityQueue<Event> queue = new PriorityQueue<>(new EventComparator());
        while (sc.hasNextDouble()) {
            double i = sc.nextDouble();
            Customer c = new Customer(i);
            Event e = new Event(c);
            queue.add(e);
        }
        while (queue.peek() != null) {
            Event e = queue.poll();
            Customer c = e.getCustomer();
            System.out.println(e);
            // if the an event is not assigned to any server yet, assign a server to it
            if (e.getServerId() == -1) {
                boolean assigned = false;
                for (Server s : servers) {
                    if (s.canServe(c) && !s.hasToWait(c)) {
                        e.setServerId(s.getId());
                        assigned = true;
                        break;
                    }
                }
                if (!assigned) {
                    for (Server s : servers) {
                        if (s.canServe(c)) {
                            e.setServerId(s.getId());
                            assigned = true;
                            break;
                        }
                    }
                }
                if (!assigned) {
                    e.setServerId(1);
                    // the customer will leave no matter what server is assigned to them.
                }
            }
            //generate new events base on the existing one
            Server s = servers[e.getServerId() - 1];
            if (e.update(s) != null) {
                queue.add(e.update(s));
            }
            servers[e.getServerId() - 1] = s.serve(c);
        }
        //print out statistics
        System.out.println("[" + String.format("%.3f", Server.getAverageWaitingTime()) + " "
                + Server.getServedNum() + " " + (Customer.total() - Server.getServedNum()) + "]");

    }
}
