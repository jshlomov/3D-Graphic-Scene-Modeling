package geometries;

import primitives.*;
import java.util.List;

/**

 A class representing a sphere in three-dimensional space.

 The sphere is defined by a radius and a center point.

 It extends the RadialGeometry class.
 */
public class Sphere extends RadialGeometry{

    /** The center point of the sphere */
    private final Point center;

    /**
     Constructs a new Sphere object with the given radius and center point.
     @param radius the radius of the sphere
     @param center the center point of the sphere
     */
    public Sphere(Point center, double radius) {
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

    @Override
    public List<Point> findIntersections(Ray ray) {
        if (center.equals(ray.getP0()))
            return List.of(center.add(ray.getDir().scale(radius)));
        Vector u = center.subtract(ray.getP0());
        double tm = ray.getDir().dotProduct(u);
        double d = Math.sqrt(Util.alignZero(u.lengthSquared() - (tm*tm)));
        if (d >= radius) return null;
        double th = Math.sqrt(radius * radius - d * d);
        double t1 = tm + th;
        double t2 = tm - th;
        if (Util.alignZero(t1) > 0 && Util.alignZero(t2) > 0)
            return List.of(ray.getPoint(t1), ray.getPoint(t2));
        else if (Util.alignZero(t1) > 0)
            return List.of(ray.getPoint(t1));
        else if (Util.alignZero(t2) > 0)
            return List.of(ray.getPoint(t2));
        else
            return null;
    }
}
