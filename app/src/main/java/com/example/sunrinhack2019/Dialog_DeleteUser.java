package com.example.sunrinhack2019;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Dialog_DeleteUser extends DialogFragment {

    //회원 탈퇴 시 표시되는 Dialog

    private FirebaseAuth firebaseAuth; //Firebase Authentication 가져오기


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        firebaseAuth = FirebaseAuth.getInstance(); //Firebase 현재 Auth 정보 가져오기

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()); //Dialog Builder 선언
        builder.setTitle("회원 탈퇴") //Dialog 타이틀 설정
                .setMessage("정말로 탈퇴하시겠습니까?") //Dialog 내용 설정
                .setPositiveButton("탈퇴", new DialogInterface.OnClickListener() { //Dialog에 Positive 버튼 추가
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseUser user = firebaseAuth.getCurrentUser(); //현재 유저 정보 가져오기
                        user.delete(); //유저 탈퇴하기
                        Toast.makeText(getContext(), "탈퇴하였습니다.", Toast.LENGTH_SHORT).show(); //탈퇴 확인 토스트
                        getActivity().finishAffinity(); //앱 종료
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
