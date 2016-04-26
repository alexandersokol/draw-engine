package com.sun40.draw.engine.noise;

import java.util.Random;

/**
 * Created by Alexander Sokol
 * on 25.04.16.
 */
public class WorleyNoise implements Noise {

    private static final String TAG = "WorleyNoise";

    private static final int MAX_FUTURES_PER_CELL = 10;
    private static final int SEED = 5321;

    private DistanceFunc mDistanceFunc;
    private MassDistribution mMassDistribution;
    private int mSeed;
    private int mMaxFuturesPerCell;

    public WorleyNoise() {
        this(new EuclideanDistance(), new RandomDistribution());
    }

    public WorleyNoise(DistanceFunc distanceFunc) {
        this(distanceFunc, new RandomDistribution());
    }

    public WorleyNoise(MassDistribution massDistribution) {
        this(new EuclideanDistance(), massDistribution);
    }

    public WorleyNoise(DistanceFunc distanceFunc, MassDistribution massDistribution) {
        this(SEED, MAX_FUTURES_PER_CELL, distanceFunc, massDistribution);
    }

    public WorleyNoise(int seed, int maxFuturesPerCell, DistanceFunc distanceFunc, MassDistribution massDistribution) {
        mSeed = seed;
        mMaxFuturesPerCell = maxFuturesPerCell;
        mDistanceFunc = distanceFunc;
        mMassDistribution = massDistribution;
    }

    @Override
    public double noise(double x, double y) {

        int evalCubeX = (int) Math.floor(x);
        int evalCubeY = (int) Math.floor(y);

        float shortestDistance = Float.MAX_VALUE;

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                int cubeX = evalCubeX + i;
                int cubeY = evalCubeY + j;

                int futurePointsCount = mMassDistribution.futureCount(mSeed,
                        cubeX, cubeY, mMaxFuturesPerCell);

                for (int p = 0; p < futurePointsCount; p++) {
                    float[] point = mMassDistribution.distribute(mSeed, p, cubeX, cubeY);
                    float diffX = point[0] + cubeX;
                    float diffY = point[1] + cubeY;
                    float distance = mDistanceFunc.distance((float) x, (float) y, diffX, diffY);
                    if (distance < shortestDistance) {
                        shortestDistance = distance;
                    }
                }
            }
        }

        return shortestDistance;
    }


    @Override
    public double noise(double x, double y, double z) {

        int evalCubeX = (int) Math.floor(x);
        int evalCubeY = (int) Math.floor(y);
        int evalCubeZ = (int) Math.floor(z);

        float shortestDistance = Float.MAX_VALUE;

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                for (int k = -1; k < 2; k++) {
                    int cubeX = evalCubeX + i;
                    int cubeY = evalCubeY + j;
                    int cubeZ = evalCubeZ + k;

                    int futurePointsCount = mMassDistribution.futureCount(mSeed,
                            cubeX, cubeY, cubeZ, mMaxFuturesPerCell);

                    for (int p = 0; p < futurePointsCount; p++) {
                        float[] point = mMassDistribution.distribute(mSeed, p, cubeX, cubeY, cubeZ);
                        float diffX = point[0] + cubeX;
                        float diffY = point[1] + cubeY;
                        float diffZ = point[2] + cubeZ;
                        float distance = mDistanceFunc.distance((float) x, (float) y, (float) z,
                                diffX, diffY, diffZ);
                        if (distance < shortestDistance) {
                            shortestDistance = distance;
                        }
                    }
                }
            }
        }

        return shortestDistance;
    }


    public static class EuclideanDistance implements DistanceFunc {

        @Override
        public float distance(float x1, float y1, float z1, float x2, float y2, float z2) {
            return (x1 - x2) * (x1 - x2) +
                    (y1 - y2) * (y1 - y2) +
                    (z1 - z2) * (z1 - z2);
        }

        @Override
        public float distance(float x1, float y1, float x2, float y2) {
            return (x1 - x2) * (x1 - x2) +
                    (y1 - y2) * (y1 - y2);
        }
    }


    public static class ManhattanDistance implements DistanceFunc {

        @Override
        public float distance(float x1, float y1, float z1, float x2, float y2, float z2) {
            return Math.abs(x1 - x2) + Math.abs(y1 - y2) + Math.abs(z1 - z2);
        }

        @Override
        public float distance(float x1, float y1, float x2, float y2) {
            return Math.abs(x1 - x2) + Math.abs(y1 - y2);
        }
    }


    public static class ChebyshevDistance implements DistanceFunc {

        @Override
        public float distance(float x1, float y1, float z1, float x2, float y2, float z2) {
            float x = Math.abs(x1 - x2);
            float y = Math.abs(y1 - y2);
            float z = Math.abs(z1 - z2);

            return Math.max(Math.max(x, y), z);
        }

        @Override
        public float distance(float x1, float y1, float x2, float y2) {
            float x = Math.abs(x1 - x2);
            float y = Math.abs(y1 - y2);

            return Math.max(x, y);
        }
    }

    public static class LinearDistance implements DistanceFunc {

        @Override
        public float distance(float x1, float y1, float z1, float x2, float y2, float z2) {
            float a = x1 - x2;
            float b = y1 - y2;
            return (float) Math.sqrt(a * a + b * b);
        }

        @Override
        public float distance(float x1, float y1, float x2, float y2) {
            float a = x1 - x2;
            float b = y1 - y2;
            return (float) Math.sqrt(a * a + b * b);
        }
    }


    public static class QuadraticDistance implements DistanceFunc {

        @Override
        public float distance(float x1, float y1, float z1, float x2, float y2, float z2) {
            float a = x1 - x2;
            float b = y1 - y2;
            return a * a + a * b + b * b;
        }

        @Override
        public float distance(float x1, float y1, float x2, float y2) {
            float a = x1 - x2;
            float b = y1 - y2;
            return a * a + a * b + b * b;
        }
    }

    public static class MinkowskiDistance implements DistanceFunc {

        private int mValue;

        public MinkowskiDistance() {
            this(3);
        }

        public MinkowskiDistance(int value) {
            mValue = value;
        }

        @Override
        public float distance(float x1, float y1, float z1, float x2, float y2, float z2) {
            float a = x1 - x2;
            float b = y1 - y2;
            float c = z1 - z2;

            double pa = Math.pow(Math.abs(a), mValue);
            double pb = Math.pow(Math.abs(b), mValue);
            double pc = Math.pow(Math.abs(c), mValue);

            return (float) Math.pow(pa + pb + pc, 1d / mValue);
        }


        @Override
        public float distance(float x1, float y1, float x2, float y2) {
            float a = x1 - x2;
            float b = y1 - y2;

            double pa = Math.pow(Math.abs(a), mValue);
            double pb = Math.pow(Math.abs(b), mValue);

            return (float) Math.pow(pa + pb, 1d / mValue);
        }
    }


    public static class RandomDistribution implements MassDistribution {

        Random random = new Random();


        @Override
        public int futureCount(long seed, int cubeX, int cubeY, int cubeZ, int max) {
            long hash = (cubeX * seed) + (cubeY * seed) + (cubeZ * seed);
            random.setSeed(hash);
            return random.nextInt(max) + 1;
        }

        @Override
        public int futureCount(long seed, int cubeX, int cubeY, int max) {
            long hash = (cubeX * seed) + (cubeY * seed);
            random.setSeed(hash);
            return random.nextInt(max) + 1;
        }


        @Override
        public float[] distribute(int seed, int hop, int cubeX, int cubeY, int cubeZ) {
            int seedX = seed * cubeX + hop;
            int seedY = seed * cubeY + hop;
            int seedZ = seed * cubeZ + hop;

            if (seedX == 0)
                seedX = seed + seedX + hop;

            if (seedY == 0)
                seedY = seed + seedY + hop;

            if (seedZ == 0)
                seedZ = seed + seedZ + hop;

            random.setSeed(seedX);
            float x = random.nextInt(100001) / 100000f;

            random.setSeed(seedY);
            float y = random.nextInt(100001) / 100000f;

            random.setSeed(seedZ);
            float z = random.nextInt(100001) / 100000f;


            return new float[]{x, y, z};
        }

        @Override
        public float[] distribute(int seed, int hop, int cubeX, int cubeY) {
            int seedX = seed * cubeX + hop;
            int seedY = seed * cubeY + hop;

            if (seedX == 0)
                seedX = seed + seedX + hop;

            if (seedY == 0)
                seedY = seed + seedY + hop;

            random.setSeed(seedX);
            float x = random.nextInt(100001) / 100000f;

            random.setSeed(seedY);
            float y = random.nextInt(100001) / 100000f;

            return new float[]{x, y};
        }
    }


    public interface DistanceFunc {
        float distance(float x1, float y1, float z1, float x2, float y2, float z2);

        float distance(float x1, float y1, float x2, float y2);
    }


    public interface MassDistribution {

        int futureCount(long seed, int cubeX, int cubeY, int cubeZ, int max);

        int futureCount(long seed, int cubeX, int cubeY, int max);

        float[] distribute(int seed, int hop, int cubeX, int cubeY, int cubeZ);

        float[] distribute(int seed, int hop, int cubeX, int cubeY);
    }
}
