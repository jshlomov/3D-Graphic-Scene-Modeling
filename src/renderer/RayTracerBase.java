package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * The RayTracerBase class represents a base class for ray tracing algorithms.
 * It provides a framework for tracing rays and computing the color of intersected objects.
 */
public abstract class RayTracerBase {
    protected Scene scene;

    /**
     * Constructs a RayTracerBase object with the given scene.
     *
     * @param scene the scene to trace rays in
     */
    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }

    /**
     * Traces the given ray in the scene and computes the color of the intersected objects.
     *
     * @param ray the ray to trace
     * @return the computed color of the intersected objects
     */
    public abstract Color traceRay(Ray ray);
}
