package MyAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import Bean.ProdeBean;
import bwie.com.jdmodel.R;
import bwie.com.jdmodel.Shop_XQ_Activity;


/**
 * Created by 木子 on 2017/10/7.
 */

public class Prode_Adapter extends RecyclerView.Adapter<Prode_Adapter.ViewHolder> {
    private List<ProdeBean.DataBean> list = new ArrayList<>();
    private Context context;
    private ViewHolder viewHolder;

    public Prode_Adapter(List<ProdeBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.pro_list, null);
        viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tv.setText(list.get(position).getTitle());
        holder.tv_price.setText("¥" + list.get(position).getPrice());
        String images = list.get(position).getImages();
        String[] split = images.split("\\|");
        Glide.with(context).load(split[0]).into(holder.iv);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                context.getSharedPreferences("PID", context.MODE_PRIVATE).edit().putString("pid", list.get(position).getPid() + "").commit();
                Intent in = new Intent(context, Shop_XQ_Activity.class);
                context.startActivity(in);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;
        private final TextView tv_price;
        private final ImageView iv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.pro_tv_title);
            tv_price = itemView.findViewById(R.id.pro_tv_price);
            iv = itemView.findViewById(R.id.pro_iv);
        }
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}