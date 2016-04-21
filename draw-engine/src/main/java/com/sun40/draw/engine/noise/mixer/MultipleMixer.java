package com.sun40.draw.engine.noise.mixer;

import com.sun40.draw.engine.noise.limit.Limit;

/**
 * Created by Alexander Sokol
 * on 21.04.16.
 */
public class MultipleMixer extends Mixer{

    public MultipleMixer(Limit limit) {
        super(limit);
    }

    @Override
    public float mix(float[] noises) {
        float value = Float.MIN_VALUE;
        for(float noise : noises){
            if(value == Float.MIN_VALUE){
                value = noise;
            }
            else {
                value *= noise;
            }
        }
        return limit().lim(1f - value);
    }
}
