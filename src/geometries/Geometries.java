package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

/**
 * The Geometries class represents a collection of intersectable geometries.
 */
public class Geometries extends Intersectable {

    private final List<Intersectable> items = new LinkedList<>();

    /**
     * Constructs an empty Geometries object.
     */
    public Geometries() {
    }

    /**
     * Constructs a Geometries object and adds the specified geometries to it.
     *
     * @param geometries the geometries to add to the collection
     */
    public Geometries(Intersectable... geometries) {
        add(geometries);
    }

    /**
     * Adds the specified geometries to the collection.
     *
     * @param geometries the geometries to add
     */
    public void add(Intersectable... geometries) {
        items.addAll(List.of(geometries));
    }

    /**
     * Finds the intersections between the ray and the geometries in the collection.
     *
     * @param ray the ray to intersect with the geometries
     * @return a list of intersection points, or null if no intersections exist
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        List<GeoPoint> intersections = null;
        for (var geometry : items) {
            List<GeoPoint> geoIntersections = geometry.findGeoIntersectionsHelper(ray);
            if (geoIntersections != null) {
                if (intersections == null)
                    intersections = new LinkedList<>();
                intersections.addAll(geoIntersections);
            }
        }
        return intersections;
    }
}