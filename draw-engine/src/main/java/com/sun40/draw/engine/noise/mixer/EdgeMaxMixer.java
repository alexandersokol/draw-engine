package com.sun40.draw.engine.noise.mixer;

import com.sun40.draw.engine.noise.limit.Limit;

/**
 * Created by Alexander Sokol
 * on 21.04.16.
 */
public class EdgeMaxMixer extends Mixer {

    public EdgeMaxMixer(Limit limit) {
        super(limit);
    }

    @Override
    public float mix(float[] noises) {
        float max = Float.MIN_VALUE;
        int pos = 0;

        for (int i = 0; i < noises.length; i++) {
            if (max < noises[i]) {
                pos = i;
                max = noises[i];
            }
        }

        float value = max;

        for(int i = 0; i < noises.length; i++){
            if(pos != i){
                value = value - noises[i];
            }
        }

        return limit().lim(value);
    }
}
