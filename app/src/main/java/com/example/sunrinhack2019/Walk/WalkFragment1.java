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
import com.github.paolorotolo.appintro.ISlideBackgroundColorHolder;

public class WalkFragment1 extends AppIntroBaseFragment implements ISlideBackgroundColorHolder {


    LinearLayout walk1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_walkthrough1, container, false);

        return v;
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public int getDefaultBackgroundColor() {
        // Return the default background color of the slide.
        return Color.parseColor("#f9cf69");
    }

    @Override
    public void setBackgroundColor(@ColorInt int backgroundColor) {
        // Set the background color of the view within your slide to which the transition should be applied.
        walk1 = getView().findViewById(R.id.walk1);
        if (walk1 != null) {
            walk1.setBackgroundColor(backgroundColor);
        }
    }
}
