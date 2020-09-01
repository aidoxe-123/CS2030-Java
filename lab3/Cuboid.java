class Cuboid implements Shape3D {
    protected final double height;
    protected final double width;
    protected final double length;

    Cuboid(double height, double width, double length) {
        this.height = height;
        this.width = width;
        this.length = length;
    }

    @Override
    public double getVolume() {
        return height * width * length;
    }

    public double getSurfaceArea() {
        return 2 * (height * width + height * length + width * length);
    }

    public Cuboid setHeight(double height) {
        return new Cuboid(height, this.width, this.length);
    }

    public Cuboid setWidth(double width) {
        return new Cuboid(this.height, width, this.length);
    }

    public Cuboid setLength(double length) {
        return new Cuboid(this.height, this.width, length);
    }

    @Override
    public String toString() {
        return "Cuboid [" + String.format("%.2f", this.height)
                + " x " + String.format("%.2f", this.width)
                + " x " + String.format("%.2f", this.length) + "]";
    }
}