package zhizhuoc.zhizhuo.com.zhizhuoc;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Scroller;

/**
 * Created by 52829 on 2017/5/24.
 */

public class MainUi extends RelativeLayout{
    private Context context;
    private FrameLayout leftMenu;
    private FrameLayout middMenu;
    private FrameLayout rightMenu;
    private Scroller mScroller;
    public static final int LEFT_ID=0xaabbcc;
    public static final int MIDEELE_ID=0xaaccbb;
    public static final int RIGHT_ID=0xbbccaa;
    public MainUi(Context context) {
        super(context);
        initView(context);
    }

    public MainUi(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }
    private void initView(Context context){
        this.context=context;
        mScroller =new Scroller(context,new DecelerateInterpolator());
        leftMenu=new FrameLayout(context);
        middMenu =new FrameLayout(context);
        rightMenu=new FrameLayout(context);

        leftMenu.setBackgroundColor(Color.RED);
        middMenu.setBackgroundColor(Color.GREEN);
        rightMenu.setBackgroundColor(Color.RED);
        addView(leftMenu);
        addView(middMenu);
        addView(rightMenu);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        middMenu.measure(widthMeasureSpec,heightMeasureSpec);
        int realWidth=MeasureSpec.getSize(widthMeasureSpec);
        int tempWidthMeasure =MeasureSpec.makeMeasureSpec(
                (int)(realWidth*0.8f),MeasureSpec.EXACTLY);
        leftMenu.measure(tempWidthMeasure,heightMeasureSpec);
        rightMenu.measure(tempWidthMeasure,heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        middMenu.layout(l,t,r,b);
        leftMenu.layout(l-leftMenu.getMeasuredWidth(),t,r,b);
        rightMenu.layout(l+middMenu.getMeasuredWidth(),
                t,l+middMenu.getMeasuredWidth()+rightMenu.getMeasuredWidth(),b);
    }

    private boolean isTestCompete;
    private boolean isleftrightEvent;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(!isTestCompete){
            getEventType(ev);
            return true;
        }
        if(isleftrightEvent){
            switch (ev.getActionMasked()){
                case MotionEvent.ACTION_MOVE:
                    int curScrollx=getScrollX();
                    int dis_x=(int)(ev.getX()-point.x);
                    int expectx=-dis_x+curScrollx;
                    int finalx=0;
                    if(expectx<0){
                        finalx=Math.max(expectx,-leftMenu.getMeasuredWidth());
                    }
                    else{
                        finalx=Math.min(expectx,rightMenu.getMeasuredWidth());
                    }
                    scrollTo(finalx,0);
                    point.x=(int)ev.getX();
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    curScrollx=getScrollX();
                    if(Math.abs(curScrollx)>leftMenu.getMeasuredWidth()>>1){
                        if(curScrollx<0){
                            mScroller.startScroll(curScrollx,0,-leftMenu.getMeasuredWidth()-curScrollx,0);
                        }
                        else {
                            mScroller.startScroll(curScrollx,0,leftMenu.getMeasuredWidth()-curScrollx,0);
                        }
                    }else {
                        mScroller.startScroll(curScrollx,0,-curScrollx,0);
                    }
                    invalidate();
                    isleftrightEvent=false;
                    isTestCompete=false;
                    break;
            }
        }
        else{
            switch (ev.getActionMasked()){
                case MotionEvent.ACTION_UP:
                    isleftrightEvent=false;
                    isTestCompete=false;
                    break;
                default:
                    break;
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(!mScroller.computeScrollOffset()){
            return;
        }
        int tempX =mScroller.getCurrX();
        scrollTo(tempX,0);
    }

    private Point point=new Point();
    private static final int TEST_DIS=20;
    private  void getEventType(MotionEvent ev){
        switch (ev.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
                point.x=(int)ev.getX();
                point.y=(int)ev.getY();
                super.dispatchTouchEvent(ev);
                break;
            case MotionEvent.ACTION_MOVE:
                int dx=Math.abs((int)ev.getX()-point.x);
                int dy=Math.abs((int)ev.getY()-point.y);
                if(dx>=TEST_DIS&&dx>dy){//左右晃动
                    isleftrightEvent=true;
                    isTestCompete=true;
                    point.x=(int)ev.getX();
                    point.y=(int)ev.getY();
                }else if(dy>=TEST_DIS&&dy>dx){//上下滑动
                    isleftrightEvent=false;
                    isTestCompete=true;
                    point.x=(int)ev.getX();
                    point.y=(int)ev.getY();
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                super.dispatchTouchEvent(ev);
                isTestCompete=false;
                isleftrightEvent=false;
                break;
        }
    }
}

