package com.drunkeninsomnia.testnative;

public class MyNdk {
    static
    {
        System.loadLibrary("Threshold");
    }

    public static native int[] FindObject(long a_Img);
}
