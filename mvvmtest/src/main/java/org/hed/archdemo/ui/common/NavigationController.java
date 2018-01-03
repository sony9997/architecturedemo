package org.hed.archdemo.ui.common;

import android.support.v4.app.FragmentManager;

import org.hed.archdemo.R;
import org.hed.archdemo.activity.MainActivity;
import org.hed.archdemo.ui.user.UsersFragment;

import javax.inject.Inject;

/**
 * Created by hedong on 2018/1/3.
 */

public class NavigationController {
    private final int containerId;
    private final FragmentManager fragmentManager;
    @Inject
    public NavigationController(MainActivity mainActivity) {
        this.containerId = R.id.fragment_container;
        this.fragmentManager = mainActivity.getSupportFragmentManager();
    }

    public void navigateToUser() {
        UsersFragment usersFragment = new UsersFragment();
        fragmentManager.beginTransaction()
                .replace(containerId, usersFragment)
                .commit();
    }
}
