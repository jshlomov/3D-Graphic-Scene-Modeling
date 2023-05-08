package primitives;

/**
 * The Ray class represents a ray in 3D space, defined by a starting point and a direction vector.
 */
public class Ray {
    /**
     * The starting point of the ray.
     */
    private final Point p0;
    /**
     * The direction vector of the ray.
     */
    private final Vector dir;

    /**
     * Constructs a new Ray object with the specified starting point and direction vector.
     *
     * @param p the starting point of the ray
     * @param v the direction vector of the ray
     */
    public Ray(Point p, Vector v) {
        p0 = p;
        dir = v.normalize();
    }

    /**
     * Returns the starting point of the ray.
     *
     * @return the starting point of the ray
     */
    @SuppressWarnings("unused")
    public Point getP0() {
        return p0;
    }

    /**
     * Returns the normalized direction vector of the ray.
     *
     * @return the normalized direction vector of the ray
     */
    @SuppressWarnings("unused")
    public Vector getDir() {
        return dir;
    }

    /**
     * return - cross point with the geometry body by getting the length
     * from the start of the ray
     * @param t length from the start of the ray
     * @return point
     *
     */
    public Point getPoint(double t) {
        return getP0().add(getDir().scale(t));
    }


    @Override
    public String toString() {
        return "Point: " + p0 + "\n" +
                "Vector: " + dir;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Ray ray)
            return p0.equals(ray.p0) && dir.equals(ray.dir);
        return false;
    }
}
