package geometries;

import primitives.Point;
import primitives.Vector;

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

    /**
     * function to calculate the normal of the triangle
     *
     * @param point pointing in the direction of the normal
     * @return call to polygon implement
     */
    @Override
    public Vector getNormal(Point point){
        return super.getNormal(point);
    }
}
