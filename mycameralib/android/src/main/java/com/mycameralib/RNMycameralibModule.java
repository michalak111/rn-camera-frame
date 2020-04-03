
package com.mycameralib;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import java.util.HashMap;
import java.util.Map;

public class RNMycameralibModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  private static final String DURATION_SHORT_KEY = "SHORT";
  private static final String DURATION_LONG_KEY = "LONG";

  public RNMycameralibModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public Map<String, Object> getConstants() {
    final Map<String, Object> constants = new HashMap<>();
    constants.put(DURATION_SHORT_KEY, Toast.LENGTH_SHORT);
    constants.put(DURATION_LONG_KEY, Toast.LENGTH_LONG);
    return constants;
  }

  @ReactMethod
  public void showToast(String message, int duration) {
    Toast toast = Toast.makeText(getReactApplicationContext(), message, duration);
    View view = toast.getView();
    TextView text = (TextView) view.findViewById(android.R.id.message);
    text.setTextColor(Color.WHITE);
    view.setBackgroundColor(Color.BLUE);
    toast.show();
  }

  @Override
  public String getName() {
    return "RNMycameralib";
  }

}
