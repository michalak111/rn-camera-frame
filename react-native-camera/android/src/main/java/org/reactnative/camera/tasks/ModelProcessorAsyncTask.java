package org.reactnative.camera.tasks;

import org.reactnative.camera.tflite.Classifier;
import android.graphics.Bitmap;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

public class ModelProcessorAsyncTask extends android.os.AsyncTask<Void, Void, List<Classifier.Recognition>> {

  private ModelProcessorAsyncTaskDelegate mDelegate;

  private Classifier mDetector;
  private Bitmap mRgbFrameBitmap;
  private Integer mSensorOrientation;

  public ModelProcessorAsyncTask(
      ModelProcessorAsyncTaskDelegate delegate,
      Classifier detector,
      Bitmap rgbFrameBitmap,
      Integer sensorOrientation
  ) {
    mDelegate = delegate;
    mDetector = detector;
    mRgbFrameBitmap = rgbFrameBitmap;
    mSensorOrientation = sensorOrientation;
  }


    
  @Override
  protected List<Classifier.Recognition> doInBackground(Void... ignored) {
    if (isCancelled() || mDelegate == null || mDetector == null) {
      return null;
    }

    final List<Classifier.Recognition> results = mDetector.recognizeImage(mRgbFrameBitmap, mSensorOrientation);
    final List<Classifier.Recognition> mappedRecognitions =
            new LinkedList<Classifier.Recognition>();

    for (final Classifier.Recognition result : results) {
      mappedRecognitions.add(result);
    }

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
