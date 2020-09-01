class LongerThan implements BooleanCondition<String> {
	private int limit;
	LongerThan(int limit) {
		this.limit = limit;
	}
	@Override
	public boolean test(String s) {
		return s.length() > limit;
	}
}
