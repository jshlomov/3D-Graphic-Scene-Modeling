package primitives;

import java.util.Objects;

/**

 The Ray class represents a ray in 3D space, defined by a starting point and a direction vector.
 */
 public class Ray {
 /**

 The starting point of the ray.
 */
final Point p0;
/**

 The direction vector of the ray.
 */
final Vector dir;
    /**
     * Constructs a new Ray object with the specified starting point and direction vector.
     * @param p the starting point of the ray
     * @param v the direction vector of the ray
     */
    public Ray(Point p, Vector v) {
        p0 = p;
        dir = v.normalize();
    }

    /**
     * Returns the starting point of the ray.
     * @return the starting point of the ray
     */
    public Point getP0() {
        return p0;
    }

    /**
     * Returns the normalized direction vector of the ray.
     * @return the normalized direction vector of the ray
     */
    public Vector getDir() {
        return dir;
    }

    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ray ray)) return false;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }
}
