package lighting;

import primitives.Color;

/**
 * The Light class represents a light source in a 3D scene.
 * It provides functionality for getting the intensity of the light.
 */
abstract class Light {

    /**
     * the intensity of the light
     */
    protected final Color intensity;

    /**
     * Constructs a Light object with the given intensity.
     *
     * @param intensity the intensity of the light
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * Gets the intensity of the light.
     *
     * @return the intensity of the light
     */
    public Color getIntensity() {
        return intensity;
    }

    @Override
    public String toString() {
        return "Light{" +
                "intensity=" + intensity +
                '}';
    }
}
