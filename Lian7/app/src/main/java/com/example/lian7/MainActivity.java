package com.example.lian7;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import adaoter.RecyclerAdapter;
import bean.Alldata;
import presenter.PresenterImpl;

public class MainActivity extends AppCompatActivity implements ViewOk {

    private RecyclerView mRecyM;
    private PresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        presenter = new PresenterImpl(this);
        presenter.getdata();

    }

    @Override
    public void getData(List<Alldata.ResultsBean> list) {
        RecyclerAdapter adapter = new RecyclerAdapter(this, list);
        mRecyM.setAdapter(adapter);
        mRecyM.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void ShowToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    private void initView() {
        mRecyM = (RecyclerView) findViewById(R.id.recy_m);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.dr();
    }
}
