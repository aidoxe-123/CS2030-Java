import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Stack;

class InfiniteListImpl<T> implements InfiniteList<T> {
    private final Lazy<T> head;
    private final Supplier<? extends InfiniteList<T>> tail;

    private InfiniteListImpl(Lazy<T> head, Supplier<? extends InfiniteList<T>> tail) {
        this.head = head;
        this.tail = tail;
    }
    public static <T> InfiniteListImpl<T> generate(Supplier<? extends T> supplier) {
        return new InfiniteListImpl<T>(Lazy.generate(supplier),
                () -> InfiniteListImpl.generate(supplier));
    }

    public static <T> InfiniteListImpl<T> iterate(T seed, UnaryOperator<T> f) {
        return new InfiniteListImpl<T>(Lazy.ofNullable(seed),
                () -> InfiniteListImpl.iterate(f.apply(seed), f));
    }

    public InfiniteList<T> peek() {
        this.head.get().ifPresent(System.out :: println);
        return this.tail.get();
    }

    public <R> InfiniteListImpl<R> map(Function<? super T, ? extends R> mapper) {
        Lazy<R> newHead = this.head.map(mapper);
        Supplier<? extends InfiniteList<R>> newTail = () -> this.tail.get().map(mapper);
        return new InfiniteListImpl<R>(newHead, newTail);
    }

    public InfiniteListImpl<T> filter(Predicate<? super T> predicate) {
        Lazy<T> newHead = this.head.filter(predicate);
        Supplier<? extends InfiniteList<T>> newTail = () -> this.tail.get().filter(predicate);
        return new InfiniteListImpl<T>(newHead, newTail);
    }

    public boolean isEmpty() {
        return false;
    }

    public InfiniteList<T> limit(long n) {
        if (n <= 0) {
            return new EmptyList<>();
        } else {
            return new InfiniteListImpl<>(this.head, () -> {
                return InfiniteListImpl.this.head.get().isPresent()
                        ? n - 1 > 0
                            ? this.tail.get().limit(n - 1)
                            : new EmptyList<>()
                        : this.tail.get().limit(n);
            });
        }
    }

    public InfiniteList<T> takeWhile(Predicate<? super T> predicate) {
        Lazy<T> newHead = this.head.filter(predicate);
        return new InfiniteListImpl<>(newHead, () -> {
            return this.head.get().isPresent() && !newHead.get().isPresent()
                    ? new EmptyList<>()
                    : this.tail.get().takeWhile(predicate);
        });
    }

    public Object[] toArray() {
        ArrayList<T> elements = new ArrayList<>();
        InfiniteListImpl<T> current = this;
        while (!current.isEmpty()) {
            Optional<T> box = current.head.get();
            if (box.isPresent()) {
                elements.add(box.get());
            }
            InfiniteList<T> next = current.tail.get();
            if (next.isEmpty()) {
                break;
            } else {
                current = (InfiniteListImpl<T>) next;
            }
        }
        return elements.toArray();
    }

    @Override
    public long count() {
        InfiniteListImpl<T> current = this;
        long result = 0;
        while(!current.isEmpty()) {
            if (current.head.get().isPresent()) {
                result++;
            }
            InfiniteList<T> next = current.tail.get();
            if (next.isEmpty()) {
                break;
            } else {
                current = (InfiniteListImpl<T>) next;
            }
        }
        return result;
    }

    @Override
    public <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator) {
        Stack<T> stack = new Stack<>();
        InfiniteListImpl<T> current = this;
        while(!current.isEmpty()) {
            Optional<T> box = current.head.get();
            if (box.isPresent()) {
                stack.push(box.get());
            }
            InfiniteList<T> next = current.tail.get();
            if (next.isEmpty()) {
                break;
            } else {
                current = (InfiniteListImpl<T>) next;
            }
        }
        U result = identity;
        while(!stack.empty()) {
            result = accumulator.apply(result, stack.pop());
        }
        return result;
    }

    public void forEach(Consumer<? super T> action) {
        InfiniteListImpl<T> current = this;
        while(!current.isEmpty()) {
            Optional<T> box = current.head.get();
            if (box.isPresent()) {
                action.accept(box.get());
            }
            InfiniteList<T> next = current.tail.get();
            if (next.isEmpty()) {
                break;
            } else {
                current = (InfiniteListImpl<T>) next;
            }
        }
    }
}