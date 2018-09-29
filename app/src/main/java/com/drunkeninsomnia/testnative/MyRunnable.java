package com.drunkeninsomnia.testnative;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;

import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class MyRunnable implements Runnable {
    Activity m_Activity;
    Mat m_Mat;
    TensorflowInference m_TensorInterface;
    MyRunnable(Activity a_Activity, byte[] a_Data, int a_Width, int a_Height, TensorflowInference a_TensorInterface)
    {
        super();
        m_Mat = new Mat(a_Height + a_Height/2, a_Width, CvType.CV_8UC3);
        m_Activity = a_Activity;
        m_Mat.put(0,0, a_Data);
        m_TensorInterface = a_TensorInterface;
    }

    public void run()
    {
        if(m_Mat !=null) {
            float[] myrect = MyNdk.FindObject(m_Mat.nativeObj);
            m_TensorInterface.RunGraph(myrect);
        }
    }//
}
