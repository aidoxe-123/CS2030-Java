class Box<T> {
	private final T t;
	
	private Box(T t) {
		this.t = t;
	}
	private T get() {
		return t;
	}
	private final static Box<?> EMPTY_BOX = new Box<>(null);
	
	/*-------------------*/
	
	public static <U> Box<U> of(U t) {
		return (t == null) ? null : new Box<>(t);
	}
	public static <U> Box<U> ofNullable(U t) {
		return (t == null) ? empty() : new Box<>(t);
	}
	@SuppressWarnings("unchecked")
	public static <U> Box<U> empty() {
		return (Box<U>) EMPTY_BOX;
	}

	/*------------------*/
	public boolean isPresent() {
		return t != null;
	}
	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		} else if (object instanceof Box) {
			return t == null ? t == ((Box) object).get() :t.equals(((Box) object).get());
		} else {
			return false;
		}
	}

	public Box<T> filter(BooleanCondition<? super T> c) {
		if (this == EMPTY_BOX) {
			return this;
		} else if (c.test(this.t) == true) {
			return this;
		} else {
			Box<T> box = empty();
			return box;
		}
	}

	public <U> Box<U> map(Transformer<? super T, U> transformer) {
		if (this == EMPTY_BOX) {
			Box<U> box = empty();
			return box;
		} else {
			return  Box.ofNullable(transformer.transform(this.t));
		}
	}

	@Override
	public String toString() {
		return "[" + (t == null ? "" : t) + "]";
	}
}

