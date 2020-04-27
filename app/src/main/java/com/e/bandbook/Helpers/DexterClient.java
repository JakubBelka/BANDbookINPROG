package com.e.bandbook.Helpers;

import android.Manifest;
import android.content.*;

import com.e.bandbook.Listeners.PermissionsListener;
import com.karumi.dexter.Dexter;

public class DexterClient {

    Context mContext;

    public DexterClient(Context context) {
        this.mContext = context;
    }

    public
    boolean checkPermission() {
        PermissionsListener permissionsListener = new PermissionsListener(mContext);
        Dexter.withContext(mContext)
                .withPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(permissionsListener).check();


        return permissionsListener.isPermissionsGranted();
    }
}
