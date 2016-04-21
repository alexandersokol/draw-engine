package com.sun40.draw.engine.noise.mixer;

import com.sun40.draw.engine.noise.limit.Limit;

/**
 * Created by Alexander Sokol
 * on 21.04.16.
 */
public class LoopMixer extends Mixer {

    public LoopMixer(Limit limit) {
        super(limit);
    }

    @Override
    public float mix(float[] noises) {
        float min = limit().min();
        float max = limit().max();
        float sum = 0f;

        for (float value : noises) {
            sum += value;
        }

        while (sum < min) {
            sum = max - sum;
        }

        while (sum > max) {
            sum = min + (sum - max);
        }

        return sum;
    }
}
