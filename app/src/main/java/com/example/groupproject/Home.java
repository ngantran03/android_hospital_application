package com.example.groupproject;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment; // Import Fragment class

import com.example.groupproject.fragments.MessageFragment;
import com.example.groupproject.fragments.HomeFragment;
import com.example.groupproject.fragments.AppointmentFragment;
import com.example.groupproject.profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.view.MenuItem;

public class Home extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private String username, role, userid;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        userid = intent.getStringExtra("userid");

        bottomNavigationView = findViewById(R.id.bottomnav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        // Create a HomeFragment instance
        Fragment homeFragment = new HomeFragment();

        // Pass data to the HomeFragment using arguments
        Bundle bundle = new Bundle();
        bundle.putString("username", username);
        bundle.putString("userid", userid);
        homeFragment.setArguments(bundle);

        // Load the HomeFragment
        loadFragment(homeFragment);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        int itemId = item.getItemId();

        if (itemId == R.id.home_nav) {
            fragment = new HomeFragment();
        } else if (itemId == R.id.appointment_nav) {
            fragment = new AppointmentFragment();
        } else if (itemId == R.id.message_nav) {
            fragment = new MessageFragment();
        } else if (itemId == R.id.profile_nav) {
            fragment = new ProfileFragment();
        }

        if (fragment != null) {
            Bundle bundle = new Bundle();
            bundle.putString("username", username);
            bundle.putString("userid", userid);
            fragment.setArguments(bundle);
            loadFragment(fragment);
        }
        return true;
    }
    void loadFragment(Fragment fragment) {
        //to attach fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.layout, fragment).commit();
    }
}