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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Unit tests for geometries.Triangle class
 */

public class TriangleTests {
    /**
     * Test method for {@link Triangle#getNormal(Point)}.
     */
    @Test
    void getNormal() {

        // ============ Equivalence Partitions Tests ==============
        Triangle testT = new Triangle(
                new Point(1, 0, 0),
                new Point(0, 1, 0),
                new Point(0, 0, 1)
        );
        double sq = Math.sqrt(1 / 3d);

        // Test that result of getNormal is proper
        assertEquals(testT.getNormal(new Point(1, 0, 0)), new Vector(sq, sq, sq));
    }
}

