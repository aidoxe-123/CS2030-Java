import java.util.Comparator;

class EventComparator implements Comparator<Event> {
    /**
     * Compare two events by time.
     * If 2 events have the same time,
     * then the one with a lower customer id will go first
     * @param e1 event
     * @param e2 event
     * @return 0 if e1 and e2 are equivalent, > 0 if e1 comes after e2, < 0 if e1 comes before e2
     */
    @Override
    public int compare(Event e1, Event e2) {
        if (e1.getEventTime() == e2.getEventTime()) {
            return e1.getCustomer().getId() - e2.getCustomer().getId();
        } else {
            return e1.getEventTime() > e2.getEventTime() ? 1 : -1;
        }
    }
}
