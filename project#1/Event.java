class Event {
    private Customer customer;
    private double eventTime;
    private double serveTime;

    Event(Customer customer) {
        this.customer = customer;
        this.eventTime = customer.getArrivalTime();
    }
    
    private Event(Customer customer, double eventTime, double serveTime) {
        this.customer = customer;
        this.eventTime = eventTime;
        this.serveTime = serveTime;
    }

    /**
     * Create a new event that is a consquence of this event.
     * @param server : the server that is related to this event
     * @return a new event
     */
    public Event update(Server server) {
        if (customer.getState() == Customer.State.ARRIVES) {
            if (server.canServe(customer)) {
                if (!server.hasToWait(customer)) {
                    Customer newState = customer.setState(Customer.State.SERVED);
                    return new Event(newState, eventTime, eventTime);
                } else {
                    Customer newState = customer.setState(Customer.State.WAITS);
                    return new Event(newState, eventTime,
                            server.getFinishTime() + (server.haveWaitingCustomer() ? 1 : 0));
                }
            } else {
                Customer newState = customer.setState(Customer.State.LEAVES);
                return new Event(newState, eventTime, eventTime);
            }
        } else if (customer.getState() == Customer.State.SERVED) {
            Customer newState = customer.setState(Customer.State.DONE);
            return new Event(newState, eventTime + 1, eventTime);
        } else if (customer.getState() == Customer.State.WAITS) {
            Customer newState = customer.setState(Customer.State.SERVED);
            return new Event(newState, serveTime, serveTime);
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
    
    @Override
    public String toString() {
        return String.format("%.3f", eventTime) + " " + customer.getId() + " " + customer.getState();
    }
}
