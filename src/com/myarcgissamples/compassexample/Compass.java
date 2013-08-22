package com.myarcgissamples.compassexample;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * This class is the view that contains the compass and rotates with the map
 * once the pinch action on the map is done.
 */
public class Compass extends ImageView {

	float map_angle = 0;
	Paint paint;
	Context m_Context;
	ImageButton north;
	Bitmap bitmap;

	// @SuppressWarnings("deprecation")
	public Compass(Context context, AttributeSet attrs) {
		super(context, attrs);
		paint = new Paint();
		m_Context = context;
		north = new ImageButton(m_Context);
		// this.setAlpha(0);
	}

	public void reset() {
		AnimationSet animSet = new AnimationSet(true);
		animSet.setInterpolator(new DecelerateInterpolator());
		animSet.setFillAfter(true);
		animSet.setFillEnabled(true);

		final RotateAnimation animRotate = new RotateAnimation(-map_angle, 0,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f);

		animRotate.setDuration(600);
		animRotate.setFillAfter(true);

		animRotate.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub

				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {

						/**
						 * Make the compass invisible if the animation is over
						 * and map angle is still set to 0
						 */
						if (map_angle == 0.0) {
							clearAnimation();
							setVisibility(View.INVISIBLE);
							postInvalidate();

						}
					}
				}, 700);

			}
		});

		animSet.addAnimation(animRotate);

		this.startAnimation(animSet);
		map_angle = 0;

	}

	/**
	 * 
	 * @param angle
	 *            - map.getRotationAngle() in degrees
	 */
	public void setRotationAngle(double angle) {
		this.map_angle = (float) angle;

	}

	/**
	 * Create the bitmap from the resource. Give the angle of rotation to the
	 * matrix. Set the center of rotation as the center of the bitmap.
	 * 
	 */
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@SuppressLint("NewApi")
	@Override
	protected void onDraw(Canvas canvas) {
		bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.icon_compass_pin);
		Matrix matrix = new Matrix();
		matrix.postRotate(-this.map_angle, bitmap.getHeight() / 2,
				bitmap.getWidth() / 2);
		canvas.drawBitmap(bitmap, matrix, paint);
		super.onDraw(canvas);
	}

}
