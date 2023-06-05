package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * A class representing a triangle in three-dimensional space.
 * <p>
 * The triangle is defined by three points.
 * <p>
 * It extends the Polygon class.
 */
public class Triangle extends Polygon {

    /**
     * Constructs a new Triangle object with the given three points.
     *
     * @param point1 the first point of the triangle
     * @param point2 the second point of the triangle
     * @param point3 the third point of the triangle
     */
    public Triangle(Point point1, Point point2, Point point3) {
        super(point1, point2, point3);
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        var intersection = plane.findGeoIntersectionsHelper(ray, maxDistance);
        // there is no intersection with the plane
        if (intersection == null)
            return null;

        Point p0 = ray.getP0();
        Vector v = ray.getDir();

        Vector v1 = vertices.get(0).subtract(p0);
        Vector v2 = vertices.get(1).subtract(p0);
        Vector n1 = (v1.crossProduct(v2));
        double t1 = alignZero(n1.dotProduct(v));
        if (t1 == 0) return null;

        Vector v3 = vertices.get(2).subtract(p0);
        Vector n2 = (v2.crossProduct(v3));
        double t2 = alignZero(n2.dotProduct(v));
        if (t1 * t2 <= 0) return null;

        Vector n3 = (v3.crossProduct(v1));
        double t3 = alignZero(n3.dotProduct(v));
        if (t1 * t3 <= 0) return null;

        return List.of(new GeoPoint(this, intersection.get(0).point));
    }
}
