import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.function.Consumer;

interface InfiniteList<T> {

    static <T> InfiniteList<T> generate(Supplier<? extends T> supplier) {
        return InfiniteListImpl.generate(supplier);
    }

    static <T> InfiniteList<T> iterate(T t, UnaryOperator<T> f) {
        return InfiniteListImpl.iterate(t, f);
    }

    InfiniteList<T> peek();

    <R> InfiniteList<R> map(Function<? super T, ? extends R> mapper);

    InfiniteList<T> filter(Predicate<? super T> predicate);

    boolean isEmpty();

    InfiniteList<T> limit(long n);

    InfiniteList<T> takeWhile(Predicate<? super T> predicate);

    Object[] toArray();

    long count();

    <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator);

    void forEach(Consumer<? super T> action);
}