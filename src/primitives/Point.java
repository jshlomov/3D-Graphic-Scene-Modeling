package primitives;

import java.util.Objects;

public class Point {
    final Double3 xyz;

    public Point(double d1, double d2, double d3) {
        xyz = new Double3(d1, d2, d3);
    }

    public Point(Double3 xyz) {
        this.xyz = xyz;
    }

    public Point add(Vector vector) {
        return new Point(this.xyz.add(vector.xyz));
    }

    public Vector substract(Point point) {
        return new Vector(point.xyz.subtract(this.xyz));
    }

    public double distanceSquared(Point p) {
        return (p.xyz.d1-this.xyz.d1) * (p.xyz.d1-this.xyz.d1) +
                (p.xyz.d2-this.xyz.d2) * (p.xyz.d2-this.xyz.d2) +
                (p.xyz.d3-this.xyz.d3) * (p.xyz.d3-this.xyz.d3);
    }

    public double distance(Point p) {
        return Math.sqrt(distanceSquared(p));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point point)) return false;
        return xyz.equals(point.xyz);
    }

    @Override
    public String toString() {
        return "Point:" + xyz.toString();
    }
}
