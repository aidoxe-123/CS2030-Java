class SolidSphere extends Sphere{
    private final double density;

    SolidSphere(double radius, double density) {
        super(radius);
        this.density = density;
    }

    public double getDensity() {
        return this.density;
    }

    public double getMass() {
        return this.density * super.getVolume();
    }

    @Override
    public SolidSphere setRadius(double radius) {
        return new SolidSphere(radius, this.density);
    }

    @Override
    public String toString() {
        return "Solid" + super.toString() + " with a mass of " + String.format("%.2f", getMass());
    }
}