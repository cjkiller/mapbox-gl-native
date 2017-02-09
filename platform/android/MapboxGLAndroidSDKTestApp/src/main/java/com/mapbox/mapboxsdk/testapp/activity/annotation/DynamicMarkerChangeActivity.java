package com.mapbox.mapboxsdk.testapp.activity.annotation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.ResultListener;
import com.mapbox.mapboxsdk.testapp.R;

public class DynamicMarkerChangeActivity extends AppCompatActivity {

  private static final LatLng LAT_LNG_CHELSEA = new LatLng(51.481670, -0.190849);
  private static final LatLng LAT_LNG_ARSENAL = new LatLng(51.555062, -0.108417);

  private MapView mapView;
  private MapboxMap mapboxMap;
  private IconFactory iconFactory;
  private Marker marker;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_dynamic_marker);

    iconFactory = IconFactory.getInstance(this);

    mapView = (MapView) findViewById(R.id.mapView);
    mapView.setTag(false);
    mapView.onCreate(savedInstanceState);
    mapView.getMapAsync(new OnMapReadyCallback() {
      @Override
      public void onMapReady(@NonNull MapboxMap mapboxMap) {
        DynamicMarkerChangeActivity.this.mapboxMap = mapboxMap;
        // Create marker
        MarkerOptions markerOptions = new MarkerOptions()
          .position(LAT_LNG_CHELSEA)
          .icon(iconFactory.fromResource(R.drawable.ic_chelsea))
          .title(getString(R.string.dynamic_marker_chelsea_title))
          .snippet(getString(R.string.dynamic_marker_chelsea_snippet));
        mapboxMap.addMarker(markerOptions, new ResultListener<Marker>() {
          @Override
          public void onResult(Marker marker) {
            DynamicMarkerChangeActivity.this.marker = marker;
          }
        });
      }
    });

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setColorFilter(ContextCompat.getColor(this, R.color.primary));
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (mapboxMap != null && marker != null) {
          updateMarker();
        }
      }
    });
  }

  private void updateMarker() {
    // update model
    boolean first = (boolean) mapView.getTag();
    mapView.setTag(!first);

    // update marker
    marker.setPosition(first ? LAT_LNG_CHELSEA : LAT_LNG_ARSENAL);
    marker.setIcon(iconFactory.fromResource(first ? R.drawable.ic_chelsea : R.drawable.ic_arsenal));
    marker.setTitle(first
      ? getString(R.string.dynamic_marker_chelsea_title) : getString(R.string.dynamic_marker_arsenal_title));
    marker.setSnippet(first
      ? getString(R.string.dynamic_marker_chelsea_snippet) : getString(R.string.dynamic_marker_arsenal_snippet));
  }

  @Override
  protected void onStart() {
    super.onStart();
    mapView.onStart();
  }

  @Override
  protected void onResume() {
    super.onResume();
    mapView.onResume();
  }

  @Override
  protected void onPause() {
    super.onPause();
    mapView.onPause();
  }

  @Override
  protected void onStop() {
    super.onStop();
    mapView.onStop();
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    mapView.onSaveInstanceState(outState);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    mapView.onDestroy();
  }

  @Override
  public void onLowMemory() {
    super.onLowMemory();
    mapView.onLowMemory();
  }
}
