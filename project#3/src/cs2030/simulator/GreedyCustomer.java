package cs2030.simulator;

public class GreedyCustomer extends Customer {
    GreedyCustomer(double arrivalTime) {
        super(arrivalTime);
    }

    @Override
    public boolean isGreedy() {
        return true;
    }

    @Override
    public String toString() {
        return "" + super.getId() + "(greedy)";
    }
}