package com.example.sunrinhack2019.Main1;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sunrinhack2019.Auth.UserDB;
import com.example.sunrinhack2019.GetTimeDate;
import com.example.sunrinhack2019.KeyModel;
import com.example.sunrinhack2019.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import me.aflak.libraries.callback.FingerprintDialogCallback;
import me.aflak.libraries.dialog.FingerprintDialog;

public class MainFragment1 extends android.support.v4.app.Fragment {

    public static MainFragment1 newInstance(){
        MainFragment1 fragment = new MainFragment1();
        return fragment;
    }




    Context context;

    TextView name, title, password;

    ImageView cameraBtn;
    CircleImageView image;

    Button uploadBtn;

    private String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}; //권한 설정 변수
    private static final int MULTIPLE_PERMISSIONS = 101; //권한 동의 여부 문의 후 CallBack 함수에 쓰일 변수

    private static final int PICK_FROM_CAMERA = 1; //카메라 촬영으로 사진 가져오기
    private static final int PICK_FROM_ALBUM = 2; //앨범에서 사진 가져오기
    private static final int CROP_FROM_CAMERA = 3; //가져온 사진을 자르기 위한 변수

    Uri photoUri;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private FirebaseAuth firebaseAuth;


    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        final View v =  inflater.inflate(R.layout.activity_main1, container, false);

        context = container.getContext();
        image = v.findViewById(R.id.main1_image);
        title = v.findViewById(R.id.main1_title);
        password = v.findViewById(R.id.main1_password);

        cameraBtn = v.findViewById(R.id.cameraBtn);
        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPermissions();
                takePhoto();
            }
        });

        UserDB userDB = new UserDB();
        name = v.findViewById(R.id.main2_username);
        name.setText(userDB.getUserNickname(context));


        uploadBtn = v.findViewById(R.id.uploadBtn);
        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GetTimeDate getTimeDate = new GetTimeDate();
                firebaseAuth = FirebaseAuth.getInstance();

                final String articleKey = firebaseAuth.getUid()+getTimeDate.getDate()+getTimeDate.getTime()+getTimeDate.getSec();

                KeyModel model = new KeyModel();
                model.setTitle(title.getText().toString());
                model.setPassword(password.getText().toString());

                Bitmap img = ((BitmapDrawable) image.getDrawable()).getBitmap();
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();

                img.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
                byte[] imageOut = outStream.toByteArray();
                String profileImageBase64 = Base64.encodeToString(imageOut, Base64.NO_WRAP);

                model.setImg(profileImageBase64);
                model.setDate(getTimeDate.getDate());
                model.setTime(getTimeDate.getTime());
                model.setUid(firebaseAuth.getUid());
                model.setCount(0);
                model.setKid(articleKey);
                databaseReference.child("keys").push().setValue(model);

                title.setText("");
                password.setText("");
                image.setImageResource(R.drawable.default_image);
                Toast.makeText(context, "성공적으로 추가했습니다!", Toast.LENGTH_SHORT).show();



            }
        });
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        try { //저는 bitmap 형태의 이미지로 가져오기 위해 아래와 같이 작업하였으며 Thumbnail을 추출하였습니다.

            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), photoUri);

            Matrix rotateMatrix = new Matrix();
            rotateMatrix.postRotate(90); //-360~360

            bitmap =  Bitmap.createBitmap(bitmap, 0, 0,
                    bitmap.getWidth(), bitmap.getHeight(), rotateMatrix, false);

            Bitmap thumbImage = ThumbnailUtils.extractThumbnail(bitmap, 480, 480);
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            thumbImage.compress(Bitmap.CompressFormat.JPEG, 10, bs); //이미지가 클 경우 OutOfMemoryException 발생이 예상되어 압축

            image.setImageBitmap(thumbImage);

            byte[] image = bs.toByteArray();
            String profileImageBase64 = Base64.encodeToString(image, Base64.NO_WRAP);
//            Intent intent = new Intent(context, UploadActivity.class);
//            intent.putExtra("image", profileImageBase64);
//            startActivity(intent);
//

            //imageView.setImageBitmap(thumbImage);
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        }
    }

    private void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //사진을 찍기 위하여 설정합니다.
        //intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
        File photoFile = null;
        try {
            photoFile = createImageFile();
        } catch (IOException e) {
            Toast.makeText(context, "이미지 처리 오류! 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
        }
        if (photoFile != null) {
            photoUri = FileProvider.getUriForFile(context,
                    "com.example.sunrinhack2019.provider", photoFile); //FileProvider의 경우 이전 포스트를 참고하세요.
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri); //사진을 찍어 해당 Content uri를 photoUri에 적용시키기 위함
            startActivityForResult(intent, PICK_FROM_CAMERA);
        }
    }


    // Android M에서는 Uri.fromFile 함수를 사용하였으나 7.0부터는 이 함수를 사용할 시 FileUriExposedException이
    // 발생하므로 아래와 같이 함수를 작성합니다. 이전 포스트에 참고한 영문 사이트를 들어가시면 자세한 설명을 볼 수 있습니다.
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("HHmmss").format(new Date());
        String imageFileName = "IP" + timeStamp + "_";
        File storageDir = new File(Environment.getExternalStorageDirectory() + "/test/"); //test라는 경로에 이미지를 저장하기 위함
        if (!storageDir.exists()) {
            storageDir.mkdirs();
        }
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );
        return image;
    }

    private boolean checkPermissions() {
        int result;
        List<String> permissionList = new ArrayList<>();
        for (String pm : permissions) {
            result = ContextCompat.checkSelfPermission(context, pm);
            if (result != PackageManager.PERMISSION_GRANTED) { //사용자가 해당 권한을 가지고 있지 않을 경우 리스트에 해당 권한명 추가
                permissionList.add(pm);
            }
        }
        if (!permissionList.isEmpty()) { //권한이 추가되었으면 해당 리스트가 empty가 아니므로 request 즉 권한을 요청합니다.
            ActivityCompat.requestPermissions(getActivity(), permissionList.toArray(new String[permissionList.size()]), MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MULTIPLE_PERMISSIONS: {
                if (grantResults.length > 0) {
                    for (int i = 0; i < permissions.length; i++) {
                        if (permissions[i].equals(this.permissions[0])) {
                            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                                showNoPermissionToastAndFinish();
                            }
                        } else if (permissions[i].equals(this.permissions[1])) {
                            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                                showNoPermissionToastAndFinish();

                            }
                        } else if (permissions[i].equals(this.permissions[2])) {
                            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                                showNoPermissionToastAndFinish();

                            }
                        }
                    }
                } else {
                    showNoPermissionToastAndFinish();
                }
                return;
            }
        }
    }


    //권한 획득에 동의를 하지 않았을 경우 아래 Toast 메세지를 띄우며 해당 Activity를 종료시킵니다.
    private void showNoPermissionToastAndFinish() {
        Toast.makeText(context, "권한이 부족해 종료합니다.", Toast.LENGTH_SHORT).show();
        getActivity().finish();
    }

}
