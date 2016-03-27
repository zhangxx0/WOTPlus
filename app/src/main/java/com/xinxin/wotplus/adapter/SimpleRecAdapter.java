package com.xinxin.wotplus.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xinxin.wotplus.R;

import java.util.List;

/**
 * Created by xinxin on 2016/3/8.
 */
public class SimpleRecAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    protected List<String> datas;
    private LayoutInflater layoutInflater;

    public SimpleRecAdapter(Context context, List<String> datas) {
        this.context = context;
        this.datas = datas;
        layoutInflater = LayoutInflater.from(context);
    }

    // 添加OnClick监听
    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    // 创建ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_simplerec_text, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    // 绑定ViewHolder
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tv.setText(datas.get(position));

        setUpItemEvent(holder);

    }

    // 抽出方法
    //  这个方法也可以写在MyViewHolder中
    protected void setUpItemEvent(final MyViewHolder holder) {
        if (mOnItemClickListener != null) {

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int layoutPosition = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, layoutPosition);
                }
            });

            // LongClick
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int layoutPosition = holder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(holder.itemView, layoutPosition);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    public void addData(int pos) {
        datas.add(pos, "insert one");
//        notifyDataSetChanged();
        notifyItemInserted(pos);
    }

    public void deleteData(int pos) {
        datas.remove(pos);
        notifyItemRemoved(pos);
    }

}

class MyViewHolder extends RecyclerView.ViewHolder {

    TextView tv;

    public MyViewHolder(View itemView) {
        super(itemView);
        tv = (TextView) itemView.findViewById(R.id.tv_simplerec);
    }
}
