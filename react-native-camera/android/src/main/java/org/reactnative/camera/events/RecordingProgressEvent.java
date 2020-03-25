package org.reactnative.camera.events;

import androidx.core.util.Pools;

import org.reactnative.camera.CameraViewManager;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;

public class RecordingProgressEvent extends Event<RecordingProgressEvent> {
    private static final Pools.SynchronizedPool<RecordingProgressEvent> EVENTS_POOL = new Pools.SynchronizedPool<>(3);
    private RecordingProgressEvent() {}

    private WritableMap mResponse;

    public static RecordingProgressEvent obtain(int viewTag) {
        RecordingProgressEvent event = EVENTS_POOL.acquire();
        if (event == null) {
        event = new RecordingProgressEvent();
        }
        event.init(viewTag);
        return event;
    }

    private void init(int viewTag, WritableMap response) {
        super.init(viewTag);
        mResponse = response;
    }

    // @Override
    // public short getCoalescingKey() {
    //     int hashCode = mResponse.getString("uri").hashCode() % Short.MAX_VALUE;
    //     return (short) hashCode;
    // }

    @Override
    public String getEventName() {
        return CameraViewManager.Events.EVENT_ON_RECORDING_PROGRESS.toString();
    }

    @Override
    public void dispatch(RCTEventEmitter rctEventEmitter) {
        rctEventEmitter.receiveEvent(getViewTag(), getEventName(), mResponse);
    }
}
