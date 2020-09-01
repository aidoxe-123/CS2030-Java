class Material {
    private final String material;
    private final double density;

    Material(String material, double density) {
        this.material = material;
        this.density = density;
    }

    public double getDensity() { return this.density; }
}