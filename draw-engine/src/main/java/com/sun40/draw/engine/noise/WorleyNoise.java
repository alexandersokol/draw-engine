package com.sun40.draw.engine.noise;

/**
 * Created by Alexander Sokol
 * on 25.04.16.
 */
public class WorleyNoise implements Noise {

    private static final String TAG = "WorleyNoise";

    private DistanceFunc mDistanceFunc;

    public WorleyNoise() {
        this(new EuclideanDistance());
    }

    public WorleyNoise(DistanceFunc distanceFunc) {
        mDistanceFunc = distanceFunc;
    }

    @Override
    public double noise(double x, double y) {
        return noise(x, y, 0);
    }

    @Override
    public double noise(double x, double y, double z) {

        return 0d;
    }


    public static class EuclideanDistance implements DistanceFunc {

        @Override
        public float distance(float x1, float y1, float z1, float x2, float y2, float z2) {
            return (x1 - x2) * (x1 - x2) +
                    (y1 - y2) * (y1 - y2) +
                    (z1 - z2) * (z1 - z2);
        }
    }


    public static class ManhattanDistance implements DistanceFunc {

        @Override
        public float distance(float x1, float y1, float z1, float x2, float y2, float z2) {
            return Math.abs(x1 - x2) + Math.abs(y1 - y2) + Math.abs(z1 - z2);
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
    }


    public interface DistanceFunc {
        float distance(float x1, float y1, float z1, float x2, float y2, float z2);
    }

    public interface MassDistibution{

    }
}
