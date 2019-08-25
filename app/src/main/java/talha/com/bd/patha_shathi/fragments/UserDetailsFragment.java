package talha.com.bd.patha_shathi.fragments;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import talha.com.bd.patha_shathi.R;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import android.widget.ImageView;

import android.widget.Toast;

import static android.app.Activity.RESULT_OK;



/**
 * A simple {@link Fragment} subclass.
 */
public class UserDetailsFragment extends Fragment implements View.OnClickListener{

    private static final int PICK_FROM_GALLERY = 0;
    private  static final int PICK_FROM_CAMERA = 1;

    public static final String ALLOW_KEY = "ALLOWED";
    public static final String CAMERA_PREF = "camera_pref";

    private EditText editTextEmail, editTextMobile, editTextJoind, editTextAddress, editTextCity,
            editTextCountry, editTextBirthDay;

    private ImageView imageViewProfileEdit, imageViewEmailEditSave, imageViewTakePhoto, imageViewProfileImage;

    public UserDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_details, container, false);



        // Find 4 image view editIconImage, saveIconImage, profileImage, ProfileImageShow.
        imageViewProfileEdit = view.findViewById(R.id.editProfile);
        imageViewEmailEditSave = view.findViewById(R.id.saveEditProfile);
        imageViewProfileImage = view.findViewById(R.id.prifileImageId);
        imageViewTakePhoto = view.findViewById(R.id.takePhotoId);

        // Find Profile fields
        editTextEmail = view.findViewById(R.id.emailFieldId);
        editTextMobile = view.findViewById(R.id.mobilefieldId);
        editTextJoind = view.findViewById(R.id.pathshathiJointFieldId);
        editTextAddress = view.findViewById(R.id.addressFieldId);
        editTextCity = view.findViewById(R.id.cityFieldId);
        editTextCountry = view.findViewById(R.id.countryFieldId);
        editTextBirthDay = view.findViewById(R.id.birthDayFieldId);

        // Set onclick listener on EditIcon, SaveIcon, TakePhotoIcon.
        imageViewProfileEdit.setOnClickListener(this);
        imageViewEmailEditSave.setOnClickListener(this);
        imageViewTakePhoto.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.editProfile){

            editTextEmail.setFocusableInTouchMode(true);
            editTextEmail.setFocusable(true);
            editTextEmail.setSelection(editTextEmail.getText().length());

            editTextMobile.setFocusableInTouchMode(true);
            editTextMobile.setFocusable(true);
            editTextMobile.setSelection(editTextMobile.getText().length());

            editTextJoind.setFocusableInTouchMode(true);
            editTextJoind.setFocusable(true);
            editTextJoind.setSelection(editTextJoind.getText().length());

            editTextAddress.setFocusableInTouchMode(true);
            editTextAddress.setFocusable(true);
            editTextAddress.setSelection(editTextAddress.getText().length());

            editTextCity.setFocusableInTouchMode(true);
            editTextCity.setFocusable(true);
            editTextCity.setSelection(editTextCity.getText().length());

            editTextCountry.setFocusableInTouchMode(true);
            editTextCountry.setFocusable(true);
            editTextCountry.setSelection(editTextCountry.getText().length());

            editTextBirthDay.setFocusableInTouchMode(true);
            editTextBirthDay.setFocusable(true);
            editTextBirthDay.setSelection(editTextBirthDay.getText().length());


            imageViewEmailEditSave.setVisibility(View.VISIBLE);
            imageViewProfileEdit.setVisibility(View.GONE);

            Toast.makeText(getActivity(), "Edit Your Profile",
                    Toast.LENGTH_SHORT).show();
        }

        else if (v.getId() == R.id.saveEditProfile){

            editTextEmail.setFocusable(false);
            editTextMobile.setFocusable(false);
            editTextJoind.setFocusable(false);
            editTextAddress.setFocusable(false);
            editTextCity.setFocusable(false);
            editTextCountry.setFocusable(false);
            editTextBirthDay.setFocusable(false);

            imageViewEmailEditSave.setVisibility(View.GONE);
            imageViewProfileEdit.setVisibility(View.VISIBLE);

            Toast.makeText(getContext(), "Save Your Data",
                    Toast.LENGTH_SHORT).show();
        }

        else if (v.getId() == R.id.takePhotoId){

            OptionDialog();
            Toast.makeText(getActivity(), "take your photo",
                    Toast.LENGTH_SHORT).show();
        }


    }

    private void OptionDialog() {

        final String [] option ={"Take Picture From Gallery","Take Picture by Camera "};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose Option");
        builder.setSingleChoiceItems(option, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(which == 0)
                {
                    openGellary();

                }
                if(which == 1)
                {


                    //UserPermission  for camera
                    if (ContextCompat.checkSelfPermission(getActivity(),
                            Manifest.permission.CAMERA)
                            != PackageManager.PERMISSION_GRANTED) {

                        if (getFromPref(getActivity(), ALLOW_KEY)) {

                            showSettingsAlert();

                        } else if (ContextCompat.checkSelfPermission(getActivity(),
                                Manifest.permission.CAMERA)
                                != PackageManager.PERMISSION_GRANTED) {
                            // Should we show an explanation?
                            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                                    Manifest.permission.CAMERA)) {
                                showAlert();
                            } else {
                                // No explanation needed, we can request the permission.
                                ActivityCompat.requestPermissions(getActivity(),
                                        new String[]{Manifest.permission.CAMERA},
                                        PICK_FROM_CAMERA);
                            }
                        }
                    }else {
                        openCamera();
                    }


                }

                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public static void saveToPreferences(Context context, String key,
                                         Boolean allowed) {
        SharedPreferences myPrefs = context.getSharedPreferences
                (CAMERA_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = myPrefs.edit();
        prefsEditor.putBoolean(key, allowed);
        prefsEditor.commit();
    }

    public static Boolean getFromPref(Context context, String key) {
        SharedPreferences myPrefs = context.getSharedPreferences
                (CAMERA_PREF, Context.MODE_PRIVATE);
        return (myPrefs.getBoolean(key, false));
    }

    private void showAlert() {
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("App needs to access the Camera.");
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "DONT ALLOW",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "ALLOW",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        ActivityCompat.requestPermissions(getActivity(),
                                new String[]{Manifest.permission.CAMERA},
                                PICK_FROM_CAMERA);

                    }
                });
        alertDialog.show();
    }

    private void showSettingsAlert() {
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("App needs to access the Camera.");
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "DONT ALLOW",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        //finish();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "SETTINGS",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });
        alertDialog.show();
    }

    @Override
    public void onRequestPermissionsResult
            (int requestCode, String permissions[], int[] grantResults) {

        switch (requestCode) {
            case PICK_FROM_CAMERA:
                {
                for (int i = 0, len = permissions.length; i < len; i++) {
                    String permission = permissions[i];
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        boolean showRationale =
                                ActivityCompat.shouldShowRequestPermissionRationale
                                        (getActivity(), permission);
                        if (showRationale) {
                            showAlert();
                        } else if (!showRationale) {
                            // user denied flagging NEVER ASK AGAIN
                            // you can either enable some fall back,
                            // disable features of your app
                            // or open another dialog explaining
                            // again the permission and directing to
                            // the app setting
                            saveToPreferences(getActivity(), ALLOW_KEY, true);
                        }
                    }
                }
            }

            // other 'case' lines to check for other
            // permissions this app might request

        }
    }




    private void openGellary() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_FROM_GALLERY);
    }

    private void openCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, PICK_FROM_CAMERA);
            Toast.makeText(getActivity(), "Camera is Calling", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == PICK_FROM_GALLERY && resultCode == RESULT_OK){
            Uri selectedImage = data.getData();
            imageViewProfileImage.setImageURI(selectedImage);
            Toast.makeText(getActivity(), "Gallery is Calling", Toast.LENGTH_SHORT).show();

        }

        if (requestCode == PICK_FROM_CAMERA && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageViewProfileImage.setImageBitmap(imageBitmap);
        }

    }


}
