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
    public TextView title, password, date;
    public CircleImageView img;//, visibleBtn;
    public ImageView moreBtn, editTitle, cameraBtn, removeBtn;

    public LinearLayout keybox, all;

    //ViewHolder
    public RecycleHolder_Main2(View itemView) {
        super(itemView);
        //각 아이템들을 RecyclerView 아이템 뷰의 항목과 연결
        title = itemView.findViewById(R.id.item_main2_title);
        password = itemView.findViewById(R.id.item_main2_password);
        date = itemView.findViewById(R.id.item_main2_date);
        img = itemView.findViewById(R.id.item_main2_image);
        moreBtn = itemView.findViewById(R.id.item_main2_moreBtn);
        keybox = itemView.findViewById(R.id.item_main2_keybox);
        editTitle = itemView.findViewById(R.id.item_main2_edit_title);
        cameraBtn = itemView.findViewById(R.id.item_main2_cameraBtn);
        removeBtn = itemView.findViewById(R.id.item_main2_removeBtn);
        all = itemView.findViewById(R.id.item_main2_all);
    }

//    public ImageView getVisibleButton() {
//        return visibleBtn;
//    }


}
