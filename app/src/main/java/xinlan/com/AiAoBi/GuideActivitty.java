package xinlan.com.AiAoBi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/11/17.
 */
public class GuideActivitty extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ImageView imageView= (ImageView) findViewById(R.id.activity_guide);
        AlphaAnimation animation=new AlphaAnimation(1,(float) 0.1);
        animation.setDuration(3000);
        animation.setFillAfter(true);
        imageView.startAnimation(animation);
    }
}
