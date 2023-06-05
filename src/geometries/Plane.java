package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Represents a plane in 3D space defined by a point on the plane and a normal vector.
 */
public class Plane extends Geometry {

    /**
     * A point on the plane.
     */
    private final Point q0;

    /**
     * The normal vector to the plane.
     */
    private final Vector normal;

    /**
     * Constructs a plane from three points on the plane.
     *
     * @param point1 a point on the plane
     * @param point2 a point on the plane
     * @param point3 a point on the plane
     */
    public Plane(Point point1, Point point2, Point point3) {
        q0 = point1;
        Vector u = point2.subtract(point1);
        Vector v = point3.subtract(point1);
        normal = u.crossProduct(v).normalize();
    }

    /**
     * Constructs a plane from a point on the plane and a normal vector.
     *
     * @param q0     a point on the plane
     * @param normal the normal vector to the plane
     */
    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal.normalize();
    }

    /**
     * Returns a point on the plane.
     *
     * @return a point on the plane
     */
    @SuppressWarnings("unused")
    public Point getQ0() {
        return q0;
    }

    /**
     * Returns the normal vector to the plane.
     *
     * @return the normal vector to the plane
     */
    public Vector getNormal() {
        return normal;
    }

    /**
     * Returns the normal vector to the plane at the given point.
     *
     * @param point a point on the plane
     * @return the normal vector to the plane at the given point
     */
    @Override
    public Vector getNormal(Point point) {
        return normal;
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        try {
            double nQMinusP0 = normal.dotProduct(q0.subtract(ray.getP0()));
            double nv = normal.dotProduct(ray.getDir());
            if (isZero(nv)) return null;
            double t = Util.alignZero(nQMinusP0 / nv);
            if (t <= 0 || alignZero(t - maxDistance) > 0)
                return null;
            return List.of(new GeoPoint(this, ray.getPoint(t)));
        } catch (IllegalArgumentException ignore) {
            // in case nQMinusP0 is 0 (Ray is neither orthogonal nor parallel
            // to the plane and begins in the same point)
            return null;
        }
    }
}
