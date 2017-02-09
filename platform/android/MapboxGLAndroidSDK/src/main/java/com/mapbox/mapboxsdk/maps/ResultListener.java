package com.mapbox.mapboxsdk.maps;

import com.mapbox.mapboxsdk.style.layers.Layer;
import com.mapbox.mapboxsdk.style.sources.Source;

public interface ResultListener<T> {

  void onResult(T t);

  interface SourceResultListener<U extends Source> {
    void onResult(U u);
  }

  interface LayerResultListener<V extends Layer> {
    void onResult(V v);
  }

}
