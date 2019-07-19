package com.example.sunrinhack2019.Walk;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.sunrinhack2019.R;
import com.github.paolorotolo.appintro.AppIntroBaseFragment;

public class WalkFragment3 extends AppIntroBaseFragment {

    LinearLayout walk3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_walkthrough3, container, false);

        return v;
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public int getDefaultBackgroundColor() {
        // Return the default background color of the slide.
        return Color.parseColor("#f38699");
    }

    @Override
    public void setBackgroundColor(@ColorInt int backgroundColor) {
        // Set the background color of the view within your slide to which the transition should be applied.
        walk3 = getView().findViewById(R.id.walk3);
        if (walk3 != null) {
            walk3.setBackgroundColor(backgroundColor);
        }
    }
}
