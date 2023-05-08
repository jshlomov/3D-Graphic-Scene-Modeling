package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class Geometries implements Intersectable {

    private List<Intersectable> items;

    public Geometries() {
        items = new LinkedList<>();
    }

    public Geometries(Intersectable... geometries) {
        items = List.of(geometries);
    }

    public void add(Intersectable... geometries) {
        items.addAll(List.of(geometries));
    }

    @Override
    public List<Point> findIntersections(Ray ray) {

        List<Point> intersections = new LinkedList<>();
        for (var geometry : items) {
            // declare list as null
            List<Point> geoIntersections = geometry.findIntersections(ray) ;

            if (geoIntersections != null) {
                intersections.addAll(geoIntersections);
            }
        }
        if (intersections.size() > 0) {
            return intersections;
        }
        return null;
    }
}
