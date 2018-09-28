package com.drunkeninsomnia.testnative;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;

import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class MyRunnable implements Runnable {
    Activity m_Activity;
    Mat m_Mat;
    MyRunnable(Activity a_Activity, byte[] a_Data, int a_Width, int a_Height)
    {
        super();
        m_Mat = new Mat(a_Height + a_Height/2, a_Width, CvType.CV_8UC1);
        m_Activity = a_Activity;
        m_Mat.put(0,0, a_Data);
    }

    public void run()
    {
        final int[] myrect = MyNdk.FindObject(m_Mat.nativeObj);
        m_Activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView tv = m_Activity.findViewById(R.id.textView);
                tv.setText(Integer.toString(myrect[0]) + " " +Integer.toString(myrect[1]) + " " +Integer.toString(myrect[2]) + " " +Integer.toString(myrect[3]));
            }
        });
    }
}
