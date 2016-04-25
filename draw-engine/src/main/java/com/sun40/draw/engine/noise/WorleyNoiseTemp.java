package com.sun40.draw.engine.noise;

import android.util.Log;

import com.sun40.draw.primitive.PointF3D;

/**
 * Created by Alexander Sokol
 * on 25.04.16.
 */
public class WorleyNoiseTemp implements Noise {

    private static final String TAG = "WorleyNoise";

    private DistanceFunc mDistanceFunc;
    private int seed = 3221;
    long OFFSET_BASIS = 2166136261L;
    int FNV_PRIME = 16777619;
    float[] distanceArray = new float[3];

    public WorleyNoiseTemp(DistanceFunc distanceFunc) {
        mDistanceFunc = distanceFunc;
    }

    @Override
    public double noise(double x, double y) {
        return noise(x, y, 0);
    }

    @Override
    public double noise(double x, double y, double z) {

        PointF3D input = new PointF3D(x, y, z);

        float value = 0f;

        long lastRandom;
        long numberFeaturePoints;
        PointF3D randomDiff = new PointF3D();
        randomDiff.x = 0;
        randomDiff.y = 0;
        randomDiff.z = 0;
        PointF3D featurePoint = new PointF3D();
        featurePoint.x = 0;
        featurePoint.y = 0;
        featurePoint.z = 0;
        int cubeX, cubeY, cubeZ;

        for (int i = 0; i < distanceArray.length; i++) {
            distanceArray[i] = 5555;
        }

        int evalCubeX = (int) (Math.floor(input.x));
        int evalCubeY = (int) (Math.floor(input.y));
        int evalCubeZ = (int) (Math.floor(input.z));

        for (int i = -1; i < 2; ++i) {
            for (int j = -1; j < 2; ++j) {
                for (int k = -1; k < 2; ++k) {
                    cubeX = evalCubeX + i;
                    cubeY = evalCubeY + j;
                    cubeZ = evalCubeZ + k;

                    lastRandom = lcgRandom(hash((cubeX + seed), (cubeY), (cubeZ)));
                    numberFeaturePoints = probLookup(lastRandom);

                    for (int l = 0; l < numberFeaturePoints; ++l) {
                        lastRandom = lcgRandom(lastRandom);
                        randomDiff.x = lastRandom / 0x100000000L;

                        lastRandom = lcgRandom(lastRandom);
                        randomDiff.y = lastRandom / 0x100000000L;

                        lastRandom = lcgRandom(lastRandom);
                        randomDiff.y = lastRandom / 0x100000000L;

                        featurePoint.x = randomDiff.x + cubeX;
                        featurePoint.y = randomDiff.y + cubeY;
                        featurePoint.z = randomDiff.z + cubeZ;


                        //5. Find the feature point closest to the evaluation point.
                        //This is done by inserting the distances to the feature points into a sorted list
                        float v = mDistanceFunc.distance(input, featurePoint);

                        insert(distanceArray, v);
                    }
                }
            }
        }

        float color = combinerFunction1(distanceArray);
        if (color < 0) color = 0;
        if (color > 1) color = 1;

        return color;
    }


    float combinerFunction1(float ar[]) {
        return ar[0];
    }

    float combinerFunction2(float ar[]) {
        return ar[1] - ar[0];
    }

    float combinerFunction3(float ar[]) {
        return ar[2] - ar[0];
    }


    void insert(float ar[], float value) {
        float temp;

        for (int i = ar.length - 1; i >= 0; i--) {
            if (value > ar[i]) break;
            temp = ar[i];
            ar[i] = value;
            if (i + 1 < ar.length)
                ar[i + 1] = temp;
        }
    }

    long lcgRandom(long lastValue) {
        return  (((1103515245) * lastValue + (12345)) % 0x100000000L);
    }

    long hash(long i, long j, long k) {
        return ((((((OFFSET_BASIS ^ (i)) * FNV_PRIME) ^ (j)) * FNV_PRIME)
                ^ (k)) * FNV_PRIME);

    }

    long probLookup(long value) {
        if (value < 393325350L) return 1;
        if (value < 1022645910L) return 2;
        if (value < 1861739990L) return 3;
        if (value < 2700834071L) return 4;
        if (value < 3372109335L) return 5;
        if (value < 3819626178L) return 6;
        if (value < 4075350088L) return 7;
        if (value < 4203212043L) return 8;
        return 9;
    }

    public static class EuclidianDistance implements DistanceFunc {

        @Override
        public float distance(PointF3D p1, PointF3D p2) {
            return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y)
                    + (p1.z - p2.z) * (p1.z - p2.z);
        }
    }

    public static class ManhattanDistance implements DistanceFunc {

        @Override
        public float distance(PointF3D p1, PointF3D p2) {
            return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y) + Math.abs(p1.z - p2.z);
        }
    }

    public static class ChebyshevDistance implements DistanceFunc {

        @Override
        public float distance(PointF3D p1, PointF3D p2) {

            PointF3D diff = new PointF3D();
            diff.x = Math.abs(p1.x - p2.x);
            diff.y = Math.abs(p1.y - p2.y);
            diff.z = Math.abs(p1.z = p2.z);

            return Math.max(Math.max(diff.x, diff.y), diff.z);
        }
    }

    public interface DistanceFunc {
        float distance(PointF3D p1, PointF3D p2);
    }
}
