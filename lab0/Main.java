import java.util.Scanner;

class Main {
    public static Circle createCircle(Point p1, Point p2, double radius) {
        Point midPoint = p1.midPoint(p2);
        double dx = (p2.getX() - p1.getX()) / 2;
        double dy = (p2.getY() - p1.getY()) / 2;
        double halfLengthSquare = dx * dx + dy * dy;
        if ((dx == 0 && dy == 0) || radius * radius < halfLengthSquare) {
            return null;
        }
        Point center = midPoint.moveTo(p1.angleTo(p2) + Math.PI / 2,
                    Math.sqrt(radius * radius - halfLengthSquare));
        return Circle.getCircle(center, radius);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double x1 = sc.nextDouble();
        double y1 = sc.nextDouble();
        double x2 = sc.nextDouble();
        double y2 = sc.nextDouble();
        double radius = sc.nextDouble();
        Circle c = createCircle(new Point(x1, y1), new Point(x2, y2), radius);
        if (c == null) {
            System.out.println("No valid circle can be created");
        } else {
            System.out.println("Created: " + c.toString());
        }
    }
}
