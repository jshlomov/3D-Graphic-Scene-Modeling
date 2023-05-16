/**
 * Creating unit tests according to TDD principle
 *
 * @author Yonatan and Itzik
 */
package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for primitives.Point class
 */
class PointTest {
    Point p1 = new Point(1, 2, 3);
    Point p2 = new Point(3, 5, 5);

    /**
     * Test method for {@link primitives.Point#add(Vector)}.
     */
    @Test
    void add() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Point addition wrong calculation
        assertEquals(new Point(0, 0, 0), p1.add(new Vector(-1, -2, -3)),
                "ERROR: add(Point) does not work correctly");
    }

    /**
     * Test method for {@link primitives.Point#subtract(Point)}.
     */
    @Test
    void subtract() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Point subtraction wrong calculation
        assertEquals(new Vector(1, 1, 1), new Point(2, 3, 4).subtract(p1),
                "ERROR: Point - Point does not work correctly");

        // TC02: Point subtraction itself
        assertThrows(IllegalArgumentException.class, () -> p1.subtract(p1), "Point - itself doesnt work corectly");
    }

    /**
     * Test method for {@link primitives.Point#distanceSquared(Point)}.
     */
    @Test
    void distanceSquared() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Point squared distance wrong calculation
        int sqrtDistance = 17;
        assertEquals(sqrtDistance, p2.distanceSquared(p1), "ERROR: DistanceSquared() wrong value");
    }

    /**
     * Test method for {@link primitives.Point#distance(Point)}.
     */
    @Test
    void distance() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Point distance wrong calculation
        assertEquals(Math.sqrt(17), p2.distance(p1), "ERROR: Distance() wrong value");

        // =============== Boundary Values Tests ==================
        // TC11: Point distance with itself wrong calculation
        assertEquals(0, p1.distance(p1), "ERROR: Distance() wrong value");
    }
}