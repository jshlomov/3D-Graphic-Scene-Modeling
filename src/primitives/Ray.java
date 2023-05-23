package primitives;

import java.util.List;
import static primitives.Util.isZero;

/**
 * The Ray class represents a ray in 3D space, defined by a starting point and a direction vector.
 */
public class Ray {
    /**
     * The starting point of the ray.
     */
    private final Point p0;
    /**
     * The direction vector of the ray.
     */
    private final Vector dir;

    /**
     * Constructs a new Ray object with the specified starting point and direction vector.
     *
     * @param p the starting point of the ray
     * @param v the direction vector of the ray
     */
    public Ray(Point p, Vector v) {
        p0 = p;
        dir = v.normalize();
    }

    /**
     * Returns the starting point of the ray.
     *
     * @return the starting point of the ray
     */
    @SuppressWarnings("unused")
    public Point getP0() {
        return p0;
    }

    /**
     * Returns the normalized direction vector of the ray.
     *
     * @return the normalized direction vector of the ray
     */
    @SuppressWarnings("unused")
    public Vector getDir() {
        return dir;
    }

    /**
     * Calculate a point on the ray at a given distance from the head of the ray
     *
     * @param t the distance value
     * @return point
     */
    public Point getPoint(double t) {
        return isZero(t) ? p0 : p0.add(dir.scale(t));
    }

    /**
     * finds the first point the ray intersect a body
     * @return the first point
     */
    public Point findClosestPoint(List<Point> intersections) {
        if (intersections.isEmpty()) return null;

        Point result = intersections.get(0);
        double minDistance = p0.distanceSquared(result);
        for (var i : intersections) {
            double distance = p0.distanceSquared(i);
            if (distance < minDistance) {
                minDistance = distance;
                result = i;
            }
        }
        return result;

//        return intersections.isEmpty() ? null :intersections.stream()
//                .min(Comparator.comparing(p0::distanceSquared)).get();
    }

    @Override
    public String toString() {
        return "Point: " + p0 + "\n" +
                "Vector: " + dir;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Ray ray)
            return p0.equals(ray.p0) && dir.equals(ray.dir);
        return false;
    }
}
