class DivisibleBy implements BooleanCondition<Integer> {
	private Integer divisor;
	DivisibleBy(Integer divisor) {
		this.divisor = divisor;
	}
	@Override
	public boolean test(Integer i) {
		return (int) i % (int) divisor == 0;
	}
}
