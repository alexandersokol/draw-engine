package com.sun40.draw.engine.noise.mixer;

import com.sun40.draw.engine.noise.limit.Limit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Alexander Sokol
 * on 21.04.16.
 */
public class CompositeMixer extends Mixer {

    private final List<Mixer> mMixers;

    public CompositeMixer(Limit limit) {
        super(limit);
        mMixers = new ArrayList<>();
    }

    public CompositeMixer(Limit limit, Collection<Mixer> mixers) {
        this(limit);
        mMixers.addAll(mixers);
    }

    public void add(Mixer mixer) {
        mMixers.add(mixer);
    }

    public void add(Collection<Mixer> mixers) {
        mMixers.addAll(mixers);
    }

    @Override
    public float mix(float[] noises) {
        float value = 0f;

        for (Mixer mixer : mMixers) {
            value += mixer.mix(noises);
        }

        if (mMixers.size() > 0) {
            value /= mMixers.size();
        }

        return limit().lim(value);
    }
}
