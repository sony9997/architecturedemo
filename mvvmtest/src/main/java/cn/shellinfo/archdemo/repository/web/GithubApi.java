package org.hed.archdemo.repository.web;

import java.util.List;

import org.hed.archdemo.model.User;
import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by hedong on 2017/11/30.
 */

public interface GithubApi {
    @GET("/users")
    Observable<List<User>> getUserList();
}
