package org.hed.archdemo.injection;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import org.hed.archdemo.repository.UserRepository;
import org.hed.archdemo.repository.db.AppDatabase;
import org.hed.archdemo.repository.web.RetrofitHelper;
import dagger.Module;
import dagger.Provides;

/**
 * Created by hedong on 2017/11/28.
 */
@Module
public class UserModule {
    private Application application;
    public UserModule(Application application){
        this.application=application;
    }

    @Provides
    Context applicationContext() {
        return application;
    }

    @Provides
    @Singleton
    UserRepository providesUserRepository(AppDatabase appDatabase ,RetrofitHelper retrofitHelper) {
        return new UserRepository(appDatabase,retrofitHelper);
    }

    @Provides
    @Singleton
    AppDatabase providesEventDatabase(Context context) {
//        return Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME).build();
        return Room.inMemoryDatabaseBuilder(context,AppDatabase.class).build();
    }

    @Provides
    @Singleton
    RetrofitHelper providesRetrofitHelper(){
        return new RetrofitHelper();
    }
}
