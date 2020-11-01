package model;

import com.example.lian7.ApiService;

import java.util.ArrayList;
import java.util.List;

import bean.Alldata;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ModelImpl {
    private Disposable dis;
    public void getData(final CallBask callBask){
        Retrofit build = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(ApiService.FU)
                .build();
        ApiService service = build.create(ApiService.class);
        Observable<Alldata> data = service.getData();
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Alldata>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                      dis =d;
                    }

                    @Override
                    public void onNext(Alldata alldata) {
                    if (alldata!=null){
                        List<Alldata.ResultsBean> results = alldata.getResults();
                        callBask.onSuccess(results);
                    }else{
                        callBask.onFail("接收失败");
                    }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBask.onFail(e.getMessage()+"错误");

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
    public void Disposable(){
        dis.dispose();//停止未完成的网络请求，（此时网络请求得到的数据已经没有用武之地了）
    }

    public interface CallBask{
        void onSuccess(List<Alldata.ResultsBean> list);
        void onFail(String str);

    }
}
