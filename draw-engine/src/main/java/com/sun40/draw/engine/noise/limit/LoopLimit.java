package com.sun40.draw.engine.noise.limit;

/**
 * Created by Alexander Sokol
 * on 21.04.16.
 */
public class LoopLimit extends Limit {

    public LoopLimit(float min, float max) {
        super(min, max);
    }

    @Override
    public float lim(float input) {
        return 0;
    }
}
