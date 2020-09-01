class Roster extends KeyableMap<String, String, Student> {
    Roster(String period) {
        super(period);
    }
    
    @Override
    public Roster put(Student student) {
        return (Roster) super.put(student);
    }

    String getGrade(String student, String module, String assessment) throws NoSuchRecordException {
        if (super.get(student) == null || super.get(student).get(module) == null
                || super.get(student).get(module).get(assessment) == null) {
            throw new NoSuchRecordException(student + " " + module + " " + assessment);
        } else {
            return super.get(student).get(module).get(assessment).getGrade();
        }
    }
}
        
