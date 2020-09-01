/open Lazy.java
/open InfiniteList.java
/open InfiniteListImpl.java
/open EmptyList.java
UnaryOperator<Integer> op = x -> {
    System.out.printf("iterate: %d -> %d\n", x, x + 1);
    return x + 1;
}
Supplier<Integer> generator = () -> {
    System.out.println("generate: 1");
    return 1;
}
Function<Integer, Integer> doubler = x -> {
    System.out.printf("doubler map: %d -> %d\n", x, x * 2);
    return x * 2;
}
Function<Integer, Integer> oneLess = x -> {
    System.out.printf("oneLess map: %d -> %d\n", x, x - 1);
    return x -1;
}
Predicate<Integer> lessThan100_2 = x -> {
    System.out.printf("filter: %d -> %b\n", x, x < 100);
    return x < 100;
}
Predicate<Integer> lessThan100 = x -> {
    System.out.printf("takeWhile: %d -> %b\n", x, x < 100);
    return x < 100;
}
Object[] arr = InfiniteList.iterate(0, op).filter(lessThan100_2).map(doubler).takeWhile(lessThan100).map(oneLess).limit(5).toArray()
System.out.println(Arrays.toString(arr))
/exit
