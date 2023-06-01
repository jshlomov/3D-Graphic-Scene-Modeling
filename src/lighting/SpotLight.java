package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import static java.lang.Math.pow;
import static primitives.Util.alignZero;

/**
 * The SpotLight class represents a spotlight source in a 3D scene.
 * It extends the PointLight class.
 */
public class SpotLight extends PointLight {

    private final Vector direction;
    private double beam = 1;

    /**
     * Constructs a SpotLight object with the given intensity, position, and direction.
     *
     * @param intensity  the intensity of the spotlight
     * @param position   the position of the spotlight
     * @param direction  the direction of the spotlight
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }

    /**
     * Sets the narrow beam angle of the spotlight source.
     *
     * @param beam the narrow beam angle to set, specified in degrees
     * @return the spotlight itself
     */
    public SpotLight setNarrowBeam(double beam) {
        this.beam = beam;
        return this;
    }


    @Override
    public Color getIntensity(Point p) {
        double cosAng = alignZero(direction.dotProduct(getL(p)));
        return cosAng <= 0 ? Color.BLACK : super.getIntensity(p).scale(pow(cosAng, beam));
    }

    @Override
    public Vector getL(Point p) {
        return super.getL(p);
    }

    @Override
    public String toString() {
        return super.toString() +
                "SpotLight{" +
                "direction=" + direction +
                '}';
    }
}
