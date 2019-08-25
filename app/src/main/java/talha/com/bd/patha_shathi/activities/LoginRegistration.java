package talha.com.bd.patha_shathi.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import talha.com.bd.patha_shathi.R;

public class LoginRegistration extends AppCompatActivity {
    Button button_login;
    TextView create_account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_registration);

        //Disable Keyboard appears
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        button_login = findViewById(R.id.btn_login);
        create_account = findViewById(R.id.link_signup);

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogin = new Intent(LoginRegistration.this, MainActivity.class);
                startActivity(intentLogin);

            }
        });
        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRegistration = new Intent(LoginRegistration.this, RegistrationPage.class);
                startActivity(intentRegistration);
            }
        });
    }
}
