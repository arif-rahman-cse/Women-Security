package talha.com.bd.patha_shathi.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import talha.com.bd.patha_shathi.R;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserLoginActivity extends AppCompatActivity {
     private Button CreateAccouunt;
     private EditText Email;
     private EditText Password;
     private CheckBox KeepMeLoggedIn;
     private Button Login;
     private TextView ForgotPassword;
     private String etEmail;
     private String etPassword;
     AlertDialog.Builder builder;
    private static String URL_LOGIN ="https://talhatraining.org/suman/login.php";
     //private static String URL_LOGIN ="http://192.168.1.13/logo/log.php";
     //UserPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        if ("bn".equalsIgnoreCase(loadLang())) {
            setTheme(R.style.Theme_Small);
        } else if ("en".equalsIgnoreCase(loadLang())) {
            setTheme(R.style.Theme_Large);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);


        CreateAccouunt = (Button) findViewById(R.id.btn_CreateAccount);
        Email = (EditText) findViewById(R.id.et_Email);
        Password = (EditText) findViewById(R.id.et_Password);
        KeepMeLoggedIn = (CheckBox) findViewById(R.id.checkBox);
        Login = (Button) findViewById(R.id.btn_Login);
        ForgotPassword = (TextView) findViewById(R.id.textView3);

        //userPreferences = new UserPreferences(this);
        //userPreferences.checkLoggin();

            Login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(UserLoginActivity.this,MainActivity.class);
                    startActivity(intent);


                    final String email = Email.getText().toString().trim();
                    final String password =Password.getText().toString().trim();
                    if (!email.isEmpty() && !password.isEmpty())
                    {
                        login(email,password);
                    }
                    else
                    {
                        Toast.makeText(UserLoginActivity.this, "All field are required", Toast.LENGTH_SHORT).show();
                    }
                }


            });


        }


        private void login(final String email, final String password) {
            StringRequest request  = new StringRequest(Request.Method.POST, URL_LOGIN, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String success = jsonObject.getString("success");
                        JSONArray jsonArray =jsonObject.getJSONArray("login");
                        if (success.equals("1")){
                            for( int i = 0;i<jsonArray.length();i++){
                                JSONObject object= jsonArray.getJSONObject(i);
                                String name = object.getString("name");
                                String email = object.getString("email");
                                String id = object.getString("id");

                                //userPreferences.createPreferences(name,email,id);

                                Toast.makeText(UserLoginActivity.this, "Login success", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(UserLoginActivity.this,MainActivity.class);
                                startActivity(intent);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();

                        Toast.makeText(UserLoginActivity.this, "Login Error"+e.toString(), Toast.LENGTH_SHORT).show();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(UserLoginActivity.this, "Login Error"+error.toString(), Toast.LENGTH_SHORT).show();
                }
            })
            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String>param = new HashMap<>();
                    param.put("email",email);
                    param.put("password",password);
                    return param;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(request);
        }


        public String loadLang() {
            SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
            String lan = prefs.getString("lang", "");
            return lan;
        }

    public void createAccount(View view) {
        Intent intent = new Intent(this,UserRegisterActivity.class);
        startActivity(intent);
        finish();
    }
}
