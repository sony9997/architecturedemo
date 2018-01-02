package org.hed.archdemo.activity;

import android.arch.lifecycle.LifecycleActivity;
import android.os.Bundle;

import org.hed.archdemo.R;
import org.hed.archdemo.ui.fragment.UsersFragment;

public class MainActivity extends LifecycleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            UsersFragment usersFragment=new UsersFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, usersFragment,"UserFragment").commit();
        }
    }
}
