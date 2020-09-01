import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.BinaryOperator;
import java.util.Optional;

interface InfiniteList<T> {
    Optional<T> get();

    InfiniteList<T> limit(long maxSize);

    void forEach(Consumer<? super T> action);

    Object[] toArray();

    <S> InfiniteList<S> map(Function<? super T, ? extends S> mapper);

    InfiniteList<T> filter(Predicate<? super T> predicate);

    InfiniteList<T> takeWhile(Predicate<? super T> predicate);

    long count();

    Optional<T> reduce(BinaryOperator<T> accumulator);

    T reduce(T identity, BinaryOperator<T> accumulator);

    static <U> InfiniteList<U> generate(Supplier<? extends U> supplier) {
        return new InfiniteListImpl<U>() {
            public Optional<U> get() {
                return Optional.of(supplier.get());
            }
            public boolean isEmpty() {
                return false;
            }
        };
    }

    static <U> InfiniteList<U> iterate(U seed, UnaryOperator<U> f) {
        return new InfiniteListImpl<U>() {
            private U element = seed;
            private boolean firstElement = true;
            public Optional<U> get() {
                if (firstElement) {
                    firstElement = false;
                } else {
                    element = f.apply(element);
                }
                return Optional.of(element);
            }
            public boolean isEmpty() {
                return false;
            }
        };
    }
}
