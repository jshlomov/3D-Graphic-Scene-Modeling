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

class SphereTests {

    /**
     * Test method for {@link Sphere#getNormal(Point)}./
     */
    @Test
    void testGetNormal() {

        // ============ Equivalence Partitions Tests ==============
        Sphere sp = new Sphere(1, new Point(0, 0, 0));
        double sq = Math.sqrt(1 / 3d);
        Vector N = sp.getNormal(new Point(sq, sq, sq));

        // Test that result of getNormal is proper.
        assertEquals(N, new Vector(sq, sq, sq));
    }
}