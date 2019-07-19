package com.example.sunrinhack2019.Main3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sunrinhack2019.Auth.LoginActivity;
import com.example.sunrinhack2019.R;
import com.google.firebase.auth.FirebaseAuth;

public class MainFragment3 extends android.support.v4.app.Fragment {

    public static MainFragment3 newInstance(){
        MainFragment3 fragment = new MainFragment3();
        return fragment;
    }

    Button logoutBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        final View v =  inflater.inflate(R.layout.activity_main3, container, false);

        logoutBtn = v.findViewById(R.id.main_logout);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut(); //Firebase의 현재 Auth 항목을 가져와 로그아웃 실행
                startActivity(new Intent(getContext(), LoginActivity.class)); //로그인 액티비티 실행
                getActivity().finish(); //현재 액티비티 종료
            }
        });


        return v;
    }
}
