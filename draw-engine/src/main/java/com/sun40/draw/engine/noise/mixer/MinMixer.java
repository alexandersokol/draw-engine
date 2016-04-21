package com.sun40.draw.engine.noise.mixer;

import com.sun40.draw.engine.noise.limit.Limit;

/**
 * Created by Alexander Sokol
 * on 21.04.16.
 */
public class MinMixer extends Mixer {

    public MinMixer(Limit limit) {
        super(limit);
    }

    @Override
    public float mix(float[] noises) {
        float min = Float.MAX_VALUE;

        for (float value : noises){
            min = Math.min(min, value);
        }

        return min;
    }
}
