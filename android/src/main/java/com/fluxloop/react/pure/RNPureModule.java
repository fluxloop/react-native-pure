
package com.fluxloop.react.pure;

import com.facebook.react.bridge.*;
import com.pure.sdk.Pure;
import com.pure.sdk.PureCallback;
import com.pure.sdk.PureResult;

import org.json.JSONArray;
import org.json.JSONException;
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
    public void associateMetadata(String type, ReadableMap data, final Promise promise) {
        try {
            Pure.associateMetadata(type, mapToJson(data), new PureCallback<PureResult>() {
                @Override
                public void onCallback(PureResult pureResult) {
                    promise.resolve(pureResult.getResultCode());
                }
            });
        } catch (Exception e) {
            promise.reject("metadata_association_failed", e);
        }
    }

    @ReactMethod
    public void associateMetadataWithForce(String type, ReadableMap data, boolean force, final Promise promise) {
        try {
            Pure.associateMetadata(type, mapToJson(data), force, new PureCallback<PureResult>() {
                @Override
                public void onCallback(PureResult pureResult) {
                    promise.resolve(pureResult.getResultCode());
                }
            });
        } catch (Exception e) {
            promise.reject("metadata_association_failed", e);
        }
    }


    @ReactMethod
    public void createEvent(String type, ReadableMap data, final Promise promise) {
        try {
            Pure.createEvent(type, mapToJson(data), new PureCallback<PureResult>() {
                @Override
                public void onCallback(PureResult pureResult) {
                    promise.resolve(pureResult.getResultCode());
                }
            });
        } catch (Exception e) {
            promise.reject("event_creation_failed", e);
        }
    }

    @ReactMethod
    public void createEventWithForce(String type, ReadableMap data, boolean force, final Promise promise) {
        try {
            Pure.createEvent(type, mapToJson(data), force, new PureCallback<PureResult>() {
                @Override
                public void onCallback(PureResult pureResult) {
                    promise.resolve(pureResult.getResultCode());
                }
            });
        } catch (Exception e) {
            promise.reject("event_creation_failed", e);
        }
    }

    private JSONObject mapToJson(ReadableMap readableMap) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        ReadableMapKeySetIterator iterator = readableMap.keySetIterator();
        while (iterator.hasNextKey()) {
            String key = iterator.nextKey();
            switch (readableMap.getType(key)) {
                case Null:
                    jsonObject.put(key, JSONObject.NULL);
                    break;
                case Boolean:
                    jsonObject.put(key, readableMap.getBoolean(key));
                    break;
                case Number:
                    jsonObject.put(key, readableMap.getDouble(key));
                    break;
                case Map:
                    jsonObject.put(key, mapToJson(readableMap.getMap(key)));
                    break;
                case String:
                    jsonObject.put(key, readableMap.getString(key));
                    break;
                case Array:
                    jsonObject.put(key, arrayToJson(readableMap.getArray(key)));
                    break;
            }
        }
        return jsonObject;
    }

    private JSONArray arrayToJson(ReadableArray readableArray) throws JSONException {
        JSONArray array = new JSONArray();
        for (int i = 0; i < readableArray.size(); i++) {
            switch (readableArray.getType(i)) {
                case Null:
                    break;
                case Boolean:
                    array.put(readableArray.getBoolean(i));
                    break;
                case Number:
                    array.put(readableArray.getDouble(i));
                    break;
                case String:
                    array.put(readableArray.getString(i));
                    break;
                case Map:
                    array.put(mapToJson(readableArray.getMap(i)));
                    break;
                case Array:
                    array.put(arrayToJson(readableArray.getArray(i)));
                    break;
            }
        }
        return array;
    }
}