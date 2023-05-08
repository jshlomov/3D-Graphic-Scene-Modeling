package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
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
    private final double height;

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
            if (Util.isZero(point.subtract(P0).dotProduct(v)) || Util.isZero((point.subtract(P1).dotProduct(v))))
                return v;
            else
                return super.getNormal(point);
        } catch (IllegalArgumentException e) {
            return v;
        }
    }
}