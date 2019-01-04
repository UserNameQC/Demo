package com.qjc.scroll_recycler.adapter;

import android.content.Context;

import java.util.LinkedList;

import com.qjc.scroll_recycler.R;
import com.qjc.scroll_recycler.entity.UserEntity;
import com.qjc.scroll_recycler.holder.BaseViewHolder;

/**
 * Created by QiaoJunChao on 2018/12/28.
 */
@SuppressWarnings("all")
public class RecycklerTestAdapter extends RecyclerViewAdapter<UserEntity> {

    public LinkedList<UserEntity> dataList = new LinkedList<>();
    public Context context;
    public int itemType;
    public boolean onIsLoading;

    public RecycklerTestAdapter(Context context, LinkedList<UserEntity> dataList, LinkedList<Integer> layoutList) {
        super(context, dataList, layoutList);
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public void convert(Context context, BaseViewHolder baseViewHolder, UserEntity userEntity, int position) {
        if (userEntity.getItemType() == 0){
            baseViewHolder.setText(R.id.name, userEntity.getName());
            baseViewHolder.setText(R.id.age, String.valueOf(userEntity.getAge()));
            baseViewHolder.setText(R.id.address, userEntity.getAddress());
        } /*else if (userEntity.getItemType() == 2) {
            *//**//*
            if (onIsLoading){
                baseViewHolder.getView(R.id.rotate_loading_footer).setVisibility(View.VISIBLE);
                baseViewHolder.getView(R.id.rotate_message).setVisibility(View.VISIBLE);
                ((RotateLoading) baseViewHolder.getView(R.id.rotate_loading_footer)).start();
            } else {
                ((RotateLoading) baseViewHolder.getView(R.id.rotate_loading_footer)).start();
                baseViewHolder.getView(R.id.rotate_loading_footer).setVisibility(View.GONE);
                baseViewHolder.getView(R.id.rotate_message).setVisibility(View.GONE);
            }
        }*/ else {
            //baseViewHolder.setBackgroundColor(R.id.imageView1, Color.parseColor("#3aa"));
            baseViewHolder.setText(R.id.name1, userEntity.getName());
            baseViewHolder.setText(R.id.age1,String.valueOf(userEntity.getAge()));
            baseViewHolder.setText(R.id.address1, userEntity.getAddress());
        }
    }

    public ItemViewTypes itemViewTypes = new ItemViewTypes() {
        @Override
        public int getType(int position) {
            /*if (position + 1 == getItemCount()){
                return 2;
            }*/
            UserEntity userEntity = dataList.get(position);
            int type = userEntity.getItemType();
            if (type == 0){
                return 0;
            }else{
                return 1;
            }
        }
    };

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    /*public void setLoading(boolean flag){
        super.setLoading(flag);
        this.onIsLoading = flag;
    }*/
}
