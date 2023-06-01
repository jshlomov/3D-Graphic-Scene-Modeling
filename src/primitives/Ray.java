package primitives;

import geometries.Intersectable.GeoPoint;

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
     * @param points all the intersections with the geometry
     * @return the first point
     */
    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point; }

    /**
     * finds the first point the ray intersect a body
     * @param intersections all the intersections with the geometry
     * @return the first point and the geometry
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> intersections) {
        GeoPoint result = null;
        double minDistance = Double.POSITIVE_INFINITY;
        for (var i : intersections) {
            double distance = p0.distanceSquared(i.point);
            if (distance < minDistance) {
                minDistance = distance;
                result = i;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "Point: " + p0 + "\n" +
                "Vector: " + dir;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o instanceof Ray ray && p0.equals(ray.p0) && dir.equals(ray.dir);
    }
}
