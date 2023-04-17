/**
 * Creating unit tests according to TDD principle
 *  @author Yonatan and Itzik
 */
package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;


class PointTest {
    Point p1 = new Point(1, 2, 3);
    Point p2 = new Point(3,5,5);

    /**
     * Test method for {@link primitives.Point#add(Vector)}.
     */
    @Test
    void add() {
        // ============ Equivalence Partitions Tests ==============

        // TC01:
        assertEquals(new Point(0, 0, 0), p1.add(new Vector(-1, -2, -3)),
                "ERROR: Point + Vector does not work correctly");
    }

    /**
     * Test method for {@link primitives.Point#subtract(Point)}.
     */
    @Test
    void subtract() {
        // ============ Equivalence Partitions Tests ==============
        assertEquals(new Vector(1, 1, 1), new Point(2, 3, 4).subtract(p1),
                "ERROR: Point - Point does not work correctly");
    }

    /**
     * Test method for {@link primitives.Point#distanceSquared(Point)}.
     */
    @Test
    void distanceSquared() {
        // ============ Equivalence Partitions Tests ==============
        int sqrtDistance = 17;
        assertEquals(sqrtDistance,p2.distanceSquared(p1),"ERROR: DistanceSquared() wrong value");
    }
    /**
     * Test method for {@link primitives.Point#distance(Point)}.
     */
    @Test
    void distance() {
        // ============ Equivalence Partitions Tests ==============
        Double distance = Math.sqrt(17);
        assertEquals(distance,p2.distance(p1),"ERROR: DistanceSquared() wrong value");

    }
}