package com.example.sunrinhack2019.Main1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sunrinhack2019.GetTimeDate;
import com.example.sunrinhack2019.KeyModel;
import com.example.sunrinhack2019.MainActivity;
import com.example.sunrinhack2019.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class UploadActivity extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private FirebaseAuth firebaseAuth;

    ImageView image;
    EditText title, info, password;
    Button uploadBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        getElements();

        Intent getIntent = getIntent();

        final String data = getIntent.getStringExtra("image");
        byte[] bytePlainOrg = Base64.decode(data, Base64.NO_WRAP);

        ByteArrayInputStream inStream = new ByteArrayInputStream(bytePlainOrg);
        Bitmap bm = BitmapFactory.decodeStream(inStream);

        image.setImageBitmap(bm);

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GetTimeDate getTimeDate = new GetTimeDate();
                firebaseAuth = FirebaseAuth.getInstance();

                final String articleKey = firebaseAuth.getUid()+getTimeDate.getDate()+getTimeDate.getTime()+getTimeDate.getSec();

                KeyModel model = new KeyModel();
                model.setTitle(title.getText().toString());
                model.setInfo(info.getText().toString());
                model.setPassword(password.getText().toString());
                model.setImg(data);
                model.setDate(getTimeDate.getDate());
                model.setTime(getTimeDate.getTime());
                model.setUid(firebaseAuth.getUid());
                model.setCount(0);
                model.setKid(articleKey);
                databaseReference.child("keys").push().setValue(model);


                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {

                        URL url = null;
                        try {
                            url = new URL("http://54.180.107.127:8000/add/");
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }

                        HttpURLConnection http = null;   // 접속
                        try {
                            http = (HttpURLConnection) url.openConnection();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        //--------------------------
                        //   전송 모드 설정 - 기본적인 설정이다
                        //--------------------------
                        http.setDefaultUseCaches(false);
                        http.setDoInput(true);                         // 서버에서 읽기 모드 지정
                        http.setDoOutput(true);                       // 서버로 쓰기 모드 지정
                        try {
                            http.setRequestMethod("POST");         // 전송 방식은 POST
                        } catch (ProtocolException e) {
                            e.printStackTrace();
                        }

                        // 서버에게 웹에서 <Form>으로 값이 넘어온 것과 같은 방식으로 처리하라는 걸 알려준다
                        //http.setRequestProperty("content-type", "application/x-www-form-urlencoded");
                        //--------------------------
                        //   서버로 값 전송
                        //--------------------------
                        StringBuffer buffer = new StringBuffer();
                        buffer.append("user_uuid").append("=").append(firebaseAuth.getUid()).append("&");                 // php 변수에 값 대입
                        buffer.append("key_uuid").append("=").append(articleKey).append("&");   // php 변수 앞에 '$' 붙이지 않는다
                        buffer.append("key_img").append("=").append(data);

                        OutputStreamWriter outStream = null;
                        try {
                            outStream = new OutputStreamWriter(http.getOutputStream(), "EUC-KR");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        PrintWriter writer = new PrintWriter(outStream);
                        writer.write(buffer.toString());
                        writer.flush();
                        //--------------------------
                        //   서버에서 전송받기
                        //--------------------------
                        InputStreamReader tmp = null;
                        try {
                            tmp = new InputStreamReader(http.getInputStream(), "EUC-KR");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        BufferedReader reader = new BufferedReader(tmp);
                        StringBuilder builder = new StringBuilder();
                        String str = null;
                        while (true) {
                            try {
                                if (!((str = reader.readLine()) != null)) break;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }       // 서버에서 라인단위로 보내줄 것이므로 라인단위로 읽는다
                            builder.append(str + "\n");                     // View에 표시하기 위해 라인 구분자 추가
                        }
                        String myResult = builder.toString();                       // 전송결과를 전역 변수에 저장
                        //((TextView)(findViewById(R.id.text_result))).setText(myResult);
                        //Toast.makeText(UploadActivity.this, myResult, Toast.LENGTH_SHORT).show();
                    }
                });


                finish();
            }
        });

    }

    void getElements() {
        image = findViewById(R.id.upload_image);
        title = findViewById(R.id.upload_title);
        info = findViewById(R.id.upload_info);
        password = findViewById(R.id.upload_password);
        uploadBtn = findViewById(R.id.upload_btn);
    }
}
