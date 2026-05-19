package com.example.animation_shashin.domains;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

import com.example.animation_shashin.presentations.ActivityContact;

public class PermissionManager {
    public static void GetPermision(Context context) {
        if(!CheckPermission(context)) {
            ActivityCompat.requestPermissions((Activity) context,
                    new String[]{
                            android.Manifest.permission.READ_CONTACTS,
                            Manifest.permission.CALL_PHONE
                    }, 100
            );
        }
    }
    public static boolean CheckPermission(Context context) {
        boolean hasReadContactsPermission = ActivityCompat.checkSelfPermission(
                context,
                android.Manifest.permission.READ_CONTACTS
        ) == PackageManager.PERMISSION_GRANTED;

        boolean hasCallPhonePermission = ActivityCompat.checkSelfPermission(
                context,
                android.Manifest.permission.CALL_PHONE
        ) == PackageManager.PERMISSION_GRANTED;

        return hasReadContactsPermission && hasCallPhonePermission;
    }
}
