package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.List;

/**
 * The RayTracerBasic class represents a basic implementation of a ray tracer.
 * It extends the RayTracerBase class and provides a simple algorithm for tracing rays and computing colors.
 */
public class RayTracerBasic extends RayTracerBase {

    /**
     * Constructs a RayTracerBasic object with the given scene.
     *
     * @param scene the scene to trace rays in
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * Traces the given ray in the scene and computes the color of the intersected objects.
     * If no intersections are found, the background color of the scene is returned.
     *
     * @param ray the ray to trace
     * @return the computed color of the intersected objects or the background color
     */
    @Override
    public Color traceRay(Ray ray) {
        List<Point> intersections = scene.geometries.findIntersections(ray);
        if (intersections == null)
            return scene.background;
        return calcColor(ray.findClosestPoint(intersections));
    }

    /**
     * Calculates the color at the closest intersection point.
     *
     * @param closestPoint the closest intersection point
     * @return the color at the closest intersection point
     */
    @SuppressWarnings("unused")
    private Color calcColor(Point closestPoint) {
        return scene.ambientLight.getIntensity();
    }
}
