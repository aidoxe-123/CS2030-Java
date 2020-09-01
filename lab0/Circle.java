class Circle {
    Point center;
    double radius;

    private Circle(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    static Circle getCircle(Point center, double radius) {
        return radius > 0 ? new Circle(center, radius) : null;
    }

    @Override
    public String toString() {
        return "circle of radius " + radius +
                " centered at " + center.toString();
    }
}
