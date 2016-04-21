package com.sun40.draw.engine.noise.mixer;

import com.sun40.draw.engine.noise.limit.Limit;

/**
 * Created by Alexander Sokol
 * on 21.04.16.
 */
public class MaxMixer extends Mixer {

    public MaxMixer(Limit limit) {
        super(limit);
    }

    @Override
    public float mix(float[] noises) {

        float max = Float.MIN_VALUE;

        for(float value : noises){
            max = Math.max(value, max);
        }

        return max;
    }
}
