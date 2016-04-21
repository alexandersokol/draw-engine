package com.sun40.draw.engine.noise;

import com.sun40.draw.engine.noise.limit.ClampLimit;
import com.sun40.draw.engine.noise.limit.Limit;
import com.sun40.draw.engine.noise.normalization.Normalization;
import com.sun40.draw.engine.noise.normalization.SmoothNormalization;

/**
 * Created by Alexander Sokol
 * on 20.04.16.
 */
public final class Octave {

    private float mXTranslate;
    private float mYTranslate;
    private float mXScale;
    private float mYScale;
    private Normalization mNormalization = new SmoothNormalization();
    private Limit mLimit = new ClampLimit(0f, 1f);
    private float mWeight = 1f;

    public Octave(float xTranslate, float yTranslate, float xScale, float yScale, float weight) {
        if (xScale <= 0 || yScale <= 0) {
            throw new IllegalArgumentException("Wrong scale value x:" + xScale + " y:" + yScale);
        }

        mXTranslate = xTranslate;
        mYTranslate = yTranslate;
        mXScale = xScale;
        mYScale = yScale;

        if (weight < 0f) {
            weight = 0f;
        } else if (weight > 1f) {
            weight = 1f;
        }
        mWeight = weight;
    }

    public Octave(float xTranslate, float yTranslate, float xScale, float yScale) {
        this(xTranslate, yTranslate, xScale, yScale, 1f);
    }

    public float weight() {
        return mWeight;
    }

    public void setWeight(float weight) {
        mWeight = weight;
    }

    public Limit limit() {
        return mLimit;
    }

    public void setLimit(Limit limit) {
        mLimit = limit;
    }

    public void setNormalization(Normalization normalization) {
        mNormalization = normalization;
    }

    public Normalization normalization() {
        return mNormalization;
    }

    public float xTranslate() {
        return mXTranslate;
    }

    public float yTranslate() {
        return mYTranslate;
    }

    public float xScale() {
        return mXScale;
    }

    public float yScale() {
        return mYScale;
    }
}
