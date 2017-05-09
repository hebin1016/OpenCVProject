LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
OPENCV_INSTALL_MODULES:=on
OPENCV_CAMERA_MODULES:=off
OPENCV_LIB_TYPE :=STATIC

ifeq ("$(wildcard $(OPENCV_MK_PATH))","")
include ~/third_party/OpenCV-2.4.9-android-sdk/sdk/native/jni/OpenCV.mk
else  
include $(OPENCV_MK_PATH)  
endif 


LOCAL_MODULE    := opencv
LOCAL_SRC_FILES := opencv.cpp
LOCAL_LDLIBS    += -lm -llog -landroid
LOCAL_STATIC_LIBRARIES += android_native_app_glue

include $(BUILD_SHARED_LIBRARY)

$(call import-module,android/native_app_glue)