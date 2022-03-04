package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {

    BottomNavigationView _btmNavView;
    TextView profileUN;
    FirebaseAuth _auth;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        profileUN = findViewById(R.id.tv_profileUN);
        profileUN.setText(user.getUsername());

        setContentView(R.layout.activity_profile);
        _btmNavView = findViewById(R.id.bottomNavigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new PlayFragment()).commit();
        _btmNavView.setSelectedItemId(R.id.nav_profile);

        _btmNavView.setOnNavigationItemSelectedListener(item -> {
            Fragment _fragment = null;
            switch (item.getItemId()) {
                case R.id.nav_play:
                    _fragment = new PlayFragment();
                    break;
                case R.id.nav_leaderboards:
                    _fragment = new LeaderboardFragment();
                    break;
                case R.id.nav_profile:
                    _fragment = new ProfileFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container, _fragment).commit();

            return true;



        });

    }
}