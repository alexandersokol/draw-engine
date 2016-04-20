package com.sun40.draw.engine.noise;

/**
 * Created by Alexander Sokol
 * on 19.04.16.
 */
public interface Noise {
    double noise(double x, double y);

    double noise(double x, double y, double z);
}
