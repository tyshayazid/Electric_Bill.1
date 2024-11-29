package com.example.electricbill;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import androidx.appcompat.app.ActionBarDrawerToggle;

public class homepage extends AppCompatActivity {

    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage); // Ensure this is the correct layout file

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout, new HomeFragment());
            transaction.commit();
        }

        // Initialize views
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Set up toolbar and navigation drawer
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Default fragment to load when the activity is created
        if (savedInstanceState == null) {
            replaceFragment(new HomeFragment());
            navigationView.setCheckedItem(R.id.nav_home);
        }

        // Handle navigation item selection from the drawer
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                // Handle drawer menu item clicks
                if (item.getItemId() == R.id.nav_home) {
                    replaceFragment(new HomeFragment());
                    return true;
                } else if (item.getItemId() == R.id.nav_formula) {
                    replaceFragment(new FormulaFragment());
                    return true;
                } else if (item.getItemId() == R.id.nav_about) {
                    replaceFragment(new AboutUsFragment());
                    return true;
                } else if (item.getItemId() == R.id.nav_logout) {
                    // Handle logout functionality here
                    logoutUser();
                    return true;
                } else {
                    return false;
                }

                // Close the drawer after an item is selected

            }
        });

        // Set up bottom navigation
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
                replaceFragment(new HomeFragment());
                return true;
            } else if (item.getItemId() == R.id.nav_formula) {
                replaceFragment(new FormulaFragment());
                return true;
            } else if (item.getItemId() == R.id.nav_about) {
                replaceFragment(new AboutUsFragment());
                return true;
            } else {
                return false;
            }
        });
    }

    // Method to replace fragments
    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    // Method to log out the user
    private void logoutUser() {
        // If you have session data or shared preferences, clear them here
        // For example, if using SharedPreferences to store login status:
        // SharedPreferences sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE);
        // SharedPreferences.Editor editor = sharedPref.edit();
        // editor.clear();
        // editor.apply();

        // Navigate to the login screen
        Intent loginIntent = new Intent(homepage.this, login.class);
        startActivity(loginIntent);
        finish(); // Optional: Call finish to remove the current activity from the back stack
    }
}
