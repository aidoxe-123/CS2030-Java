class Student extends KeyableMap<String,String,Module> {
    Student(String name) {
        super(name);
    }

    @Override
    public Student put(Module module) {
        return (Student) super.put(module);
    }
}
