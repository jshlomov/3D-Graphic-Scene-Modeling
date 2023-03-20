package geometries;

import primitives.Point;
import primitives.Vector;

public abstract class RadialGeometry implements Geometry {
    protected double radius;

    public RadialGeometry(double radius) {
        this.radius = radius;
    }

    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}
