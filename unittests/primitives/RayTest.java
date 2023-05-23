package primitives;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Test methods of the class {@link primitives.Ray}
 */
class RayTest {

    /**
     * Test method {@link primitives.Ray#getPoint(double)}
     */
    @Test
    void testGetPoint() {
        // ============ Equivalence Partitions Tests ==============
        Ray ray = new Ray(new Point(1,1,1), new Vector(0,0,1));

        // TC01: Positive distance
        Point p = new Point(1,1,2);
        assertEquals(p, ray.getPoint(1), "Wrong point positive distance");

        // TC02: Negative distance
        p = new Point(1,1,0);
        assertEquals(p, ray.getPoint(-1), "Wrong point negative distance");

        // =============== Boundary Values Tests ==================
        // TC10: Zero distance
        p = new Point(1,1,1);
        assertEquals(p, ray.getPoint(0), "Wrong point negative distance");
    }

    /**
     * Test method {@link primitives.Ray#findClosestPoint(List)}
     */
    @Test
    void findClosestPoint() {
        Ray rayTest = new Ray(new Point(1,1,0), new Vector(-1,0,0));
        Point p1 = new Point(0,1,0);
        Point p2 = new Point(-1,1,0);
        Point p3 = new Point(-2,1,0);

        // ============ Equivalence Partitions Tests ==============
        // TC01: The point in the middle of the list is the closest
        List<Point> intersections = new LinkedList<>();
        intersections.add(p2);
        intersections.add(p1);
        intersections.add(p3);

        assertEquals(p1, rayTest.findClosestPoint(intersections),"Wrong");

        // =============== Boundary Values Tests ==================
        // TC10: the list is empty
        intersections = new LinkedList<>();

        assertNull(rayTest.findClosestPoint(intersections), "Wrong");

        // TC11: The point at the beginning of the list is the closest
        intersections.add(p1);
        intersections.add(p2);
        intersections.add(p3);
        assertEquals(p1, rayTest.findClosestPoint(intersections),"Wrong");

        // TC12: The point at the end of the list is the closest
        intersections.add(p3);
        intersections.add(p2);
        intersections.add(p1);
        assertEquals(p1, rayTest.findClosestPoint(intersections),"Wrong");

    }
}