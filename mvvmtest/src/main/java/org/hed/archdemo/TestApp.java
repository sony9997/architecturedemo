package org.hed.archdemo;

import android.app.Application;

import org.hed.archdemo.injection.DaggerUserComponent;
import org.hed.archdemo.injection.UserComponent;
import org.hed.archdemo.injection.UserModule;

/**
 * Created by hedong on 2017/11/28.
 */

public class TestApp extends Application {
    private final UserComponent userComponent = createUserComponent();
    @Override
    public void onCreate() {
        super.onCreate();
    }

    protected UserComponent createUserComponent() {
        return DaggerUserComponent.builder()
                .userModule(new UserModule(this))
                .build();
    }

    public UserComponent getUserComponent() {
        return userComponent;
    }
}
