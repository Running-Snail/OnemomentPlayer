package co.yishun.library;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

/**
 * Created by jay on 10/4/15.
 */
public class VideoSquareContainer extends FrameLayout {
    private int mSize;
    private OnMeasuredListener mOnMeasuredListener;

    public VideoSquareContainer(Context context) {
        super(context);
    }

    public VideoSquareContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VideoSquareContainer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public int getSize() {
        return mSize;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int size;
        if(widthMode == MeasureSpec.EXACTLY && widthSize > 0){
            size = widthSize;
        }
        else if(heightMode == MeasureSpec.EXACTLY && heightSize > 0){
            size = heightSize;
        }
        else{
            size = widthSize < heightSize ? widthSize : heightSize;
        }

        int finalMeasureSpec = MeasureSpec.makeMeasureSpec(size, MeasureSpec.EXACTLY);
        mSize = size;
        if (mOnMeasuredListener != null) {
            mOnMeasuredListener.onMeasured(mSize, mSize);
        }
        Log.i("[VTC]", "tag container size " + mSize);
        super.onMeasure(finalMeasureSpec, finalMeasureSpec);
    }

    public void setOnMeasuredListener(OnMeasuredListener l) {
        mOnMeasuredListener = l;
    }

    public interface OnMeasuredListener {
        public void onMeasured(int width, int height);
    }
}
