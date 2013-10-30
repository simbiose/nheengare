package simbio.se.nheengare.view.animation;

import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * adapted from
 * http://stackoverflow.com/questions/8140571/resizing-layouts-programatically
 * -as-animation
 */
public class ResizeHeigthAnimation extends Animation {

	private View mView;
	private float mToHeight;
	private float mFromHeight;

	public ResizeHeigthAnimation(View v, float toHeight) {
		mToHeight = toHeight;
		mFromHeight = v.getHeight();
		mView = v;
		setDuration(300);
	}

	@Override
	protected void applyTransformation(float interpolatedTime, Transformation t) {
		float height = (mToHeight - mFromHeight) * interpolatedTime
				+ mFromHeight;
		LayoutParams p = mView.getLayoutParams();
		p.height = (int) height;
		mView.requestLayout();
	}
}
