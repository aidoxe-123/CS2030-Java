class Server {
    private double finishTime = 0;
    private static int servedNum = 0;
    private static double totalWaitingTime = 0;
    private Customer isWaiting = null;
    
    Server() {}
    /**
     * Generate a new server depending on the customer and the server's current state.
     * @param c : customer, old: the server's current state
     * @return a new server
     */
    
    private Server(Customer c, Server old) {
        if (old.isWaiting == null) {
            if (c.getArrivalTime() < old.finishTime) {
                this.finishTime = old.finishTime;
                this.isWaiting = c;
                totalWaitingTime += this.finishTime - c.getArrivalTime();
            } else {
                this.finishTime = 1 + c.getArrivalTime();
            }
        } else {
            if (old.finishTime + 1 <= c.getArrivalTime()) {
                this.finishTime = 1 + c.getArrivalTime();
            } else {
                this.finishTime = old.finishTime + 1;
                this.isWaiting = c;
                totalWaitingTime += this.finishTime - c.getArrivalTime();
            }
        }
    }

    /**
     * Decide whether the customer has to wait.
     * @param other new customer
     * @return true if the customer has to wait, false otherwise
     */
    public boolean hasToWait(Customer other) {
        return (isWaiting == null && finishTime > other.getArrivalTime())
                || (isWaiting != null && finishTime + 1 > other.getArrivalTime());
    }

    public boolean canServe(Customer other) {
        return isWaiting == null || finishTime <= other.getArrivalTime();
    }

    /**
     * Serve a new customer.
     * @param other new customer
     * @return if the customer sacrifice the conditions, then return a new server
     *         else return this server.
     */
    public Server serve(Customer other) {
        if (other.getState() == Customer.State.ARRIVES && this.canServe(other)) {
            servedNum++;
            return new Server(other, this);
        } else {
            return this;
        }
    }

    public double getFinishTime() {
        return this.finishTime;
    }

    public static int getServedNum() {
        return servedNum;
    }

    public static double getAverageWaitingTime() {
        return totalWaitingTime / servedNum;
    }

    public boolean haveWaitingCustomer() {
        return isWaiting != null;
    }

}
