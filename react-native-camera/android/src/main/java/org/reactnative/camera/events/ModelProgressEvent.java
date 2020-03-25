package org.reactnative.camera.events;

import android.util.Log;

import androidx.core.util.Pools;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import org.reactnative.camera.CameraViewManager;

import java.nio.ByteBuffer;


public class ModelProgressEvent extends Event<ModelProgressEvent> {
    private static final Pools.SynchronizedPool<ModelProgressEvent> EVENTS_POOL = new Pools.SynchronizedPool<>(3);
    private ModelProgressEvent() {}

    private ByteBuffer mData;
    private WritableMap mResponse;

    public static ModelProgressEvent obtain(int viewTag, ByteBuffer data) {
        ModelProgressEvent event = EVENTS_POOL.acquire();
        if (event == null) {
        event = new ModelProgressEvent();
        }
        event.init(viewTag, data);
        return event;
    }

    private void init(int viewTag, ByteBuffer data) {
        super.init(viewTag);
        if(data != null) {
            mData = data;
            mData.rewind();
            byte[] byteArray = new byte[mData.capacity()];
            mData.get(byteArray);
            WritableArray dataList = Arguments.createArray();
            for (byte b : byteArray) {
                dataList.pushInt((int)b);
            }

            mResponse.putArray("data", dataList);
            Log.v("MODEL DATA:::", dataList.toString());
        }

        Log.v("MODEL DATA 2:::", data.toString());
    }

    // @Override
    // public short getCoalescingKey() {
    //     int hashCode = mResponse.getString("uri").hashCode() % Short.MAX_VALUE;
    //     return (short) hashCode;
    // }

    @Override
    public String getEventName() {
        return CameraViewManager.Events.EVENT_ON_MODEL_PROGRESS.toString();
    }

    @Override
    public void dispatch(RCTEventEmitter rctEventEmitter) {
        rctEventEmitter.receiveEvent(getViewTag(), getEventName(), mResponse);
    }
}
