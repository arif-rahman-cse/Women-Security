
package talha.com.bd.patha_shathi.activities;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import androidx.appcompat.app.AlertDialog;
import talha.com.bd.patha_shathi.R;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;


public class UserAuthenticationActivity extends AppCompatActivity {

    private static final String TAG = "UserAuthenticationActiv";
    Button regButton, loginButton, button;
    ImageView myLanguage;
    TextView textView3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if("en".equalsIgnoreCase(loadLang()))
        {

        }
        else if("bn".equalsIgnoreCase(loadLang()))
        {

        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_authentication);


        myLanguage =findViewById(R.id.myLanguage);
        myLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChangeLanguageDialog();
            }


        });
        regButton = findViewById(R.id.regButton);
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regPageIntent= new Intent(UserAuthenticationActivity.this,UserRegisterActivity.class);
                startActivity(regPageIntent);
            }
        });

        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logPageIntent =new Intent(UserAuthenticationActivity.this, UserLoginActivity.class);
                startActivity(logPageIntent);
            }
        });

    }
    private void showChangeLanguageDialog() {

        final String[] listItem ={"English","বাংলা"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(UserAuthenticationActivity.this);
        mBuilder.setTitle("Change language....");
        mBuilder.setSingleChoiceItems(listItem, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int selectLanguage) {
                if(selectLanguage == 0)
                {
                    setLocale("en", "EN");
                    Toast.makeText(UserAuthenticationActivity.this, "Successfully selected English", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(MainActivity.this, loadLang(), Toast.LENGTH_SHORT).show();
                    recreate();
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }
                else if(selectLanguage==1)
                {
                    setLocale("bn", "BN");
                    Toast.makeText(UserAuthenticationActivity.this, "সফলভাবে বাংলা নির্বাচিত", Toast.LENGTH_SHORT).show();
                    // Toast.makeText(MainActivity.this, loadLang(), Toast.LENGTH_SHORT).show();
                    recreate();
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }
                dialog.dismiss();
            }
        });
        AlertDialog mDialog= mBuilder.create();
        mDialog.show();
    }

    private void setLocale(String lang, String country)
    {
        Locale locale =new Locale(lang,country);
        Configuration config =new Configuration();
        config.locale=locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("lang",lang);
        editor.putString("country",country);
        editor.apply();
    }


    private String loadLang() {
        SharedPreferences prefs = getSharedPreferences("Settings",Activity.MODE_PRIVATE);
        String lan=prefs.getString("lang","");
        return lan;
    }
}
