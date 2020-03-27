package org.reactnative.camera.tasks;

import org.reactnative.camera.tflite.Classifier;
import org.reactnative.camera.tflite.TFLiteObjectDetectionAPIModel;
import org.tensorflow.lite.Interpreter;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.SystemClock;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.io.ByteArrayOutputStream;

import android.util.Log;
import android.util.Size;
import android.view.Surface;
import android.view.TextureView;

import com.facebook.react.uimanager.ThemedReactContext;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.Arrays;

public class ModelProcessorAsyncTask extends android.os.AsyncTask<Void, Void, List<Classifier.Recognition>> {

  private ModelProcessorAsyncTaskDelegate mDelegate;

  private Classifier mDetector;
  private Bitmap mCroppedBitmap;

  public ModelProcessorAsyncTask(
      ModelProcessorAsyncTaskDelegate delegate,
      Classifier detector,
      Bitmap croppedBitmap
  ) {
    mDelegate = delegate;
    mDetector = detector;
    mCroppedBitmap = croppedBitmap;
  }


    
  @Override
  protected List<Classifier.Recognition> doInBackground(Void... ignored) {
//    if (isCancelled() || mDelegate == null || mModelProcessor == null) {
//      return null;
//    }
//    long startTime = SystemClock.uptimeMillis();
//    try {
//      mInputBuf.rewind();
//      mOutputBuf.rewind();
//      mModelProcessor.run(mInputBuf, mOutputBuf);
//      Log.v("DOMODEL", "1");
//    } catch (Exception e) {
//      Log.e("DOMODEL", e.toString());
//    }
//    try {
//      if (mModelMaxFreqms > 0) {
//        long endTime = SystemClock.uptimeMillis();
//        long timeTaken = endTime - startTime;
//        if (timeTaken < mModelMaxFreqms) {
//          TimeUnit.MILLISECONDS.sleep(mModelMaxFreqms - timeTaken);
//        }
//      }
//      Log.v("DOMODEL", "2");
//    } catch (Exception e) {}
//            LOGGER.i("Running detection on image " + currTimestamp);

    final long startTime = SystemClock.uptimeMillis();
    final List<Classifier.Recognition> results = mDetector.recognizeImage(mCroppedBitmap);
    final List<Classifier.Recognition> mappedRecognitions =
            new LinkedList<Classifier.Recognition>();

    for (final Classifier.Recognition result : results) {
      Log.v("RESULTX", result.toString());
      mappedRecognitions.add(result);
    }

  // computingDetection = false; MODEL_LOCK

    return mappedRecognitions;
  }

  @Override
  protected void onPostExecute(List<Classifier.Recognition> data) {
    super.onPostExecute(data);

    if (data != null) {
      mDelegate.onModelProcessed(data);
    }
    mDelegate.onModelProcessorTaskCompleted();
  }
}
