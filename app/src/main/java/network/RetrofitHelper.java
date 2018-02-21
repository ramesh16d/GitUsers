package network;

import java.util.List;

import model.Follower;
import model.User;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by User on 2/20/2018.
 */

public class RetrofitHelper {

    public static class Factory{

        static Retrofit retrofit = create();
        static GithubService services = retrofit.create(GithubService.class);

        private static Retrofit create() {
            return new Retrofit.Builder().baseUrl(GithubService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io())).build();
        }

      public static Observable<List<Follower>> createFollowerListObservable (String user)
      {
          return services.getfollowers("YML");
      }

        public static Observable<User> createUserDetailObservable (String user)
        {
            return services.getUserDetails("YML");
        }
    }
}
