package com.drunkeninsomnia.testnative;

import android.app.Activity;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.content.Context;
import android.widget.TextView;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

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
            m_Mat.release();
            final float[] output = m_TensorInterface.RunGraph(myrect);
            m_Activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    TextView temp = m_Activity.findViewById(R.id.textView);
                    temp.setText(Float.toString(output[0]) + ' ' + Float.toString(output[1]));

                }
            });
        }
    }//
}
