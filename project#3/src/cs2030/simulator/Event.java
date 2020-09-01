package cs2030.simulator;

public class Event {
    private Customer customer;
    private double eventTime;
    private State state;
    private int serverId;

    /**
     * The state of the event.
     */
    enum State {
        ARRIVES("arrives"),
        SERVED("served"),
        LEAVES("leaves"),
        DONE("done"),
        WAITS("waits"),
        SERVER_REST(" server rest"),
        SERVER_BACK(" server back");
        private String string;

        State(String string) {
            this.string = string;
        }

        @Override
        public String toString() {
            return string;
        }
    }

    /**
     * Create an event associate with a customer and a serverId.
     * @param customer customer associate with the event
     * @param eventTime the time that this event happens
     * @param state the state of this event
     * @param serverId the sever corresponds to this event
     */
    Event(Customer customer, double eventTime, State state, int serverId) {
        this.customer = customer;
        this.eventTime = eventTime;
        this.state = state;
        this.serverId = serverId;
    }

    public double getEventTime() {
        return eventTime;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getServerId() {
        return this.serverId;
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

    public State getState() {
        return state;
    }
    
    @Override
    public String toString() {
        if (state == state.SERVER_REST || state == state.SERVER_BACK) {
            return serverId + state.toString();
        }
        String sufix;
        if (state == State.DONE) {
            sufix = " serving by ";
        } else if (state == State.SERVED) {
            sufix = " by ";
        } else if (state == State.WAITS) {
            sufix = " to be served by ";
        } else {
            sufix = "";
        }
        return String.format("%.3f", eventTime) + " " + customer.toString() + " "
                + state + sufix;
    }
}