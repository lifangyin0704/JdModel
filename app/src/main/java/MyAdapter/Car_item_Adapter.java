package MyAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.List;

import Bean.CarBean;
import bwie.com.jdmodel.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by 木子 on 2017/10/7.
 */

public class Car_item_Adapter extends RecyclerView.Adapter<Car_item_Adapter.ViewHolder> {
    private List<CarBean.DataBean.ListBean> list;
    private Context context;
    private ViewHolder viewHolder;
    private int selected;
    private int isSelect;


    public Car_item_Adapter(List<CarBean.DataBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.car_item, null);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tv.setText(list.get(position).getTitle());
        holder.tv_price.setText("¥" + list.get(position).getBargainPrice());
        String images = list.get(position).getImages();
        final String[] split = images.split("\\|");
        if (list.get(position).getSelected() == 0) {
            holder.cb.setChecked(false);
        } else {
            holder.cb.setChecked(true);
        }
        Glide.with(context).load(split[0]).into(holder.iv);
        holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    isSelect = 1;
                } else {
                    isSelect = 0;
                }
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://120.27.23.105/product/updateCarts?uid=188&sellerid=" + list.get(position).getSellerid() + "&pid=" + list.get(position).getPid() + "&selected=" + isSelect + "&num=" + list.get(position).getNum())
                        .build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                    }
                });

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;
        private ImageView iv;
        private TextView tv_price;
        private CheckBox cb;

        public ViewHolder(View itemView) {
            super(itemView);
            cb = itemView.findViewById(R.id.radio);
            tv = itemView.findViewById(R.id.tv_car);
            tv_price = itemView.findViewById(R.id.tv_price_car);
            iv = itemView.findViewById(R.id.imageView7);
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