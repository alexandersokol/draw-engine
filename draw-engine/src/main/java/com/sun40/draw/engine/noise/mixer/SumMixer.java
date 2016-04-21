package com.sun40.draw.engine.noise.mixer;

import com.sun40.draw.engine.noise.limit.Limit;

/**
 * Created by Alexander Sokol
 * on 21.04.16.
 */
public class SumMixer extends Mixer {

    public SumMixer(Limit limit) {
        super(limit);
    }

    @Override
    public float mix(float[] noises) {
        float sum = 0f;

        for (float value : noises) {
            sum += value;
        }

        return limit().lim(sum);
    }
}
