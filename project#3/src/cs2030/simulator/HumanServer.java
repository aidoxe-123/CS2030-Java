package cs2030.simulator;

public class HumanServer extends Server {
    HumanServer(int capacity, RandomGenerator generator, double restingProp) {
        super(capacity, generator, restingProp, 1);
    }
}