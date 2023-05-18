package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.*;

/**
 * The Camera class represents a camera in a 3D scene.
 */
public class Camera {
    private final Point p0;
    private final Vector vTo;
    private final Vector vUp;
    private final Vector vRight;
    private double height;
    private double width;
    private double distance;

    /**
     * Constructs a camera with the given parameters.
     *
     * @param p0  the camera's position in 3D space
     * @param vTo the direction the camera is facing
     * @param vUp the up vector of the camera
     * @throws IllegalArgumentException if the given vectors are not vertical
     */
    public Camera(Point p0, Vector vTo, Vector vUp) {
        if (!isZero(vUp.dotProduct(vTo)))
            throw new IllegalArgumentException("The given vectors are not vertical.");
        this.p0 = p0;
        this.vUp = vUp.normalize();
        this.vTo = vTo.normalize();
        this.vRight = vTo.crossProduct(vUp).normalize();
    }

    /**
     * Gets the position of the camera.
     *
     * @return the camera's position
     */
    @SuppressWarnings("unused")
    public Point getP0() {
        return p0;
    }

    /**
     * Gets the up vector of the camera.
     *
     * @return the up vector
     */
    @SuppressWarnings("unused")
    public Vector getVUp() {
        return vUp;
    }

    /**
     * Gets the direction the camera is facing.
     *
     * @return the direction vector
     */
    public Vector getVTo() {
        return vTo;
    }

    /**
     * Gets the right vector of the camera.
     *
     * @return the right vector
     */
    @SuppressWarnings("unused")
    public Vector getVRight() {
        return vRight;
    }

    /**
     * Gets the width of the view plane.
     *
     * @return the width of the view plane
     */
    @SuppressWarnings("unused")
    public double getWidth() {
        return width;
    }

    /**
     * Gets the height of the view plane.
     *
     * @return the height of the view plane
     */
    @SuppressWarnings("unused")
    public double getHeight() {
        return height;
    }

    /**
     * Gets the distance between the camera and the view plane.
     *
     * @return the distance between the camera and the view plane
     */
    @SuppressWarnings("unused")
    public double getDistance() {
        return distance;
    }

    /**
     * Sets the size of the view plane.
     *
     * @param width  the width of the view plane
     * @param height the height of the view plane
     * @return the camera itself
     * @throws IllegalArgumentException if width or height is negative or zero
     */
    public Camera setVPSize(double width, double height) {
        if (alignZero(width) <= 0 || alignZero(height) <= 0)
            throw new IllegalArgumentException("Width or height cannot be negative or zero!");
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * Sets the distance between the camera and the view plane.
     *
     * @param distance the distance between the camera and the view plane
     * @return the camera itself
     * @throws IllegalArgumentException if the distance is negative or zero
     */
    public Camera setVPDistance(double distance) {
        if (alignZero(distance) <= 0)
            throw new IllegalArgumentException("The distance must be greater than zero.");
        this.distance = distance;
        return this;
    }

    /**
     * Constructs a ray that passes through the middle of a pixel (i, j) on the view plane.
     *
     * @param nX the number of pixels in the X direction of the view plane
     * @param nY the number of pixels in the Y direction of the view plane
     * @param j  the column index of the pixel
     * @param i  the row index of the pixel
     * @return the constructed ray
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        // Image center:
        Point pC = p0.add(vTo.scale(this.distance));

        // Ratio (pixel height and width):
        double ry = height / nY;
        double rx = width / nX;

        // Pixel[i,j] center
        double yi = alignZero(-(i - (nY - 1) / 2.0) * ry);
        double xj = alignZero((j - (nX - 1) / 2.0) * rx);

        // Start at the center of the View Plane and then move according to Xj and to Yi
        Point pIJ = pC;
        // To avoid a zero vector exception (when no need to move)
        if (xj != 0) pIJ = pIJ.add(vRight.scale(xj));
        if (yi != 0) pIJ = pIJ.add(vUp.scale(yi));

        return new Ray(p0, pIJ.subtract(this.p0));
    }
}
