package renderer;

import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

import static java.lang.Math.pow;
import static primitives.Util.alignZero;

/**
 * The RayTracerBasic class represents a basic implementation of a ray tracer.
 * It extends the RayTracerBase class and provides a simple algorithm for tracing rays and computing colors.
 */
public class RayTracerBasic extends RayTracerBase {

    private static final double DELTA = 0.1;
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final Double3 INITIAL_K = Double3.ONE;


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
        GeoPoint closestPoint = findClosestIntersection(ray);
        return closestPoint == null ? scene.background : calcColor(closestPoint, ray);
    }

    /**
     * Calculates the closest intersection point for the given ray in the scene.
     *
     * @param ray the ray to find the closest intersection for
     * @return the closest intersection point, or null if no intersections are found
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        return intersections == null ? null : ray.findClosestGeoPoint(intersections);
    }

    /**
     * Calculates the color of the given intersection point and ray.
     *
     * @param gp    the intersection point
     * @param ray   the ray that intersects the objects
     * @return the color at the intersection point
     */
    private Color calcColor(GeoPoint gp, Ray ray) {
        return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(scene.ambientLight.getIntensity());
    }

    /**
     * Calculates the color at the given intersection point and ray, considering global effects like reflection and refraction.
     *
     * @param intersection the intersection point
     * @param ray          the ray that intersects the objects
     * @param level        the current recursion level
     * @param k            the accumulated reflection/refraction coefficient
     * @return the color at the intersection point
     */
    private Color calcColor(GeoPoint intersection, Ray ray, int level, Double3 k) {
        Color color = intersection.geometry.getEmission();
        color = color.add(calcLocalEffects(intersection, ray, k));
        return level == 1 ? color : color.add(calcGlobalEffects(intersection, ray, level, k));
    }

    /**
     * Calculates the global effects (reflection and refraction) at the given intersection point and ray.
     *
     * @param gp    the intersection point
     * @param ray   the ray that intersects the objects
     * @param level the current recursion level
     * @param k     the accumulated reflection/refraction coefficient
     * @return the color contribution from global effects
     */
    private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {
        Color color = Color.BLACK;
        Vector n = gp.geometry.getNormal(gp.point);
        Material material = gp.geometry.getMaterial();

        Double3 kkr = material.kr.product(k);
        if (!kkr.lowerThan(MIN_CALC_COLOR_K))
            color = calcGlobalEffect(constructReflectedRay(gp.geometry.getNormal(gp.point), gp.point, ray), level, material.kr, kkr);

        Double3 kkt = material.kt.product(k);
        if (!kkt.lowerThan(MIN_CALC_COLOR_K))
            color = color.add(calcGlobalEffect(constructRefractedRay(gp.geometry.getNormal(ray.getP0()), gp.point, ray), level, material.kt, kkt));

        return color;
    }

    /**
     * Calculates the color contribution from global effects (reflection or refraction) at the given intersection point and ray.
     *
     * @param ray   the ray for the global effect
     * @param level the current recursion level
     * @param kx    the reflection/refraction coefficient of the current object
     * @param kkx   the accumulated reflection/refraction coefficient
     * @return the color contribution from the global effect
     */
    private Color calcGlobalEffect(Ray ray, int level, Double3 kx, Double3 kkx) {
        GeoPoint gp = findClosestIntersection(ray);
        return gp == null ? scene.background : calcColor(gp, ray, level - 1, kkx).scale(kx);
    }

    /**
     * Constructs a refracted ray based on the given normal vector, intersection point, and incident ray.
     *
     * @param n     the normal vector at the intersection point
     * @param point the intersection point
     * @param inRay the incident ray
     * @return the refracted ray
     */
    private Ray constructRefractedRay(Vector n, Point point, Ray inRay) {
        return new Ray(inRay.getDir(), point, n);
    }

    /**
     * Constructs a reflected ray based on the given normal vector, intersection point, and incident ray.
     *
     * @param n     the normal vector at the intersection point
     * @param point the intersection point
     * @param inRay the incident ray
     * @return the reflected ray
     */
    private Ray constructReflectedRay(Vector n, Point point, Ray inRay) {
        Vector v = inRay.getDir();
        Vector r = v.subtract(n.scale(alignZero(2 * (n.dotProduct(v)))));
        return new Ray(r.normalize(), point, n);
    }

    /**
     * Calculates the color contributions from local effects, such as diffusive and specular reflections.
     *
     * @param intersection the intersection point
     * @param ray          the ray that intersects the objects
     * @param k            the accumulated reflection/refraction coefficient
     * @return the color contributions from local effects
     */
    private Color calcLocalEffects(GeoPoint intersection, Ray ray, Double3 k) {
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
                Double3 ktr = transparency(lightSource, l, n, intersection);
                if (!k.product(ktr).lowerThan(MIN_CALC_COLOR_K)) {
                    Color il = lightSource.getIntensity(intersection.point).scale(ktr);
                    color = color.add(il.scale(calcDiffusive(material, nl)), il.scale(calcSpecular(material, n, l, nl, v)));
                }
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

    /**
     * Checks if a point is unshaded by a specific light source.
     * It casts a ray from the point towards the light source and checks if any intersections occur
     * between the point and the light source, indicating shading.
     *
     * @param ls the light source
     * @param gp the geometric intersection point
     * @param l  the direction vector towards the light source
     * @param n  the normal vector at the intersection point
     * @return true if the point is unshaded by the light source, false otherwise
     */
    private boolean unshaded(LightSource ls, GeoPoint gp, Vector l, Vector n) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(lightDirection, gp.point, n);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay, ls.getDistance(lightRay.getP0()));

        if (intersections == null)
            return true;

        for (GeoPoint geoPoint : intersections) {
            if (geoPoint.geometry.getMaterial().kt.equals(Double3.ZERO))
                return false;
        }

        return true;
    }

    /**
     * Calculates the transparency coefficient (ktr) for the given light source and intersection point.
     * It casts a ray from the intersection point towards the light source and checks if any intersections occur
     * between the intersection point and the light source. The product of the transparency coefficients of the
     * objects between the intersection point and the light source is returned.
     *
     * @param ls          the light source
     * @param l           the direction vector towards the light source
     * @param n           the normal vector at the intersection point
     * @param intersection the intersection point
     * @return the transparency coefficient (ktr)
     */
    private Double3 transparency(LightSource ls, Vector l, Vector n, GeoPoint intersection) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(lightDirection, intersection.point, n);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay, ls.getDistance(lightRay.getP0()));

        if (intersections == null)
            return Double3.ONE;

        Double3 ktr = Double3.ONE;
        for (GeoPoint geoPoint : intersections) {
            ktr = ktr.product(geoPoint.geometry.getMaterial().kt);
            if (ktr.lowerThan(MIN_CALC_COLOR_K))
                return Double3.ZERO;
        }
        return ktr;
    }
}
