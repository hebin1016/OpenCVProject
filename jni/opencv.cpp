#include "com_example_opencvproject_NdkUtils.h"
#include <stdlib.h>
#include <stdio.h>
#include <stdio.h>
#include "opencv2/opencv.hpp"
#include"opencv2/highgui/highgui.hpp"
#include"opencv2/imgproc/imgproc.hpp"
using namespace cv;

JNIEXPORT jintArray JNICALL Java_com_example_opencvproject_NdkUtils_filter(
		JNIEnv *env, jclass jcls, jintArray j_input, jint j_width, jint j_heigh,
		jint j_type, jint j_kernal) {

	jint *cbuf = env->GetIntArrayElements(j_input, JNI_FALSE);
	if (cbuf == NULL) {
		return 0;
	}

	Mat imgData(j_heigh, j_width, CV_8UC4, (unsigned char *) cbuf);

	Mat element = getStructuringElement(MORPH_RECT, Size(j_kernal, j_kernal));

	if (j_type == 0) {
		//进行腐蚀操作
		erode(imgData, imgData, element);
	} else {
		//进行膨胀操作
		dilate(imgData, imgData, element);
	}

	int size = j_heigh * j_width;
	jintArray result = env->NewIntArray(size);
	env->SetIntArrayRegion(result, 0, size, cbuf);
	env->ReleaseIntArrayElements(j_input, cbuf, 0);

	return result;

}

