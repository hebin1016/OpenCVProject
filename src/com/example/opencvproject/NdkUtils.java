package com.example.opencvproject;

import android.util.Log;

public class NdkUtils {
	final static String TAG="NdkUtils";
	static{
		System.loadLibrary("opencv");
	}
	
	static {
	    try {
	        System.loadLibrary("gnustl_shared"); // added
	        System.loadLibrary("opencv_java");
	        System.loadLibrary("opencv");
	    } catch (UnsatisfiedLinkError e) {
	        Log.v(TAG, "Native code library failed to load.\n" + e);
	    } catch (Exception e) {
	        Log.v(TAG, "Exception: " + e);
	    }
	}
	
	public static native int[] filter(int[] input,int width,int height,int type,int progress);

}
