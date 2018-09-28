//
// Created by zackh on 2018-05-16.
//
#include "com_drunkeninsomnia_testnative_MyNdk.h"
#include <opencv2\opencv.hpp>

using namespace cv;

JNIEXPORT jintArray JNICALL Java_com_drunkeninsomnia_testnative_MyNdk_FindObject
  (JNIEnv *environment, jclass, jlong a_img)
  {
      Mat *img = (Mat*)a_img;
      Mat thresh;
      //Mat thresh2;
      Mat Points;
	  cvtColor(*img, *img, COLOR_YCrCb2RGB);
      cvtColor(*img, *img, COLOR_RGB2HSV);

      //inRange(*img, Scalar(170,70,50), Scalar(180,255,255), thresh);
      inRange(*img, Scalar(0,70,50), Scalar(10,120,100), thresh);
      //thresh |= thresh2;
      Mat k3 = getStructuringElement(MORPH_ERODE, Size(3,3));
      Mat k8 = getStructuringElement(MORPH_DILATE, Size(8, 8));

      erode(thresh,thresh, k3);
      erode(thresh, thresh, k3);

      dilate(thresh, thresh, k8);
      dilate(thresh, thresh, k8);
      threshold(thresh, thresh, 245, 255, THRESH_BINARY);
      findNonZero(thresh, Points);
      //java
      jintArray arr = environment->NewIntArray(4);
	  if(!Points.empty())
	  {
      Rect r = boundingRect(Points);
      jint tmp[4];
      tmp[0] = r.x;
      tmp[1] = r.y;
      tmp[2] = r.width;
      tmp[3] = r.height;
	  environment->SetIntArrayRegion(arr,0,4,tmp);
	  }
      else
	  {
		  jint tmp[4];
		  tmp[0] = 0;
		  tmp[1] = 0;
		  tmp[2] = img->size().width;
		  tmp[3] = img->size().height;
		  environment->SetIntArrayRegion(arr,0,4,tmp);
	  }
      return arr;
  }