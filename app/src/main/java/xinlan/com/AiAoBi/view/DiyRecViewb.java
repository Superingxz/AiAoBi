package xinlan.com.AiAoBi.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import xinlan.com.AiAoBi.App;
import xinlan.com.AiAoBi.entity.BasesaleInfo;

/**
 * Created by Administrator on 2017/2/14.
 */
public class DiyRecViewb extends View {
    public DiyRecViewb(Context context) {
        this(context,null);
    }

    public DiyRecViewb(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DiyRecViewb(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 实际绘画区的四边距离
     */
    private static final int SRC_LEFT=80;
    private static final int SRC_TOP=50;
    private static final int SRC_RIGHT=30;
    private static final int SRC_BuTTOM=50;
    private static final int RECWIDTH=36;
    private List<BasesaleInfo> infoList;//数据源
    //view的宽高
    private float viewHeight;
    private float viewWidth;
    private Paint recPaint;//画矩形的笔
    private Paint recPaintb;//画特殊矩形的笔
    private Paint charPaint;//画文字笔
    private void init() {
        recPaint=new Paint();
        recPaint.setColor(Color.parseColor("#C7F78F"));
        recPaint.setStrokeWidth(1);
        recPaint.setAntiAlias(true);
        charPaint=new Paint();
        charPaint.setColor(Color.WHITE);
        charPaint.setAntiAlias(true);
        charPaint.setStrokeWidth(1);
        charPaint.setTextSize(18);
        charPaint.setTextAlign(Paint.Align.CENTER);

        recPaintb=new Paint();
        recPaintb.setColor(Color.parseColor("#FF3F70"));
        recPaintb.setStrokeWidth(1);
        recPaintb.setAntiAlias(true);
    }

    /**
     * 计算最大值
     * @return
     */
    private float caculateMax(){
        float max = 0;
        if (infoList.isEmpty())return 0;
        if (infoList.size()==1)return Float.parseFloat(infoList.get(0).getSalemoney());
        for (int i = 0; i < infoList.size(); i++) {
            if (i==0){
                if (Float.parseFloat(infoList.get(i).getSalemoney())>Float.parseFloat(infoList.get(i+1).getSalemoney())){
                    max=Float.parseFloat(infoList.get(i).getSalemoney());
                }else max=Float.parseFloat(infoList.get(i+1).getSalemoney());
            }
            if (i>1){
                if (Float.parseFloat(infoList.get(i).getSalemoney())>max){
                    max=Float.parseFloat(infoList.get(i).getSalemoney());
                }
            }
        }
        return max;
    }
    private float RecWidth;//每条矩状的宽度
    /**
     * 计算字体高度
     * @param paint
     */
    private float caculateTextHeight(Paint paint){
        return  Math.abs(paint.ascent()+paint.descent());
    }
    @Override
    protected void onDraw(Canvas canvas) {
        if (infoList ==null|| infoList.isEmpty())return;
        super.onDraw(canvas);
        viewHeight=getHeight();
        viewWidth=getWidth();
        RecWidth=(viewWidth-SRC_LEFT-SRC_RIGHT)/ infoList.size();
        float temp;
        for (int i = 0; i < infoList.size(); i++) {
            if (infoList.size()<9){
                temp=RECWIDTH;
            }else {
                temp=RecWidth*0.6f;
                charPaint.setTextSize(15);
            }

            if (i<9) {
                drawChat(canvas,i,temp);
            }else {
                if (i==9){
                    if (findMax()==i){//该代理商刚好排在第十
                        drawChat(canvas,i,temp);
                    }
                    else {//排在第十的是其他代理商
                        if (findMax()>9){//该代理商排在第十之后，此时把该代理上放在第十的位置，高度为第九位的一半，并改变颜色
                            drawChatB(canvas,i,temp);
                        }else {
                            drawChat(canvas,i,temp);
                        }
                    }
                }
            }
        }
        canvas.restore();
    }

    private void drawChatB(Canvas canvas, int i, float temp) {
        canvas.drawRect(SRC_LEFT + RecWidth * i,
                SRC_TOP + caculateTextHeight(charPaint) + (viewHeight - (SRC_TOP + caculateTextHeight(charPaint)) * 2) * (1 - Float.parseFloat(infoList.get(8).getSalemoney())/2 / caculateMax()),
                SRC_LEFT +RecWidth*i+ temp,
                viewHeight - SRC_BuTTOM - caculateTextHeight(charPaint), recPaintb);
        canvas.drawText(infoList.get(findMax()).getAgentname(),
                SRC_LEFT + RecWidth * i +temp/2,
                viewHeight - SRC_BuTTOM - caculateTextHeight(charPaint) / 2 + 20, charPaint);
        canvas.drawText(infoList.get(findMax()).getRanking(),
                SRC_LEFT + RecWidth * i +temp/2,
                SRC_TOP + caculateTextHeight(charPaint) + (viewHeight - (SRC_TOP + caculateTextHeight(charPaint)) * 2) * (1 - Float.parseFloat(infoList.get(8).getSalemoney())/2 / caculateMax()) - 20, charPaint);

    }


    /**
     * 找出该代理商所在的位置
     * @return len
     */
    private int findMax(){
        int len=0;
        for (int i = 0; i < infoList.size(); i++) {
            if (App.getApp().getUserInfo().getAgentname().equals(infoList.get(i).getAgentname())){
                len=i;//该代理商的位置
            }
        }
        return len;
    }
    private void drawChat(Canvas canvas,int i,Float temp){
        canvas.drawRect(SRC_LEFT + RecWidth * i,
                        SRC_TOP + caculateTextHeight(charPaint) + (viewHeight - (SRC_TOP + caculateTextHeight(charPaint)) * 2) * (1 - Float.parseFloat(infoList.get(i).getSalemoney()) / caculateMax()),
                        SRC_LEFT + RecWidth*i+temp,
                        viewHeight - SRC_BuTTOM - caculateTextHeight(charPaint),
                        recPaint);
        canvas.drawText(infoList.get(i).getAgentname(),
                SRC_LEFT + RecWidth*i+temp/2,
                viewHeight - SRC_BuTTOM - caculateTextHeight(charPaint) / 2 + 20, charPaint);
        canvas.drawText(infoList.get(i).getRanking(),
                SRC_LEFT + RecWidth * i+temp/2,
                SRC_TOP + caculateTextHeight(charPaint) + (viewHeight - (SRC_TOP + caculateTextHeight(charPaint)) * 2) * (1 - Float.parseFloat(infoList.get(i).getSalemoney()) / caculateMax()) - 20, charPaint);

    }
    public void setDataToList(List<BasesaleInfo> list){
        /*处理list只有一个数据的情况*/
        if (list.size()==1){
            list.get(0).setRanking("第1名");
            init();
            invalidate();
            return;
        }
        infoList =new ArrayList<>();
        BasesaleInfo[] array = list.toArray(new BasesaleInfo[list.size()]);
        float a,b;
        /*冒泡排序*/
        for (int i = 0; i <  array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                a=Float.parseFloat(array[j].getSalemoney());
                b=Float.parseFloat(array[j+1].getSalemoney());
                if (a<b){
                    BasesaleInfo temp;
                    temp= array[j];
                    array[j]=array[j+1];
                    array[j+1]=temp;
                }
            }
        }
        for (int i = 0; i < list.size(); i++) {
            infoList.add(array[i]);
        }
        /*处理名次并列的情况*/
        for (int i = 0; i <infoList.size()-1; i++) {
            if (infoList.get(i).getSalemoney().equals(infoList.get(i+1).getSalemoney())){
                if (infoList.get(i).getRanking()==null){
                    infoList.get(i).setRanking("第"+(i+1)+"名");
                }
                infoList.get(i+1).setRanking(infoList.get(i).getRanking());
            }else {
                if (i>0&&infoList.get(i-1).getSalemoney().equals(infoList.get(i).getSalemoney())){
                    infoList.get(i).setRanking(infoList.get(i-1).getRanking());
                }else {
                    infoList.get(i).setRanking("第"+(i+1)+"名");
                    infoList.get(i+1).setRanking("第"+(i+2)+"名");
                }
            }
        }
        init();
        invalidate();
    }

}
