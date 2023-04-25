package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * A class representing a cylinder in three-dimensional space.
 * <p>
 * The cylinder is defined by a radius, an axis ray, and a height.
 * <p>
 * It extends the Tube class.
 */
public class Cylinder extends Tube {

    /**
     * The height of the cylinder
     */
    final double height;

    /**
     * Constructs a new Cylinder object with the given radius, axis ray, and height.
     *
     * @param radius  the radius of the cylinder
     * @param axisRay the axis ray of the cylinder
     * @param height  the height of the cylinder
     */
    public Cylinder(double radius, Ray axisRay, double height) {
        super(radius, axisRay);
        this.height = height;
    }

    /**
     * Returns the height of the cylinder.
     *
     * @return the height of the cylinder
     */
    public double getHeight() {
        return height;
    }

    @Override
    public Vector getNormal(Point point) {
        Point P0 = axisRay.getP0(); //middle of starting base
        Vector v = axisRay.getDir();
        Point P1 = P0.add(v.scale(height)); //middle of far base

        try {
            //if point is on the starting base - if distance from p0 is radius, and orthogonal to ray (so it is not on ray itself)
            //if point is on the far base - if distance from p1 is radius, and orthogonal to ray (so it is not on ray itself)
            if ((point.distance(P0) <= radius) && (point.subtract(P0).dotProduct(v) == 0)
                    || (point.distance(P1) <= radius) && (point.subtract(P1).dotProduct(v) == 0)) {
                return v;
            }

            // if point is on round surfaces - not based:
            else {
                Vector PMinusP0 = point.subtract(P0);
                double t = v.dotProduct(PMinusP0);
                Point O = P0.add(v.scale(t));
                return (point.subtract(O)).normalize();
            }
        } catch (Exception e) {
            return v;
        }
    }
}