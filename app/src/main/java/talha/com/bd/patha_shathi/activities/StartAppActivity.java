package talha.com.bd.patha_shathi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import talha.com.bd.patha_shathi.R;


public class StartAppActivity extends AppCompatActivity {

    private TextView textTV;
    Handler handler;

    LinearLayout l1,l2;
    Button btnsub;
    Animation uptodown,downtoup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_app);

        textTV = findViewById(R.id.title_id);

        l1 =  findViewById(R.id.l1);
        l2 =  findViewById(R.id.l2);

        uptodown = AnimationUtils.loadAnimation(this,R.anim.up_to_down);
        downtoup = AnimationUtils.loadAnimation(this,R.anim.down_to_up);
        l1.setAnimation(uptodown);
        l2.setAnimation(downtoup);

        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.mytrasition);
        textTV.startAnimation(myanim);
        handler = new Handler();

        handler.postDelayed(new Runnable() {

            @Override

            public void run() {

                Intent intent = new Intent(StartAppActivity.this,LoginRegistration.class);
                startActivity(intent);

                finish();

            }

        },3000);

    }

}
