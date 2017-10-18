package MyAdapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import Bean.MiSaBean;
import bwie.com.jdmodel.R;

/**
 * Created by 木子 on 2017/10/10.
 */

public class MS_Adapter extends RecyclerView.Adapter<MS_Adapter.ViewHolder> {

    private List<MiSaBean> list = new ArrayList<>();
    private Context context;
    private ViewHolder viewHolder;


    public MS_Adapter(List<MiSaBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MS_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.ms_item, null);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MS_Adapter.ViewHolder holder, final int position) {
        holder.tv_old.setText("¥" + list.get(position).oldPrice);
        holder.tv_old.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.tv_new.setText("¥" + list.get(position).newPrice);
        String picUrl = list.get(position).picUrl;
        String[] split = picUrl.split("\\|");
        Glide.with(context).load(split[0]).into(holder.iv);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv;
        private TextView tv_old;
        private TextView tv_new;

        public ViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.ms_iv);
            tv_old = itemView.findViewById(R.id.old_tv);
            tv_new = itemView.findViewById(R.id.new_tv);
        }
    }


}
