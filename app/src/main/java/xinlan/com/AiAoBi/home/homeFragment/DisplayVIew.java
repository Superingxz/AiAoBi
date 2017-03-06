package xinlan.com.AiAoBi.home.homeFragment;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;
import xinlan.com.AiAoBi.R;

/**
 * Created by Administrator on 2016/11/4.
 */
public class DisplayVIew extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_view);
        PhotoView imageView= (PhotoView) findViewById(R.id.display_view);
        String bitmap= (String) getIntent().getExtras().get("bitmap");
        String url = getIntent().getExtras().getString("url");
        //加载图片
        if (url!=null){
            Glide.with(this).load(url)
                    .placeholder(ResourcesCompat.getDrawable(getResources(),R.drawable.shape,null))
                    .error(ResourcesCompat.getDrawable(getResources(),R.mipmap.error200,null))
                    .into(imageView);
        }
        if (bitmap!=null){
            //imageView.setImageBitmap(bitmap);
            imageView.setImageURI(Uri.parse(bitmap));
        }
        imageView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float x, float y) {
                DisplayVIew.this.finish();
            }
        });
    }
}
