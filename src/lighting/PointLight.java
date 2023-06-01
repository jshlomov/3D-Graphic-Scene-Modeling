package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * The PointLight class represents a point light source in a 3D scene.
 * It extends the Light class and implements the LightSource interface.
 */
public class PointLight extends Light implements LightSource {

    private Point position;
    private double kc = 1, kl = 0, kq = 0;

    /**
     * Constructs a PointLight object with the given intensity and position.
     *
     * @param intensity the intensity of the point light
     * @param position  the position of the point light
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }

    /**
     * Sets the constant attenuation factor of the point light.
     *
     * @param kc the constant attenuation factor to set
     * @return the point light itself
     */
    public PointLight setKc(double kc) {
        this.kc = kc;
        return this;
    }

    /**
     * Sets the linear attenuation factor of the point light.
     *
     * @param kl the linear attenuation factor to set
     * @return the point light itself
     */
    public PointLight setKl(double kl) {
        this.kl = kl;
        return this;
    }

    /**
     * Sets the quadratic attenuation factor of the point light.
     *
     * @param kq the quadratic attenuation factor to set
     * @return the point light itself
     */
    public PointLight setKq(double kq) {
        this.kq = kq;
        return this;
    }

    @Override
    public Color getIntensity(Point p) {
        double d = position.distance(p);
        return intensity.reduce(kc + kl * d + kq * d * d);
    }

    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }

    @Override
    public String toString() {
        return super.toString() +
                "PointLight{" +
                "position=" + position +
                ", kc=" + kc +
                ", kl=" + kl +
                ", kq=" + kq +
                '}';
    }
}
