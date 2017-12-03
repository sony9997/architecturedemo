package org.hed.archdemo.injection;

import javax.inject.Singleton;

import org.hed.archdemo.viewmodel.UserViewModel;
import dagger.Component;

/**
 * Created by hedong on 2017/11/28.
 */
@Singleton
@Component(modules = {UserModule.class})
public interface UserComponent {
    void inject(UserViewModel userViewModel);

    interface Injectable {
        void inject(UserComponent userComponent);
    }
}
