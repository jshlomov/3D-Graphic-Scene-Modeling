package renderer;

import static org.junit.jupiter.api.Assertions.*;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Test;
import geometries.*;
import primitives.*;

/**
 * @author Yonatan Shlomov & Itzik Nisan
 *
 */
public class IntegrationTests {

    /**
     * Tests the camera rays and sphere intersections
     */
    @Test
    public void testCameraAndSphere() {

        // **** Group: integration test cases with sphere ****//

        // 01: intersects 2 points with sphere
        Camera camera = new Camera(new Point(0,0,0), new Vector(0, 0, -1), new Vector(0, 1, 0)).setVPDistance(1);
        LinkedList<Ray> rayList = findAllRaysInVpPixels(camera.setVPSize(3, 3), 3, 3);
        Sphere sphereTest = new Sphere(new Point(0, 0, -3), 1);
        assertEquals(2, countIntersections(rayList, sphereTest),"Wrong number of intersections with sphere (2)");

        // 02: intersects 18 points with sphere
        camera = new Camera(new Point(0, 0, 0.5), new Vector(0, 0, -1), new Vector(0, 1, 0)).setVPDistance(1);
        rayList = findAllRaysInVpPixels(camera.setVPSize(3, 3), 3, 3);
        sphereTest = new Sphere(new Point(0, 0, -2.5), 2.5);
        assertEquals(18, countIntersections(rayList, sphereTest),"Wrong number of intersections with sphere (18)");

        // 03: Camera rays intersects 10 points with sphere
        sphereTest = new Sphere(new Point(0, 0, -2), 2);
        assertEquals(10, countIntersections(rayList, sphereTest),"Wrong number of intersections with sphere (10)");

        // 04: Camera rays intersects 9 points with sphere
        sphereTest = new Sphere(new Point(0, 0, -2), 4);
        assertEquals(9, countIntersections(rayList, sphereTest),"Wrong number of intersections with sphere (9)");

        // 05: No camera rays intersection with sphere (sphere before the camera.)
        sphereTest = new Sphere(new Point(0, 0, 1), 0.5);
        assertEquals(0, countIntersections(rayList, sphereTest),"Wrong number of intersections with sphere (0)");
    }

    /**
     * Tests the camera rays and plane intersections
     */
    @Test
    public void testCameraAndPlane() {
        // **** Group: Plane&Camera integration test cases ****//

        Camera  camera = new Camera(new Point(0, 0, 0.5), new Vector(0, 0, -1), new Vector(0, 1, 0)).setVPDistance(1);
        LinkedList<Ray> rayList = findAllRaysInVpPixels(camera.setVPSize(3, 3), 3, 3);

        // 11: Camera intersects 9 points with plan
        Plane plane = new Plane(new Point(0, 0, -4), camera.getvTo());
        assertEquals(9, countIntersections(rayList, plane),"Wrong number of intersections of camera rays with plane - expected 9");

        // 12: Camera rays intersects 9 points with plan
        plane = new Plane(new Point(0, 0, -4), new Vector(0, -0.2, 1));
        assertEquals( 9, countIntersections(rayList, plane),"Wrong number of intersections of camera rays with plane - expected 9");

        // 13: Camera rays intersects 6 points with plan
        plane = new Plane(new Point(0, 0, -4), new Vector(0, -1.5, 1));
        assertEquals(6, countIntersections(rayList, plane),"Wrong number of intersections of camera rays with plane - expected 6");
    }

    /**
     * Tests the camera rays and triangle intersections
     */
    @Test
    public void testCameraAndTriangle() {
        // **** Group: Triangle&Camera integration test cases ****//

        Camera  camera = new Camera(new Point(0, 0, 0.5), new Vector(0, 0, -1), new Vector(0, 1, 0)).setVPDistance(1);
        LinkedList<Ray> rayList = findAllRaysInVpPixels(camera.setVPSize(3, 3), 3, 3);

        // 21: Camera rays intersects 1 points with triangle
        Triangle tri = new Triangle(new Point(0, 1, -2), new Point(1, -1, -2), new Point(-1, -1, -2));
        assertEquals(1, countIntersections(rayList, tri),"Wrong number of intersections of camera rays with triangle - expected 1");

        // 22: Camera rays intersects 2 points with triangle
        tri = new Triangle(new Point(0, 20, -2), new Point(1, -1, -2), new Point(-1, -1, -2));
        assertEquals(2, countIntersections(rayList, tri),"Wrong number of intersections of camera rays with triangle - expected 2");
    }

    /**
     * Calculates all the rays from camera to middle of pixels
     *
     * @param camera
     * @param nX
     * @param nY
     * @return List of rays
     */
    public LinkedList<Ray> findAllRaysInVpPixels(Camera camera, int nX, int nY) {

        LinkedList<Ray> raysList = new LinkedList<>();
        for (int j = 0; j < nY; j++) {
            for (int i = 0; i < nX; i++) {
                raysList.add(camera.constructRay(nX, nY, j, i));
            }
        }
        return raysList;
    }

    /**
     * Sums the number of camera rays that intersect a shape
     *
     * @param rayList
     * @param shape
     * @return Number of intersections
     */
    public int countIntersections(LinkedList<Ray> rayList, Intersectable shape) {
        int count = 0;
        for (Ray r : rayList)
        {
            List<Point> pointsList = shape.findIntersections(r);
            if (pointsList != null)
                count += pointsList.size();
        }
        return count;
    }
}
