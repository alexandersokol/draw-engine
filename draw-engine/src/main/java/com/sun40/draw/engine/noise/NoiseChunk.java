package com.sun40.draw.engine.noise;

/**
 * Created by Alexander Sokol
 * on 20.04.16.
 */
public class NoiseChunk {

    private int mWidth;
    private int mHeight;

    private float[][] mNoiseData;

    public NoiseChunk(int width, int height, OctaveModel octaveModel, Noise noise) {
        mWidth = width;
        mHeight = height;

        if (width < 1 || height < 1) {
            throw new IllegalArgumentException("NoiseChunk has wrong size: " + width + "x" + height);
        }

        mNoiseData = new float[width][height];
        for (int x = 0; x < mNoiseData.length; x++) {
            for (int y = 0; y < mNoiseData[x].length; y++) {
                float xx = (x * octaveModel.xScale() / 256.0f) + octaveModel.getXTranslate();
                float yy = (y * octaveModel.yScale() / 256.0f) + octaveModel.getYTranslate();
                mNoiseData[x][y] = octaveModel.getNormalization().normalize((float) noise.noise(xx, yy));
            }
        }
    }

    public float[][] data() {
        return mNoiseData;
    }


    public float[] flatData() {
        float[] flat = new float[mWidth * mHeight];
        for (int i = 0; i < mHeight * mWidth; i++) {
            int y = (i / mWidth) % mHeight;
            int x = (i % mWidth);
            flat[i] = mNoiseData[x][y];
        }
        return flat;
    }
}
