package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**

 A class representing a tube in three-dimensional space.

 The tube is defined by a radius and an axis ray.

 It extends the RadialGeometry class.
 */
public class Tube extends RadialGeometry {

    /** The axis ray of the tube */
    final Ray axisRay;

    /**

     Constructs a new Tube object with the given radius and axis ray.
     @param radius the radius of the tube
     @param axisRay the axis ray of the tube
     */
    public Tube(double radius, Ray axisRay) {
        super(radius);
        this.axisRay = axisRay;
    }
    /**

     Returns the axis ray of the tube.
     @return the axis ray of the tube
     */
    public Ray getAxisRay() {
        return axisRay;
    }

    /**
     * function that receive a point in a body and return a normal in this point to the body
     *
     * @param point pointing in the direction of the normal
     * @return normal vector to the Geometry
     */
    @Override
    public Vector getNormal(Point point) {
        Vector v = axisRay.getDir();
        Point p0 = axisRay.getP0();
        double t= point.subtract(p0).dotProduct(v);
        // getting the center point
        Point center = p0.add(v.scale(t));
        return (point.subtract(center)).normalize();
    }

}
