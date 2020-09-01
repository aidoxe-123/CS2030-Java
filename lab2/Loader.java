class Loader {
    protected final int id;
    protected final Cruise is_serving;

    Loader(int id) {
        this.id = id;
        this.is_serving = null;
    }
    Loader(int id, Cruise is_serving) {
        this.id = id;
        this.is_serving = is_serving;
    }

    public Loader serve(Cruise c) {
        return canServe(c) ? new Loader(id, c) : null;
    }

    public boolean canServe(Cruise new_cruise) {
        if (this.is_serving == null) {
            return true;
        } else {
            return is_serving.getServiceCompletionTime() <= new_cruise.getArrivalTime();
        }
    }

    @Override
    public String toString() {
        return "Loader " + id
                + (is_serving != null ? " serving " + is_serving.toString() : "");
    }
}