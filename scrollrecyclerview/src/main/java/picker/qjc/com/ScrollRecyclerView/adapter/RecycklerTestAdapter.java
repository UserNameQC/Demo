package picker.qjc.com.ScrollRecyclerView.adapter;

import android.content.Context;
import android.graphics.Color;

import java.util.LinkedList;

import picker.qjc.com.ScrollRecyclerView.R;
import picker.qjc.com.ScrollRecyclerView.entity.UserEntity;
import picker.qjc.com.ScrollRecyclerView.holder.BaseViewHolder;

/**
 * Created by QiaoJunChao on 2018/12/28.
 */

public class RecycklerTestAdapter extends RecyclerViewAdapter<UserEntity> {

    public LinkedList<UserEntity> dataList = new LinkedList<>();
    public Context context;
    public int itemType;

    public RecycklerTestAdapter(Context context, LinkedList<UserEntity> dataList, LinkedList<Integer> layoutList) {
        super(context, dataList, 1, layoutList);
        this.context = context;
        this.dataList = dataList;
        this.itemType = itemType;
    }

    @Override
    public void convert(Context context, BaseViewHolder baseViewHolder, UserEntity userEntity, int position) {
        if (userEntity.getItemType() == 0){
            baseViewHolder.setText(R.id.name, userEntity.getName());
            baseViewHolder.setText(R.id.age, String.valueOf(userEntity.getAge()));
            baseViewHolder.setText(R.id.address, userEntity.getAddress());
        } else {
            //baseViewHolder.setBackgroundColor(R.id.imageView1, Color.parseColor("#3aa"));
            baseViewHolder.setText(R.id.name1, userEntity.getName());
            baseViewHolder.setText(R.id.age1,String.valueOf(userEntity.getAge()));
            baseViewHolder.setText(R.id.address1, userEntity.getAddress());
        }
    }

    public ItemViewTypes itemViewTypes = new ItemViewTypes() {
        @Override
        public int getType(int position) {
            UserEntity userEntity = dataList.get(position);
            int type = userEntity.getItemType();
            if (type == 0){
                return 0;
            }else{
                return 1;
            }
        }
    };

}
