package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry {
    final Point q0;
    final Vector normal;

    public Plane(Point point1, Point point2, Point point3) {
        q0 = point1;
        normal = null;
    }

    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal.normalize();
    }

    public Point getQ0() {
        return q0;
    }

    public Vector getNormal() {
        return normal;
    }

    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}
