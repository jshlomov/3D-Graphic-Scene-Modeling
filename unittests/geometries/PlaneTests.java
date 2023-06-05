/**
 * @author: Yonatan Shlomov 319217162
 * Itzik Nisan 312517261
 * Creating unit tests according to TDD principle
 */
package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * unit tests for geometries.Plane class
 */
class PlaneTests {

    /**
     * Test method for ctor who get 3 point.
     */
    @Test
    public void Plane() {

        // =============== Boundary Values Tests ==================

        // TC11: Test for 2 similar points
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(new Point(1, 1, 1), new Point(1, 1, 1), new Point(0, 0, 0))
                , "Constructor with same points - error");

        // TC12: Check for 3 points on one straight line
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(new Point(1, 1, 1), new Point(2, 2, 2), new Point(3, 3, 3))
                , "Constructor with points on one straight line - error");
    }

    /**
     * Test method for {@link Plane#getNormal(Point)}.
     */
    @Test
    void GetNormal() {
        Plane testPlane = new Plane(
                new Point(1, 0, 0),
                new Point(0, 1, 0),
                new Point(0, 0, 1)
        );
        double sq = Math.sqrt(1 / 3d);

        // ============ Equivalence Partitions Tests ==============

        //?? ask if its necessary
        // TC01: Test that result of getNormal is proper
        assertEquals(new Vector(sq, sq, sq), testPlane.getNormal(new Point(0, 0, 1)), "Bad normal to plane");

        // TC02: Two opposite sides of the vector
        assertTrue(new Vector(sq, sq, sq).equals(testPlane.getNormal(new Point(0, 0, 1)))
                        || new Vector(-1 * sq, -1 * sq, -1 * sq).equals(testPlane.getNormal(new Point(0, 0, 1)))
                , "Bad normal to plane");
    }

    /**
     * Test method for {@link Plane#findIntersections(Ray)}.
     */
    @Test
    public void FindIntersections() {

        Plane plane = new Plane(
                new Point(0, 2, 2),
                new Point(2, 0, 2),
                new Point(2, 2, 0)
        );

        Point p1 = new Point(1, 1, 0);

        // ============ Equivalence Partitions Tests ==============

        // **** Group:The Ray must be neither orthogonal nor parallel to the plane

        // TC01: Ray intersects the plane(1 point)
        List<Point> result = plane.findIntersections(new Ray(p1, new Vector(0, 0, 1)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(List.of(new Point(1, 1, 2)), result, "Ray crosses plane once");

        // TC02: Ray does not intersect the plane(0 point)
        assertNull(plane.findIntersections(new Ray(p1, new Vector(0, 0, -1)))
                , "Ray's crosses the plane");

        // =============== Boundary Values Tests ==================

        // **** Group: Ray is parallel to the plane

        // TC10: The ray is not included in the plane
        assertNull(plane.findIntersections(new Ray(p1, new Vector(-0.5, -0.5, 1)))
                , "Ray's crosses the plane");

        // TC11: The ray included in the plane
        assertNull(plane.findIntersections(new Ray(new Point(0, 4, 0), new Vector(-0.5, -0.5, 1)))
                , "Ray's crosses the plane");

        // **** Group: Ray is orthogonal to the plane

        // TC12: The ray starts is before the plane
        result = plane.findIntersections(new Ray(p1, new Vector(0.5, 0.5, 1)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(new Point(1.5, 1.5, 1), result.get(0), "Ray crosses plane once");

        // TC13: The ray starts is in the plane
        assertNull(plane.findIntersections(new Ray(new Point(1.5, 1.5, 1), new Vector(0.5, 0.5, 1)))
                , "Ray's crosses the plane");

        // TC14: The ray starts is after the plane
        assertNull(plane.findIntersections(new Ray(new Point(2, 2, 2), new Vector(0.5, 0.5, 1)))
                , "Ray's crosses the plane");

        // ***********

        // TC15: Ray is neither orthogonal nor parallel to and begins at the plane
        assertNull(plane.findIntersections(new Ray(new Point(1.5, 1.5, 1), new Vector(0, 0, 1)))
                , "Ray's crosses the plane");

        // TC16: Ray is neither orthogonal nor parallel to the plane and begins in the
        // same point
        assertNull(plane.findIntersections(new Ray(new Point(0, 2, 2), new Vector(0, 0, 1)))
                , "Ray's crosses the plane");
    }

    /**
     * Test method for {@link Plane#findGeoIntersectionsHelper(Ray, double)}.
     */
    @Test
    public void findGeoIntersectionsHelper() {

        // checking points inside/before the distance

        Plane tPlane = new Plane(
                new Point(1,1,1),
                new Point(0,0,1),
                new Point(1,0,1)
        );
        Ray tRay = new Ray(new Point(0,1,-1), new Vector(0,0,1));

        // TC01: inside distance
        assertEquals(1, tPlane.findGeoIntersectionsHelper(tRay, 2).size(), "wrong");

        // TC02: outside distance
        assertNull(tPlane.findGeoIntersectionsHelper(tRay, 1));
    }
}
