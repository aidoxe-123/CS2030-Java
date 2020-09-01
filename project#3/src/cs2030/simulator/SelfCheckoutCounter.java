package cs2030.simulator;

public class SelfCheckoutCounter extends Server {
    public SelfCheckoutCounter(int capacity, RandomGenerator generator, int nSelf) {
        super(capacity, generator, 0, nSelf);
    }

    @Override
    public String toString() {
        return "self-check ";
    }
}