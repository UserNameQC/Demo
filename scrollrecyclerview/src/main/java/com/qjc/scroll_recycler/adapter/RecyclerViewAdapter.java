package com.qjc.scroll_recycler.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.LinkedList;

import com.qjc.scroll_recycler.holder.BaseViewHolder;

/**
 * Created by QiaoJunChao on 2018/12/28.
 */

@SuppressWarnings("all")
public abstract class RecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    public String TAG = "RecyclerViewAdapter";
    public LinkedList<T> dataList = new LinkedList<>();
    public int layoutId = -1;
    public Context context;
    public int itemType = -1;
    public LinkedList<Integer> layoutIdList;
    public ItemViewTypes ItemViewTypes;
    public OnItemClickListener onItemClickListener;
    public boolean isHasHeader;
    public boolean isHasFooter;
    public boolean isScrollFresh;
    public View headerView = null;
    public View footerView = null;
    public boolean onIsLoading;

    /**
     * 普通布局构造方法 有重复嫌疑
     * @param context 上下文
     * @param dataList 数据集合
     * @param layoutId item布局id
     */
    public RecyclerViewAdapter(Context context, LinkedList<T> dataList, int layoutId){
        this(context, dataList);
        this.layoutId = layoutId;
    }

    /**
     * 基本构造方法
     * @param context 上下文
     * @param dataList 数据集合
     */
    public RecyclerViewAdapter(Context context, LinkedList<T> dataList){
        this.dataList = dataList;
        this.context = context;
    }

    /**
     * 配合 setItemType()方法使用
     * 只传递布局id集合即可
     * @param context 上下文
     * @param dataList 数据集合
     * @param layoutIdList 布局id集合
     */
    public RecyclerViewAdapter(Context context, LinkedList<T> dataList, LinkedList<Integer> layoutIdList){
        this(context, dataList);
        this.layoutIdList = layoutIdList;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (itemType == -1 && layoutId != -1){
            view= LayoutInflater.from(context).inflate(layoutId, parent, false);
            return new BaseViewHolder(view);
        } /*else if (viewType == 2 && footerView != null) {
            return new BaseViewHolder(footerView);
        }*/ else {
            if (!layoutIdList.isEmpty()) {
                view = LayoutInflater.from(context).inflate(layoutIdList.get(viewType), parent, false);
                return new BaseViewHolder(view);
            } else {
                Log.e(TAG, "onCreateViewHolder: 请保证有至少一个布局Id");
                return null;
            }
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        holder.itemView.setTag(position);
        convert(context, holder, dataList.get(position), position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null){
                    onItemClickListener.onItemClick(v, position);
                }
            }
        });
    }

    public abstract void convert(Context context, BaseViewHolder baseViewHolder, T t, int position);

    public void setLoading(boolean flag){
        this.onIsLoading = flag;
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (itemType == 1) {
            return ItemViewTypes.getType(position);
        }
        return 0;
    }

    public interface ItemViewTypes{
        int getType(int position);
    }

    /**
     * 多布局类型设置各个Item的布局类型，
     * @param itemViewTypes 返回布局类型的接口
     */
    public void setItemViewType(ItemViewTypes itemViewTypes){
        this.ItemViewTypes = itemViewTypes;
    }

    /**
     * 给Adapter设置一个格式，用来区分是多布局 还是单布局
     * 此方法与构造方法中 itemType意义相同，在使用时只要有一种设置即可
     * @param itemType 多布局或者单布局的判断标志
     */
    public void setItemType(int itemType){
        this.itemType = itemType;
    }

    /**
     * 设置多布局 要使用到的layout ID
     * @param layoutList layout ID， 顺序要与布局类型一一对应，否则会出现混乱
     */
    public void setResourceLayoutId(LinkedList<Integer> layoutList){
        this.layoutIdList = layoutList;
    }

    /**
     * item的监听事件
     */
    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public void addAll(LinkedList<T> liat){
        dataList.addAll(liat);
        notifyDataSetChanged();
    }

    public void addItem(T t, int position){
        dataList.add(position, t);
        notifyItemInserted(position);
        notifyDataSetChanged();
    }

    public void removeItem(int position){
        dataList.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    public LinkedList<T> getDataList(){
        return dataList;
    }

    /**
     * 设置是否有头部view
     * @param flag
     */
    /*public void isHasHeaerView(boolean flag){
        this.isHasHeader = flag;
    }*/

    /**
     * 设置是否有底部view
     * @param flag
     */
    /*public void isHasFooterView(boolean flag){
        this.isHasFooter = flag;
    }*/

    /**
     * 设置头部view
     * @param view
     */
    /*public void setHeaderView(View view){
        this.headerView = view;
    }*/

    /**
     * 设置底部view
     * @param footerView
     */
    public void setFooterView(View footerView){
        this.footerView = footerView;
    }

    /**
     * 设置头部view
     * @param layoutId 布局id
     */
   /* public void setHeaderView(int layoutId){
       this.headerView = LayoutInflater.from(context).inflate(layoutId, null);
    }*/

    /**
     * 设置底部view
     * @param layoutId 布局id
     */
    public void setFooterView(int layoutId){
        this.footerView = LayoutInflater.from(context).inflate(layoutId, null);
    }

    /*public void isScrollFresh(boolean isScrollFresh){
        this.isScrollFresh = isScrollFresh;
    }*/
}
