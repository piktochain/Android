package com.example.sunrinhack2019.Walk;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.WindowManager;

import com.example.sunrinhack2019.Auth.DBLoadActivity;
import com.example.sunrinhack2019.R;
import com.github.paolorotolo.appintro.AppIntro;

public class WalkthroughActivity extends AppIntro {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        SliderPage sliderPage1 = new SliderPage();
//        sliderPage1.setTitle("우리들의 팀빌딩 플랫폼, Builders");
//        sliderPage1.setDescription("Builders는 마음이 통하는 누구나 팀으로 이어주는 팀 빌딩 플랫폼입니다.");
//        //sliderPage1.setImageDrawable(R.drawable.builders_icon);
//        sliderPage1.setBgColor(getResources().getColor(R.color.materialRed));
//        addSlide(AppIntroFragment.newInstance(sliderPage1));
//
//        SliderPage sliderPage2 = new SliderPage();
//        sliderPage2.setTitle("다양한 분야에서 함께할 팀원을 모집하세요");
//        sliderPage2.setDescription("공모전, 대회, 봉사, 공연 등 분야에 제한받지 않는 팀 빌딩을 경험하세요.");
//        //sliderPage2.setImageDrawable(R.drawable.builders_icon);
//        sliderPage2.setBgColor(getResources().getColor(R.color.materialYellow));
//        addSlide(AppIntroFragment.newInstance(sliderPage2));
//
//        SliderPage sliderPage3 = new SliderPage();
//        sliderPage3.setTitle("나만의 포트폴리오를 만드세요");
//        sliderPage3.setDescription("팀원에게 나를 소개하는 포트폴리오를 만들어 보세요.");
//        //sliderPage3.setImageDrawable(R.drawable.builders_icon);
//        sliderPage3.setBgColor(getResources().getColor(R.color.materialGreen));
//        addSlide(AppIntroFragment.newInstance(sliderPage3));

        WalkFragment1 walkFragment1 = new WalkFragment1();
        addSlide(walkFragment1);
        WalkFragment2 walkFragment2 = new WalkFragment2();
        addSlide(walkFragment2);
        WalkFragment3 walkFragment3 = new WalkFragment3();
        addSlide(walkFragment3);

        // OPTIONAL METHODS
        // Override bar/separator color.
        //setBarColor(Color.parseColor("#3F51B5"));
        //setSeparatorColor(Color.parseColor("#2196F3"));

        // Hide Skip/Done button.
        showSkipButton(true);
        setProgressButtonEnabled(true);

        setImageNextButton(getResources().getDrawable(R.drawable.ic_navigate_next_white_24dp));
        setSkipText("건너뛰기");
        setDoneText("시작하기");
        setColorTransitionsEnabled(true);

        // Turn vibration on and set intensity.
        // NOTE: you will probably need to ask VIBRATE permission in Manifest.
//        setVibrate(true);
//        setVibrateIntensity(30);
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        Intent intent = new Intent(WalkthroughActivity.this, DBLoadActivity.class);
        startActivity(intent);
        finish();
        //overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        //super.onSkipPressed(currentFragment);

        // Do something when users tap on Skip button.
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {

        Intent intent = new Intent(WalkthroughActivity.this, DBLoadActivity.class);
        startActivity(intent);
        finish();
        //super.onDonePressed(currentFragment);
        // Do something when users tap on Done button.
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
    }


}
