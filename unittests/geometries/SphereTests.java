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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Unit tests for geometries.Sphere class
 */
class SphereTests {
    /**
     * Test method for {@link Sphere#getNormal(Point)}./
     */
    @Test
    void testGetNormal() {

        // ============ Equivalence Partitions Tests ==============
        Sphere sp = new Sphere(new Point(0, 0, 0), 1d);
        double sq = Math.sqrt(1 / 3d);

        // TC01: Test that result of getNormal is proper.
        assertEquals(new Vector(sq, sq, sq), sp.getNormal(new Point(sq, sq, sq)), "Bad normal to Sphere");
    }

    /**
     * Test method for {@link Sphere#findIntersections(Ray)}.
     */
    @Test
    void findIntersections() {
        Sphere testSphere = new Sphere(new Point(1, 0, 0), 1);
        Point p1 = new Point(0.0651530771650466, 0.355051025721682, 0);
        Point p2 = new Point(1.53484692283495, 0.844948974278318, 0);
        Vector v = new Vector(3, 1, 0);
        List<Point> result;

        // ============ Equivalence Partitions Tests ==============

        //TC01: Ray's line is outside the sphere (0 points)
        assertNull(testSphere.findIntersections(new Ray(new Point(0, 1, 0), new Vector(0, -1, 1))),
                "Ray's line out of sphere");

        // TC02: Ray starts before and crosses the sphere (2 points)
        result = testSphere.findIntersections(new Ray(new Point(-1, 0, 0), v));
        assertEquals(2, result.size(), "Wrong number of points");
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1, p2), result, "Ray crosses sphere");

        // TC03: Ray starts inside the sphere (1 point)
        result = testSphere.findIntersections(new Ray(new Point(0.5, 0.5, 0), v));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(p2, result.get(0), "Ray crosses sphere");

        // TC04: Ray starts after the sphere (0 points)
        assertNull(testSphere.findIntersections(new Ray(new Point(2, 1, 0), v)),
                "Ray's line out of sphere");

        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)

        // TC11: Ray starts at sphere and goes inside (1 point)
        result = testSphere.findIntersections(new Ray(p1, v));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(p2, result.get(0), "Ray crosses sphere");

        // TC12: Ray starts at sphere and goes outside (0 points)
        assertNull(testSphere.findIntersections(new Ray(p2, v)), "Ray's line out of sphere");

        // **** Group: Ray's line goes through the center

        Vector v1 = new Vector(0, -1, 0);

        // TC13: Ray starts before the sphere (2 points)
        result = testSphere.findIntersections(new Ray(new Point(1, 2, 0), v1));
        assertEquals(2, result.size(), "Wrong number of points");
        if (result.get(0).getY() < result.get(1).getY())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(new Point(1, 1, 0), new Point(1, -1, 0)), result, "Ray crosses sphere twice");

        // TC14: Ray starts at sphere and goes inside (1 point)
        result = testSphere.findIntersections(new Ray(new Point(1, 1, 0), v1));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(new Point(1, -1, 0), result.get(0), "Ray crosses sphere");

        // TC15: Ray starts inside (1 point)
        result = testSphere.findIntersections(new Ray(new Point(1, 0.5, 0), v1));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(new Point(1, -1, 0), result.get(0), "Ray crosses sphere");

        // TC16: Ray starts at the center (1 point)
        result = testSphere.findIntersections(new Ray(new Point(1, 0, 0), v1));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(new Point(1, -1, 0), result.get(0), "Ray crosses sphere");

        // TC17: Ray starts at sphere and goes outside (0 points)
        assertNull(testSphere.findIntersections(new Ray(new Point(1, -1, 0), v1)), "Ray's line out of sphere");

        // TC18: Ray starts after sphere (0 points)
        assertNull(testSphere.findIntersections(new Ray(new Point(1, -2, 0), v1)), "Ray's line out of sphere");

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)

        // TC19: Ray starts before the tangent point
        assertNull(testSphere.findIntersections(new Ray(new Point(2, 1, 0), v1)), "Ray's line out of sphere");

        // TC20: Ray starts at the tangent point
        assertNull(testSphere.findIntersections(new Ray(new Point(2, 0, 0), v1)), "Ray's line out of sphere");

        // TC21: Ray starts after the tangent point
        assertNull(testSphere.findIntersections(new Ray(new Point(2, -1, 0), v1)), "Ray's line out of sphere");

        // **** Group: Special cases

        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        assertNull(testSphere.findIntersections(new Ray(new Point(3, 0, 0), v1)), "Ray's line out of sphere");

    }
}