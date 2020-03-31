package org.reactnative.camera.tasks;

import org.reactnative.camera.tflite.Classifier;
import java.util.List;

public interface ModelProcessorAsyncTaskDelegate {
  void onModelProcessed(List<Classifier.Recognition> data /*, int sourceWidth, int sourceHeight, int sourceRotation*/);
  void onModelProcessorTaskCompleted();
}
