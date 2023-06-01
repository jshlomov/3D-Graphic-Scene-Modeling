package renderer;

import lighting.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;
import java.util.List;

import static java.lang.Math.pow;
import static primitives.Util.alignZero;

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
        List<GeoPoint> intersections = scene.geometries.findGeoIntersectionsHelper(ray);
        return intersections == null ? scene.background : calcColor(ray.findClosestGeoPoint(intersections), ray);
    }

    /**
     * Calculates the color at the closest intersection point.
     *
     * @param intersection the closest intersection point
     * @param ray          the ray that intersects the objects
     * @return the color at the closest intersection point
     */
    private Color calcColor(GeoPoint intersection, Ray ray) {
        return scene.ambientLight.getIntensity()
                .add(calcLocalEffects(intersection, ray));
    }

    /**
     * Calculates the color contributions from local effects, such as diffusive and specular reflections.
     *
     * @param intersection the intersection point
     * @param ray          the ray that intersects the objects
     * @return the color contributions from local effects
     */
    private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
        Color color = intersection.geometry.getEmission();
        Vector v = ray.getDir();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = alignZero(n.dotProduct(v));
        if (n == v) return color;
        Material material = intersection.geometry.getMaterial();

        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(intersection.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // checks if sign(nl) == sign(nv)
                Color il = lightSource.getIntensity(intersection.point);
                color = color.add(il.scale(calcDiffusive(material, nl)), il.scale(calcSpecular(material, n, l, nl, v)));
            }
        }
        return color;
    }

    /**
     * Calculates the diffusive reflection contribution.
     *
     * @param material the material of the intersected object
     * @param nl       the dot product of the normal and the light vector
     * @return the diffusive reflection contribution
     */
    private Double3 calcDiffusive(Material material, double nl) {
        return material.kd.scale(nl >= 0 ? nl : nl * -1);
    }

    /**
     * Calculates the specular reflection contribution.
     *
     * @param material the material of the intersected object
     * @param n        the normal vector at the intersection point
     * @param l        the light vector
     * @param nl       the dot product of the normal and the light vector
     * @param v        the view vector
     * @return the specular reflection contribution
     */
    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
        Vector r = l.subtract(n.scale(2 * nl));
        double vr = alignZero(v.scale(-1).dotProduct(r));
        return material.ks.scale(pow(vr < 0 ? 0 : vr, material.nShininess));
    }
}
