package com.example.isabelmendes.project1.gcm;

import java.io.IOException;
import java.sql.Timestamp;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;

public class GCMRegister {
    private static final String TAG = "GCMRegister";
    private static final String PROPERTY_ON_SERVER_EXPIRATION_TIME =
            "onServerExpirationTimeMs";
    private static final String PROPERTY_REG_ID = "registration_id";
    static final String PROPERTY_SENDER_ID = "senderID";
    private static final String PROPERTY_APP_VERSION = "appVersion";
    private IOException ioException;
    private static final long REGISTRATION_EXPIRY_TIME_MS =
            1000 * 3600 * 24 * 7;
    private Context context;
    private GoogleCloudMessaging gcm;
    private String regid;
    private String senderID;
    private GCMRegisterEvents gcmRegisterEvents;
    public GCMRegister(Context context,
                       GCMRegisterEvents gcmRegisterEvents) {
        this.context = context;
        this.gcmRegisterEvents = gcmRegisterEvents;
    }
}
