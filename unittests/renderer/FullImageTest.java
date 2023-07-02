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
        ImageWriter imageWriter = new ImageWriter("fullImageMP1", 1080, 1080);

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
        Color colFloor =  new Color(blue);
        Material matFloor = new Material().setKr(.1).setKd(0.5).setKs(0.5).setShininess(300);
        Material matSmallSphere = new Material().setKr(0.05).setKd(0.1).setKs(1).setShininess(100);

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
                .setRayTracer(new RayTracerSuperSampling(scene, camera, 4).setAdaptiveSuperSampling(true))
//                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImageWithThreads(3,0.01) //
//                .renderImage()
                .writeToImage();
    }

    /**
     * image with many bodies and lights with focus 2
     */
    @Test
    public void fullImage2() {
        ImageWriter imageWriter = new ImageWriter("fullImageMP2 Without Focus", 1080, 1080);

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

                //basis
                new Geometries(
                        new Triangle(pl.get(8), pl.get(9), pl.get(10)).setMaterial(matBasis).setEmission(colBasis),
                        new Triangle(pl.get(8), pl.get(10), pl.get(11)).setMaterial(matBasis).setEmission(colBasis),
                        new Triangle(pl.get(8), pl.get(11), pl.get(12)).setMaterial(matBasis).setEmission(colBasis),
                        new Triangle(pl.get(8), pl.get(12), pl.get(9)).setMaterial(matBasis).setEmission(colBasis)),

                // Lottery sphere
                new Geometries(
                        new Sphere(new Point(0, 0, 60), 50).setEmission(new Color(30, 30, 30)).setMaterial(
                                new Material().setKt(0.7).setKr(0.1).setKd(0.2).setKs(0.3).setShininess(50)),
                        new Sphere(new Point(0, -41, 60), 7).setMaterial(matSmallSphere)
                                .setEmission(new Color(255, 128, 0)),
                        new Sphere(new Point(10, -37, 70), 7).setMaterial(matSmallSphere)
                                .setEmission(new Color(0, 255, 255)),
                        new Sphere(new Point(-20, 23, 27), 7).setMaterial(matSmallSphere)
                                .setEmission(new Color(255, 0, 0)),
                        new Sphere(new Point(15, 10, 90), 7).setMaterial(matSmallSphere)
                                .setEmission(new Color(200, 255, 30)),
                        new Sphere(new Point(10, 40, 52), 7).setMaterial(matSmallSphere)
                                .setEmission(new Color(0, 255, 0)),
                        new Sphere(new Point(-30, -30, 70), 7).setMaterial(matSmallSphere)
                                .setEmission(new Color(128, 255, 128)),
                        new Sphere(new Point(20, -14, 92), 7).setMaterial(matSmallSphere)
                                .setEmission(new Color(255, 0, 128)),
                        new Sphere(new Point(30, 3, 100), 7).setMaterial(matSmallSphere)
                                .setEmission(new Color(128, 0, 64)),
                        new Sphere(new Point(-24, 23, 40), 7).setMaterial(matSmallSphere)
                                .setEmission(new Color(64, 128, 128)),
                        new Sphere(new Point(0, -10, 90), 7).setMaterial(sphereMirror)
                                .setEmission(new Color(40, 40, 40))),

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
//                .setRayTracer(new RayTracerSuperSampling(scene, camera, 4).setAdaptiveSuperSampling(false))
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImageWithThreads(3,0.01) //
//                .renderImage()
                .writeToImage();
    }
}
