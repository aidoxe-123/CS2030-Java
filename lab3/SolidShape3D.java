class SolidShape3D {
    private Shape3D shape;
    private Material material;

    SolidShape3D(Shape3D shape, Material material) {
        this.shape = shape;
        this.material = material;
    }
    public double getDensity() {
        return material.getDensity();
    }

    public double getMass() {
        return getDensity() * shape.getVolume();
    }

    @Override
    public String toString() {
        return "Solid" + shape.toString() + " with a mass of " + String.format("%.2f", getMass());
    }
}