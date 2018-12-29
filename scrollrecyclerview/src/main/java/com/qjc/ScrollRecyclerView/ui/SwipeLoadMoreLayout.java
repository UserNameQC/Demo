package com.qjc.ScrollRecyclerView.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import picker.qjc.com.ScrollRecyclerView.R;

/**
 * Created by QiaoJunChao on 2018/12/29.
 */

public class SwipeLoadMoreLayout extends SwipeRefreshLayout {

    public RecyclerView recyclerView;
    public int TouchSlop;
    public int DownY;
    public int LastY;
    public boolean isLoading = false;
    public View footView;
    public LoadMoreListener loadMoreListener;

    public SwipeLoadMoreLayout(Context context) {
        super(context, null);
    }

    public SwipeLoadMoreLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        footView = LayoutInflater.from(context).inflate(R.layout.foot_progress_layout, null);
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (recyclerView == null){
            if (getChildCount() > 0){
                if (getChildAt(0) instanceof  RecyclerView) {
                    recyclerView = (RecyclerView) getChildAt(0);
                    setRecyclerViewClickListener();
                }
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    public void setRecyclerViewClickListener(){
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (isRecyclerViewBottom()){
                    Loading();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    /**
     * 判断是否已经滑动到底部
     */
    public boolean isRecyclerViewBottom(){
        if (recyclerView == null) return false;
        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset()
                >= recyclerView.computeVerticalScrollRange())
            return true;
        return false;
    }

    public interface LoadMoreListener{
        void LoadMore();
    };

    public void setLoadMoreListener(LoadMoreListener loadMoreListener){
        this.loadMoreListener = loadMoreListener;
    }

    public void setLoading(boolean flag){
        /*isLoading = flag;
        if (flag){
            recyclerView.addView(footView, recyclerView.getChildCount());
        } else {
            recyclerView.removeView(footView);
            DownY = 0;
            LastY = 0;
        }*/
        isLoading = flag;
        if (onIsLoading != null){
            onIsLoading.setLoading(flag);
        }
    }

    public interface OnIsLoading{
        void setLoading(boolean flag);
    }
    public OnIsLoading onIsLoading;
    public void setIsLoading(OnIsLoading onIsLoading){
        this.onIsLoading = onIsLoading;
    }

    public void Loading(){
        setLoading(true);
        if (loadMoreListener != null){
            loadMoreListener.LoadMore();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                DownY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                LastY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_UP:
                if (canLoad()){
                    Loading();
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    public boolean canLoad(){
        return !isLoading && isRecyclerViewBottom() && isPullUp();
    }

    public boolean isPullUp(){
        return (DownY - LastY) >= TouchSlop;
    }
}
