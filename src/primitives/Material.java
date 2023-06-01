package primitives;

/**
 * The Material class represents the material properties of an object in a 3D scene.
 * It contains information about the diffuse reflection coefficient (kd), specular reflection coefficient (ks),
 * and shininess (nShininess) of the material.
 */
public class Material {
    /**
     * The diffuse reflection coefficient (kd) of the material.
     */
    public Double3 kd = Double3.ZERO;

    /**
     * The specular reflection coefficient (ks) of the material.
     */
    public Double3 ks = Double3.ZERO;

    /**
     * The shininess (nShininess) of the material.
     */
    public int nShininess = 0;

    /**
     * Sets the diffuse reflection coefficient (kd) of the material.
     *
     * @param kd the diffuse reflection coefficient to set
     * @return the material itself
     */
    public Material setKd(Double3 kd) {
        this.kd = kd;
        return this;
    }

    /**
     * Sets the diffuse reflection coefficient (kd) of the material with a single value.
     *
     * @param kd the diffuse reflection coefficient to set
     * @return the material itself
     */
    public Material setKd(double kd) {
        this.kd = new Double3(kd);
        return this;
    }

    /**
     * Sets the specular reflection coefficient (ks) of the material.
     *
     * @param ks the specular reflection coefficient to set
     * @return the material itself
     */
    public Material setKs(Double3 ks) {
        this.ks = ks;
        return this;
    }

    /**
     * Sets the specular reflection coefficient (ks) of the material with a single value.
     *
     * @param ks the specular reflection coefficient to set
     * @return the material itself
     */
    public Material setKs(double ks) {
        this.ks = new Double3(ks);
        return this;
    }

    /**
     * Sets the shininess (nShininess) of the material.
     *
     * @param nShininess the shininess to set
     * @return the material itself
     */
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

    @Override
    public String toString() {
        return "kd= " + kd + " ks = " + ks + " shininess = " + nShininess;
    }
}
