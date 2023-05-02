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
 * Unit tests for geometries.Sphere class
 */
class SphereTests {
    /**
     * Test method for {@link Sphere#getNormal(Point)}./
     */
    @Test
    void testGetNormal() {

        // ============ Equivalence Partitions Tests ==============
        Sphere sp = new Sphere(new Point(0, 0, 0), 1);
        double sq = Math.sqrt(1 / 3d);

        // TC01: Test that result of getNormal is proper.
        assertEquals(new Vector(sq, sq, sq), sp.getNormal(new Point(sq, sq, sq)), "Bad normal to Sphere");
    }
}