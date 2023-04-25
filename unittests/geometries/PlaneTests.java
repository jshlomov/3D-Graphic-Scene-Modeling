/**
 * @author: Yonatan Shlomov 319217162
 *          Itzik Nisan 312517261
 *Creating unit tests according to TDD principle
 */
package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PlaneTests {

    /**
     * Test method for ctor who get 3 point.
     */
    @Test
    public void testPlane() {

        // =============== Boundary Values Tests ==================

        // Test for 2 similar points
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(new Point(1, 1, 1), new Point(1,  1, 1), new Point(0, 0, 0))
                ,"Constructor with same points - error");

        // Check for 3 points on one straight line
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(new Point(1, 1, 1), new Point(2, 2, 2), new Point(3, 3, 3))
                ,"Constructor with points on one straight line - error");
    }

    /**
     * Test method for {@link Plane#getNormal(Point)}.
     */
    @Test
    void testGetNormal() {

        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Plane testPlane = new Plane(
                new Point(1, 0, 0),
                new Point(0, 1, 0),
                new Point(0, 0, 1)
        );
        double sqrt3 = Math.sqrt(1 / 3d);

        // Test that result of getNormal is proper
        assertEquals(new Vector(sqrt3, sqrt3, sqrt3), testPlane.getNormal(new Point(0, 0, 1)), "Bad normal to plane");
    }
}
