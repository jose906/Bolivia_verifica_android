package com.bolivia.verifica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.bolivia.verifica.fragments.Home2Fragment;
import com.bolivia.verifica.fragments.HomeFragment;
import com.bolivia.verifica.fragments.MyNewsFragmnet;
import com.bolivia.verifica.fragments.SearchFragment;
import com.bolivia.verifica.fragments.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottom_navigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openFragment(Home2Fragment.newInstance());
        bottom_navigation = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        openFragment(Home2Fragment.newInstance());
                        break;

                    case R.id.sections:
                        openFragment(HomeFragment.newInstance());
                        break;
                    case R.id.search_post:
                        openFragment(SearchFragment.newInstance());
                        break;
                    case R.id.settings:
                        openFragment(SettingsFragment.newInstance());
                        break;
                    case R.id.MisSecciones:
                        openFragment(MyNewsFragmnet.newInstance());
                        break;


                }

                return true;
            }

        });


    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.containerView, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
      //  super.onBackPressed();

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);

        startActivity(intent);

    }
}