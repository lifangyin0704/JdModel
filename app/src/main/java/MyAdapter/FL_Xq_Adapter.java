package MyAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Bean.FeileiBean;
import bwie.com.jdmodel.FlActivity;
import bwie.com.jdmodel.R;


/**
 * Created by 木子 on 2017/10/7.
 */

public class FL_Xq_Adapter extends RecyclerView.Adapter<FL_Xq_Adapter.ViewHolder> {
    private List<FeileiBean.DataBean> list = new ArrayList<>();
    private Context context;
    private ViewHolder viewHolder;
    private List<FeileiBean.DataBean.ListBean> list1 = new ArrayList<>();

    public FL_Xq_Adapter(List<FeileiBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.recy_item, null);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tv.setText(list.get(position).getName());
        final List<FeileiBean.DataBean.ListBean> list = this.list.get(position).getList();
        GridLayoutManager glm = new GridLayoutManager(context, 3);
        holder.rv.setLayoutManager(glm);
        Lei_Adapter adapter = new Lei_Adapter(list, context);
        holder.rv.setAdapter(adapter);
        adapter.setOnItemClickListener(new Lei_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent in = new Intent(context, FlActivity.class);
                in.putExtra("pcid", list.get(position).getPscid() + "");
                context.startActivity(in);
            }
        });
        if (mOnItemClickListener != null)
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
        private RecyclerView rv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_recy_item);
            rv = itemView.findViewById(R.id.recycler_view_2);

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