package org.hed.archdemo.di;

import android.app.Application;
import android.arch.persistence.room.Room;

import org.hed.archdemo.repository.db.AppDatabase;
import org.hed.archdemo.repository.db.dao.UserDao;
import org.hed.archdemo.repository.web.GithubApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hedong on 2018/1/2.
 */
@Module(includes = ViewModelModule.class)
class AppModule {
    @Singleton
    @Provides
    GithubApi provideGithubService() {
        return new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(GithubApi.class);
    }

    @Singleton @Provides
    AppDatabase provideDb(Application app) {
        return Room.databaseBuilder(app, AppDatabase.class,"github.db").build();
    }

    @Singleton @Provides
    UserDao provideUserDao(AppDatabase db) {
        return db.userDao();
    }
}
