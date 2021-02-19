package com.example.studyguide_assoiateandroid.UserInterface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.Menu;

import com.example.studyguide_assoiateandroid.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class NavigationLayoutTry2 extends AppCompatActivity {
    // info from - https://developer.android.com/guide/navigation/navigation-ui#add_a_navigation_drawer

    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_drawer_navigation);

        DrawerLayout drawerLayout = findViewById(R.id.my_drawer_layout);
        CollapsingToolbarLayout layout = findViewById(R.id.collapsing_toolbar_layout);
        // where to display options
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = findViewById(R.id.my_nav_view);

        // define the fragment things will be displayed in
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.my_host_fragment);
        // navController tells the NavigationUI where to load the fragment
        NavController navController = navHostFragment.getNavController();
       // NavController navController = Navigation.findNavController(this, R.id.my_host_fragment);

        // AppBarConfiguration defines fragment hierarchy
        appBarConfiguration = new AppBarConfiguration.Builder( R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawerLayout)
                .build();

        // register the navigation controller
       // NavigationUI.setupWithNavController(layout, toolbar, navController, appBarConfiguration);
        //NavigationUI.setupWithNavController(toolbar, navController);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        // show three line symbol
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        NavigationUI.setupWithNavController(bottomNav, navController);



        // **************** use if using default action bar ******************
        // NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.drawer_layout_ex, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.my_host_fragment);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}