package com.sun40.draw.engine.noise.normalization;

/**
 * Created by Alexander Sokol
 * on 20.04.16.
 */
public class NoNormalization implements Normalization{
    @Override
    public float normalize(float value) {
        return value;
    }
}
