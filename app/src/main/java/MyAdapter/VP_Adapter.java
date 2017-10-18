package MyAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import Bean.ShapBean;
import bwie.com.jdmodel.R;

/**
 * Created by 木子 on 2017/10/7.
 */

public class VP_Adapter extends RecyclerView.Adapter<VP_Adapter.ViewHolder>{
    private List<ShapBean> list = new ArrayList<>();
    private Context context;
    private ViewHolder viewHolder;

    public VP_Adapter(List<ShapBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.vp_item, null);
        viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv.setText(list.get(position).name);
        Glide.with(context).load(list.get(position).vpicon).into(holder.iv);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }





    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;
        private ImageView iv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.fl_vp);
            iv = itemView.findViewById(R.id.iv_vp);
        }
    }

}