package com.draw.enginesample;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.sun40.draw.engine.noise.BasicPerlinNoise;
import com.sun40.draw.engine.noise.Noise;
import com.sun40.draw.engine.noise.NoiseChunk;
import com.sun40.draw.engine.noise.Octave;
import com.sun40.draw.engine.noise.SimplexNoise;
import com.sun40.draw.engine.noise.WorleyNoiseTemp;
import com.sun40.draw.engine.noise.limit.ClampLimit;
import com.sun40.draw.engine.noise.mixer.MiddleMixer;
import com.sun40.draw.engine.noise.mixer.Mixer;
import com.sun40.draw.engine.noise.normalization.NoNormalization;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ImageView mNoiseView;
    List<NoiseChunk> mChunks = new ArrayList<>();

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Noise noise = new BasicPerlinNoise();
        Octave mOctave = new Octave(0, 0, 3.5f, 3.5f, 1.0f);
        NoiseChunk glob = new NoiseChunk(512, 512, mOctave, noise);

        Noise noise2 = new SimplexNoise();
        Octave mOctave2 = new Octave(0, 0, 10f, 10f, 0.5f);
        NoiseChunk glob2 = new NoiseChunk(512, 512, mOctave2, noise2);

        Noise noise3 = new SimplexNoise();
        Octave mOctave3 = new Octave(11f, -11f, 250f, 250f, 0.5f);
        NoiseChunk glob3 = new NoiseChunk(512, 512, mOctave3, noise3);

        Noise noise4 = new WorleyNoiseTemp(new WorleyNoiseTemp.EuclideanDistance());
        Octave octave4 = new Octave(10f, 6f, 5f, 5f, 1.0f);
        octave4.setNormalization(new NoNormalization());
        NoiseChunk glob4 = new NoiseChunk(512, 512, octave4, noise4);

//        mChunks.add(glob);
//        mChunks.add(glob2);
//        mChunks.add(glob3);
        mChunks.add(glob4);

        mNoiseView = (ImageView) findViewById(R.id.noiseView);
        drawNoise();

        findViewById(R.id.noiseButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawNoise();
            }
        });
//        testChunk();
    }


    public void testChunk() {
        Noise noise = new BasicPerlinNoise();
        Octave octave = new Octave(0, 0, 1f, 1f);
        NoiseChunk noiseChunk = new NoiseChunk(16, 8, octave, noise);

        float[] noiseFlat = noiseChunk.flatData();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < noiseFlat.length; i++) {
            builder.append(noiseFlat[i]);
            builder.append("\t");
            if (i % 16 == 0 && i != 0) {
                Log.d(TAG, builder.toString());
                builder.setLength(0);
            }
        }
        Log.d(TAG, builder.toString());

        float[][] dim = noiseChunk.data();
        for (int i = 0; i < dim.length; i++) {
            builder = new StringBuilder();
            for (int j = 0; j < dim[i].length; j++) {
                builder.append(dim[i][j]);
                builder.append("\t");
            }
            Log.v(TAG, builder.toString());
        }
    }


    public void drawNoise() {
        Bitmap bitmap = createBitmap(mChunks);
        Log.v(TAG, "Bitmap created");
        mNoiseView.setImageBitmap(bitmap);
    }


    public static Bitmap createBitmap(List<NoiseChunk> chunks) {

//        MiddleMixer middleMixer = new MiddleMixer(new ClampLimit());
//        EdgeMaxMixer edgeMaxMixer = new EdgeMaxMixer(new LoopLimit());
//        List<Mixer> mixers = new ArrayList<>();
//        mixers.add(middleMixer);
//        mixers.add(edgeMaxMixer);
//
//        Mixer mixer = new CompositeMixer(new StrongLimit(), mixers);

        Mixer mixer = new MiddleMixer(new ClampLimit());

        float[][] noise = mixer.mix(chunks).data();
        Bitmap bitmap = Bitmap.createBitmap(noise.length, noise[0].length, Bitmap.Config.ARGB_8888);

        for (int i = 0; i < noise.length; i++) {
            for (int j = 0; j < noise[i].length; j++) {
                bitmap.setPixel(i, j, blackWhiteColor(noise[i][j]));
            }
        }

        return bitmap;
    }


    public static int blackWhiteColor(float value) {
        int g = (int) (value * 255f);
        return Color.rgb(g, g, g);
    }

}
