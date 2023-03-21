package primitives;

public class Vector extends Point{

    public Vector(double d1, double d2, double d3) {
        super(d1, d2, d3);
        if (xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("The vector is equal to zero");
    }

    public Vector(Double3 xyz) {
        super(xyz);
        if (xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("The vector is equal to zero");
    }
    public Vector add(Vector vector){
        return new Vector(xyz.add(vector.xyz));
    }
    public Vector scale(double d){
        return new Vector(xyz.scale(d));
    }
    public double dotProduct(Vector vector){
        return ((vector.xyz.d1*this.xyz.d1)+(vector.xyz.d2*this.xyz.d2)+(vector.xyz.d3*this.xyz.d3));
    }
    public Vector crossProduct(Vector vector){
        return new Vector((xyz.d2 * vector.xyz.d3)-(xyz.d3 * vector.xyz.d2),
                (xyz.d3 * vector.xyz.d1)-(xyz.d1 * vector.xyz.d3),
                (xyz.d1 * vector.xyz.d2)-(xyz.d2 * vector.xyz.d1));
    }
    public double lengthSquared(){
        return ((xyz.d1* xyz.d1)+(xyz.d2* xyz.d2)+(xyz.d3*xyz.d3));
    }
    public double length(){
        return (Math.sqrt(lengthSquared()));
    }
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
