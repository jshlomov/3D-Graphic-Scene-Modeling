package renderer;

import geometries.Geometries;
import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.DirectionalLight;
import lighting.PointLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import scene.Scene;

import java.util.List;

import static java.awt.Color.*;

/**
 * mini project 1
 */
public class FullImageTest {

    /**
     * image with many bodies and lights with focus
     */
    @Test
    public void fullImage1() {
        ImageWriter imageWriter = new ImageWriter("fullImageMP1 withFocus", 1080, 1080);

        Scene scene = new Scene("Test scene").setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0.1)))
                .setBackground(new Color(0, 0, 128));

        Camera camera = new Camera(new Point(150, 0, 410), new Vector(-40, 0, -100), new Vector(0, 1, 0)) //
                .setVPSize(200, 200).setVPDistance(300).setApertureRadius(5).setFocalDistance(50);

        List<Point> pl = List.of(new Point(150, 100, -50), new Point(150, -100, -50), new Point(-100, 100, -50),
                new Point(-100, -100, -50), new Point(-100, 100, -40), new Point(-100, -100, -40),
                new Point(-100, 100, 200), new Point(-100, -100, 200), new Point(0, -50, 60),
                new Point(-10, -100, 70), new Point(10, -100, 70), new Point(10, -100, 50),
                new Point(-10, -100, 50), new Point(200, -100, -50), new Point(-200, -100, -50),
                new Point(-200, -100, 200), new Point(200, -100, 200));

        Material mirror = new Material().setKr(0.7).setKd(0.3).setKs(1).setShininess(50);
        Color colMirror = new Color(20, 20, 20);
        Material matBasis = new Material().setKr(0.2).setKd(0.3).setKs(0.3).setShininess(100);
        Color colBasis = new Color(153, 51, 0);
        Color colFloor =  new Color(blue);
        Material matFloor = new Material().setKr(.1).setKd(0.5).setKs(0.5).setShininess(300);
        Material matSmallSphere = new Material().setKr(0.05).setKd(0.1).setKs(1).setShininess(100);
        Material sphereMirror = new Material().setKr(0.4).setKd(0.3).setKs(1).setShininess(50);

        scene.geometries.add(
                // back mirror
                new Geometries(new Triangle(pl.get(0), pl.get(1), pl.get(2)).setMaterial(mirror).setEmission(colMirror),
                        new Triangle(pl.get(1), pl.get(2), pl.get(3)).setMaterial(mirror).setEmission(colMirror)),

                // left mirror
                new Geometries(new Triangle(pl.get(4), pl.get(6), pl.get(7)).setMaterial(mirror).setEmission(colMirror),
                        new Triangle(pl.get(7), pl.get(5), pl.get(4)).setMaterial(mirror).setEmission(colMirror)),

                // floor
                new Geometries(
                        new Triangle(pl.get(13), pl.get(14), pl.get(15)).setEmission(colFloor).setMaterial(matFloor),
                        new Triangle(pl.get(13), pl.get(15), pl.get(16)).setEmission(colFloor).setMaterial(matFloor)),

                // floor spheres
                new Geometries(
                        // right sphere
                        new Geometries(
                                new Sphere(new Point(90, -93, 21), 7).setMaterial(matSmallSphere)
                                        .setEmission(new Color(0, 0, 0)),
                                new Sphere(new Point(102, -93, 93), 7).setMaterial(matSmallSphere)
                                        .setEmission(new Color(34, 177, 76)),
                                new Sphere(new Point(73, -93, 120), 7).setMaterial(matSmallSphere)
                                        .setEmission(new Color(128, 0, 64)),
                                new Sphere(new Point(54, -93, 95), 7).setMaterial(matSmallSphere)
                                        .setEmission(new Color(255, 50, 128))),
                        // center sphere
                        new Geometries(
                                new Sphere(new Point(-15, -93, 80), 7).setMaterial(matSmallSphere)
                                        .setEmission(new Color(0, 0, 255)),
                                new Sphere(new Point(20, -93, 32), 7).setMaterial(matSmallSphere)
                                        .setEmission(new Color(0, 255, 255)),
                                new Sphere(new Point(-20, -93, 40), 7).setMaterial(matSmallSphere)
                                        .setEmission(new Color(255, 0, 0)),
                                new Sphere(new Point(-23, -93, 90), 7).setMaterial(matSmallSphere)
                                        .setEmission(new Color(200, 255, 30)),
                                new Sphere(new Point(8, -93, 23), 7).setMaterial(matSmallSphere)
                                        .setEmission(new Color(0, 255, 0)),
                                new Sphere(new Point(24, -93, 104), 7).setMaterial(matSmallSphere)
                                        .setEmission(new Color(200, 180, 98))),
                        // left spheres
                        new Geometries(
                                new Sphere(new Point(-70, -93, 105), 7).setMaterial(matSmallSphere)
                                        .setEmission(new Color(200, 167, 30)),
                                new Sphere(new Point(-82, -93, 94), 7).setMaterial(matSmallSphere)
                                        .setEmission(new Color(20, 154, 76)))));

        scene.lights.add(new DirectionalLight(new Color(300, 300, 300), new Vector(-10, -5, -10)));
        scene.lights.add(new PointLight(new Color(300, 200, 100), new Point(-50, 100, 60)).setKl(4E-2).setKq(2E-8));
        scene.lights.add(new SpotLight(new Color(800, 400, 400), new Point(80, -60, 70), new Vector(-5, -2, -1)) //
                .setNarrowBeam(4).setKl(0.001).setKq(0.0000025));

        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerSuperSampling(scene, camera, 17))
//                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }

}
