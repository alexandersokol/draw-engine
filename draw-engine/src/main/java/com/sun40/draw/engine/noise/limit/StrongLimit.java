package com.sun40.draw.engine.noise.limit;

/**
 * Created by Alexander Sokol
 * on 25.04.16.
 */
public class StrongLimit extends Limit {

    @Override
    public float lim(float input) {
        float middle = ((max() - min()) / 2f);
        if (input < middle) {
            return min();
        } else if (input > middle) {
            return max();
        }
        return middle;
    }
}
