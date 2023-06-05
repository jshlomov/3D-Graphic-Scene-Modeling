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
 * Unit tests for geometries.Triangle class
 */
public class TriangleTests {
    /**
     * Test method for {@link Triangle#getNormal(Point)}.
     */
    @Test
    void getNormal() {
        Triangle testT = new Triangle(
                new Point(1, 0, 0),
                new Point(0, 1, 0),
                new Point(0, 0, 1)
        );
        double sq = Math.sqrt(1 / 3d);

        // ============ Equivalence Partitions Tests ==============

        //?? ask if its necessary
        // TC01: Test that result of getNormal is proper
        assertEquals(new Vector(sq, sq, sq), testT.getNormal(new Point(1, 0, 0)), "Bad normal to Triangle");

        // TC02: Two opposite sides of the vector
        assertTrue(new Vector(sq, sq, sq).equals(testT.getNormal(new Point(0, 0, 1)))
                || new Vector(-1 * sq, -1 * sq, -1 * sq).equals(testT.getNormal(new Point(0, 0, 1))), "Bad normal to plane");
    }

    /**
     * Test method for {@link Triangle#findIntersections(Ray)}.
     */
    @Test
    public void findIntersections() {

        Triangle tr = new Triangle(
                new Point(1, 0, 0),
                new Point(0, 1, 0),
                new Point(0, 0, 1)
        );

        // ============ Equivalence Partitions Tests ==============

        // TC01: Inside triangle
        List<Point> result = tr.findIntersections(new Ray(new Point(-1, -2, -1), new Vector(1, 2, 1)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(new Point(0.25, 0.5, 0.25), result.get(0), "Ray crosses triangle once");

        // TC02: Outside against edge
        assertNull(tr.findIntersections(new Ray(new Point(-1, -2, -1), new Vector(1, 3, 3)))
                , "Ray's crosses outside the triangle");

        // TC03: Outside against vertex
        assertNull(tr.findIntersections(new Ray(new Point(-1, -2, -1), new Vector(1, 2, 4)))
                , "Ray's crosses outside the triangle");


        // =============== Boundary Values Tests ==================

        // TC10: In vertex
        assertNull(tr.findIntersections(new Ray(new Point(-1, -2, -1), new Vector(1, 2, 2)))
                , "Ray's crosses the triangle's vertices");

        // TC11: On edge
        assertNull(tr.findIntersections(new Ray(new Point(-1, -2, -1), new Vector(1.5, 2, 1.5)))
                , "Ray's crosses the triangle's edge");

        // TC12: On edge's continuation
        assertNull(tr.findIntersections(new Ray(new Point(-1, -2, -1), new Vector(0, 2, 3)))
                , "Ray's crosses the triangle's edge");
    }

    /**
     * Test method for {@link Triangle#findGeoIntersectionsHelper(Ray, double)}.
     */
    @Test
    public void findGeoIntersectionsHelper() {

        // checking points inside/before the distance

        Triangle tTriangle = new Triangle(
                new Point(2,2,1),
                new Point(-2,2,1),
                new Point(2,-2,1)
        );
        Ray tRay = new Ray(new Point(0,1,-1), new Vector(0,0,1));
        Ray tRay2 = new Ray(new Point(0,10,-1), new Vector(0,0,1));

        // TC01: inside distance
        assertEquals(1, tTriangle.findGeoIntersectionsHelper(tRay, 3).size(), "wrong");

        // TC02: outside distance
        assertNull(tTriangle.findGeoIntersectionsHelper(tRay, 1.5));

        // TC03: intersect the plane but not the triangle
        assertNull(tTriangle.findGeoIntersectionsHelper(tRay2, 3), "wrong");

        // TC04: outside distance
        assertNull(tTriangle.findGeoIntersectionsHelper(tRay2, 1.5));
    }
}

