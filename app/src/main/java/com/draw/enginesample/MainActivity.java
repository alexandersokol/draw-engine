package com.draw.enginesample;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.sun40.draw.engine.noise.BasicPerlinNoise;
import com.sun40.draw.engine.noise.Noise;
import com.sun40.draw.engine.noise.NoiseChunk;
import com.sun40.draw.engine.noise.OctaveModel;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static int color0_0;
    private static int color0_5;
    private static int color1_0;
    private static int color1_5;
    private static int color2_0;
    private static int color2_5;
    private static int color3_0;
    private static int color3_5;
    private static int color4_0;
    private static int color4_5;
    private static int color5_0;
    private static int color5_5;
    private static int color6_0;
    private static int color6_5;
    private static int color7_0;
    private static int color7_5;
    private static int color8_0;
    private static int color8_5;
    private static int color9_0;
    private static int color9_5;
    private static int color10_0;

    private ImageView mNoiseView;

    Noise noise = new BasicPerlinNoise();
    OctaveModel octaveModel = new OctaveModel(0, 0, 1f, 1f);
    NoiseChunk glob = new NoiseChunk(512, 512, octaveModel, noise);

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initColors(this);

        mNoiseView = (ImageView) findViewById(R.id.noiseView);
        drawNoise(glob);

        findViewById(R.id.noiseButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawNoise(glob);
            }
        });
        testChunk();
    }


    public void testChunk() {
        Noise noise = new BasicPerlinNoise();
        OctaveModel octaveModel = new OctaveModel(0, 0, 1f, 1f);
        NoiseChunk noiseChunk = new NoiseChunk(16, 8, octaveModel, noise);

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


    public void drawNoise(NoiseChunk noiseChunk) {
        Bitmap bitmap = createBitmap(noiseChunk);
        mNoiseView.setImageBitmap(bitmap);
    }


    public static Bitmap createBitmap(NoiseChunk chunk) {
        float[][] noise = chunk.data();
        Bitmap bitmap = Bitmap.createBitmap(noise.length, noise[0].length, Bitmap.Config.ARGB_8888);

        for (int i = 0; i < noise.length; i++) {
            for (int j = 0; j < noise[i].length; j++) {
                bitmap.setPixel(i, j, blackWhiteColor(noise[i][j]));
            }
        }

        return bitmap;
    }


    public static int blackWhiteColor(float value){
        int g = (int) (value * 255f);
        return Color.rgb(g, g, g);
    }

    public static int strongColor(double value) {
        if (value <= 0.00f) {
            return color0_0;
        } else if (value > 0.00f && value <= 0.05f) {
            return color0_5;
        } else if (value > 0.05f && value <= 0.10f) {
            return color1_0;
        } else if (value > 0.10f && value <= 0.15f) {
            return color1_5;
        } else if (value > 0.15f && value <= 0.20f) {
            return color2_0;
        } else if (value > 0.20f && value <= 0.25f) {
            return color2_5;
        } else if (value > 0.25f && value <= 0.30f) {
            return color3_0;
        } else if (value > 0.30f && value <= 0.35f) {
            return color3_5;
        } else if (value > 0.35f && value <= 0.40f) {
            return color4_0;
        } else if (value > 0.40f && value <= 0.45f) {
            return color4_5;
        } else if (value > 0.45f && value <= 0.50f) {
            return color5_0;
        } else if (value > 0.50f && value <= 0.55f) {
            return color5_5;
        } else if (value > 0.55f && value <= 0.60f) {
            return color6_0;
        } else if (value > 0.60f && value <= 0.65f) {
            return color6_5;
        } else if (value > 0.65f && value <= 0.70f) {
            return color7_0;
        } else if (value > 0.70f && value <= 0.75f) {
            return color7_5;
        } else if (value > 0.75f && value <= 0.80f) {
            return color8_0;
        } else if (value > 0.80f && value <= 0.85f) {
            return color8_5;
        } else if (value > 0.85f && value <= 0.90f) {
            return color9_0;
        } else if (value > 0.90f && value <= 0.95f) {
            return color9_5;
        } else {
            return color10_0;
        }
    }


    private static void initColors(Context context) {
        color0_0 = ContextCompat.getColor(context, R.color.c0_0);
        color0_5 = ContextCompat.getColor(context, R.color.c0_5);
        color1_0 = ContextCompat.getColor(context, R.color.c1_0);
        color1_5 = ContextCompat.getColor(context, R.color.c1_5);
        color2_0 = ContextCompat.getColor(context, R.color.c2_0);
        color2_5 = ContextCompat.getColor(context, R.color.c2_5);
        color3_0 = ContextCompat.getColor(context, R.color.c3_0);
        color3_5 = ContextCompat.getColor(context, R.color.c3_5);
        color4_0 = ContextCompat.getColor(context, R.color.c4_0);
        color4_5 = ContextCompat.getColor(context, R.color.c4_5);
        color5_0 = ContextCompat.getColor(context, R.color.c5_0);
        color5_5 = ContextCompat.getColor(context, R.color.c5_5);
        color6_0 = ContextCompat.getColor(context, R.color.c6_0);
        color6_5 = ContextCompat.getColor(context, R.color.c6_5);
        color7_0 = ContextCompat.getColor(context, R.color.c7_0);
        color7_5 = ContextCompat.getColor(context, R.color.c7_5);
        color8_0 = ContextCompat.getColor(context, R.color.c8_0);
        color8_5 = ContextCompat.getColor(context, R.color.c8_5);
        color9_0 = ContextCompat.getColor(context, R.color.c9_0);
        color9_5 = ContextCompat.getColor(context, R.color.c9_5);
        color10_0 = ContextCompat.getColor(context, R.color.c10_0);
    }
}
