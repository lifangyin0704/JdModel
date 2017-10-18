package MyAdapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import java.util.List;

import Bean.CarBean;
import bwie.com.jdmodel.R;


/**
 * Created by 木子 on 2017/10/7.
 */

public class Car_Adapter extends RecyclerView.Adapter<Car_Adapter.ViewHolder> {
    private List<CarBean.DataBean> list;
    private Context context;
    private ViewHolder viewHolder;


    public Car_Adapter(List<CarBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.carlayout, null);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        String sellerid = list.get(position).getSellerid();
        context.getSharedPreferences("Sellerid",context.MODE_PRIVATE).edit().putString("userid",sellerid).commit();
        holder.tv.setText("  " + this.list.get(position).getSellerName());
        List<CarBean.DataBean.ListBean> list1 = this.list.get(position).getList();
        LinearLayoutManager llm = new LinearLayoutManager(context);
        holder.rv.setLayoutManager(llm);
        Car_item_Adapter afapter = new Car_item_Adapter(list1, context);
        holder.rv.setAdapter(afapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        private  CheckBox tv;
        private  RecyclerView rv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.cb_car);
            rv = itemView.findViewById(R.id.rv_car);


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