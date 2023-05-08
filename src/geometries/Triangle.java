package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;

/**

 A class representing a triangle in three-dimensional space.

 The triangle is defined by three points.

 It extends the Polygon class.
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
    public List<Point> findIntersections(Ray ray) {
        // there is no intersection with the plane
        if (plane.findIntersections(ray) == null)
            return null;

        Vector v1 = vertices.get(0).subtract(ray.getP0());
        Vector v2 = vertices.get(1).subtract(ray.getP0());
        Vector v3 = vertices.get(2).subtract(ray.getP0());
        Vector n1 = (v1.crossProduct(v2)).normalize();
        Vector n2 = (v2.crossProduct(v3)).normalize();
        Vector n3 = (v3.crossProduct(v1)).normalize();
        double t1 = alignZero(n1.dotProduct(ray.getDir()));
        double t2 = alignZero(n2.dotProduct(ray.getDir()));
        double t3 = alignZero(n3.dotProduct(ray.getDir()));

        //  the point outside the triangle
        if (t1 == 0 || t2 == 0 || t3 == 0)
            return null;

        if ((t1 > 0 && t2 > 0 && t3 > 0) || (t1 < 0 && t2 < 0 && t3 < 0)) { // In case the all scalars are in the same sign, the point is in the triangle
            return plane.findIntersections(ray);
        }
        return null;
    }
}
