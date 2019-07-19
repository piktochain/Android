package com.example.sunrinhack2019.Main2;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.sunrinhack2019.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecycleHolder_Main2 extends RecyclerView.ViewHolder {

    //People 탭의 RecyclerView ViewHolder

    //TextView, ImageView 선언
    public TextView title, password;
    public CircleImageView img;//, visibleBtn;

    //ViewHolder
    public RecycleHolder_Main2(View itemView) {
        super(itemView);
        //각 아이템들을 RecyclerView 아이템 뷰의 항목과 연결
        title = itemView.findViewById(R.id.item_main2_title);
        //password = itemView.findViewById(R.id.item_main2_password);
        img = itemView.findViewById(R.id.item_main2_image);
        //visibleBtn = itemView.findViewById(R.id.item_main2_visible);
    }

//    public ImageView getVisibleButton() {
//        return visibleBtn;
//    }


}
