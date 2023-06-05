package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Unit tests for geometries.Geometries class
 */
class GeometriesTest {

    /**
     * Test method for {@link Cylinder#findIntersections(Ray)}.
     */
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

    /**
     * Test method for {@link Geometries#findGeoIntersectionsHelper(Ray, double)}.
     */
    @Test
    public void findGeoIntersectionsHelper() {
        // checking points inside/before the distance
        Triangle tTriangle1 = new Triangle(
                new Point(2,2,1),
                new Point(-2,2,1),
                new Point(2,-2,1)
        );

        Triangle tTriangle3 = new Triangle(
                new Point(2,2,10),
                new Point(-2,2,10),
                new Point(2,-2,10)
        );

        Triangle tTriangle2 = new Triangle(
                new Point(2,2,5),
                new Point(-2,2,5),
                new Point(2,-2,5)
        );
        Geometries geometries = new Geometries(tTriangle1, tTriangle2, tTriangle3);

        Ray tRay = new Ray(new Point(0,1,-1), new Vector(0,0,1));

        // TC01: 2 intersections inside distance
        assertEquals(1, geometries.findGeoIntersectionsHelper(tRay, 5).size(), "wrong");

        // TC02: outside distance
        assertNull(geometries.findGeoIntersectionsHelper(tRay, 1));
    }
}