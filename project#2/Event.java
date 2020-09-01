class Event {
    private Customer customer;
    private double eventTime;
    private double serveTime;
    private int serverId;

    Event(Customer customer) {
        this.customer = customer;
        this.eventTime = customer.getArrivalTime();
        this.serverId = -1;
    }
    
    private Event(Customer customer, double eventTime, double serveTime, int serverId) {
        this.customer = customer;
        this.eventTime = eventTime;
        this.serveTime = serveTime;
        this.serverId = serverId;
    }

    /**
     * Create a new event that is a result of this event, depending on the input server's state.
     * @param server : the server that is related to this event
     * @return a new event
     */
    public Event update(Server server) {
        if (customer.getState() == Customer.State.ARRIVES) {
            if (server.canServe(customer)) {
                if (!server.hasToWait(customer)) {
                    Customer newState = customer.setState(Customer.State.SERVED);
                    return new Event(newState, eventTime, eventTime, this.serverId);
                } else {
                    Customer newState = customer.setState(Customer.State.WAITS);
                    return new Event(newState, eventTime,
                            server.getFinishTime() + (server.haveWaitingCustomer() ? 1 : 0),
                            this.serverId);
                }
            } else {
                Customer newState = customer.setState(Customer.State.LEAVES);
                return new Event(newState, eventTime, eventTime, this.serverId);
            }
        } else if (customer.getState() == Customer.State.SERVED) {
            Customer newState = customer.setState(Customer.State.DONE);
            return new Event(newState, eventTime + 1, eventTime, this.serverId);
        } else if (customer.getState() == Customer.State.WAITS) {
            Customer newState = customer.setState(Customer.State.SERVED);
            return new Event(newState, serveTime, serveTime, this.serverId);
        } else {
            return null;
        }
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
    
    @Override
    public String toString() {
        String sufix;
        if (customer.getState() == Customer.State.DONE) {
            sufix = " serving by " + serverId;
        } else if (customer.getState() == Customer.State.SERVED) {
            sufix = " by " + serverId;
        } else if (customer.getState() == Customer.State.WAITS) {
            sufix = " to be served by " + serverId;
        } else {
            sufix = "";
        }
        return String.format("%.3f", eventTime) + " " + customer.getId() + " "
                + customer.getState() + sufix;
    }
}
