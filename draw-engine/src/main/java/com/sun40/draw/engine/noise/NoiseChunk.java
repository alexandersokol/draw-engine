package com.sun40.draw.engine.noise;

/**
 * Created by Alexander Sokol
 * on 20.04.16.
 */
public class NoiseChunk {

    private int mWidth;
    private int mHeight;

    private float[][] mNoiseData;

    public NoiseChunk(int width, int height, Octave octave, Noise noise) {
        mWidth = width;
        mHeight = height;

        if (width < 1 || height < 1) {
            throw new IllegalArgumentException("NoiseChunk has wrong size: " + width + "x" + height);
        }

        mNoiseData = new float[width][height];
        for (int x = 0; x < mNoiseData.length; x++) {
            for (int y = 0; y < mNoiseData[x].length; y++) {
                float xx = (x * octave.xScale() / 256.0f) + octave.xTranslate();
                float yy = (y * octave.yScale() / 256.0f) + octave.yTranslate();
                float value = (float) noise.noise(xx, yy);
                value = octave.normalization().normalize(value);
                value = octave.limit().lim(value);

                mNoiseData[x][y] = value;
            }
        }
    }


    public NoiseChunk(int width, int height, float[][] data) {
        mWidth = width;
        mHeight = height;

        if (width < 1 || height < 1) {
            throw new IllegalArgumentException("NoiseChunk has wrong size: " + width + "x" + height);
        }

        mNoiseData = data;
    }

    public int width() {
        return mWidth;
    }

    public int height() {
        return mHeight;
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
