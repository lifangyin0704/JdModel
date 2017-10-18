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

import Bean.ProBean;
import bwie.com.jdmodel.R;


/**
 * Created by 木子 on 2017/10/7.
 */

public class Pro_Adapter1 extends RecyclerView.Adapter<Pro_Adapter1.ViewHolder> {
    private List<ProBean.DataBean> list = new ArrayList<>();
    private Context context;
    private ViewHolder viewHolder;

    public Pro_Adapter1(List<ProBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.pro_gri, null);
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

                mOnItemClickListener.onItemClick(holder.itemView, position);
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
            tv = itemView.findViewById(R.id.pro_tv_title_1);
            tv_price = itemView.findViewById(R.id.pro_tv_price_1);
            iv = itemView.findViewById(R.id.pro_iv_1);
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