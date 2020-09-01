import java.util.Optional;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.BinaryOperator;
import java.util.Stack;
abstract class InfiniteListImpl<T> implements InfiniteList<T> {
    abstract public Optional<T> get();
    abstract public boolean isEmpty();
    
    @Override
    public void forEach(Consumer<? super T> action) {
        Optional<T> curr;
        while(!this.isEmpty()) {
            curr = this.get();
            curr.ifPresent(action);
        }
    }

    @Override
    public InfiniteList<T> limit(long maxSize) throws IllegalArgumentException {
        if (maxSize >= 0) {
            return new InfiniteListImpl<T>() {
                private long count = 1;
                public Optional<T> get() {
                    if (this.isEmpty()) {
                        return Optional.empty();
                    } else {
                        Optional<T> curr = InfiniteListImpl.this.get();
                        while (curr.isEmpty() && !isEmpty()) {
                            curr = InfiniteListImpl.this.get();
                        }
                        count++;
                        return curr;
                    }
                }

                @Override
                public boolean isEmpty() {
                    return InfiniteListImpl.this.isEmpty()
                            ? true
                            : count > maxSize;
                }
            };
        } else {
            throw new IllegalArgumentException(Long.toString(maxSize));
        }
    }

    @Override
    public Object[] toArray() {
        ArrayList<T> tempList = new ArrayList<>();
        this.forEach((x) -> tempList.add(x));
        return tempList.toArray();
    }

    @Override
    public <S> InfiniteList<S> map(Function<? super T, ? extends S> mapper) {
        return new InfiniteListImpl<S>() {
            public Optional<S> get() {
                return isEmpty()
                        ? Optional.empty()
                        : InfiniteListImpl.this.get().map(mapper);
            }
            public boolean isEmpty() {
                return InfiniteListImpl.this.isEmpty();
            }
        };
    }

    @Override
    public InfiniteList<T> filter(Predicate<? super T> predicate) {
        return new InfiniteListImpl<T>() {
            public Optional<T> get() {
                return isEmpty()
                        ? Optional.empty()
                        : InfiniteListImpl.this.get().filter(predicate);
            }
            public boolean isEmpty() {
                return InfiniteListImpl.this.isEmpty();
            }
        };
    }

    @Override
    public InfiniteList<T> takeWhile(Predicate<? super T> predicate) {
        return new InfiniteListImpl<T>() {
            private boolean stop = false;
            public Optional<T> get() {
                if (!this.isEmpty() ) {
                    Optional<T> curr = InfiniteListImpl.this.get();
                    if (curr.isPresent()) {
                        if (predicate.test(curr.get())) {
                            return curr;
                        } else {
                            stop = true;
                        }
                    }
                }
                return Optional.empty();
            }
            @Override
            public boolean isEmpty() {
                return InfiniteListImpl.this.isEmpty()
                        ? true
                        : stop;
            }
        };
    }

    @Override
    public long count() {
        Optional<T> curr;
        long result = 0;
        while(!this.isEmpty()) {
            curr = this.get();
            if (curr.isPresent()) {
                result++;
            }
        }
        return result;
    }

    @Override
    public Optional<T> reduce(BinaryOperator<T> accumulator) {
        T result;
        Optional<T> curr;
        Stack<T> stack = new Stack<>();
        while(!this.isEmpty()) {
            curr = this.get();
            if (curr.isPresent()) {
                stack.push(curr.get());
            }
        }
        if (stack.empty()) {
            return Optional.empty();
        } else {
            result = stack.pop();
            while(!stack.empty()) {
                result = accumulator.apply(result, stack.pop());
            }
            return Optional.of(result);
        }
    }

    @Override
    public T reduce(T identity, BinaryOperator<T> accumulator) {
        T result;
        Optional<T> curr;
        Stack<T> stack = new Stack<>();
        stack.push(identity);
        while(!this.isEmpty()) {
            curr = this.get();
            if (curr.isPresent()) {
                stack.push(curr.get());
            }
        }
        result = stack.pop();
        while(!stack.empty()) {
            result = accumulator.apply(result, stack.pop());
        }
        return result;
    }
}
