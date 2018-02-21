package network;


import java.util.List;

import model.Follower;
import model.User;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface GithubService {
    String BASE_URL= "https://api.github.com/";
    @GET("users/{user}/followers")
    Observable<List<Follower>> getfollowers(@Path("user") String user);

    @GET("users/{user}")
    Observable<User> getUserDetails(@Path("user") String user);
}
