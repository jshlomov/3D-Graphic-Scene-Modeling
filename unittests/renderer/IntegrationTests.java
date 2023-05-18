package renderer;

import geometries.Intersectable;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * IntegrationTests is a class that contains integration test cases for the interactions
 * between the camera rays and different geometric shapes such as spheres, planes, and triangles.
 * These tests ensure the correctness of the ray intersections with the shapes.
 * The test cases cover scenarios where the camera rays intersect different numbers of points
 * with the shapes.
 *
 * @author Yonatan Shlomov &amp; Itzik Nisan
 */
public class IntegrationTests {

    /**
     * Tests camera rays and sphere intersections
     */
    @Test
    public void testCameraAndSphere() {

        //////   integration test cases with sphere //////

        // 01: intersects 2 points with sphere
        Camera camera = new Camera(new Point(0, 0, 0), new Vector(0, 0, -1), new Vector(0, 1, 0)).setVPDistance(1);
        List<Ray> raysList = findAllRaysInVpPixels(camera.setVPSize(3, 3), 3, 3);
        Sphere sphereTest = new Sphere(new Point(0, 0, -3), 1);
        assertEquals(2, sumOfIntersections(raysList, sphereTest), "Wrong number of intersections with sphere (2)");

        // 02: intersects 18 points with sphere
        camera = new Camera(new Point(0, 0, 0.5), new Vector(0, 0, -1), new Vector(0, 1, 0)).setVPDistance(1);
        raysList = findAllRaysInVpPixels(camera.setVPSize(3, 3), 3, 3);
        sphereTest = new Sphere(new Point(0, 0, -2.5), 2.5);
        assertEquals(18, sumOfIntersections(raysList, sphereTest), "Wrong number of intersections with sphere (18)");

        // 03: Camera rays intersects 10 points with sphere
        sphereTest = new Sphere(new Point(0, 0, -2), 2);
        assertEquals(10, sumOfIntersections(raysList, sphereTest), "Wrong number of intersections with sphere (10)");

        // 04: Camera rays intersects 9 points with sphere
        sphereTest = new Sphere(new Point(0, 0, -2), 4);
        assertEquals(9, sumOfIntersections(raysList, sphereTest), "Wrong number of intersections with sphere (9)");

        // 05: Camera rays are not intersect the sphere (sphere before the camera.)
        sphereTest = new Sphere(new Point(0, 0, 1), 0.5);
        assertEquals(0, sumOfIntersections(raysList, sphereTest), "Wrong number of intersections with sphere (0)");
    }

    /**
     * Tests camera rays and plane intersections
     */
    @Test
    public void testCameraAndPlane() {
        ////// Plane&Camera integration test cases //////

        Camera camera = new Camera(new Point(0, 0, 0.5), new Vector(0, 0, -1), new Vector(0, 1, 0)).setVPDistance(1);
        List<Ray> raysList = findAllRaysInVpPixels(camera.setVPSize(3, 3), 3, 3);

        // 11: Camera intersects 9 points with plan
        Plane planeTest = new Plane(new Point(0, 0, -4), camera.getVTo());
        assertEquals(9, sumOfIntersections(raysList, planeTest), "Wrong number of intersections of camera rays with plane (9)");

        // 12: Camera rays intersects 9 points with plan
        planeTest = new Plane(new Point(0, 0, -4), new Vector(0, -0.2, 1));
        assertEquals(9, sumOfIntersections(raysList, planeTest), "Wrong number of intersections of camera rays with plane (9)");

        // 13: Camera rays intersects 6 points with plan
        planeTest = new Plane(new Point(0, 0, -4), new Vector(0, -1.5, 1));
        assertEquals(6, sumOfIntersections(raysList, planeTest), "Wrong number of intersections of camera rays with plane (6)");
    }

    /**
     * Tests the camera rays and triangle intersections
     */
    @Test
    public void testCameraAndTriangle() {
        ////// Triangle&Camera integration test cases //////

        Camera camera = new Camera(new Point(0, 0, 0.5), new Vector(0, 0, -1), new Vector(0, 1, 0)).setVPDistance(1);
        List<Ray> raysList = findAllRaysInVpPixels(camera.setVPSize(3, 3), 3, 3);

        // 21: Camera rays intersects 1 points with triangle
        Triangle triangleTest = new Triangle(new Point(0, 1, -2), new Point(1, -1, -2), new Point(-1, -1, -2));
        assertEquals(1, sumOfIntersections(raysList, triangleTest), "Wrong number of intersections of camera rays with triangle (1)");

        // 22: Camera rays intersects 2 points with triangle
        triangleTest = new Triangle(new Point(0, 20, -2), new Point(1, -1, -2), new Point(-1, -1, -2));
        assertEquals(2, sumOfIntersections(raysList, triangleTest), "Wrong number of intersections of camera rays with triangle (2)");
    }

    /**
     * Calculates all the rays from camera to middle of pixels.
     *
     * @param camera the camera object
     * @param nX     the number of pixels in the X direction
     * @param nY     the number of pixels in the Y direction
     * @return a LinkedList of rays from the camera to the middle of pixels
     */
    public List<Ray> findAllRaysInVpPixels(Camera camera, int nX, int nY) {

        var raysList = new LinkedList<Ray>();
        for (int j = 0; j < nY; j++) {
            for (int i = 0; i < nX; i++) {
                raysList.add(camera.constructRay(nX, nY, j, i));
            }
        }
        return raysList;
    }

    /**
     * Sums the number of camera rays that intersect a shape.
     *
     * @param rayList the list of camera rays
     * @param shape   the intersectable shape
     * @return the number of intersections between the camera rays and the shape
     */
    public int sumOfIntersections(List<Ray> rayList, Intersectable shape) {
        int count = 0;
        for (Ray r : rayList) {
            var pointsList = shape.findIntersections(r);
            if (pointsList != null)
                count += pointsList.size();
        }
        return count;
    }
}
