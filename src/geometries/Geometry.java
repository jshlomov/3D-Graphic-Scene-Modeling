package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;

/**
 * The Geometry interface represents a geometric shape in 3D space and provides methods for working with that shape.
 */
public abstract class Geometry extends Intersectable {

    /**
     * the color of the geometry
     */
    protected Color emission = Color.BLACK;

    private Material material = new Material();

    /**
     * Returns the emission color of the geometry.
     *
     * @return The emission color.
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * Returns the material of the geometry.
     *
     * @return The material.
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Sets the emission color of the geometry.
     *
     * @param emission The emission color to set.
     * @return The geometry itself.
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    /**
     * Sets the material of the geometry.
     *
     * @param material The material to set.
     * @return The geometry itself.
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }

    /**
     * Returns the normal vector to the geometry shape at the specified point on the shape's surface.
     *
     * @param point The point on the surface of the shape for which to retrieve the normal vector.
     * @return A normalized Vector representing the normal to the shape at the given point.
     */
    public abstract Vector getNormal(Point point);
}
