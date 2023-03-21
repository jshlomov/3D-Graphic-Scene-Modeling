package primitives;

/**
 * Represents a three-dimensional vector in Cartesian coordinate system.
 * A vector is defined by its x, y, and z components, which correspond to the
 * magnitudes of the vector along the x, y, and z axes, respectively.
 */
public class Vector extends Point{

    /**
     * Creates a new vector with the given coordinates.
     * @param d1 The x coordinate.
     * @param d2 The y coordinate.
     * @param d3 The z coordinate.
     * @throws IllegalArgumentException if the vector is equal to the zero vector.
     */
    public Vector(double d1, double d2, double d3) {
        super(d1, d2, d3);
        if (xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("The vector is equal to zero");
    }

    /**
     * Creates a new vector with the given coordinates.
     * @param xyz A {@link Double3} object containing the x, y, and z coordinates.
     * @throws IllegalArgumentException if the vector is equal to the zero vector.
     */
    public Vector(Double3 xyz) {
        super(xyz);
        if (xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("The vector is equal to zero");
    }
    /**
     * Returns a new vector that is the sum of this vector and the specified vector.
     *
     * @param vector the vector to add
     * @return a new vector that is the sum of this vector and the specified vector
     */
    public Vector add(Vector vector){
        return new Vector(xyz.add(vector.xyz));
    }
    /**
     * Returns a new vector that is the scalar product of this vector and the specified value.
     *
     * @param d the value to scale the vector by
     * @return a new vector that is the scalar product of this vector and the specified value
     */
    public Vector scale(double d){
        return new Vector(xyz.scale(d));
    }
    /**
     * Returns the dot product of this vector and the specified vector.
     *
     * @param vector the vector to compute the dot product with
     * @return the dot product of this vector and the specified vector
     */
    public double dotProduct(Vector vector){
        return ((vector.xyz.d1*this.xyz.d1)+(vector.xyz.d2*this.xyz.d2)+(vector.xyz.d3*this.xyz.d3));
    }
    /**
     * Returns the cross product of this vector and the specified vector.
     *
     * @param vector the vector to compute the cross product with
     * @return the cross product of this vector and the specified vector
     */
    public Vector crossProduct(Vector vector){
        return new Vector((xyz.d2 * vector.xyz.d3)-(xyz.d3 * vector.xyz.d2),
                (xyz.d3 * vector.xyz.d1)-(xyz.d1 * vector.xyz.d3),
                (xyz.d1 * vector.xyz.d2)-(xyz.d2 * vector.xyz.d1));
    }
    /**
     * Returns the squared length of this vector.
     *
     * @return the squared length of this vector
     */
    public double lengthSquared(){
        return ((xyz.d1* xyz.d1)+(xyz.d2* xyz.d2)+(xyz.d3*xyz.d3));
    }
    /**
     * Returns the length of this vector.
     *
     * @return the length of this vector
     */
    public double length(){
        return (Math.sqrt(lengthSquared()));
    }
    /**
     * Returns a new vector that is the normalization of this vector.
     *
     * @return a new vector that is the normalization of this vector
     */
    public Vector normalize(){
        return new Vector(xyz.reduce(length()));
    }

    @Override
    public String toString() {
        return "Vector{" + super.toString()
                ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector vector))
            return false;
        return xyz.equals(vector.xyz);
    }
}
