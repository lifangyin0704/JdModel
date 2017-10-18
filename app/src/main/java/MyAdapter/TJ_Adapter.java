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

import Bean.TuiJianBean;
import bwie.com.jdmodel.R;
import bwie.com.jdmodel.Shop_XQ_Activity;

/**
 * Created by 木子 on 2017/10/10.
 */

public class TJ_Adapter extends RecyclerView.Adapter<TJ_Adapter.ViewHolder> {

    private List<TuiJianBean.TuijianBean.ListBean> list = new ArrayList<>();
    private Context context;
    private ViewHolder viewHolder;


    public TJ_Adapter(List<TuiJianBean.TuijianBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public TJ_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.tuijian_item, null);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final TJ_Adapter.ViewHolder holder, final int position) {
        holder.tv_title.setText(list.get(position).getTitle());
        holder.tv_new.setText("¥" + this.list.get(position).getPrice());
        String picUrl = this.list.get(position).getImages();
        String[] split = picUrl.split("\\|");
        Glide.with(context).load(split[0]).into(holder.iv);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.getSharedPreferences("PID", context.MODE_PRIVATE).edit().putString("pid",list.get(position).getPid() + "").commit();
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

        private ImageView iv;
        private TextView tv_title;
        private TextView tv_new;

        public ViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.id_tj_iv);
            tv_title = itemView.findViewById(R.id.id_tj_tv);
            tv_new = itemView.findViewById(R.id.id_tj_price);

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
