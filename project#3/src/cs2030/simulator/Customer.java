package cs2030.simulator;

public class Customer {
    private static int totalCustomer = 0;
    private double arrivalTime;
    private int id;

    
    public Customer(double arrivalTime) {
        this.arrivalTime = arrivalTime;
        id = ++totalCustomer;
    }

    @Override
    public String toString() {
        return id + " arrives at " + String.format("%.3f", arrivalTime);
    }

    public double getArrivalTime() {
        return arrivalTime;
    }
    
    public int getId() {
        return id;
    }

    public boolean isGreedy() {
        return false;
    }

    public static int total() {
        return totalCustomer;
    }
}