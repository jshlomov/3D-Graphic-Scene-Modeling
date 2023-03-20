package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Cylinder extends Tube{
    final double height;

    public Cylinder(double radius, Ray axisRay, double height) {
        super(radius, axisRay);
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public Vector getNormal(Point point) {
        return super.getNormal(point);
    }
}
