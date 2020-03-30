package org.reactnative.camera.events;

import androidx.core.util.Pools;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import org.reactnative.camera.CameraViewManager;
import org.reactnative.camera.tflite.Classifier;
import java.util.List;

public class ModelProgressEvent extends Event<ModelProgressEvent> {
    private static final Pools.SynchronizedPool<ModelProgressEvent> EVENTS_POOL = new Pools.SynchronizedPool<>(3);

    private ModelProgressEvent() {
    }

    private List<Classifier.Recognition> mData;

    public static ModelProgressEvent obtain(
            int viewTag,
            List<Classifier.Recognition> data) {
        ModelProgressEvent event = EVENTS_POOL.acquire();
        if (event == null) {
            event = new ModelProgressEvent();
        }
        event.init(viewTag, data);
        return event;
    }

    private void init(int viewTag, List<Classifier.Recognition> data) {
        super.init(viewTag);
        mData = data;
    }

    private WritableMap serializeEventData() {

        WritableArray dataList = Arguments.createArray();
        for (Classifier.Recognition entry : mData) {
            WritableMap item = Arguments.createMap();
            item.putString("title", entry.getTitle());
            item.putDouble("result", entry.getConfidence());
            dataList.pushMap(item);
        }

        WritableMap event = Arguments.createMap();
        event.putString("type", "textBlock");
        event.putArray("dataList", dataList);
        event.putInt("target", getViewTag());
        return event;
    }

    @Override
    public String getEventName() {
        return CameraViewManager.Events.EVENT_ON_MODEL_PROGRESS.toString();
    }

    @Override
    public void dispatch(RCTEventEmitter rctEventEmitter) {
        rctEventEmitter.receiveEvent(getViewTag(), getEventName(), serializeEventData());
    }
}
