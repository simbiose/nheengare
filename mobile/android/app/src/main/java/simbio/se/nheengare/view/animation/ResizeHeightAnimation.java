package simbio.se.nheengare.view.animation;

import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class ResizeHeightAnimation extends Animation {

    private final View mView;
    private final float mToHeight;
    private final float mFromHeight;

    public ResizeHeightAnimation(View view, float toHeight) {
        mToHeight = toHeight;
        mFromHeight = view.getHeight();
        mView = view;
        setDuration(300);
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation transformation) {
        float height = (mToHeight - mFromHeight) * interpolatedTime + mFromHeight;
        LayoutParams p = mView.getLayoutParams();
        p.height = (int) height;
        mView.requestLayout();
    }

}
