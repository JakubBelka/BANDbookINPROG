package com.e.bandbook.Listeners;

import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.BaseMultiplePermissionsListener;
import android.content.*;
import android.widget.Toast;

import java.util.List;

public class PermissionsListener extends BaseMultiplePermissionsListener {
    Context mContext;
    boolean isPermissionGranted = false;


    public PermissionsListener(Context context) {
        super();
        this.mContext = context;
    }

    @Override
    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
        super.onPermissionsChecked(multiplePermissionsReport);
        if(multiplePermissionsReport.areAllPermissionsGranted()){
            isPermissionGranted = true;
        }
        else{
            isPermissionGranted = false;
            Toast.makeText(mContext, "Nie przyznano uprawnień", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
        super.onPermissionRationaleShouldBeShown(list, permissionToken);
    }

    public boolean isPermissionsGranted() {
        return isPermissionGranted;
    }
}
