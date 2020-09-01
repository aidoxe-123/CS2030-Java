import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.function.Consumer;

class EmptyList<T> implements InfiniteList<T> {
    EmptyList() {

    }
    public boolean isEmpty() {
        return true;
    }

    public EmptyList<T> peek() {
        return this;
    }

    public <R> EmptyList<R> map(Function<? super T, ? extends R> mapper) {
        return new EmptyList<>();
    }

    public EmptyList<T> filter(Predicate<? super T> predicate) {
        return this;
    }

    public EmptyList<T> limit(long n) {
        return this;
    }

    public EmptyList<T> takeWhile(Predicate<? super T> predicate) {
        return this;
    }

    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator) {
        return identity;
    }

    public void forEach(Consumer<? super T> action) {
        //do nothing
    }
}