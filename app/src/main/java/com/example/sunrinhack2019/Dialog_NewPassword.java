package com.example.sunrinhack2019;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Dialog_NewPassword extends DialogFragment {

    //비밀번호 재설정 시 표시되는 Dialog

    private FirebaseAuth firebaseAuth; //Firebase Authentication 가져오기

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        firebaseAuth = FirebaseAuth.getInstance(); //Firebase 현재 Auth 정보 가져오기

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()); //Dialog Builder 선언
        builder.setTitle("비밀전호 재설정") //Dialog 타이틀 설정
                .setMessage("이메일 주소로 비밀번호 재설정 메일을 전송합니다") //Dialog 내용 설정
                .setPositiveButton("전송", new DialogInterface.OnClickListener() { //Dialog에 Positive 버튼 추가
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseUser user = firebaseAuth.getCurrentUser(); //현재 유저 정보 가져오기
                        firebaseAuth.sendPasswordResetEmail(user.getEmail()); //비밀번호 재설정 메일 전송
                        Toast.makeText(getContext(), "성공적으로 전송했습니다!", Toast.LENGTH_SHORT).show(); //전송 완료 토스트
                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() { //Dialog에 Negative 버튼 추가
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return builder.create(); //Builder 반환
    }
}
