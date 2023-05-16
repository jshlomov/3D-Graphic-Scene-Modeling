package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * The Geometry interface represents a geometric shape in 3D space and provides methods for working with that shape.
 */
public interface Geometry extends Intersectable {

    /**
     * Returns the normal vector to this Geometry shape at the specified point on the shape's surface.
     *
     * @param point The point on the surface of the shape for which to retrieve the normal vector.
     * @return A normalized Vector representing the normal to the shape at the given point.
     */
    Vector getNormal(Point point);
}