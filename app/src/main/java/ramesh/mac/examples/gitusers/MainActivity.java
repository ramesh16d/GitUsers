package ramesh.mac.examples.gitusers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.Follower;
import network.RetrofitHelper;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.hello)
    TextView myTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        rxjavaPart();
    }



    public void rxjavaPart()
    {
        Observable<List<Follower>> resultUserObservable = RetrofitHelper.Factory.createFollowerListObservable("d");
        Observer<List<Follower>> listObserver = new Observer<List<Follower>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<Follower> followers) {
                Log.d("HERE_", "onNext: "+followers.size()+"\n");
                for (Follower follower : followers)
                {
                    Log.d("HERE_", "onNext: "+follower.getLogin()+"\n");
                }
                myTextView.setText(followers.size()+"\n");
            }
        };

        resultUserObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listObserver);
    }

}
