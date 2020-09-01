/open Lazy.java
/open InfiniteList.java
/open InfiniteListImpl.java
/open EmptyList.java
/open Store.java
UnaryOperator<Integer> op = x -> {
    System.out.printf("iterate: %d -> %d\n", x, x + 1);
    return x + 1;
}
Predicate<Integer> lessThan5 = x -> {
    System.out.printf("takeWhile: %d -> %b\n", x, x < 5);
    return x < 5;
}
InfiniteList<Integer> list = InfiniteList.iterate(1, op).takeWhile(lessThan5).peek().peek()
System.out.println();
list = InfiniteList.iterate(1, op).takeWhile(x -> x < 0).peek()
/exit
