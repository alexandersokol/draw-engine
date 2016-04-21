package com.sun40.draw.engine.noise.limit;

/**
 * Created by Alexander Sokol
 * on 21.04.16.
 */
public abstract class Limit {

    private float mMin;
    private float mMax;

    public Limit(float min, float max) {
        mMin = min;
        mMax = max;
    }

    public final float min() {
        return mMin;
    }

    public final float max() {
        return mMax;
    }

    public abstract float lim(float input);

}
