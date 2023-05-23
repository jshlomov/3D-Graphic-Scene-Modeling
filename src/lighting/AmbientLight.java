package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * The AmbientLight class represents ambient lighting in a scene.
 */
public class AmbientLight {

    /**
     * The intensity of the ambient light.
     */
    private Color intensity;

    /**
     * A constant representing no ambient light.
     */
    public final static AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO);

    /**
     * Constructs an AmbientLight object with the specified intensity and scaling factor.
     *
     * @param ia The intensity of the ambient light.
     * @param ka The scaling factor for the intensity.
     */
    public AmbientLight(Color ia, Double3 ka) {
        intensity = ia.scale(ka);
    }

    /**
     * Constructs an AmbientLight object with the specified intensity and scaling factor.
     *
     * @param ia The intensity of the ambient light.
     * @param ka The scaling factor for the intensity.
     */
    @SuppressWarnings("unused")
    public AmbientLight(Color ia, double ka) {
        intensity = ia.scale(ka);
    }

    /**
     * Returns the intensity of the ambient light.
     *
     * @return The intensity of the ambient light.
     */
    public Color getIntensity() {
        return intensity;
    }

}
