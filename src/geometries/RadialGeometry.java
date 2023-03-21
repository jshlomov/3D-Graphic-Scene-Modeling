package geometries;

import primitives.Point;
import primitives.Vector;

/**

 An abstract class representing a radial geometry object in three-dimensional space.

 Radial geometry objects are defined by a radius value.

 It implements the Geometry interface.
 */
public abstract class RadialGeometry implements Geometry {

    /** The radius of the radial geometry object */
    protected double radius;

    /**

     Constructs a new RadialGeometry object with the given radius.
     @param radius the radius of the radial geometry object
     */
    public RadialGeometry(double radius) {
        this.radius = radius;
    }

    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}