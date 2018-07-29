package com.example.isabelmendes.project1;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.isabelmendes.project1.fragments.ListaPedidosFragments;
import com.example.isabelmendes.project1.fragments.OrdersFragment;
import com.example.isabelmendes.project1.fragments.SettingsFragment;
import com.example.isabelmendes.project1.fragments.Tela1Fragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            displayFragment(R.id.nav_tela1);
        }
    }

    private void displayFragment (int fragmentId) {
        Class fragmentClass;
        Fragment fragment = null;
        int backStackEntryCount;
        backStackEntryCount = getFragmentManager().getBackStackEntryCount();

        for (int j = 0; j < backStackEntryCount; j++) {
            getFragmentManager().popBackStack();
        }
        try {
            switch (fragmentId) {
                case R.id.nav_tela1:
                    fragmentClass = Tela1Fragment.class;
                    fragment = (Fragment) fragmentClass.newInstance();
                    break;
                case R.id.nav_tela2:
// fragmentClass = Tela2Fragment.class;
//                    fragment = (Fragment) fragmentClass.newInstance();
                    break;


                case R.id.nav_listaPedidos:
                    fragmentClass = OrdersFragment.class;
                    fragment = (Fragment) fragmentClass.newInstance();
                    break;

                case R.id.nav_settings:
                    fragmentClass = SettingsFragment.class;
                    fragment = (Fragment) fragmentClass.newInstance();
                    break;

                default:
                    fragmentClass = Tela1Fragment.class;
                    fragment = (Fragment) fragmentClass.newInstance();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container,
                    fragment).commit();
        }
        DrawerLayout drawer = (DrawerLayout)
                findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        displayFragment(item.getItemId());
        return true;
    }
}
