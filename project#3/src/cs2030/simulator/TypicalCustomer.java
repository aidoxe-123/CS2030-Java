package cs2030.simulator;

public class TypicalCustomer extends Customer {
    TypicalCustomer(double arrivalTime) {
        super(arrivalTime);
    }

    @Override
    public boolean isGreedy() {
        return false;
    }

    @Override
    public String toString() {
        return "" + super.getId();
    }
}
