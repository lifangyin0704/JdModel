package MyAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import bwie.com.jdmodel.R;

/**
 * Created by 木子 on 2017/10/9.
 */

public class RecylerAdapter extends CommonAdapter<String> {
    public Context context;
    public List<String> list;
    public int mlayoutId;

    public RecylerAdapter(Context context, int layoutId, List datas) {
        super(context, layoutId, datas);
        this.context = context;
        this.mlayoutId = layoutId;
        this.list = mDatas;
    }

    @Override
    protected void convert(ViewHolder holder, String s, int position) {
        holder.setText(R.id.id_item_list_title, s);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = ViewHolder.createViewHolder(context,parent,mlayoutId);
        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        int item_list = R.layout.item_list;
        return item_list;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onViewHolderCreated(ViewHolder holder, View itemView) {
        super.onViewHolderCreated(holder, itemView);
    }

}
