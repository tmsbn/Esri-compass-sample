package com.myarcgissamples.compassexample;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import com.esri.android.map.MapView;
import com.esri.android.map.event.OnPinchListener;

public class ListenerOnMapPinch implements OnPinchListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MapView map;
	private Compass compass;

	public ListenerOnMapPinch(Activity activity, MapView map, int compassId) {
		this.map = map;
		compass = (Compass) activity.findViewById(compassId);

	}

	public Compass getCompass() {
		return compass;
	}

	public void resetCompass() {

		map.getCallout().hide();
		compass.reset();

		map.setRotationAngle(0, map.getCenter(), true);

	}

	@Override
	public void postPointersDown(float arg0, float arg1, float arg2,
			float arg3, double arg4) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postPointersMove(float arg0, float arg1, float arg2,
			float arg3, double arg4) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postPointersUp(float arg0, float arg1, float arg2, float arg3,
			double arg4) {
		// TODO Auto-generated method stub

	}

	@Override
	public void prePointersDown(float arg0, float arg1, float arg2, float arg3,
			double arg4) {
		// TODO Auto-generated method stub

	}

	@Override
	public void prePointersMove(float arg0, float arg1, float arg2, float arg3,
			double arg4) {
		// TODO Auto-generated method stub

		rotateCompass();

	}

	@Override
	public void prePointersUp(float arg0, float arg1, float arg2, float arg3,
			double arg4) {
		// TODO Auto-generated method stub

	}

	public void rotateCompass() {

		if (map.getRotationAngle() != 0.0)
			compass.setVisibility(View.VISIBLE);

		compass.setRotationAngle(map.getRotationAngle());
		compass.postInvalidate();

		Log.v(this.getClass().getSimpleName(),
				"compass angle:" + map.getRotationAngle());

	}

}
