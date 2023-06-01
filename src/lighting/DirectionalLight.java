package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * The DirectionalLight class represents a directional light source in a 3D scene.
 * It inherits from the Light class and implements the LightSource interface.
 * It provides functionality for getting the intensity and direction of the light.
 */
public class DirectionalLight extends Light implements LightSource {

    private Vector direction;

    /**
     * Constructs a DirectionalLight object with the given intensity and direction.
     *
     * @param intensity the intensity of the light
     * @param direction the direction of the light
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction.normalize();
    }

    @Override
    public Color getIntensity(Point p) {
        return intensity;
    }

    @Override
    public Vector getL(Point p) {
        return direction;
    }

    @Override
    public String toString() {
        return super.toString() +
                "DirectionalLight{" +
                "direction=" + direction +
                '}';
    }
}
