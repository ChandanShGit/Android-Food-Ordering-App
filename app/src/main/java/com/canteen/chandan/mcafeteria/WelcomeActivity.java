package com.canteen.chandan.mcafeteria;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.security.Permission;

import static android.icu.text.DisplayContext.LENGTH_SHORT;

public class WelcomeActivity extends AppCompatActivity {

    private String[] REQUIRED_PERMISSIONS={
            Manifest.permission.INTERNET,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private int REQUEST_CODE=112;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        if(!hasRequiredPermission(this,REQUIRED_PERMISSIONS)){
            ActivityCompat.requestPermissions(this,REQUIRED_PERMISSIONS,REQUEST_CODE);
        }else{
            startActivity(new Intent(this,MainActivity.class));
            this.finish();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
if(REQUEST_CODE==112) {

    if (grantResults.length > 0) {
        for (int grant : grantResults) {
            if (grantResults[grant] != PackageManager.PERMISSION_GRANTED) {
                return;
            }
        }
        startActivity(new Intent(this, MainActivity.class));
        this.finish();
    } else {
        Toast.makeText(this, "Please Grant All Permissions", Toast.LENGTH_SHORT).show();
    }

}else{
    Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_SHORT).show();

}

    }



    private boolean hasRequiredPermission(Context ctx, String[] permissions) {
        boolean isPermission=true;
        for(String per : permissions){
            int result=ctx.checkCallingOrSelfPermission(per);
            if(result!=PackageManager.PERMISSION_GRANTED){
                isPermission=false;
            }
        }
        return isPermission;
    }
}
