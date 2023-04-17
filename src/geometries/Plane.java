package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Represents a plane in 3D space defined by a point on the plane and a normal vector.
 */
public class Plane implements Geometry {

    /**
     * A point on the plane.
     */
    final Point q0;

    /**
     * The normal vector to the plane.
     */
    final Vector normal;

    /**
     * Constructs a plane from three points on the plane.
     *
     * @param point1 a point on the plane
     * @param point2 a point on the plane
     * @param point3 a point on the plane
     */
    public Plane(Point point1, Point point2, Point point3) {
        q0 = point1;
        normal = null;
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
        return null;
    }
}
