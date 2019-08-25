package talha.com.bd.patha_shathi.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import talha.com.bd.patha_shathi.R;

import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.cameraview.CameraView;
import com.google.android.cameraview.CameraViewImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
public class UserImageCaptureActivity extends AppCompatActivity {


    private static final int IMAGE_CHOOSE_CODE = 1;
    private static final int REQUEST_CAMERA = 2;
    ImageView userImage , cameraFrame;
    private Button takephoto,uploadphoto, captureAgainBtn, saveBtn;
    private CameraView cameraView;
    private TextView frameAlert;
    private boolean isCameraOk = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_image_capture);

        userImage = findViewById(R.id.userImage);
        takephoto = findViewById(R.id.btn_photo_capture);
        uploadphoto = findViewById(R.id.UploadPicture);
        cameraFrame = findViewById(R.id.camera_frame);
        captureAgainBtn = findViewById(R.id.btn_capture_again);
        saveBtn = findViewById(R.id.save_photo);
        frameAlert = findViewById(R.id.tv_FramInstruction);

        cameraView  = findViewById(R.id.camera_view);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);


        uploadphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ActivityCompat.requestPermissions(UserImageCaptureActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, IMAGE_CHOOSE_CODE);
            }
        });

        takephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraView.takePicture();
                cameraView.setVisibility(View.GONE);
                cameraFrame.setVisibility(View.GONE);
                userImage.setVisibility(View.VISIBLE);
                captureAgainBtn.setVisibility(View.VISIBLE);
                takephoto.setVisibility(View.GONE);
                uploadphoto.setVisibility(View.GONE);
                frameAlert.setVisibility(View.GONE);
                saveBtn.setVisibility(View.VISIBLE);
            }
        });

        captureAgainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraView.setVisibility(View.VISIBLE);
                cameraFrame.setVisibility(View.VISIBLE);
                userImage.setVisibility(View.GONE);
                captureAgainBtn.setVisibility(View.GONE);
                takephoto.setVisibility(View.VISIBLE);
                uploadphoto.setVisibility(View.VISIBLE);
                frameAlert.setVisibility(View.VISIBLE);
                saveBtn.setVisibility(View.GONE);
            }
        });


    }


    @Override
    protected void onResume() {
        super.onResume();

        if (isCameraOk){
            cameraView.start();
        }
       // ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);

        cameraView.setOnFocusLockedListener(new CameraViewImpl.OnFocusLockedListener() {
            @Override
            public void onFocusLocked() {
                //playShutterAnimation();
            }
        });


        cameraView.setOnPictureTakenListener(new CameraViewImpl.OnPictureTakenListener() {
            @Override
            public void onPictureTaken(final Bitmap bitmap, final int rotationDegrees) {
                //startSavingPhoto(bitmap, int rotationDegrees);
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        Matrix matrix = new Matrix();
                        matrix.postRotate(-rotationDegrees);
                        Bitmap myBitmap= Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                        userImage.setImageBitmap(myBitmap);
                    }
                });
            }
        });
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == IMAGE_CHOOSE_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, IMAGE_CHOOSE_CODE);
            } else {
                Toast.makeText(this, "need permission to access.", Toast.LENGTH_SHORT).show();
            }
        }
        else if (requestCode==REQUEST_CAMERA){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                isCameraOk = true;
                cameraView.start();
            }
            else {
                Toast.makeText(this, "need permission to access.", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode== Activity.RESULT_OK){
             if (requestCode==IMAGE_CHOOSE_CODE){
                Uri selectedImageUri= data.getData();
                //try {
                    if (selectedImageUri != null) {

                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        Cursor cursor = getContentResolver().query(selectedImageUri, filePathColumn, null, null, null);
                        cursor.moveToFirst();

                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        String picturePath = cursor.getString(columnIndex);
                        cursor.close();

                        Bitmap loadedBitmap = BitmapFactory.decodeFile(picturePath);

                        ExifInterface exif = null;
                        try {
                            File pictureFile = new File(picturePath);
                            exif = new ExifInterface(pictureFile.getAbsolutePath());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        int orientation = ExifInterface.ORIENTATION_NORMAL;

                        if (exif != null)
                            orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

                        switch (orientation) {
                            case ExifInterface.ORIENTATION_ROTATE_90:
                                loadedBitmap = rotateBitmap(loadedBitmap, 90);
                                break;
                            case ExifInterface.ORIENTATION_ROTATE_180:
                                loadedBitmap = rotateBitmap(loadedBitmap, 180);
                                break;

                            case ExifInterface.ORIENTATION_ROTATE_270:
                                loadedBitmap = rotateBitmap(loadedBitmap, 270);
                                break;
                        }

                        cameraView.setVisibility(View.GONE);
                        cameraFrame.setVisibility(View.GONE);
                        userImage.setVisibility(View.VISIBLE);
                        captureAgainBtn.setVisibility(View.VISIBLE);
                        takephoto.setVisibility(View.GONE);
                        uploadphoto.setVisibility(View.GONE);
                        frameAlert.setVisibility(View.GONE);
                        saveBtn.setVisibility(View.VISIBLE);

                        userImage.setImageBitmap(loadedBitmap);



                    /*    InputStream inputStream = getContentResolver().openInputStream(selectedImageUri); //read resource from uri
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        Glide.with(this)
                                .asBitmap()
                                .load(bitmap)
                                .into(userImage);
                        userImage.setVisibility(View.VISIBLE);
                        userImage.setImageBitmap(bitmap);*/
                    }
               /* } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }*/
            }
        }
    }


    public static Bitmap rotateBitmap(Bitmap bitmap, int degrees) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degrees);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    @Override
    protected void onPause() {
        cameraView.stop();
        super.onPause();
    }
}


