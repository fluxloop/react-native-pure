
package com.fluxloop.react.pure;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.pure.sdk.Pure;
import com.pure.sdk.PureCallback;
import com.pure.sdk.PureResult;

import org.json.JSONObject;

public class RNPureModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  public RNPureModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "Pure";
  }

  @ReactMethod
  public void startTracking() {
    Pure.startTracking();
  }

  @ReactMethod
  public void startTrackingWithResponse(final Promise promise) {
    Pure.startTracking(new PureCallback<PureResult>() {
      @Override
      public void onCallback(PureResult pureResult) {
        promise.resolve(pureResult.getResultCode());
      }
    });
  }

  @ReactMethod
  public void stopTracking() {
    Pure.stopTracking();
  }

  @ReactMethod
  public void stopTrackingWithResponse(final Promise promise) {
    Pure.stopTracking(new PureCallback<PureResult>() {
      @Override
      public void onCallback(PureResult pureResult) {
        promise.resolve(pureResult.getResultCode());
      }
    });
  }

  @ReactMethod
  public void isTracking(Promise promise) {
    boolean isTracking = Pure.getIsTracking();
    promise.resolve(isTracking);
  }

  @ReactMethod
  public void getClientId(final Promise promise) {
    Pure.getClientId(new PureCallback<String>() {
      @Override
      public void onCallback(String s) {
       promise.resolve(s);
      }
    });
  }

  @ReactMethod
  public void init(final Promise promise) {
    Pure.init(getReactApplicationContext(), new PureCallback<PureResult>() {
      @Override
      public void onCallback(PureResult pureResult) {
        promise.resolve(pureResult.getResultCode());
      }
    });
  }

  @ReactMethod
  public void associateMetadata(String type, JSONObject data, final Promise promise) {
    Pure.associateMetadata(type, data, new PureCallback<PureResult>() {
      @Override
      public void onCallback(PureResult pureResult) {
        promise.resolve(pureResult.getResultCode());
      }
    });
  }

  @ReactMethod
  public void associateMetadataWithForce(String type, JSONObject data, boolean force, final Promise promise) {
    Pure.associateMetadata(type, data, force, new PureCallback<PureResult>() {
      @Override
      public void onCallback(PureResult pureResult) {
        promise.resolve(pureResult.getResultCode());
      }
    });
  }


  @ReactMethod
  public void createEvent(String type, JSONObject data, final Promise promise) {
    Pure.createEvent(type, data, new PureCallback<PureResult>() {
      @Override
      public void onCallback(PureResult pureResult) {
        promise.resolve(pureResult.getResultCode());
      }
    });
  }

  @ReactMethod
  public void createEventWithForce(String type, JSONObject data, boolean force, final Promise promise) {
    Pure.createEvent(type, data, force, new PureCallback<PureResult>() {
      @Override
      public void onCallback(PureResult pureResult) {
        promise.resolve(pureResult.getResultCode());
      }
    });
  }
}