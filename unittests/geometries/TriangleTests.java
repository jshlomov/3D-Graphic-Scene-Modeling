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
                ||  new Vector(-1 * sq, -1 * sq, -1 * sq).equals(testT.getNormal(new Point(0, 0, 1))),"Bad normal to plane");
    }
}

