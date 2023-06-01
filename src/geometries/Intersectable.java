package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

/**
 * The Intersectable interface represents an intersectable geometric object.
 * It defines methods for finding intersections of a ray with the object.
 */
public abstract class Intersectable {

    /**
     * Finds intersections of a ray with the geometric object and returns them as a list of points.
     *
     * @param ray the ray to intersect with the object
     * @return a list of intersections in the geometric object
     */
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
    }

    /**
     * Finds intersections of a ray with the geometric object and returns them as a list of GeoPoints.
     *
     * @param ray the ray to intersect with the object
     * @return a list of GeoPoints representing the intersections in the geometric object
     */
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersectionsHelper(ray);
    }

    /**
     * Helper method to find the geometric intersections of a ray with the object.
     * This method should be implemented by subclasses to provide the specific intersection logic.
     *
     * @param ray the ray to intersect with the object
     * @return a list of GeoPoints representing the intersections in the geometric object
     */
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);

    /**
     * The GeoPoint class represents a geometric intersection point.
     * It contains the geometry object and the point of intersection.
     */
    public static class GeoPoint {

        /**
         * present the geometry its intersected
         */
        public Geometry geometry;

        /**
         * the closest intersection
         */
        public Point point;

        /**
         * Constructs a GeoPoint with the given geometry and point of intersection.
         *
         * @param geometry the geometry object
         * @param point    the point of intersection
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            return o instanceof GeoPoint geoPoint &&
                    geometry.equals(geoPoint.geometry) && point.equals(geoPoint.point);
        }

        @Override
        public String toString() {
            return "" + geometry.toString() + " " + point.toString();
        }
    }
}
