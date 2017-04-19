//
// Created by InnF on 2017/4/17.
//

#include "com_innofang_ndkdemo_JniUtil.h"

JNIEXPORT jint JNICALL Java_com_innofang_ndkdemo_JniUtil_add
  (JNIEnv *env, jobject obj, jint a, jint b) {
    return a + b;
}

/*
 * Class:     com_innofang_ndkdemo_JniUtil
 * Method:    sub
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_com_innofang_ndkdemo_JniUtil_sub
  (JNIEnv *env, jobject obj, jint a, jint b) {
    return a - b;
}

/*
 * Class:     com_innofang_ndkdemo_JniUtil
 * Method:    mul
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_com_innofang_ndkdemo_JniUtil_mul
  (JNIEnv *env, jobject obj, jint a, jint b) {
    return a * b;
}

/*
 * Class:     com_innofang_ndkdemo_JniUtil
 * Method:    div
 * Signature: (II)D
 */
JNIEXPORT jdouble JNICALL Java_com_innofang_ndkdemo_JniUtil_div
  (JNIEnv *env, jobject obj, jint a, jint b) {
    return a * 1.0 / b * 1.0;
}