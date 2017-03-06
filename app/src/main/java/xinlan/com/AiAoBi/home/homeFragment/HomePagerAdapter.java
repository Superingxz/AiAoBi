package xinlan.com.AiAoBi.home.homeFragment;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/24.
 */
public class HomePagerAdapter extends PagerAdapter {
    private List<ImageView> data = new ArrayList<>();

    public HomePagerAdapter(List<ImageView> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (position < 0) {
            position = data.size() + position;
        }
        position = position % data.size();
        ImageView view = data.get(position);
        ViewParent vp = view.getParent();
        if (vp != null) {
            ViewGroup group = (ViewGroup) vp;
            group.removeView(view);
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }
}
