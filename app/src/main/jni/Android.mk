LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
OPENCVROOT := D:\Downloads\opencv-2.4.13.6-android-sdk\OpenCV-android-sdk
OPENCV_CAMERA_MODULES:=off
OPENCV_INSTALL_MODULES:=on
OPENCV_LIB_TYPE:=STATIC
include $(OPENCVROOT)/sdk/native/jni/OpenCV.mk
LOCAL_C_INCLUDES := src/main/jni

LOCAL_MODULE := Threshold
LOCAL_SRC_FILES := Threshold.cpp
include $(BUILD_SHARED_LIBRARY)