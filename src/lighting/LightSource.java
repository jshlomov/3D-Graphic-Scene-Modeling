package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * The LightSource interface represents a light source in a 3D scene.
 * It defines methods for getting the intensity of the light at a given point
 * and the direction of the light from a given point.
 */
public interface LightSource {

    /**
     * Gets the intensity of the light at a given point.
     *
     * @param p the point at which to calculate the intensity
     * @return the intensity of the light at the point
     */
    public Color getIntensity(Point p);

    /**
     * Gets the direction of the light from a given point.
     *
     * @param p the point from which to calculate the direction
     * @return the direction of the light from the point
     */
    public Vector getL(Point p);
}

