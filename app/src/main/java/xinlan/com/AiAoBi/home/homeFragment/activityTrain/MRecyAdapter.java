package xinlan.com.AiAoBi.home.homeFragment.activityTrain;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import java.util.List;

import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.home.homeFragment.activityTrain.enityTrain.RecyAdapterData;

/**
 * Created by Administrator on 2016/10/19.
 */
public class MRecyAdapter extends RecyclerView.Adapter<MRecyAdapter.RecyHoder>{
    private Context context;
    private List<RecyAdapterData> datas;

    public MRecyAdapter(Context context, List<RecyAdapterData> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public RecyHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.train_rec_adapter, parent, false);
        Log.i("halo","ccccccc:");
        return new RecyHoder(view);
    }

    @Override
    public void onBindViewHolder(RecyHoder holder, int position) {
        Log.i("haloo","datas:"+datas.get(position).getUrlImage());
       /* RequestQueue queue = Volley.newRequestQueue(context);
        ImageLoader loader=new ImageLoader(queue, new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String url) {
                return null;
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {

            }
        });
        holder.iv.setImageUrl(datas.get(position).getUrlImage(),loader);
        holder.iv.setErrorImageResId(R.mipmap.tyx);*/
        Glide.with(context).load(datas.get(position).getUrlImage()).into(holder.iv);
        holder.title.setText(datas.get(position).getTitle());
        holder.message.setText(datas.get(position).getMessege());
        holder.date.setText(datas.get(position).getDate());
    }
    @Override
    public int getItemCount() {
        return datas.size();
    }

    class RecyHoder extends RecyclerView.ViewHolder {
        private ImageView iv;
        private TextView title;
        private TextView message;
        private TextView date;
        public RecyHoder(View itemView) {
            super(itemView);
            title= (TextView) itemView.findViewById(R.id.train_rec_adapter_title);
            message= (TextView) itemView.findViewById(R.id.train_rec_adapter_messge);
            date= (TextView) itemView.findViewById(R.id.train_rec_adapter_date);
            iv= (ImageView) itemView.findViewById(R.id.train_rec_adapter_iv);
        }
    }
}
