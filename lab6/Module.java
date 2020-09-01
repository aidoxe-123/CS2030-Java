class Module extends KeyableMap<String, String, Assessment> {    
    Module(String moduleCode) {
        super(moduleCode);
    }

    @Override
    public Module put(Assessment assessment) {
        return (Module) super.put(assessment);
    }
}
