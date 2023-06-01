package geometries;

/**
 * An abstract class representing a radial geometry object in three-dimensional space.
 * <p>
 * Radial geometry objects are defined by a radius value.
 * <p>
 * It implements the Geometry interface.
 */
public abstract class RadialGeometry extends Geometry {

    /**
     * The radius of the radial geometry object
     */
    protected final double radius;
    /**
     * The squared radius of the radial geometry object
     */
    protected final double radiusSquared;

    /**
     * Constructs a new RadialGeometry object with the given radius.
     *
     * @param radius the radius of the radial geometry object
     */
    public RadialGeometry(double radius) {
        this.radius = radius;
        radiusSquared = radius * radius;
    }
}