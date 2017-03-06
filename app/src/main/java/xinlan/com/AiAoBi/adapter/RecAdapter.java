package xinlan.com.AiAoBi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.home.enity.MyUnderclerkInfo;

/**
 * Created by Administrator on 2016/10/11.
 */
public class RecAdapter extends RecyclerView.Adapter<RecAdapter.MyViewHodler> {
    private Context context;
    private List<MyUnderclerkInfo> list;

    public RecAdapter(Context context, List list) {
        this.context = context;
        this.list=list;
    }

    @Override
    public MyViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rec_adapter, parent, false);
        return new MyViewHodler(view);
    }

    @Override
    public void onBindViewHolder(MyViewHodler holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.msg.setText(list.get(position).getMsg());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyViewHodler extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView msg;
        public MyViewHodler(View itemView) {
            super(itemView);
            msg= (TextView) itemView.findViewById(R.id.recadapter_msg);
            name= (TextView) itemView.findViewById(R.id.recadapter_name);
        }
    }
}
