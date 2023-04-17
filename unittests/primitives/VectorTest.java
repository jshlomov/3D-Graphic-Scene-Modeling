/**
 * @author: Yonatan Shlomov 319217162
 *          Itzik Nisan 312517261
 *Creating unit tests according to TDD principle
 */
package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

class VectorTest {

    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(2, 4, 6);
    Vector v3 = new Vector(0, 3, -2);

    /**
     * Test method for {@link primitives.Vector# testAdd(primitives.Point)}.
     */
    @Test
    void add() {
        // ============ Equivalence Partitions Tests ==============

        // Test that sum of add is proper
        assertEquals(new Vector(3, 6, 9), v1.add(v2), "ERROR: add() wrong value");
    }

    /**
     * Test method for {@link primitives.Vector#  testScale(primitives.Point)}.
     */
    @Test
    void scale() {
        // ============ Equivalence Partitions Tests ==============

        // Test that result of scale is proper
        assertEquals(v2, v1.scale(2), "ERROR: scale() wrong value");
    }

    /**
     * Test method for {@link primitives.Vector#  testDotProduct(primitives.Point)}.
     */
    @Test
    void dotProduct() {
        // ============ Equivalence Partitions Tests ==============

        // Test that result of dotProduct is proper
        assertEquals(28, v1.dotProduct(v2), "ERROR: dotProduct() wrong value");
        assertEquals(0, v1.dotProduct(new Vector(0, 3, -2)), "ERROR: dotProduct() wrong value");
    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */
    @Test
    public void testCrossProduct() {

        // ============ Equivalence Partitions Tests ==============
        Vector vr = v1.crossProduct(v3);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals(v1.length() * v3.length(), vr.length(), 0.00001, "crossProduct() wrong result length");

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue(isZero(vr.dotProduct(v1)), "crossProduct() result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProduct(v3)), "crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-product of co-lined vectors
        Vector v4 = new Vector(-2, -4, -6);
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v4), "crossProduct() for parallel vectors does not throw an exception");
    }

    /**
     * Test method for {@link primitives.Vector#  testLengthSquared(primitives.Point)}.
     */
    @Test
    void testLengthSquared() {
        // ============ Equivalence Partitions Tests ==============
        int length1 = 14;
        int length2 = 56;

        // Test that length squared is proper
        assertEquals(length1, v1.lengthSquared(), "ERROR: lengthSquared() wrong value");
        assertEquals(length2, v2.lengthSquared(), "ERROR: lengthSquared() wrong value");

    }

    /**
     * Test method for {@link primitives.Vector#  testLength(primitives.Point)}.
     */
    @Test
    void testLength() {
        // ============ Equivalence Partitions Tests ==============
        int length2 = 5;

        // Test that length is proper
        assertEquals(length2, new Vector(0, 3, 4).length(), "ERROR: length() wrong value");

    }

    /**
     * Test method for {@link primitives.Vector#  testNormalize(primitives.Point)}.
     */
    @Test
    void testNormalize() {
        Vector v = new Vector(0, 3, 4);
        Vector n = v.normalize();
        // ========================= Equivalence Partitions Tests ===========
        // TC01: Simple test
        assertEquals(1d, n.lengthSquared(), 0.00001, "wrong normalized vector Length");
        assertThrows(IllegalArgumentException.class, () -> v.crossProduct(n),
                "normalized vector is not in the same direction");
        assertEquals(new Vector(0, 0.6, 0.8), n, "wrong normalized vector");
    }
}
