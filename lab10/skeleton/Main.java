import java.time.Instant;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

/**
 * This program finds different ways one can travel by bus (with a bit 
 * of walking) from one bus stop to another.
 *
 * @author: Ooi Wei Tsang
 * @version: CS2030 AY19/20 Semester 1, Lab 10
 */
public class Main {
    /**
     * The program read a sequence of (id, search string) from standard input.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        //ArrayList<CompletableFuture<String>> waitList = new ArrayList<>();
        Instant start = Instant.now();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            BusStop srcId = new BusStop(sc.next());
            String searchString = sc.next();
            CompletableFuture<String> toWait = BusSg.findBusServicesBetween(srcId, searchString)
                    .thenCompose(str -> str.description());
            System.out.println(toWait.join());
            //waitList.add(toWait);
        }
        //CompletableFuture<Void> wait = CompletableFuture.allOf(waitList.toArray(new CompletableFuture<?>[1]));
        //wait.join();
        sc.close();
        Instant stop = Instant.now();
        System.out.printf("Took %,dms\n", Duration.between(start, stop).toMillis());
    }
}