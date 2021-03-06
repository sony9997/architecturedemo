package org.hed.archdemo.repository;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import org.hed.archdemo.model.User;
import org.hed.archdemo.repository.db.AppDatabase;
import org.hed.archdemo.repository.web.RetrofitHelper;
import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hedong on 2017/11/28.
 */

public class UserRepository {
    private static final String tag="UserRepository";
    @Inject
    AppDatabase appDatabase;

    @Inject
    RetrofitHelper retrofitHelper;

    public UserRepository(AppDatabase appDatabase, RetrofitHelper retrofitHelper) {
        this.appDatabase = appDatabase;
        this.retrofitHelper = retrofitHelper;
    }

    /**
     * 获取所有用户信息列表
     *
     * @return
     */
    public LiveData<List<User>> loadAllUsers() {
        retrofitHelper.getGithubApi().getUserList()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<List<User>>() {
                    @Override
                    public void accept(List<User> users) throws Exception {
                        Log.d(tag,"onNext:"+Thread.currentThread().getName());
                        Log.d(tag,"users:"+users);
                        appDatabase.userDao().addUsers(users);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<List<User>>() {
                    @Override
                    public void onNext(List<User> users) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(tag,"onError:"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(tag,"onComplete");
                    }
                });
        return appDatabase.userDao().loadAllUsers();
    }

    /**
     * @param user
     * @return
     */
    public Completable addUser(final User user) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                appDatabase.userDao().insertUser(user);
            }
        });
    }

    /**
     * 更新用户
     *
     * @param user
     * @return
     */
    public Completable updateUer(final User user) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                appDatabase.userDao().updateUser(user);
            }
        });
    }

    /**
     * 删除用户
     *
     * @param user
     * @return
     */
    public Completable deleteUser(final User user) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                appDatabase.userDao().deleteUser(user);
            }
        });
    }
}
