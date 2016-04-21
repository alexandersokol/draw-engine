package com.sun40.draw.engine.noise.mixer;

import com.sun40.draw.engine.noise.NoiseChunk;
import com.sun40.draw.engine.noise.limit.Limit;

import java.util.List;

/**
 * Created by Alexander Sokol
 * on 21.04.16.
 */
public abstract class Mixer {

    private Limit mLimit;

    public Mixer(Limit limit) {
        mLimit = limit;
    }

    public void setLimit(Limit limit) {
        mLimit = limit;
    }

    public Limit limit() {
        return mLimit;
    }

    public NoiseChunk mix(List<NoiseChunk> chunks) {
        NoiseChunk baseChunk = chunks.get(0);
        final float[][] data = new float[baseChunk.width()][baseChunk.height()];

        for (int x = 0; x < data.length; x++) {
            for (int y = 0; y < data[x].length; y++) {
                float[] noises = new float[chunks.size()];
                for (int i = 0; i < noises.length; i++) {
                    noises[i] = chunks.get(i).data()[x][y];
                }
                data[x][y] = mix(noises);
            }
        }
        return new NoiseChunk(baseChunk.width(), baseChunk.height(), data);
    }


    public abstract float mix(float[] noises);
}
