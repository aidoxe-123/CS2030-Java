class Point {
    private double x;
    private double y;
    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    Point midPoint(Point other) {
        double newX = (this.x + other.x) / 2;
        double newY = (this.y + other.y) / 2;
        return new Point(newX, newY);
    }

    double angleTo(Point other) {
        double dx = other.x - this.x;
        double dy = other.y - this.y;
        if (dx == 0 && dy == 0) {
            return 0;
        }
        if (dx >= 0) {
            return Math.atan(dy / dx);
        } else {
            if (dy >= 0) {
                return Math.PI + Math.atan(dy / dx);
            } else {
                return Math.atan(dy / dx) - Math.PI;
            }
        }
    }
    
    Point moveTo(double angle, double distance) {
        double newX = this.x + distance * Math.cos(angle);
        double newY = this.y + distance * Math.sin(angle);
        return new Point(newX, newY);
    }
    double getX() {
        return this.x;
    }
    double getY() {
        return this.y;
    }
    @Override
    public String toString() {
        return "point (" + String.format("%.3f", x) + ", " + 
                String.format("%.3f", y) + ")";
    }
}
