import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Iterator;

class Main {
    /**
     * Read the input, then process the event and output them in a proper order .
     * @param args : argument string array
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Server s = new Server();
        PriorityQueue<Event> queue = new PriorityQueue<>(new EventComparator());
        while (sc.hasNextDouble()) {
            double i = sc.nextDouble();
            Customer c = new Customer(i);
            Event e = new Event(c);
            queue.add(e);
        }
        while (queue.peek() != null) {
            Event e = queue.poll();
            System.out.println(e);
            if (e.update(s) != null) {
                queue.add(e.update(s));
            }
            s = s.serve(e.getCustomer());
        }
        System.out.println("[" + String.format("%.3f", Server.getAverageWaitingTime()) + " "
                + Server.getServedNum() + " " + (Customer.total() - Server.getServedNum()) + "]");

    }
}
