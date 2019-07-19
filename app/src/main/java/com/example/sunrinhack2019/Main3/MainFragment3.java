package com.example.sunrinhack2019.Main3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.sunrinhack2019.Auth.LoginActivity;
import com.example.sunrinhack2019.Auth.UserDB;
import com.example.sunrinhack2019.Dialog_DeleteUser;
import com.example.sunrinhack2019.Dialog_NewPassword;
import com.example.sunrinhack2019.R;
import com.example.sunrinhack2019.Walk.WalkthroughActivity;
import com.google.firebase.auth.FirebaseAuth;

public class MainFragment3 extends android.support.v4.app.Fragment {

    public static MainFragment3 newInstance(){
        MainFragment3 fragment = new MainFragment3();
        return fragment;
    }

    Button logoutBtn, helpBtn, changeBtn, deleteBtn;

    Context context;
    TextView name;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        final View v =  inflater.inflate(R.layout.activity_main3, container, false);

        context = container.getContext();

        UserDB userDB = new UserDB();
        name = v.findViewById(R.id.main2_username);
        name.setText(userDB.getUserNickname(context));

        helpBtn = v.findViewById(R.id.main_help);
        helpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, WalkthroughActivity.class));
                getActivity().finish();
            }
        });

        logoutBtn = v.findViewById(R.id.main_logout);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut(); //Firebase의 현재 Auth 항목을 가져와 로그아웃 실행
                startActivity(new Intent(getContext(), LoginActivity.class)); //로그인 액티비티 실행
                getActivity().finish(); //현재 액티비티 종료
            }
        });

        changeBtn = v.findViewById(R.id.main_changepw);
        changeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogFragment = new Dialog_NewPassword();
                dialogFragment.show(getActivity().getSupportFragmentManager(), "dialog_newpassword");
            }
        });

        deleteBtn = v.findViewById(R.id.main_delete);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogFragment = new Dialog_DeleteUser();
                dialogFragment.show(getActivity().getSupportFragmentManager(), "dialog_deleteuser");
            }
        });


        return v;
    }
}
