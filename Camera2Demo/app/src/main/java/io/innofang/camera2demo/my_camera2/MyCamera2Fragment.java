package io.innofang.camera2demo.my_camera2;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.media.ImageReader;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.innofang.camera2demo.R;
import io.innofang.camera2demo.utils.RequestPermissions;

/**
 * Author: Inno Fang
 * Time: 2017/9/25 21:55
 * Description:
 */


public class MyCamera2Fragment extends Fragment {

    private static final String TAG = "MyCamera2Fragment";

    private CameraManager mCameraManager;
    private SurfaceView mSurfaceView;
    private SurfaceHolder mSurfaceHolder;
    private Handler mHandler;
    private String mCameraId;
    private ImageReader mImageReader;
    private CameraCaptureSession mSession;
    private CaptureRequest.Builder mPreviewBuilder;

    public static MyCamera2Fragment newInstance() {
        return new MyCamera2Fragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_camera2, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        mCameraManager = (CameraManager) getContext().getSystemService(Context.CAMERA_SERVICE);
        mSurfaceView = (SurfaceView) view.findViewById(R.id.surface_view);
        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                initCameraAndPreview();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });

        view.findViewById(R.id.capture_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Log.i(TAG, "take picture");
//                    mState = STATE_WAITING_CAPTURE;
                    mSession.setRepeatingRequest(mPreviewBuilder.build(), mSessionCaptureCallback, mHandler);
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initCameraAndPreview() {
        Log.i(TAG, "initCameraAndPreview: is called");

        HandlerThread handlerThread = new HandlerThread("Camera2");
        handlerThread.start();
        mHandler = new Handler(handlerThread.getLooper());

        mCameraId = CameraCharacteristics.LENS_FACING_FRONT + "";
        mImageReader = ImageReader.newInstance(
                mSurfaceView.getWidth(),
                mSurfaceView.getHeight(),
                ImageFormat.JPEG,
                7 /* maxImages */
        );
        mImageReader.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() {
            @Override
            public void onImageAvailable(ImageReader reader) {

            }
        }, mHandler);

        RequestPermissions.requestRuntimePermission(
                getActivity(),
                new String[]{Manifest.permission.CAMERA},
                new RequestPermissions.OnRequestPermissionsListener() {
                    @Override
                    public void onGranted() {
                        try {
                            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                                return;
                            }
                            mCameraManager.openCamera(mCameraId, deviceStateCallback, mHandler);
                        } catch (CameraAccessException e) {
                            Log.e(TAG, "initCameraAndPreview: open camera failed", e);
                        }
                    }

                    @Override
                    public void onDenied(List<String> deniedPermission) {

                    }
                });


    }

    private CameraDevice.StateCallback deviceStateCallback = new CameraDevice.StateCallback() {
        @Override
        public void onOpened(@NonNull CameraDevice camera) {
            Log.i(TAG, "onOpened: camera was opened");

        }

        @Override
        public void onDisconnected(@NonNull CameraDevice camera) {

        }

        @Override
        public void onError(@NonNull CameraDevice camera, int error) {

        }


    };

    private CameraCaptureSession.StateCallback mSessionPreviewStateCallback = new
            CameraCaptureSession.StateCallback() {

                @Override
                public void onConfigured(CameraCaptureSession session) {
                    Log.d(TAG, "mSessionPreviewStateCallback onConfigured");
                    mSession = session;
                    try {
                        mPreviewBuilder.set(CaptureRequest.CONTROL_AF_MODE,
                                CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE);
                        mPreviewBuilder.set(CaptureRequest.CONTROL_AE_MODE,
                                CaptureRequest.CONTROL_AE_MODE_ON_AUTO_FLASH);
                        session.setRepeatingRequest(mPreviewBuilder.build(), mSessionCaptureCallback, mHandler);
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                        Log.e(TAG, "set preview builder failed." + e.getMessage());
                    }
                }

                @Override
                public void onConfigureFailed(@NonNull CameraCaptureSession session) {

                }
            };

    private CameraCaptureSession.CaptureCallback mSessionCaptureCallback =
            new CameraCaptureSession.CaptureCallback() {

                @Override
                public void onCaptureCompleted(CameraCaptureSession session, CaptureRequest request,
                                               TotalCaptureResult result) {
                    //            Log.d(TAG,"mSessionCaptureCallback, onCaptureCompleted");
                    mSession = session;
                    checkState(result);
                }

                @Override
                public void onCaptureProgressed(CameraCaptureSession session, CaptureRequest request,
                                                CaptureResult partialResult) {
                    Log.d(TAG, "mSessionCaptureCallback,  onCaptureProgressed");
                    mSession = session;
                    checkState(partialResult);
                }

                private void checkState(CaptureResult result) {
                    /*switch (mState) {
                        case STATE_PREVIEW:
                            // NOTHING
                            break;
                        case STATE_WAITING_CAPTURE:
                            int afState = result.get(CaptureResult.CONTROL_AF_STATE);

                            if (CaptureResult.CONTROL_AF_STATE_FOCUSED_LOCKED == afState ||
                                    CaptureResult.CONTROL_AF_STATE_NOT_FOCUSED_LOCKED == afState
                                    ||  CaptureResult.CONTROL_AF_STATE_PASSIVE_FOCUSED == afState
                                    || CaptureResult.CONTROL_AF_STATE_PASSIVE_UNFOCUSED == afState) {
                                //do something like save picture
                            }
                            break;
                    }*/
                }

            };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        RequestPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
