class Cruise {
    private final String identifier;
    private final int arrival_time;
    private final int num_loaders;
    private final int service_time;

    public Cruise(String identifier, int arrival_time, int num_loaders, int service_time) {
            this.identifier = identifier;
            this.arrival_time = (arrival_time / 100) * 60 + (arrival_time % 100);
            this.num_loaders = num_loaders;
            this.service_time = service_time;
    }

    public int getNumOfLoadersRequired() {
        return num_loaders;
    }
    
    public int getArrivalTime() {
        return arrival_time;
    }

    public int getServiceCompletionTime() {
        return arrival_time + service_time;
    }

    @Override
    public String toString() {
        return identifier + "@" + String.format("%04d", (arrival_time / 60) * 100  + arrival_time % 60);
    }
}

