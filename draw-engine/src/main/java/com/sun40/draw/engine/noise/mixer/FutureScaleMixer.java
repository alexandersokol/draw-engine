package com.sun40.draw.engine.noise.mixer;

import com.sun40.draw.engine.noise.limit.Limit;

/**
 * Created by Alexander Sokol
 * on 21.04.16.
 */
public class FutureScaleMixer extends Mixer {


    public FutureScaleMixer(Limit limit) {
        super(limit);
    }

    @Override
    public float mix(float[] noises) {
        float sum = 0f;
        float min = limit().max();
        float max = limit().min();

        for (float value : noises) {
            sum += value;
            min = Math.min(min, value);
            max = Math.max(max, value);
        }
        return (sum - min) / (max - min);

    }
}
