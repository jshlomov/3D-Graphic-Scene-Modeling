package geometries;

import primitives.Point;
import primitives.Vector;

/**

 A class representing a sphere in three-dimensional space.

 The sphere is defined by a radius and a center point.

 It extends the RadialGeometry class.
 */
public class Sphere extends RadialGeometry{

    /** The center point of the sphere */
    final Point center;

    /**

     Constructs a new Sphere object with the given radius and center point.
     @param radius the radius of the sphere
     @param center the center point of the sphere
     */
    public Sphere(double radius, Point center) {
        super(radius);
        this.center = center;
    }
    /**

     Returns the center point of the sphere.
     @return the center point of the sphere
     */
    public Point getCenter() {
        return center;
    }
    /**
     * function to calculate the normal of the sphere
     *
     * @param point pointing in the direction of the normal
     * @return the normal vector
     */
    @Override
    public Vector getNormal(Point point) {
        return (point.subtract(center)).normalize();
    }
}
