package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**

 A class representing a cylinder in three-dimensional space.

 The cylinder is defined by a radius, an axis ray, and a height.

 It extends the Tube class.
 */
public class Cylinder extends Tube {

    /** The height of the cylinder */
    final double height;

    /**

     Constructs a new Cylinder object with the given radius, axis ray, and height.
     @param radius the radius of the cylinder
     @param axisRay the axis ray of the cylinder
     @param height the height of the cylinder
     */
    public Cylinder(double radius, Ray axisRay, double height) {
        super(radius, axisRay);
        this.height = height;
    }
    /**

     Returns the height of the cylinder.
     @return the height of the cylinder
     */
    public double getHeight() {
        return height;
    }

    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}
