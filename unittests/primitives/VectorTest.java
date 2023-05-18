/**
 * @author: Yonatan Shlomov 319217162
 * Itzik Nisan 312517261
 * Creating unit tests according to TDD principle
 */
package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for primitives.Vector class
 */
class VectorTest {

    private final Vector v1 = new Vector(1, 2, 3);
    private final Vector v2 = new Vector(-2, -4, -6);
    private final Vector v3 = new Vector(0, 3, -2);

    /**
     * Test method for {@link primitives.Vector#add(primitives.Vector)}.
     */
    @Test
    void add() {
        Vector v4 = new Vector(-1, -2, -3);
        // ============ Equivalence Partitions Tests ==============

        // TC01: Test that sum of add is proper
        assertEquals(new Vector(-1, -2, -3), v1.add(v2), "ERROR: add() wrong value");

        //TC02: Wrong vectors addition with itself
        assertThrows(IllegalArgumentException.class, () -> v1.add(v4), "ERROR: Vector + -itself does not throw an exception");
    }

    /**
     * Test method for {@link Vector#subtract(Point)}.
     */
    @Test
    void subtract() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Test that sum of add is proper
        assertEquals(new Vector(3, 6, 9), v1.subtract(v2), "ERROR: subtract() wrong value");

        // =============== Boundary Values Tests ==================

        //TC11: Wrong vectors addition with itself
        assertThrows(IllegalArgumentException.class, () -> v1.subtract(v1), "ERROR: Vector - itself does not throw an exception");
    }

    /**
     * Test method for {@link primitives.Vector#scale(double)}.
     */
    @Test
    void scale() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Test that result of scale is proper
        assertEquals(v2, v1.scale(-2), "ERROR: scale() wrong value");

        // =============== Boundary Values Tests ==================
        // TC11: scale with zero
        assertThrows(IllegalArgumentException.class, () -> v1.scale(0), "equal to zero!");
    }

    /**
     * Test method for {@link Vector#dotProduct(Vector)}.
     */
    @Test
    void dotProduct() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Orthogonal vectors dot product doesn't equal zero
        assertEquals(0, v1.dotProduct(v3), "dotProduct() for orthogonal vectors is not zero");

        // TC02: Wrong vectors dot product calculations
        assertEquals(0, v1.dotProduct(v2) + 28, "dotProduct() wrong value");
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
        assertEquals(0, vr.dotProduct(v1), "crossProduct() result is not orthogonal to 1st operand");
        assertEquals(0, vr.dotProduct(v3), "crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================

        // TC11: test zero vector from cross-product of co-lined vectors
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v2), "crossProduct() for parallel vectors does not throw an exception");
    }

    /**
     * Test method for {@link primitives.Vector#lengthSquared()}.
     */
    @Test
    void testLengthSquared() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Test that length squared is proper
        assertEquals(14, v1.lengthSquared(), 0.000001, "ERROR: lengthSquared() wrong value");
    }

    /**
     * Test method for {@link primitives.Vector#length()}.
     */
    @Test
    void testLength() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Test that length is proper
        assertEquals(5, new Vector(0, 3, 4).length(), "ERROR: length() wrong value");
    }

    /**
     * Test method for {@link primitives.Vector#normalize()}.
     */
    @Test
    void testNormalize() {
        Vector u = v1.normalize();
        double root14 = Math.sqrt(14);

        // ============= Equivalence Partitions Tests ===========
        // TC01:  normalize() doesn't make it a unit vector
        assertEquals(new Vector(1 / root14, 2 / root14, 3 / root14), u, "the normalized vector is not a unit vector of the source vector");
    }
}
