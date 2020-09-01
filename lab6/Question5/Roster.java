import java.util.NoSuchElementException;

class Roster extends KeyableMap<String, String, Student> {
    Roster(String period) {
        super(period);
    }
    
    @Override
    public Roster put(Student student) {
        return (Roster) super.put(student);
    }

    String getGrade(String student, String module, String assessment) throws NoSuchRecordException {
        try {
            return super.get(student).orElseThrow()
                    .get(module).orElseThrow()
                    .get(assessment).orElseThrow().getGrade();
        } catch (NoSuchElementException e) {
            throw new NoSuchRecordException(student + " " + module + " " + assessment);
        }
    }
}
        
