package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class GeometriesTest {

    @Test
    void findIntersections() {

        Sphere tSphere = new Sphere(new Point(1, 0, 0), 1);
        Plane tPlane = new Plane(new Point(0, 2, 2), new Point(2, 0, 2), new Point(2, 2, 0));
        Triangle tTriangle = new Triangle(new Point(2, 0, 0), new Point(0, 2, 0), new Point(0, 0, 2));
        Geometries collection = new Geometries(tTriangle, tPlane, tSphere);

        // ============ Equivalence Partitions Tests ==============

        // TC01: Some Geometries are intersected (Intersects only plane and Triangle)
        Ray ray = new Ray(new Point(0, 1, 0), new Vector(1, 1, 0.5));
        assertEquals(2, collection.findIntersections(ray).size()
                , "Wrong number of intersection points");

        // =============== Boundary Values Tests ==================

        // TC11: All the Geometries are intersected
        ray = new Ray(new Point(0, -0.5, 0), new Vector(1, 1, 0.5));
        assertEquals(4, collection.findIntersections(ray).size()
                , "Wrong number of intersection points");

        // TC12: No Geometries are intersected
        ray = new Ray(new Point(0, 1, 0), new Vector(-1, 0, 0));
        assertNull(collection.findIntersections(ray), "No intersection points");

        // TC13: Only one Geometry shape is intersected (Intersects only plane)
        ray = new Ray(new Point(0, 3, 0), new Vector(0, 0, 1));
        assertEquals(1, collection.findIntersections(ray).size()
                , "Wrong number of intersection points");

        // TC14: Empty Geometries collection
        collection = new Geometries();
        assertNull(collection.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(1, 1, 0)))
                , "No geometry shapes in the collection");
    }

}