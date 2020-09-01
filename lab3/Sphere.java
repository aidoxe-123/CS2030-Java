class Sphere implements Shape3D {
    protected final double radius;

    Sphere(double radius) {
        this.radius = radius;
    }

    @Override
    public double getVolume() {
        return Math.PI * Math.pow(this.radius, 3) * 4 / 3;
    }

    public double getSurfaceArea() {
        return Math.PI * Math.pow(this.radius, 2) * 4;
    }

    public Sphere setRadius(double radius) {
        return new Sphere(radius);
    }

    @Override
    public String toString() {
        return "Sphere [" + String.format("%.2f", this.radius) + "]";
    }
}