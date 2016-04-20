package com.sun40.draw.engine.noise.normalization;

/**
 * Created by Alexander Sokol
 * on 20.04.16.
 */
public class SmoothNormalization implements Normalization {

    private final float mMinClamp;
    private final float mMaxClamp;

    public SmoothNormalization(float minClamp, float maxClamp) {
        mMinClamp = minClamp;
        mMaxClamp = maxClamp;
    }

    public SmoothNormalization() {
        mMinClamp = 0f;
        mMaxClamp = 1f;
    }

    @Override
    public float normalize(float value) {
        value = (value + 1) / 2;
        if (value < mMinClamp) {
            return mMinClamp;
        } else if (value > mMaxClamp)
            return mMaxClamp;

        return value;
    }
}
