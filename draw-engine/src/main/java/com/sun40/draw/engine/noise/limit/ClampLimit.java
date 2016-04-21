package com.sun40.draw.engine.noise.limit;

/**
 * Created by Alexander Sokol
 * on 21.04.16.
 */
public class ClampLimit extends Limit {

    public ClampLimit(float min, float max) {
        super(min, max);
    }

    @Override
    public float lim(float input) {
        if (input < min()) {
            input = min();
        } else if (input > max()) {
            input = max();
        }
        return input;
    }
}
