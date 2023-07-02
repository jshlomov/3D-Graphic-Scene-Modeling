package renderer;

import primitives.Point;
import primitives.Util;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Class for create BlackBoard of points
 */
public abstract class BlackBoard {

    /**
     * constructor list of points to create circle black board
     * by get size (x/y), 3D point and direction vectors, and radius of circle
     * the given center Point always return in the list.
     *
     *  @param xy number of points in x and y. NEED TO BE over than 1 (1 return only the given center point)
     *  @param center point to create points around
     *  @param vUp vUp vector
     *  @param vRight vRight vector
     *  @param radius of black board (radius 0.0 return only the center point)
     *  @return list of all points
     */
    public static List<Point> constructBlackBorad(int xy, Point center, Vector vUp, Vector vRight, double radius) {

        if (xy < 1)
            throw new IllegalArgumentException("xy must be over than 0");

        if ((xy == 1) || (radius == 0.0))
            return List.of(center);

        List<Point> pointList = new LinkedList<>();
        pointList.add(center);

        double rectSize = 2 * radius;

        double rxy = Util.alignZero(rectSize / xy);
        Random random = new Random();

        for (int i = 0; i < xy; i++) {
            for (int j = 0; j < xy; j++) {
                double moveUpDown = random.nextDouble(-(rxy/2),(rxy/2));
                double moveSide = random.nextDouble(-(rxy/2),(rxy/2));

                double yI = Util.alignZero(((-(i - (xy - 1) / 2.0)) * rxy) + moveSide);
                double xJ = Util.alignZero(((j - (xy - 1) / 2.0) * rxy) + moveUpDown);

                Point pIJ = center;

                if (xJ != 0) pIJ = pIJ.add(vRight.scale(xJ));
                if (yI != 0) pIJ = pIJ.add(vUp.scale(yI));

                pointList.add(pIJ);
            }
        }

        return pointList;
    }
}
