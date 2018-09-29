package com.drunkeninsomnia.testnative;
import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import org.tensorflow.Graph;
import org.tensorflow.Operation;
import org.tensorflow.Shape;
import org.tensorflow.contrib.android.TensorFlowInferenceInterface;

public class TensorflowInference {

    String m_FileName="file:///android_asset/Striped_Frozen_Graph.pb";
    TensorFlowInferenceInterface m_TensorflowInference;
    String m_InputName = "Input_Tensor";
    String[] m_OutputNames = {"softmax_tensor"};

    TensorflowInference(Context a_Context)
    {
        AssetManager l_AssetManager = a_Context.getAssets();
        //getAssets
        //model name
        m_TensorflowInference = new TensorFlowInferenceInterface(l_AssetManager, m_FileName);
        Operation InputOperation=m_TensorflowInference.graphOperation(m_InputName);
        Shape checkShape = InputOperation.output(0).shape();
        //operation name
        final Operation l_Operation = m_TensorflowInference.graphOperation(m_OutputNames[0]);
        final int l_NumOfClasses = (int)l_Operation.output(0).shape().size(1);
        Log.d("Model Info", "Number of output classes: " + l_NumOfClasses);
    }

     float[] RunGraph(float[] a_Img)
    {
        float[] l_Output=new float[2];
        m_TensorflowInference.feed(m_InputName, a_Img);
        m_TensorflowInference.run(m_OutputNames);
        m_TensorflowInference.fetch(m_OutputNames[0], l_Output);
        return l_Output;
    }

}
