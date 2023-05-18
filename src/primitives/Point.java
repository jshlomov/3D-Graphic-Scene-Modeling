package primitives;

/**
 * Represents a point in three-dimensional space.
 */

public class Point {
    /**
     * The coordinates of the point as a {@code Double3} object.
     */
    protected final Double3 xyz;

    /**
     * Constructs a new point with the given coordinates.
     *
     * @param d1 the x-coordinate of the point
     * @param d2 the y-coordinate of the point
     * @param d3 the z-coordinate of the point
     */
    public Point(double d1, double d2, double d3) {
        xyz = new Double3(d1, d2, d3);
    }

    /**
     * Constructs a new point with the given coordinates.
     *
     * @param xyz the coordinates of the point as a {@code Double3} object
     */
    Point(Double3 xyz) {
        this.xyz = xyz;
    }

    /**
     * Returns the x-coordinate of the point
     * @return the x-coordinate of the point
     */
    public double getX() {
        return xyz.d1;
    }

    /**
     * Returns the y-coordinate of the point
     * @return the y-coordinate of the point
     */
    public double getY() {
        return xyz.d2;
    }

    /**
     * Returns the z-coordinate of the point
     * @return the z-coordinate of the point
     */
    @SuppressWarnings("unused")
    public double getZ() {
        return xyz.d3;
    }


    /**
     * Returns a new point that is the result of adding the given vector to this point.
     *
     * @param vector the vector to add
     * @return a new point that is the result of adding the given vector to this point
     */
    public Point add(Vector vector) {
        return new Point(this.xyz.add(vector.xyz));
    }

    /**
     * Returns a new vector that is the result of subtracting the given point from this point.
     *
     * @param point the point to subtract
     * @return a new vector that is the result of subtracting the given point from this point
     */
    public Vector subtract(Point point) {
        return new Vector(this.xyz.subtract(point.xyz));
    }

    /**
     * Calculates and returns the squared Euclidean distance between this point and the given point.
     *
     * @param p the point to calculate the distance to
     * @return the squared Euclidean distance between this point and the given point
     */
    public double distanceSquared(Point p) {
        return (p.xyz.d1 - this.xyz.d1) * (p.xyz.d1 - this.xyz.d1) +
                (p.xyz.d2 - this.xyz.d2) * (p.xyz.d2 - this.xyz.d2) +
                (p.xyz.d3 - this.xyz.d3) * (p.xyz.d3 - this.xyz.d3);
    }

    /**
     * Calculates and returns the Euclidean distance between this point and the given point.
     *
     * @param p the point to calculate the distance to
     * @return the Euclidean distance between this point and the given point
     */
    public double distance(Point p) {
        return Math.sqrt(distanceSquared(p));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return (o instanceof Point point) && xyz.equals(point.xyz);
    }

    @Override
    public String toString() {
        return "" + xyz;
    }
}
