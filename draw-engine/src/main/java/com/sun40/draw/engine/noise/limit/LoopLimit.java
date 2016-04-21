package com.sun40.draw.engine.noise.limit;

/**
 * Created by Alexander Sokol
 * on 21.04.16.
 */
public class LoopLimit extends Limit {

    public LoopLimit() {
        super();
    }

    public LoopLimit(float min, float max) {
        super(min, max);
    }

    @Override
    public float lim(float input) {
        while (input < min()) {
            input = max() - input;
        }

        while (input > max()) {
            input = min() + (input - max());
        }

        return input;
    }
}
