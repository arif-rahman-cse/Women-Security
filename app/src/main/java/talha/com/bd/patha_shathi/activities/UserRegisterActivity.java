package talha.com.bd.patha_shathi.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import talha.com.bd.patha_shathi.models.MySingletone;
import talha.com.bd.patha_shathi.R;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserRegisterActivity extends AppCompatActivity {

    private TextView titleTV,complateTV;
    private EditText nameET,emailET,passwordET;
    private ImageView imageIV;
    private Button registrationBtn;
    private String name,email,password;

     private static final String TAG = "UserRegistationActivity";

    AlertDialog.Builder builder;



      private static String URL_REGISTATION="https://talhatraining.org/suman/registation.php";
      //private static String URL_REGISTATION ="http://192.168.1.13/logo/reg.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if ("bn".equalsIgnoreCase(loadLang())) {
            setTheme(R.style.Theme_Small);
        } else if ("en".equalsIgnoreCase(loadLang())) {
            setTheme(R.style.Theme_Large);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        //textview
        titleTV = findViewById(R.id.title_ID);
        complateTV = findViewById(R.id.com_ID);

        //imageView
        imageIV = findViewById(R.id.logo_ID);

        //EditTextView

        //button
        registrationBtn = findViewById(R.id.button2);

        nameET = findViewById(R.id.name_ID);
        emailET = findViewById(R.id.email_ID);
        passwordET = findViewById(R.id.password_ID);

        registrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                
                name = nameET.getText().toString();
                email = emailET.getText().toString();
                password = passwordET.getText().toString();
                if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty())
                {
                    registation(name,email,password);

                }
                else {
                    Toast.makeText(UserRegisterActivity.this, "All field are required", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void registation(final String name, final String email, final String password) {

      StringRequest request = new StringRequest(Request.Method.POST, URL_REGISTATION, new Response.Listener<String>() {
          @Override
          public void onResponse(String response) {
              try {

                  JSONObject jsonObject = new JSONObject(response);
                  String success = jsonObject.getString("success");
                  if (success.equals("1"))
                  {
                      Intent intent = new Intent(UserRegisterActivity.this,UserLoginActivity.class);
                      nameET.setText("");
                      emailET.setText("");
                      passwordET.setText("");
                      startActivity(intent);
                      Toast.makeText(UserRegisterActivity.this, "Registation Success", Toast.LENGTH_SHORT).show();
                  }



              } catch (JSONException e) {
                  e.printStackTrace();
                  Toast.makeText(UserRegisterActivity.this, "Registation Error!"+e.toString(), Toast.LENGTH_SHORT).show();
              }

          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {

              Toast.makeText(UserRegisterActivity.this,"Registation Error"+error.toString(),Toast.LENGTH_SHORT).show();
          }
      })
      {
          @Override
          protected Map<String, String> getParams() throws AuthFailureError {
              HashMap<String,String>param = new HashMap<String, String>();
              param.put("name",name);
              param.put("email",email);
              param.put("password",password);

              return param;
          }
      };
        MySingletone.getInstance(UserRegisterActivity.this).addToRequestQueue(request);
    }


    public String loadLang() {
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String lan = prefs.getString("lang", "");
        return lan;
    }


    public void logIn(View view) {
        Intent intent = new Intent(this,UserLoginActivity.class);
        startActivity(intent);
        finish();
    }
}
