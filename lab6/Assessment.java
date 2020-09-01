class Assessment implements Keyable<String> {
    private String name;
    private String grade;
    
    Assessment(String name, String grade) {
        this.name = name;
        this.grade = grade;
    }

    @Override
    public String getKey() {
        return this.name;
    }

    String getGrade() {
        return this.grade;
    }

    @Override
    public String toString() {
        return "{" + name + ": " + grade + "}";
    }
}
