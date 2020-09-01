class BigCruise extends Cruise {
    private static final int LENGTH_PER_LOADER = 40;
    private static final int PASSENGER_PER_MINUTE = 50;
    BigCruise(String identifier, int arrival_t , int length, int passenger_num) {
        super(identifier,
              arrival_t,
              length % LENGTH_PER_LOADER == 0 ? length / LENGTH_PER_LOADER : length / LENGTH_PER_LOADER + 1,
              passenger_num % PASSENGER_PER_MINUTE == 0 ? passenger_num / PASSENGER_PER_MINUTE : passenger_num / PASSENGER_PER_MINUTE + 1);
    }
}
