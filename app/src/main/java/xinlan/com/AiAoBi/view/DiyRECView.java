package xinlan.com.AiAoBi.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import xinlan.com.AiAoBi.R;

/**
 * Created by Administrator on 2017/2/5.
 */
public  class DiyRECView extends View {
    private static final int SRCX =20;
    private static final int SRCY =50;
    /*纵坐标字体和线的距离*/
    private static final int SPACE_XTEXT_TO_LINE =10;
    private Paint paint;//纵坐标字笔
    private Paint linePaint;//线笔
    private TextPaint XtextPaint;//横坐标字笔
    private Paint RecPaint;//矩形笔
    private List<String> listx;//横坐标的字体集
    private List<String> listy;//纵坐标坐标的字体集
    private List<String> listData;//矩形数据

    private float textHight;//字体的高
    private float XtextHight;//x轴字体的高
    private float RECWidth;//矩形的宽度
    private float Yspace;//纵坐标没项的所占高度
    //view的宽高
    private float viewHeight;
    private float viewWidth;
    public DiyRECView(Context context) {
        this(context,null);
    }

    public DiyRECView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DiyRECView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setDataToList(List<String> listx,List<String> listy,List<String> listData) {
        this.listx=listx;
        this.listy=listy;
        this.listData=listData;
        init();
        invalidate();
    }

    private void init() {
        if (listx==null||listx.isEmpty()||listy==null||listy.isEmpty()||listData==null||listData.isEmpty())return;
        paint=new Paint();
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(1);
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.RIGHT);
        paint.setTextSize(20);
        linePaint=new Paint();
        linePaint.setColor(getResources().getColor(R.color.green));
        linePaint.setStrokeWidth(1);
        linePaint.setAntiAlias(true);
        XtextPaint=new TextPaint();
        XtextPaint.setColor(Color.WHITE);
        XtextPaint.setStrokeWidth(1);
        XtextPaint.setAntiAlias(true);
        XtextPaint.setTextAlign(Paint.Align.CENTER);
        XtextPaint.setTextSize(18);
        RecPaint=new Paint();
        RecPaint.setColor(Color.parseColor("#C7F78F"));
        RecPaint.setStrokeWidth(1);
        RecPaint.setAntiAlias(true);
        textHight=Math.abs(paint.ascent()+ paint.descent());
        XtextHight=Math.abs(XtextPaint.ascent()+ XtextPaint.descent());
        viewHeight=getHeight()-100;
        viewWidth=getWidth()-50;
        Yspace=viewHeight/(listy.size()-1);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (listx==null||listx.isEmpty()||listy==null||listy.isEmpty()||listData==null||listData.isEmpty())return;
        float textWidth=0;//纵坐标字体宽最大值
        int max=1;//纵坐标最大值
        for (int i = 0; i <listy.size(); i++) {
            if (i==0){
                if (paint.measureText(listy.get(i))>paint.measureText(listy.get(i+1))){
                    textWidth=paint.measureText(listy.get(i));
                }else textWidth=paint.measureText(listy.get(i+1));

                if (Integer.parseInt(listy.get(i))>Integer.parseInt(listy.get(i+1))){
                    max=Integer.parseInt(listy.get(i));
                }else max=Integer.parseInt(listy.get(i+1));
            }
            else if (i>1){
                if (paint.measureText(listy.get(i))>textWidth){
                    textWidth=paint.measureText(listy.get(i));
                }

                if (Integer.parseInt(listy.get(i))>max){
                    max=Integer.parseInt(listy.get(i));
                }
            }
        }
        RECWidth=(viewWidth-textWidth- SRCX -SPACE_XTEXT_TO_LINE)/ listx.size();
        for (int i = 0; i < listy.size(); i++) {
            canvas.drawText(listy.get(i),textWidth+ SRCX,viewHeight-i*Yspace+textHight/2+ SRCY,paint);//绘制纵坐标的字体
            //绘制横线
            canvas.drawLine(textWidth+ SRCX +SPACE_XTEXT_TO_LINE,viewHeight-Yspace*i+ SRCY,viewWidth,viewHeight-Yspace*i+ SRCY,linePaint);
        }

        for (int i = 0; i < listData.size(); i++) {
            float men=Float.parseFloat(listData.get(i))/max;
            if (listx.size()<9){
            canvas.drawRect(RECWidth*i+textWidth+2* SRCX,viewHeight*(1-men)+ SRCY,RECWidth*i+36+textWidth+2* SRCX,viewHeight+ SRCY,RecPaint);
            canvas.drawText(listx.get(i),RECWidth*i+textWidth+2* SRCX +18,viewHeight+ SRCY +XtextHight/2+20,XtextPaint);
            }else {
                XtextPaint.setTextSize(15);
                canvas.drawRect(RECWidth*i+textWidth+2* SRCX,viewHeight*(1-men)+ SRCY,RECWidth*(i+0.6f)+textWidth+2* SRCX,viewHeight+ SRCY,RecPaint);
                canvas.drawText(listx.get(i),RECWidth*i+textWidth+2* SRCX +RECWidth*0.2f,viewHeight+ SRCY +XtextHight/2+20,XtextPaint);
            }
        }
        canvas.restore();
    }
}
