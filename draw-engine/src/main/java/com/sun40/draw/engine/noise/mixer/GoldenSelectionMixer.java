package com.sun40.draw.engine.noise.mixer;

import com.sun40.draw.engine.noise.limit.Limit;

/**
 * Created by Alexander Sokol
 * on 21.04.16.
 */
public class GoldenSelectionMixer extends Mixer {

    private static final float PROPORTION = 1.61803398874989f;
    private static final float ERROR = 0.002f;

    public GoldenSelectionMixer(Limit limit) {
        super(limit);
    }

    @Override
    public float mix(float[] noises) {
        float value = Float.MIN_VALUE;
        for (float noise : noises) {
            if (value == Float.MIN_VALUE) {
                value = noise;
            } else {
                value = golden(value, noise);
            }
        }
        return value;
    }

    private float golden(float a, float b) {
        float x1 = b - ((b - a) / PROPORTION);
        float x2 = a + ((b - a) / PROPORTION);
        if (Math.abs(x1 - x2) < ERROR) {
            return (a + b) / 2f;
        } else {
            return golden(x1, x2);
        }
    }
}
