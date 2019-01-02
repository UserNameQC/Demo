package com.qjc.ScrollRecyclerView;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import java.util.LinkedList;

import com.qjc.ScrollRecyclerView.adapter.RecycklerTestAdapter;
import com.qjc.ScrollRecyclerView.databinding.ActivityMainBinding;
import com.qjc.ScrollRecyclerView.entity.UserEntity;
import com.qjc.ScrollRecyclerView.ui.SwipeLoadMoreLayout;

public class DemoRecyclerViewActivity extends Activity {

    public ActivityMainBinding binding;
    public RecycklerTestAdapter recycklerTestAdapter;
    public LinkedList<UserEntity> dataList = new LinkedList<>();
    public LinkedList<Integer> layoutList = new LinkedList<>();//{R.layout.main_item_layout, R.layout.main_item_one_layout};
    public String TAG = "DemoRecyclerViewActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(DemoRecyclerViewActivity.this, R.layout.activity_main);
        initData();
        initView();
    }

    public void initData(){
        for (int i = 0; i < 10; i++) {
            int type = 0 + (int) (Math.random() * 2);
            UserEntity userEntity = new UserEntity();
            userEntity.setName(String.valueOf(i + type));
            userEntity.setAddress("jinan");
            userEntity.setAge(i);
            userEntity.setItemType(type);
            dataList.add(userEntity);
        }
        layoutList.add(R.layout.main_item_layout);
        layoutList.add(R.layout.main_item_one_layout);
    }

    public void initView(){
        binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recycklerTestAdapter = new RecycklerTestAdapter(this, dataList, layoutList);
        recycklerTestAdapter.setItemViewType(recycklerTestAdapter.itemViewTypes);
        binding.recyclerView.setAdapter(recycklerTestAdapter);

        binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.sendEmptyMessage(200);
            }
        });
        binding.swipeRefreshLayout.setLoadMoreListener(loadMoreListener);
        binding.swipeRefreshLayout.setIsLoading(new SwipeLoadMoreLayout.OnIsLoading() {
            @Override
            public void setLoading(boolean flag) {
                if (flag){
                    binding.rotateLoading.start();
                    binding.progressLayout.setVisibility(View.VISIBLE);
                }else{
                    binding.progressLayout.setVisibility(View.GONE);
                    binding.rotateLoading.stop();
                    binding.swipeRefreshLayout.DownY = 0;
                    binding.swipeRefreshLayout.LastY = 0;
                }
            }
        });
    }

    public SwipeLoadMoreLayout.LoadMoreListener loadMoreListener = new SwipeLoadMoreLayout.LoadMoreListener() {
        @Override
        public void LoadMore() {
            handler.sendEmptyMessage(201);
        }
    };

    public Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 200) {
                for (int i = 0; i < 5; i++) {
                    int type = 0 + (int) (Math.random() * 2);
                    UserEntity userEntity = new UserEntity();
                    userEntity.setName(String.valueOf(i + type));
                    userEntity.setAddress("jinan");
                    userEntity.setAge(i);
                    userEntity.setItemType(type);
                    dataList.add(userEntity);
                }

                recycklerTestAdapter.notifyDataSetChanged();
                binding.swipeRefreshLayout.setRefreshing(false);
            } else if (msg.what == 201){
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 5; i++) {
                            int type = 0 + (int) (Math.random() * 2);
                            UserEntity userEntity = new UserEntity();
                            userEntity.setName(String.valueOf(i + type));
                            userEntity.setAddress("jinan");
                            userEntity.setAge(i);
                            userEntity.setItemType(type);
                            dataList.add(userEntity);
                        }

                        recycklerTestAdapter.notifyDataSetChanged();
                        binding.swipeRefreshLayout.setLoading(false);
                    }
                }, 1500);

            }
        }
    };
}
