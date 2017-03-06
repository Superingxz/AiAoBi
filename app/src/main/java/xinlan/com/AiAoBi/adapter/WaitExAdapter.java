package xinlan.com.AiAoBi.adapter;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.home.enity.WaitExaminationInfo;

/**
 * Created by Administrator on 2016/10/26.
 */
public class WaitExAdapter extends CommonAdapter<WaitExaminationInfo> {
    public static String[] slevelArray = new String[]{"总裁", "官方", "总代", "一级", "经销商", "特约", "花瓣"};
    public static String[] checkflagArray = new String[]{"推荐人审核", "总代审核", "总裁审核", "公司审核"};
    public static String[] ismoreString = new String[]{"单品", "全系"};

    public WaitExAdapter(Context context, int itemLayoutId, List<WaitExaminationInfo> mDatas) {
        super(context, itemLayoutId, mDatas);
    }

    @Override
    public void convert(ViewHolder helper, WaitExaminationInfo item) {
        helper.setText(R.id.ex_number, String.valueOf(helper.getPosition() + 1));
        helper.setText(R.id.ex_time, item.getOperatdate());
        helper.setText(R.id.ex_link, checkflagArray[Integer.valueOf(item.getCheckflag())-1]);

       /* if (!TextUtils.isEmpty(item.getBrand_up())&&!TextUtils.isEmpty(item.getSlevel_up())) {
            helper.setText(R.id.ex_leve, item.getBrand_up()+"-"+slevelArray[Integer.valueOf(item.getSlevel_up()) - 1]);
        }else helper.setText(R.id.ex_leve, item.getBrand()+"-"+slevelArray[Integer.valueOf(item.getSlevel()) - 1]);*/
        String selevel = null;
        String brand = null;
        if ("apply".equals(item.getReftype())){//新代理商加入
            if (!TextUtils.isEmpty(item.getSlevel()))
            selevel=slevelArray[Integer.valueOf(item.getSlevel())-1];
            if ("0".equals(item.getIsmore())){//单品
                brand=item.getBrand();
            }else if ("1".equals(item.getIsmore())){//全系
                brand="全系";
            }
        }
        if ("up".equals(item.getReftype())){//升级审批
            if (!TextUtils.isEmpty(item.getSlevel()))
                selevel=slevelArray[Integer.valueOf(item.getSlevel_up())-1];
            if (!TextUtils.isEmpty(item.getBrand_up())){//单品
                brand=item.getBrand_up();
            }else {
                brand="全系";
            }
        }
        helper.setText(R.id.ex_leve, brand+"-"+selevel);
        helper.setText(R.id.ex_preson, item.getAgentname());
    }

}
