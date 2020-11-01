package presenter;

import com.example.lian7.ViewOk;

import java.util.List;

import bean.Alldata;
import model.ModelImpl;

public class PresenterImpl implements ModelImpl.CallBask {
    private ModelImpl model;
    private ViewOk view1;

    public PresenterImpl(ViewOk view) {
        view1 = view;
         model = new ModelImpl();
    }
    public void getdata(){
        model.getData(this);
    }
    public void dr(){
        if (model!=null){
            model.Disposable();
            model=null;

        }
        if (view1!=null){
            view1=null;
        }

    }

    @Override
    public void onSuccess(List<Alldata.ResultsBean> list) {
        if (model!=null)
            view1.getData(list);

    }

    @Override
    public void onFail(String str) {
        view1.ShowToast(str);
    }
}
