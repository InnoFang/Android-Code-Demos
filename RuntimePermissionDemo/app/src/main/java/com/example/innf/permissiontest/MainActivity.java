package com.example.innf.permissiontest;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*File file = getExternalCacheDir();
        file.getPath();*/
        /*File file = getExternalFilesDir("storage");
        file.getPath();*/
    }

    public void buttonClick(View view) {

        requestRuntimePermission(new String[]{Manifest.permission.CALL_PHONE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA}, new PermissionListener() {
            @Override
            public void onGranted() {
                Toast.makeText(MainActivity.this, "所有权限都同意了", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onDenied(List<String> deniedPermission) {
                for (String permission : deniedPermission) {
                    Toast.makeText(MainActivity.this, "被拒绝权限" + permission, Toast.LENGTH_LONG).show();
                }
            }
        });
        /*List<String> permissionList = new ArrayList<>();
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.CALL_PHONE);
        }
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(this, permissionList.toArray(new String[permissionList.size()]), 1);
        } else {
            // do something
            doSomething();

        }*/

    /*
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);// requestCode回调时用来标识是谁申请了权限
        } else {
            makeCall();
        }
        */
    }

    private void makeCall() {
        try {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel://1234567890"));
            startActivity(intent);
        } catch (SecurityException e){
            e.printStackTrace();
        }
    }

    private void doSomething(){
        Toast.makeText(this, "权限都同意了", Toast.LENGTH_SHORT).show();
    }

   /* @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    for (int grantResult : grantResults) {
                        if (grantResult != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this, "某个权限被拒绝了", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            doSomething();
                        }
                    }
                }
               *//* if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 同意权限申请了
                    makeCall();
                } else {
                    // 拒绝权限申请
                    Toast.makeText(this, "权限被拒绝了", Toast.LENGTH_SHORT).show();
                }*//*
                break;
            default:
                break;
        }
    }*/
}
