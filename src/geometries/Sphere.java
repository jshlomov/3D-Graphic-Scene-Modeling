package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * A class representing a sphere in three-dimensional space.
 * <p>
 * The sphere is defined by a radius and a center point.
 * <p>
 * It extends the RadialGeometry class.
 */
public class Sphere extends RadialGeometry {

    /**
     * The center point of the sphere
     */
    private final Point center;

    /**
     * Constructs a new Sphere object with the given radius and center point.
     *
     * @param radius the radius of the sphere
     * @param center the center point of the sphere
     */
    public Sphere(Point center, double radius) {
        super(radius);
        this.center = center;
    }

    /**
     * Returns the center point of the sphere.
     *
     * @return the center point of the sphere
     */
    @SuppressWarnings("unused")
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
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        if (center.equals(ray.getP0()))
            return List.of(new GeoPoint(this, ray.getPoint(radius)));

        Vector u = center.subtract(ray.getP0());
        double tm = ray.getDir().dotProduct(u);
        double dSquared = u.lengthSquared() - (tm * tm);
        double thSquared = alignZero(radiusSquared - dSquared);
        if (thSquared <= 0) return null;
        double th = Math.sqrt(thSquared);

        double t1 = tm + th;
        if (alignZero(t1) <= 0) return null;

        double t2 = tm - th;
        return alignZero(t2) > 0 ?
                List.of(new GeoPoint(this, ray.getPoint(t1)),new GeoPoint(this, ray.getPoint(t2)))
                : List.of(new GeoPoint(this,ray.getPoint(t1)));
    }
}
