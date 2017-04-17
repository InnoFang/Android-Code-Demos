//
// Created by InnF on 2017/4/17.
//

#include "com_innofang_ndkdemo_JniUtil.h"

JNIEXPORT jint JNICALL Java_com_innofang_ndkdemo_JniUtil_add
  (JNIEnv *env, jobject obj, jint a, jint b) {
    return a + b;
}