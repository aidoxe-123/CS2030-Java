import java.util.function.Supplier;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.Optional;

class Lazy<T> {
    private T v;
    private Supplier<? extends T> supplier;
    
    private Lazy(T v) {
        this.v = v;
        this.supplier = null;
    }

    private Lazy(Supplier<? extends T> supplier) {
        this.v = null;
        this.supplier = supplier;
    }

    static <T> Lazy<T> ofNullable(T v) {
        return new Lazy<>(v);
    }

    static <T> Lazy<T> generate(Supplier<? extends T> supplier) {
        return new Lazy<>(supplier);
    }
    
    <R> Lazy<R> map(Function<? super T, ? extends R> mapper) {
        Lazy<R> newLazy = new Lazy<>(() -> {
            Optional<R> temp = this.get().map(mapper);
            return temp.isPresent() ? temp.get() : null;
        });
        return newLazy;
    }
    
    Lazy<T> filter(Predicate<? super T> predicate) {
        Lazy<T> newLazy = new Lazy<>(() -> {
            Optional<T> temp = this.get().filter(predicate);
            return temp.isPresent() ? temp.get() : null;
        });
        return newLazy;
    }

    Optional<T> get() {
        if (this.v == null) {
            if (this.supplier == null) {
                return Optional.empty();
            } else {
                this.v = supplier.get();
                this.supplier = null;
                return Optional.ofNullable(v);
            }
        } else {
            return Optional.of(this.v);
        }
    }
    @Override
    public String toString() {
        return this.v == null
                ? this.supplier == null
                        ? "null"
                        : "?"
                : this.v.toString();
    }
}