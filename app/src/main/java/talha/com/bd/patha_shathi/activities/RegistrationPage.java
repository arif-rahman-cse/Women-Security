package talha.com.bd.patha_shathi.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import talha.com.bd.patha_shathi.R;

public class RegistrationPage extends AppCompatActivity {
    TextView signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        //Disable Keyboard appears
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        signIn = findViewById(R.id.link_signin);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = new Intent(RegistrationPage.this,LoginRegistration.class);
                startActivity(signInIntent);
            }
        });
    }
}
