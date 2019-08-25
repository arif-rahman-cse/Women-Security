package talha.com.bd.patha_shathi.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import talha.com.bd.patha_shathi.R;
import talha.com.bd.patha_shathi.fragments.HomeFragment;
import talha.com.bd.patha_shathi.fragments.MyPathasathiFragment;
import talha.com.bd.patha_shathi.fragments.NotificationFragment;
import talha.com.bd.patha_shathi.fragments.UserDetailsFragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements HomeFragment.OpenMypathaSathi {

    private BottomNavigationView bottomNavigationView;
    private HomeFragment homeFragment;
    private MyPathasathiFragment myPathasathiFragment;
    private UserDetailsFragment userDetailsFragment;
    private NotificationFragment notificationFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeFragment = new HomeFragment();
        myPathasathiFragment = new MyPathasathiFragment();
        notificationFragment = new NotificationFragment();
        userDetailsFragment = new UserDetailsFragment();

        bottomNavigationView  = findViewById(R.id.bottom_navigation);

        replaceFragment(homeFragment);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        replaceFragment(homeFragment);
                        return true;
                    case R.id.nav_user:
                        replaceFragment(userDetailsFragment);
                        return true;
                    case R.id.nav_notification:
                        replaceFragment(notificationFragment);
                        return true;
                    default:
                        return false;
                }
            }
        });

    }

    //Just replace the one fragment to another fragment
    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_container, fragment);
        fragmentTransaction.commit();

    }

    @Override
    public void openMypathaSathiFragment() {
    replaceFragment(myPathasathiFragment);
    }

}
