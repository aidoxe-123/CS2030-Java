class SmallCruise extends Cruise {
    private static final int LOADER_NUM = 1;
    private static final int TIME_TO_LOAD = 30;
    SmallCruise(String identifier, int arrival_t) {
        super(identifier, arrival_t, 1, 30);
    }
}
