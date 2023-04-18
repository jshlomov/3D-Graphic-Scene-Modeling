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

class CylinderTests {

    /**
     * Test method for {@link Cylinder#getNormal(Point)}.
     */
    @Test
    void getNormal() {

        // ============ Equivalence Partitions Tests ==============
        Ray ray = new Ray(new Point(0, -2, 0), new Vector(0, 1, 0));
        Cylinder cl = new Cylinder( 2,ray, 3);

        // Test that result of getNormal is proper

        //TC01 - inside first base:
        assertEquals(cl.getNormal(new Point(0, -2, 1)), new Vector(0, -1, 0));

        //TC02 - inside far base:
        assertEquals(cl.getNormal(new Point(0, 1, 1)), new Vector(0, 1, 0));

        //TC03 - round surface:
        assertEquals(cl.getNormal(new Point(0, 0, 2)), new Vector(0, 0, 1));


        // =============== Boundary Values Tests ==================
        //TC10 - corner first base, normal should be like inside base
        assertEquals(cl.getNormal(new Point(0, -2, 2)), new Vector(0, -1, 0));

        //TC11 - corner far base - normal should be like inside base
        assertEquals(cl.getNormal(new Point(0, 1, 2)), new Vector(0, 1, 0));
    }

}