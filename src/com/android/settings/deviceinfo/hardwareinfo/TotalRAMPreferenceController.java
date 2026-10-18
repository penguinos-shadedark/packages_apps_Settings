/*
 * Copyright (C) 2026 The Android Open Source Project
 * Licensed under the Apache License, Version 2.0.
 */
package com.android.settings.deviceinfo.hardwareinfo;

import android.app.ActivityManager;
import android.content.Context;
import android.text.format.Formatter;

import com.android.settings.core.BasePreferenceController;

/** Shows the total memory size reported by the running system. */
public class TotalRAMPreferenceController extends BasePreferenceController {

    public TotalRAMPreferenceController(Context context, String preferenceKey) {
        super(context, preferenceKey);
    }

    private long getTotalRamBytes() {
        ActivityManager manager = (ActivityManager)
                mContext.getSystemService(Context.ACTIVITY_SERVICE);
        if (manager == null) {
            return 0L;
        }
        ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
        manager.getMemoryInfo(info);
        return info.totalMem;
    }

    @Override
    public int getAvailabilityStatus() {
        return getTotalRamBytes() > 0L ? AVAILABLE : UNSUPPORTED_ON_DEVICE;
    }

    @Override
    public CharSequence getSummary() {
        long totalRam = getTotalRamBytes();
        return totalRam > 0L ? Formatter.formatFileSize(mContext, totalRam) : "";
    }
}
