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

import static org.junit.jupiter.api.Assertions.assertEquals;

class TubeTests {
    /**
     * Test method for {@link Tube#getNormal(Point)}.
     */
    @Test
    void testGetNormal() {
        Tube tube = new Tube(1.0, new Ray(new Point(0, 0, 1), new Vector(0, 1, 0)));

        // ============ Equivalence Partitions Tests ==============

        // TC01: Wrong normal calculation
        assertEquals(new Vector(0, 0, 1), tube.getNormal(new Point(0, 0.5, 2)), "Bad normal to tube");
    }
}