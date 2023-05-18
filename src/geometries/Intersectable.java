package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

/**
 * interface Intersectable
 */
public interface Intersectable {

    /**
     * Finds intersections of a ray with geometric object and returns them as list of points
     *
     * @param ray the ray to intersect with the shape
     * @return list of intersections in geometric object
     */
    List<Point> findIntersections(Ray ray);
}
