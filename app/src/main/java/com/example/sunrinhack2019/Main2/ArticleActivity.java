package com.example.sunrinhack2019.Main2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sunrinhack2019.R;

import java.io.ByteArrayInputStream;

public class ArticleActivity extends AppCompatActivity {

    TextView title, info, password;
    ImageView image, visibleBtn;
    Button changeBtn, deleteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        getElements();

        final Intent getIntent = getIntent();

        title.setText(getIntent.getStringExtra("title"));
        info.setText(getIntent.getStringExtra("info"));
        password.setText("****");

        final String data = getIntent.getStringExtra("image");
        byte[] bytePlainOrg = Base64.decode(data, Base64.NO_WRAP);

        ByteArrayInputStream inStream = new ByteArrayInputStream(bytePlainOrg);
        Bitmap bm = BitmapFactory.decodeStream(inStream);

        image.setImageBitmap(bm);

        visibleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(visibleBtn.getTag().equals("n")){
                    visibleBtn.setImageResource(R.drawable.ic_visibility_black_24dp);
                    password.setText(getIntent.getStringExtra("password"));
                    visibleBtn.setTag("y");
                }
                else{
                    visibleBtn.setImageResource(R.drawable.ic_visibility_off_black_24dp);
                    password.setText("****");
                    visibleBtn.setTag("n");
                }
            }
        });

        changeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    void getElements(){
        title = findViewById(R.id.article_title);
        info = findViewById(R.id.article_info);
        password = findViewById(R.id.article_password);
        image = findViewById(R.id.article_image);
        visibleBtn = findViewById(R.id.article_visible);
        changeBtn = findViewById(R.id.article_changepw);
        deleteBtn = findViewById(R.id.article_delete);

    }
}
