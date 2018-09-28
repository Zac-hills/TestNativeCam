package com.drunkeninsomnia.testnative;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Trace;
import android.util.Log;

import org.tensorflow.Graph;
import org.tensorflow.Operation;
import org.tensorflow.contrib.android.TensorFlowInferenceInterface;

public class TensorflowInference {
    TensorFlowInferenceInterface m_TensorflowInference;
    String InputName;
    String[] OutputName;
    TensorflowInference(AssetManager a_AssetManager)
    {
        //model name
        m_TensorflowInference = new TensorFlowInferenceInterface(a_AssetManager, "");
        //operation name
        final Operation l_Operation = m_TensorflowInference.graphOperation("");
        final int l_NumOfClasses = (int)l_Operation.output(0).shape().size(1);
        Log.d("Model Info", "Number of output classes: " + l_NumOfClasses);
    }

    float[] RunGraph(byte[] a_Img)
    {
        float[] Output=new float[2];
        m_TensorflowInference.feed("", a_Img);
        m_TensorflowInference.run(OutputName);
        m_TensorflowInference.fetch(OutputName, Output);
        return Output;
    }
}
