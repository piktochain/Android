package com.example.sunrinhack2019.Main2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sunrinhack2019.Auth.UserDB;
import com.example.sunrinhack2019.GetTimeDate;
import com.example.sunrinhack2019.KeyModel;
import com.example.sunrinhack2019.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;

public class MainFragment2 extends android.support.v4.app.Fragment {

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private FirebaseAuth firebaseAuth;

    public static MainFragment2 newInstance(){
        MainFragment2 fragment = new MainFragment2();
        return fragment;
    }

    RecyclerView rcv;
    RecycleAdapter_Main2 rcvAdap;

    TextView name;

    Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        final View v =  inflater.inflate(R.layout.activity_main2, container, false);

        context = container.getContext();

        UserDB userDB = new UserDB();
        name = v.findViewById(R.id.main2_username);
        name.setText(userDB.getUserNickname(context));

        rcv = v.findViewById(R.id.main2_recycler);
        LinearLayoutManager lm = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);

        rcv.setLayoutManager(lm); //RecyclerView에 LayoutManager 지정

        rcvAdap = new RecycleAdapter_Main2();

        rcv.setAdapter(rcvAdap);



        databaseReference.child("keys").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                KeyModel model = dataSnapshot.getValue(KeyModel.class);
                rcvAdap.add(model);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        RecycleClick_Main2.addRecycler(rcv).setOnItemClickListener(new RecycleClick_Main2.OnItemClickListener() { //RecyclerViewd에 onClickListener 추가
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                //Toast.makeText(ChatActivity.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), ArticleActivity.class); //프로필 화면으로 Intent 선언

                //Intent에 RecyclerView의 클릭된 위치 아이템에서 가져온 메세지 유저 정보 첨부
                intent.putExtra("title", rcvAdap.getItems().get(position).getTitle());
                intent.putExtra("info", rcvAdap.getItems().get(position).getInfo());
                intent.putExtra("password", rcvAdap.getItems().get(position).getPassword());
                intent.putExtra("image", rcvAdap.getItems().get(position).getImg());
                intent.putExtra("kid", rcvAdap.getItems().get(position).getKid());

                startActivity(intent); //프로필 화면 Activity 실행
            }
        });

        return v;

    }



}
