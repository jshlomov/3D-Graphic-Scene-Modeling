package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.LinkedList;
import java.util.List;

/**
 * Class RayTracerSuperSampling for calc color by ray from camera extends
 * RayTracerBasic and add depth of field effect by send ray beam to calc color
 *
 */
public class RayTracerSuperSampling extends RayTracerBasic {

    private List<Point> aperturePointList;
    private Camera camera;
    private int xyBeamSize;
    private int beamSize;

    /**
     * A boolean operator if to use the feature Adaptive-Supersampling
     */
    boolean isAdaptiveSupersampling = true;

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

        List<Ray> raysBeam = createRayBeamFromPointsToTarget(ray, aperturePointList);

        for (Ray r : raysBeam)
            color = color.add(super.traceRay(r));

        return color.reduce(beamSize);
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

        double t = camera.getDistance()+camera.getFocalDistance();
        t /= camera.getVTo().dotProduct(ray.getDir());

        Point targetPoint = ray.getPoint(t);
        List<Ray> rayList = new LinkedList<>();

        for (Point pnt : pointList) {
            rayList.add(new Ray(pnt, targetPoint.subtract(pnt).normalize()));
        }

        return rayList;
    }

    public RayTracerSuperSampling setAdaptiveSuperSampling(boolean adaptiveSupersampling) {
        isAdaptiveSupersampling = adaptiveSupersampling;
        return this;
    }
}