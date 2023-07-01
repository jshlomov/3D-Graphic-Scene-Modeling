/**
 *
 */
package renderer;

import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.PointLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import scene.Scene;

import java.util.List;

import static java.awt.Color.*;

/** Tests for reflection and transparency functionality, test for partial
 * shadows
 * (with transparency)
 * @author dzilb */
public class ReflectionRefractionTests {
    private final Scene scene = new Scene("Test scene");

    /** Produce a picture of a sphere lighted by a spotlight */
    @Test
    public void twoSpheres() {
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(150, 150).setVPDistance(1000);

        scene.geometries.add( //
                new Sphere(new Point(0, 0, -50), 50d).setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)),
                new Sphere(new Point(0, 0, -50), 25d).setEmission(new Color(RED)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)));
        scene.lights.add( //
                new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2)) //
                        .setKl(0.0004).setKq(0.0000006));

        camera.setImageWriter(new ImageWriter("refractionTwoSpheres", 500, 500)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }

    /** Produce a picture of a sphere lighted by a spotlight */
    @Test
    public void twoSpheresOnMirrors() {
        Camera camera = new Camera(new Point(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(2500, 2500).setVPDistance(10000); //

        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

        scene.geometries.add( //
                new Sphere(new Point(-950, -900, -1000), 400d).setEmission(new Color(0, 50, 100)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)
                                .setKt(new Double3(0.5, 0, 0))),
                new Sphere(new Point(-950, -900, -1000), 200d).setEmission(new Color(100, 50, 20)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),
                new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
                        new Point(670, 670, 3000)) //
                        .setEmission(new Color(20, 20, 20)) //
                        .setMaterial(new Material().setKr(1)),
                new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
                        new Point(-1500, -1500, -2000)) //
                        .setEmission(new Color(20, 20, 20)) //
                        .setMaterial(new Material().setKr(new Double3(0.5, 0, 0.4))));

        scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point(-750, -750, -150), new Vector(-1, -1, -4)) //
                .setKl(0.00001).setKq(0.000005));

        ImageWriter imageWriter = new ImageWriter("reflectionTwoSpheresMirrored", 500, 500);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }

    /** Produce a picture of two triangles lighted by a spotlight with a
     * partially
     * transparent Sphere producing partial shadow */
    @Test
    public void trianglesTransparentSphere() {
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(200, 200).setVPDistance(1000);

        scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));

        scene.geometries.add( //
                new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135),
                        new Point(75, 75, -150)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)), //
                new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)), //
                new Sphere(new Point(60, 50, -50), 30d).setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)));

        scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1)) //
                .setKl(4E-5).setKq(2E-7));

        ImageWriter imageWriter = new ImageWriter("refractionShadow", 600, 600);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }

    /**
     * presents transparency with two spheres
     */
    @Test
    public void twoSpheresAndTwoTriangles() {
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(150, 150).setVPDistance(1000);

        scene.geometries.add( //
                new Sphere(new Point(0, 0, -50), 50d).setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)),
                new Sphere(new Point(0, 0, -50), 25d).setEmission(new Color(RED)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)));
        scene.lights.add( //
                new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2)) //
                        .setKl(0.0004).setKq(0.0000006));

        camera.setImageWriter(new ImageWriter("refractionTwoSpheres", 500, 500)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }

    /**
     * test with few shapes and two lights present reflection and transparency
     */
    @Test
    public void fourShapesWithAllEffects() {
        Camera camera = new Camera(new Point(8,8,4), new Vector(-1,-1,-0.4), new Vector(-1,0,2.5))
                .setVPSize(3.2,3).setVPDistance(5).setFocalDistance(10).setApertureRadius(8);
        Scene scene1 = new Scene("new test");

        scene1.geometries.add(
                new Triangle(new Point(2,-2,0), new Point(-2,2,0), new Point(-2,2,2)).setEmission(new Color(50,50,50))
                        .setMaterial(new Material().setKr(1)),
                new Triangle(new Point(2,-2,0), new Point(-2,2,2), new Point(2,-2,2)).setEmission(new Color(50,50,50))
                        .setMaterial(new Material().setKr(1)),
                new Triangle(new Point(2,-2,0), new Point(-2,2,0), new Point(3,3,0)).setEmission(new Color(20,20,20))
                        .setMaterial(new Material().setKd(0.5).setKs(0.2).setShininess(100)),
                new Sphere(new Point(1.5,0.5,1), 0.5).setEmission(new Color(BLUE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.1).setShininess(100)),
                new Sphere(new Point(2,-0.75,0.5), 0.3).setEmission(new Color(YELLOW))
                        .setMaterial(new Material().setKr(1)),
                new Triangle(new Point(2,2,1), new Point(3,1,1), new Point(2,2,2)).setEmission(new Color(GREEN))
                        .setMaterial(new Material().setKd(0.00001).setKs(0.0000002).setShininess(30).setKt(0.6)),
                new Triangle(new Point(3,1,1), new Point(2,2,2), new Point(3,1,2)).setEmission(new Color(GREEN))
                        .setMaterial(new Material().setKd(0.00001).setKs(0.0000002).setShininess(30).setKt(0.6))
        );

        scene1.lights.addAll(List.of(new PointLight(new Color(500,400,400), new Point(20,4,20)).setKl(0.00001).setKq(0.000006),
                new SpotLight(new Color(700, 400, 400), new Point(-20,2,5), new Vector(1,0,0)).setKl(0.0000001).setKq(0.000005)

                ));

        camera.setImageWriter(new ImageWriter("exe7 test5", 500, 500)) //
                .setRayTracer(new RayTracerSuperSampling(scene1,camera,9)) //
                .renderImage() //
                .writeToImage();
    }

    /**
     * test with few shapes and two lights present reflection and transparency
     */
    @Test
    public void fourShapesWithAllEffects1() {
        Camera camera = new Camera(new Point(10,3.5,3), new Vector(-3,-1,-1), new Vector(-1,0,3))
                .setVPSize(3,3).setVPDistance(6);

        Scene scene1 = new Scene("new test");

        scene1.geometries.add(
                new Triangle(new Point(2,-2,0), new Point(-2,2,0), new Point(0,0,2)).setEmission(new Color(10,10,10))
                        .setMaterial(new Material().setKr(1)),
                new Triangle(new Point(2,-2,0), new Point(-2,2,0), new Point(3,3,0)).setEmission(new Color(20,20,20))
                        .setMaterial(new Material().setKd(0.5).setKs(0.2).setShininess(100)),
                new Sphere(new Point(1.5,0.5,1), 0.5).setEmission(new Color(BLUE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.1).setShininess(100)),
                new Sphere(new Point(2,-0.75,0.5), 0.3).setEmission(new Color(YELLOW))
                        .setMaterial(new Material().setKr(1)),
                new Triangle(new Point(2,2,1), new Point(3,1,1), new Point(2,2,2)).setEmission(new Color(GREEN))
                        .setMaterial(new Material().setKd(0.00001).setKs(0.0000002).setShininess(30).setKt(0.6)),
                new Triangle(new Point(3,1,1), new Point(2,2,2), new Point(3,1,2)).setEmission(new Color(GREEN))
                        .setMaterial(new Material().setKd(0.00001).setKs(0.0000002).setShininess(30).setKt(0.6))
        );

        scene1.lights.addAll(List.of(new PointLight(new Color(500,400,400), new Point(20,4,20)).setKl(0.00001).setKq(0.000006),
                new SpotLight(new Color(700, 400, 400), new Point(-20,2,5), new Vector(1,0,0)).setKl(0.0000001).setKq(0.000005)

        ));

        camera.setImageWriter(new ImageWriter("exe7 test1", 500, 500)) //
                .setRayTracer(new RayTracerBasic(scene1)) //
                .renderImage() //
                .writeToImage();
    }
}
