package com.sun40.draw.engine.noise.mixer;

import com.sun40.draw.engine.noise.limit.Limit;

/**
 * Created by Alexander Sokol
 * on 21.04.16.
 */
public class EdgeMinMixer extends Mixer {

    public EdgeMinMixer(Limit limit) {
        super(limit);
    }

    @Override
    public float mix(float[] noises) {
        float min = Float.MAX_VALUE;
        int pos = 0;

        for (int i = 0; i < noises.length; i++) {
            if (min > noises[i]) {
                pos = i;
                min = noises[i];
            }
        }

        float value = min;

        for (int i = 0; i < noises.length; i++) {
            if (pos != i) {
                value = value + noises[i];
            }
        }

        return limit().lim(value);
    }
}
