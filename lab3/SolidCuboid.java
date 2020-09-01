class SolidCuboid extends Cuboid {
    private final double density;

    SolidCuboid(double height, double width, double length, double density) {
        super(height, width, length);
        this.density = density;
    }

    @Override
    public SolidCuboid setHeight(double height) {
        return new SolidCuboid(height, super.width, super.length, this.density);
    }

    @Override
    public SolidCuboid setWidth(double width) {
        return new SolidCuboid(super.height, width, super.length, this.density);
    }

    @Override
    public SolidCuboid setLength(double length) {
        return new SolidCuboid(super.height, super.width, length, this.density);
    }

    public double getDensity() {
        return this.density;
    }

    public double getMass() {
        return this.density * super.getVolume();
    }

    @Override
    public String toString() {
        return "Solid" + super.toString() + " with a mass of " + String.format("%.2f", getMass());
    }
}