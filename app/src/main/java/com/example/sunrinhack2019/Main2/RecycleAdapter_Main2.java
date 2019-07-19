package com.example.sunrinhack2019.Main2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sunrinhack2019.Auth.UserDB;
import com.example.sunrinhack2019.KeyModel;
import com.example.sunrinhack2019.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

public class RecycleAdapter_Main2 extends RecyclerView.Adapter<RecycleHolder_Main2> {

    //Main2 탭의 RecyclerView Adapter

    List<KeyModel> items = new ArrayList<>(); //RecyclerView에 들어갈 아이템 저장 ArrayList 선언

    public List<KeyModel> getItems() {
        return items;
    } //List의 아이템을 반환하는 함수

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private FirebaseAuth firebaseAuth;

    Context context;

    public void add(KeyModel data){ //리스트에 값을 추가하는 함수
        items.add(data); //리스트에 양식으로 전달받은 값 추가
        notifyDataSetChanged(); //RecyclerView 갱신
    }

    @NonNull
    @Override
    public RecycleHolder_Main2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_main2, parent, false); //RecyclerView에 뿌려줄 아이템 뷰 설정
        context = v.getContext();

        return new RecycleHolder_Main2(v); //뷰 반환
    }

    @Override
    public void onBindViewHolder(@NonNull final RecycleHolder_Main2 holder, int position) {

        final KeyModel item = items.get(position); //리스트의 position 위치 값을 com.example.builders.Main.ArticleModel 양식으로 가져오기

        //가져온 값을 holder에 대입
        holder.title.setText(item.getTitle());
        //holder.password.setText("****");
        holder.password.setText(item.getPassword());
        holder.date.setText(item.getDate());

        String data = item.getImg();
        byte[] bytePlainOrg = Base64.decode(data, Base64.NO_WRAP);

        ByteArrayInputStream inStream = new ByteArrayInputStream(bytePlainOrg);
        Bitmap bm = BitmapFactory.decodeStream(inStream) ;

        holder.img.setImageBitmap(bm);

        holder.moreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.moreBtn.getTag().equals("n")){
                    holder.moreBtn.setImageResource(R.drawable.item_less);
                    holder.keybox.setVisibility(View.VISIBLE);
                    holder.editTitle.setVisibility(View.VISIBLE);
                    holder.cameraBtn.setVisibility(View.VISIBLE);
                    holder.removeBtn.setVisibility(View.VISIBLE);
                    holder.moreBtn.setTag("y");
                }
                else{
                    holder.moreBtn.setImageResource(R.drawable.item_more);
                    holder.keybox.setVisibility(View.GONE);
                    holder.editTitle.setVisibility(View.GONE);
                    holder.cameraBtn.setVisibility(View.GONE);
                    holder.removeBtn.setVisibility(View.GONE);
                    holder.moreBtn.setTag("n");
                }
            }
        });

//        holder.getVisibleButton().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(holder.visibleBtn.getTag().equals("n")){
//                    holder.visibleBtn.setImageResource(R.drawable.ic_visibility_black_24dp);
//                    holder.password.setText(item.getPassword());
//                    holder.visibleBtn.setTag("y");
//                }
//                else{
//                    holder.visibleBtn.setImageResource(R.drawable.ic_visibility_off_black_24dp);
//                    holder.password.setText("****");
//                    holder.visibleBtn.setTag("n");
//                }
//            }
//
//        });
    }

    @Override
    public int getItemCount() {
        return items.size(); //리스트 크기 반환
    }



}
