package com.example.sunrinhack2019;

import android.content.Intent;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sunrinhack2019.Auth.LoginActivity;

import me.aflak.libraries.callback.FingerprintDialogCallback;
import me.aflak.libraries.dialog.FingerprintDialog;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
//                startActivity(intent);
//                //overridePendingTransition(R.anim.fade_in_s, R.anim.fade_out_s); //화면 전환 효과
//                finish();
                FingerprintDialog.initialize(IntroActivity.this)
                        .title("지문 인증")
                        .message("비밀번호에 접근하려면 지문 인증이 필요합니다.")
                        .callback(new FingerprintDialogCallback() {
                            @Override
                            public void onAuthenticationSucceeded() {
                                Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            @Override
                            public void onAuthenticationCancel() {
                                finish();
                            }
                        })
                        .show();
            }
        }, 1000);
    }
}
