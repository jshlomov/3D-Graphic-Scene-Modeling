package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

import java.util.LinkedList;
import java.util.List;

/**
 * Class RayTracerSuperSampling for calc color by ray from camera extends
 * RayTracerBasic and add depth of field effect by send ray beam to calc color
 *
 */
public class RayTracerSuperSampling extends RayTracerBasic {

    private final List<Point> aperturePointList;
    private final Camera camera;
    private final int xyBeamSize;
    private final int beamSize;
    private boolean isAdaptiveSuperSampling;

    /**
     * Constructor of RayTracerSuperSampling
     *
     * @param scene      to read geometries data
     * @param camera     to get data of focal plan and Aperture Radius
     * @param xyBeamSize to create beam of xy*xy rays in each beam
     */
    public RayTracerSuperSampling(Scene scene, Camera camera, int xyBeamSize) {
        super(scene);
        this.camera = camera;
        this.xyBeamSize = xyBeamSize;

        aperturePointList = BlackBoard.constructBlackBorad(xyBeamSize, camera.getP0(), camera.getVUp(),
                camera.getVRight(), camera.getApertureRadius());
        beamSize = aperturePointList.size();
    }

    @Override
    public Color traceRay(Ray ray) {
        Color color = Color.BLACK;

        if (!isAdaptiveSuperSampling) {
            List<Ray> raysBeam = createRayBeamFromPointsToTarget(ray, aperturePointList);
            for (Ray r : raysBeam)
                color = color.add(super.traceRay(r));

            return color.reduce(beamSize);
        }
        else {
            return recCastRay(xyBeamSize, color, camera.getP0(), ray);
        }
    }

    /**
     * The function checks the color at the edges of the pixel square by sending 4 rays,
     * if their color is equal it returns the color,
     * and if it is different it divides the square into 4 quarters,
     * and calls the function recursively for each quarter.
     * The function returns the weighted color of the pixel.
     *
     * @param nXY  A square root of the number of rays
     * @param color The color on which color is added in recursion
     * @param middle  The middle of the square that divides the pixel
     * @param ray  the given ray
     * @return The color of the pixel that called the function
     */
    protected Color recCastRay(double nXY, Color color, Point middle, Ray ray) {

        List<Point> points = constructPoints(nXY, middle);
        List<Ray> raysForFocus = createRayBeamFromPointsToTarget(ray, points);
        List<Color> colors = new LinkedList<>();

        for (Ray r : raysForFocus) {
            colors.add(super.traceRay(r));
        }

        if (nXY / 4 < 1){
            for (Color c: colors) {
                color = color.add(c);// if recursion gets to max
            }
            return color;
        }
        if (colors.get(0).getColor().equals(colors.get(1).getColor()) && colors.get(0).getColor().equals(colors.get(2).getColor())&& colors.get(0).getColor().equals(colors.get(3).getColor())) {
            return color.add(colors.get(0));
        }

        Vector vRight = camera.getVRight();
        Vector vUp = camera.getVUp();

        return color.add(recCastRay(nXY / 2, color, middle.add(vRight.scale(nXY / 4)).add(vUp.scale(nXY / 4)), ray),
                recCastRay(nXY / 2,  color, middle.add(vRight.scale(nXY / 4)).add(vUp.scale(-nXY / 4)), ray),
                recCastRay(nXY / 2,  color, middle.add(vRight.scale(-nXY / 4)).add(vUp.scale(nXY / 4)), ray),
                recCastRay(nXY / 2,  color, middle.add(vRight.scale(-nXY / 4)).add(vUp.scale(-nXY / 4)), ray));
    }

    /**
     * Creates 4 points at the four ends of the square of the pixel
     * @param beamSize beam of xy*xy rays in each beam
     * @param middle middle of square
     * @return the square's points
     */
    protected  List<Point> constructPoints(double beamSize, Point middle){
        Vector vRight = camera.getVRight();
        Vector vUp = camera.getVUp();
        Point p1 = middle.add(vRight.scale(beamSize / 2)).add(vUp.scale(beamSize / 2));  //Up-right corner
        Point p2 = middle.add(vRight.scale(beamSize / 2)).add(vUp.scale(-beamSize / 2));  //bottom-right corner
        Point p3 = middle.add(vRight.scale(-beamSize / 2)).add(vUp.scale(beamSize / 2));  //Up-left corner
        Point p4 = middle.add(vRight.scale(-beamSize / 2)).add(vUp.scale(-beamSize / 2));  //bottom-left corner

        List<Point> points = new LinkedList<>();

        points.add(p1);
        points.add(p2);
        points.add(p3);
        points.add(p4);

        return points;
    }

    /**
     * get basic ray from camera and create rays from all aperture Points to the target focal point
     *
     *  @param ray center ray to get target focal point
     *  @param pointList pointList for source of ray beam
     *  @return list of all rays to target
     */
    private List<Ray> createRayBeamFromPointsToTarget(Ray ray, List<Point> pointList){
        if (pointList.size() == 1)
            return List.of(ray);

        Point targetPoint = calculateFocalPoint(ray);
        List<Ray> rayList = new LinkedList<>();

        for (Point pnt : pointList) {
            rayList.add(new Ray(pnt, targetPoint.subtract(pnt).normalize()));
        }

        return rayList;
    }

    private Point calculateFocalPoint(Ray ray) {
        double t = camera.getDistance()+camera.getFocalDistance();
        t /= camera.getVTo().dotProduct(ray.getDir());
        return ray.getPoint(t);
    }

    /**
     *  sets the adaptive Super sampling activator
     * @param adaptiveSuperSampling  the activator
     * @return the ray tracer itself
     */
    public RayTracerSuperSampling setAdaptiveSuperSampling(boolean adaptiveSuperSampling) {
        isAdaptiveSuperSampling = adaptiveSuperSampling;
        return this;
    }
}