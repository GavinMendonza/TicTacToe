package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainMenu extends AppCompatActivity {

    BottomNavigationView _btmNavView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        _btmNavView=findViewById(R.id.bottomNavigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container,new PlayFragment()).commit();
        _btmNavView.setSelectedItemId(R.id.nav_play);

    _btmNavView.setOnNavigationItemSelectedListener(item -> {
        Fragment _fragment=null;
        switch (item.getItemId()){
            case R.id.nav_play:
                _fragment=new PlayFragment();
                break;
            case  R.id.nav_leaderboards:
                _fragment=new LeaderboardFragment();
                break;
            case R.id.nav_profile:
                _fragment=new ProfileFragment();
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container,_fragment).commit();

        return true;
    });

//        _btmNavView.setOnItemSelectedListener(item -> {
//            Fragment _fragment=null;
//            switch (item.getItemId()){
//                case R.id.nav_play:
//                    _fragment=new PlayFragment();
//                    break;
//                case  R.id.nav_leaderboards:
//                    _fragment=new LeaderboardFragment();
//                    break;
//                case R.id.nav_profile:
//                    _fragment=new ProfileFragment();
//                    break;
//            }
//            getSupportFragmentManager().beginTransaction().replace(R.id.main_container,_fragment).commit();
//
//            return true;
//        });
    }

    public void goToSinglePlayer(View view) {
        Intent i = new Intent(getApplicationContext(), SinglePlayer.class);
        startActivity(i);

    }

    public void goToMultiPlayerDifferentDevice(View view) {
        Intent i = new Intent(getApplicationContext(), MultiPlayerDifferentDevice.class);
        startActivity(i);

    }

    public void goToMultiPlayerSameDevice(View view) {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.settings:
                Intent i = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}