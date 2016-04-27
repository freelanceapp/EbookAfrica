package com.apporio.ebookafrica;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ru.noties.filldrawable.FillDrawable;
import ru.noties.filldrawable.FillImageView;

public class Main2Activity extends AppCompatActivity {







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);




        final List<FillDrawable> drawables = createDrawables();



        final FillImageView fillImageView1 = (FillImageView) findViewById(R.id.fill_image_view_1);

        drawables.add(0, fillImageView1.getFillDrawable());

        final Handler handler = new Handler();
        handler.post(new FakeProgress(handler, new FakeProgress.OnProgressChange() {
            @Override
            public void onProgressChange(float progress) {
                for (FillDrawable drawable : drawables) {
                    drawable.setFillPercent(progress);
                }
            }
        }));
    }

    private List<FillDrawable> createDrawables() {

        final Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_android);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

        final int alpha = 125;

        final int rightColor = ContextCompat.getColor(this, R.color.icons_8_muted_green_1);
        final FillDrawable right = new FillDrawable(FillDrawable.FROM_RIGHT, drawable.mutate())
                .setNormalColor(ColorUtils.applyAlpha(rightColor, alpha))
                .setFillColor(rightColor);

        final int bottomColor = ContextCompat.getColor(this, R.color.icons_8_muted_violet);
        final FillDrawable bottom = new FillDrawable(FillDrawable.FROM_BOTTOM, drawable.mutate())
                .setNormalColor(ColorUtils.applyAlpha(bottomColor, alpha))
                .setFillColor(bottomColor);

        return new ArrayList<FillDrawable>() {{
            add(right);
            add(bottom);
        }};
    }

    private static class FakeProgress implements Runnable {

        interface OnProgressChange {
            void onProgressChange(float progress);
        }

        private final Handler mHandler;
        private final Random mRandom;
        private final OnProgressChange mOnProgressChange;
        private final float mPercent = 1.F / 100;
        int runs;
        boolean isGrowing = true;

        FakeProgress(Handler handler, OnProgressChange onProgressChange) {
            mHandler = handler;
            mOnProgressChange = onProgressChange;
            mRandom = new Random();
        }

        @Override
        public void run() {

            if (isGrowing && ++runs >= 100) {
                isGrowing = false;
                runs = 100;
            } else if (!isGrowing && --runs <= 0) {
                isGrowing = true;
                runs = 0;
            }

            final int step = mRandom.nextInt(50) + 50;
            mOnProgressChange.onProgressChange(mPercent * runs);
            mHandler.postDelayed(this, step);
        }
    }
}