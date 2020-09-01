class RecycledLoader extends Loader {
    //private final boolean is_recycled = true;

    RecycledLoader(int id) {
        super(id);
    }
    RecycledLoader(int id, Cruise is_serving) {
        super(id, is_serving);
    }

    @Override
    public RecycledLoader serve(Cruise c) {
        return canServe(c) ? new RecycledLoader(id, c) : null;
    }

    @Override
    public boolean canServe(Cruise new_cruise) {
        if (super.is_serving == null) {
            return true;
        } else {
            return super.is_serving.getServiceCompletionTime() + 60 <= new_cruise.getArrivalTime();
        }
    }

    @Override
    public String toString() {
        return "Loader " + super.id + " (recycled)"
                + (super.is_serving != null ? " serving " + super.is_serving.toString() : "");
    }
}