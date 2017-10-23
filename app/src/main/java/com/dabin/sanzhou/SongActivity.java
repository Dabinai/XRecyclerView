package com.dabin.sanzhou;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.io.IOException;
import java.util.List;

import adapter.SongAdapter;
import bean.NewBase;
import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import utils.GsonObjectCallback;
import utils.OkHttp3Utils;

public class SongActivity extends AppCompatActivity {

    @Bind(R.id.id_recyclerview)
    XRecyclerView idRecyclerview;
    private String path = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.billboard.billList&type=";
    private int page = 1;
    private String path1 = "&size=10&offset=0";
    List<NewBase.SongListBean> song_list;
    SongAdapter songAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        ButterKnife.bind(this);
        idRecyclerview.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        getData(path + page + path1);
        //XRecyclerView 的监听方法
        idRecyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            //刷新
            @Override
            public void onRefresh() {
                //refresh data here
                getData(path + 1 + path1);
                idRecyclerview.refreshComplete();
            }

            //加载
            @Override
            public void onLoadMore() {
                // load more data here
                page += 1;
                getData(path + page + path1);
                idRecyclerview.loadMoreComplete();
            }
        });
    }

    //OKhttp3网络请求，我用的是封装好的okhttp，在源码处下载
    public void getData(String urll) {
        OkHttp3Utils.doGet(urll, new GsonObjectCallback<NewBase>() {
            @Override
            public void onUi(NewBase newBase) {
                song_list = newBase.getSong_list();
                songAdapter = new SongAdapter(SongActivity.this, song_list);
                idRecyclerview.setAdapter(songAdapter);
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });


    }


}
