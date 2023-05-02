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
    protected final Ray axisRay;

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

    @Override
    public Vector getNormal(Point p) {
        double t = (axisRay.getDir()).dotProduct(p.subtract(axisRay.getP0()));
        if (t != 0) //in case the point is not across the ray point
        {
            Point center = axisRay.getP0().add(axisRay.getDir().scale(t));
            return p.subtract(center).normalize();
        }
        else // in case the point is across the ray point
            return p.subtract(axisRay.getP0()).normalize();
    }
}
