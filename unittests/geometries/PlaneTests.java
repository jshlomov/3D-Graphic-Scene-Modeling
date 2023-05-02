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
                () -> new Plane(new Point(1, 1, 1), new Point(1,  1, 1), new Point(0, 0, 0))
                ,"Constructor with same points - error");

        // TC12: Check for 3 points on one straight line
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(new Point(1, 1, 1), new Point(2, 2, 2), new Point(3, 3, 3))
                ,"Constructor with points on one straight line - error");
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
                ,"Bad normal to plane");
    }
}
