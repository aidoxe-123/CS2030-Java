import java.util.stream.IntStream;
import java.util.stream.Stream;

class Main {
    static boolean isPrime(int n) {
        return IntStream
              .range(2,n)
              .noneMatch(x -> n % x == 0);
    }
    /*
    static int[] twinPrimes(int n) {
        return IntStream
              .rangeClosed(2, n)
              .boxed()
              .map(x -> new Pair<>(x, x + 2))
              .filter(pair -> isPrime(pair.first) && isPrime(pair.second))
              .flatMap(pair -> isPrime(pair.first - 2) && (pair.first - 2 != 1)
                              ? Stream.<Integer>of(pair.second)
                              : pair.second > n
                              ? Stream.<Integer>of(pair.first)
                              : Stream.<Integer>of(pair.first, pair.second))
              .mapToInt(x -> x)
              .toArray();
    }
    */
    /*static int gcd(int m, int n) {
        return Stream
              .iterate(new Pair<>(m, n),
                       pair -> pair.second > pair.first
                                ? new Pair<>(pair.second, pair.first)
                                : pair.second == 0
                                ? new Pair<>(0,0)
                                : new Pair<>(pair.second, pair.first % pair.second))
              .takeWhile(pair -> !(pair.first == 0 && pair.second == 0))
              .reduce(new Pair<>(m,n), 
                      (pair1, pair2) -> pair1.second == 0 ? pair1 : pair2)
              .first;
                                                    
    }*/
    static int gcd(int m, int n) {
        return Stream
            .iterate(new Pair<>(m, n),
                    pair -> pair.second > pair.first
                            ? new Pair<>(pair.second, pair.first)
                            : pair.second == 0
                            ? new Pair<>(0, 0)
                            : new Pair<>(pair.second, pair.first % pair.second))
            .filter(pair -> pair.second == 0)
            .findFirst()
            .get()
            .first;
    }
    /*
    static long countRepeats(int... array) {
        return (long) IntStream
                     .range(0, array.length)
                     .map(i -> i == 0
                            ? 0
                            : array[i] == array[i - 1] 
                              && (i == array.length - 1 || array[i] != array[i + 1])
                            ? 1
                            : 0)
                     .sum();
    }
    */
    /*
    static double normalizedMean(Stream<Integer> stream) {
        KeepTrack tracking = stream.map(x -> new KeepTrack(0,0,x,0))
                                   .reduce(null,
                      (a, b) -> a == null
                              ? new KeepTrack(b.sum, b.sum, b.sum, 1)
                              : new KeepTrack(Math.max(a.max, b.sum),
                                              Math.min(a.min, b.sum),
                                              a.sum + b.sum,
                                              a.count + 1));
        return tracking == null
            || tracking.count == 0 
            || (double) tracking.sum / tracking.count - tracking.min == 0
            ? 0
            : ((double) tracking.sum / tracking.count - tracking.min)
                / (tracking.max - tracking.min);
    }
    */
}
