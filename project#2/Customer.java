class Customer {
    private static int totalCustomer = 0;
    private double arrivalTime;
    private int id;
    private State state;
    
    enum State {
        ARRIVES("arrives"),
        SERVED("served"),
        LEAVES("leaves"),
        DONE("done"),
        WAITS("waits");
        private String string;
        
        State(String string) {
            this.string = string;
        }
        
        @Override
        public String toString() {
            return string;
        }
    }
    
    Customer(double arrivalTime) {
        this.arrivalTime = arrivalTime;
        id = ++totalCustomer;
        state = State.ARRIVES;
    }

    /**
     * Generate a Customer with a state other than State.ARRIVES
     */
    private Customer(Customer old, State state) {
        this.arrivalTime = old.arrivalTime;
        this.id = old.id;
        this.state = state;
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

    public State getState() {
        return state;
    }

    public Customer setState(State state) {
        return new Customer(this, state);
    }

    public static int total() {
        return totalCustomer;
    }
}

