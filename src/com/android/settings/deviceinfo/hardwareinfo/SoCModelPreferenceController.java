/*
 * Copyright (C) 2026 The Android Open Source Project
 * Licensed under the Apache License, Version 2.0.
 */
package com.android.settings.deviceinfo.hardwareinfo;

import android.content.Context;
import android.os.SystemProperties;
import android.text.TextUtils;

import com.android.settings.core.BasePreferenceController;

/** Shows the SoC model reported by Android, falling back to the board platform. */
public class SoCModelPreferenceController extends BasePreferenceController {

    public SoCModelPreferenceController(Context context, String preferenceKey) {
        super(context, preferenceKey);
    }

    private String getSoCModel() {
        String model = SystemProperties.get("ro.soc.model");
        if (TextUtils.isEmpty(model)) {
            model = SystemProperties.get("ro.vendor.soc.model");
        }
        if (TextUtils.isEmpty(model)) {
            model = SystemProperties.get("ro.board.platform");
        }
        return model;
    }

    @Override
    public int getAvailabilityStatus() {
        return TextUtils.isEmpty(getSoCModel()) ? UNSUPPORTED_ON_DEVICE : AVAILABLE;
    }

    @Override
    public CharSequence getSummary() {
        return getSoCModel();
    }
}
