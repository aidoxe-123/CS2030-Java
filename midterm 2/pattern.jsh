String pattern(int n) {
   return Stream.iterate(n, x -> x - 1).limit(n).map(x -> 
        Stream.iterate(x, a -> a - 1)
              .limit(x)
              .reduce(0, (a, b) -> 10 * a + b)
   ).map(x -> x.toString() + "\n").reduce("", (x, y) -> x + y);
}
