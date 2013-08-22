package com.myarcgissamples.compassexample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.esri.android.map.MapView;
import com.esri.android.map.ags.ArcGISDynamicMapServiceLayer;
import com.esri.android.map.ags.ArcGISTiledMapServiceLayer;

public class CompassExampleActivity extends Activity implements OnClickListener {

	MapView mMapView;
	Compass compass;
	ListenerOnMapPinch listenerOnMapPinch;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mMapView = (MapView) findViewById(R.id.mapview);

		ArcGISTiledMapServiceLayer tiledLayer = new ArcGISTiledMapServiceLayer(
				"http://services.arcgisonline.com/ArcGIS/rest/services/World_Street_Map/MapServer");

		mMapView.addLayer(tiledLayer);

		mMapView.setAllowRotationByPinch(true);

		listenerOnMapPinch = new ListenerOnMapPinch(this, mMapView,
				R.id.button_compass);

		mMapView.setOnPinchListener(listenerOnMapPinch);

		compass = (Compass) findViewById(R.id.button_compass);
		compass.setOnClickListener(this);

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		super.onPause();
		mMapView.pause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		mMapView.unpause();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button_compass:
			listenerOnMapPinch.resetCompass();

		default:
			break;
		}
		// TODO Auto-generated method stub

	}
}