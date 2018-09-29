package com.drunkeninsomnia.testnative;

public class MyNdk {
    static
    {
        System.loadLibrary("Threshold");
    }

    public static native float[] FindObject(long a_Img);
}
