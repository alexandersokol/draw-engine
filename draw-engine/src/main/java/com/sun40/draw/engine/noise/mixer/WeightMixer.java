package com.sun40.draw.engine.noise.mixer;

import com.sun40.draw.engine.noise.NoiseChunk;
import com.sun40.draw.engine.noise.limit.Limit;

import java.util.List;

/**
 * Created by Alexander Sokol
 * on 21.04.16.
 */
public class WeightMixer extends Mixer {


    public WeightMixer(Limit limit) {
        super(limit);
    }

    @Override
    public NoiseChunk mix(List<NoiseChunk> chunks) {
        NoiseChunk baseChunk = chunks.get(0);
        final float[][] data = new float[baseChunk.width()][baseChunk.height()];

        for (int x = 0; x < data.length; x++) {
            for (int y = 0; y < data[x].length; y++) {
                float[] noises = new float[chunks.size()];
                float[] weights = new float[chunks.size()];
                for (int i = 0; i < noises.length; i++) {
                    noises[i] = chunks.get(i).data()[x][y];
                    weights[i] = chunks.get(i).octave().weight();
                }
                data[x][y] = mix(noises, weights);
            }
        }
        return new NoiseChunk(baseChunk.width(), baseChunk.height(), data);
    }


    public float mix(float[] noises, float[] weights) {
        for (int i = 0; i < noises.length; i++) {
            noises[i] = noises[i] * weights[i];
        }
        return mix(noises);
    }


    @Override
    public float mix(float[] noises) {
        float sum = 0f;
        for (float value : noises) {
            sum += value;
        }
        sum /= noises.length;

        return limit().lim(sum);
    }
}
