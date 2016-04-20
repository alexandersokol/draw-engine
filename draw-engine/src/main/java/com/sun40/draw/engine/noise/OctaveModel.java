package com.sun40.draw.engine.noise;

import com.sun40.draw.engine.noise.normalization.Normalization;
import com.sun40.draw.engine.noise.normalization.SmoothNormalization;

/**
 * Created by Alexander Sokol
 * on 20.04.16.
 */
public class OctaveModel {

    private float mXTranslate;
    private float mYTranslate;
    private float mXScale;
    private float mYScale;
    private Normalization mNormalization = new SmoothNormalization();

    public OctaveModel(float xTranslate, float yTranslate, float xScale, float yScale) {
        if (xScale <= 0 || yScale <= 0) {
            throw new IllegalArgumentException("Wrong scale value x:" + xScale + " y:" + yScale);
        }

        mXTranslate = xTranslate;
        mYTranslate = yTranslate;
        mXScale = xScale;
        mYScale = yScale;
    }

    public void setNormalization(Normalization normalization) {
        mNormalization = normalization;
    }

    public Normalization getNormalization() {
        return mNormalization;
    }

    public float getXTranslate() {
        return mXTranslate;
    }

    public float getYTranslate() {
        return mYTranslate;
    }

    public float xScale() {
        return mXScale;
    }

    public float yScale() {
        return mYScale;
    }
}
