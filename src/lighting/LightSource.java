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
    Color getIntensity(Point p);

    /**
     * Gets the direction of the light from a given point.
     *
     * @param p the point from which to calculate the direction
     * @return the direction of the light from the point
     */
    Vector getL(Point p);

    /**
     * Gets the distance between the light source and a given point.
     *
     * @param point the point from which to calculate the distance
     * @return the distance between the light source and the point
     */
    double getDistance(Point point);
}

