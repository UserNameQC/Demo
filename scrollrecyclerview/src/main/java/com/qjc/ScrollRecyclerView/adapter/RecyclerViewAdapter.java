package com.qjc.ScrollRecyclerView.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.LinkedList;

import com.qjc.ScrollRecyclerView.holder.BaseViewHolder;

/**
 * Created by QiaoJunChao on 2018/12/28.
 */

public abstract class RecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    public LinkedList<T> dataList = new LinkedList<>();
    public int layoutId;
    public Context context;
    public int itemType;
    public LinkedList<Integer> layoutIdList;
    public ItemViewTypes ItemViewTypes;
    public OnItemClickListener onItemClickListener;

    public RecyclerViewAdapter(Context context, LinkedList<T> dataList, int layoutId){
        this(context, dataList);
        this.layoutId = layoutId;
    }

    public RecyclerViewAdapter(Context context, LinkedList<T> dataList){
        this.dataList = dataList;
        this.context = context;
    }

    public RecyclerViewAdapter(Context context, LinkedList<T> dataList, int layoutId, int itemType){
        this(context, dataList, layoutId);
        this.itemType = itemType;
    }

    public RecyclerViewAdapter(Context context, LinkedList<T> dataList, int itemType, LinkedList<Integer> layoutList){
        this.context = context;
        this.dataList = dataList;
        this.itemType = itemType;
        this.layoutIdList = layoutList;
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == -1){
            view= LayoutInflater.from(context).inflate(layoutId, parent, false);
            return new BaseViewHolder(view);
        } else {
            view = LayoutInflater.from(context).inflate(layoutIdList.get(viewType), parent, false);
            return new BaseViewHolder(view);
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

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (itemType == 1){
            return ItemViewTypes.getType(position);
        }
        return -1;
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
}
