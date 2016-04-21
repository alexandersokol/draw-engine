package com.sun40.draw.engine.noise.mixer;

import com.sun40.draw.engine.noise.limit.Limit;

/**
 * Created by Alexander Sokol
 * on 21.04.16.
 */
public class MinMaxDiffMixer extends Mixer {

    public MinMaxDiffMixer(Limit limit) {
        super(limit);
    }

    @Override
    public float mix(float[] noises) {

        float min = Float.MAX_VALUE;
        float max = Float.MIN_VALUE;

        for (float value : noises) {
            min = Math.min(value, min);
            max = Math.max(value, max);
        }

        return max - min;
    }
}
