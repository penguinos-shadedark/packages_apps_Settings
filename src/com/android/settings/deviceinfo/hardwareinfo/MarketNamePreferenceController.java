/*
 * Copyright (C) 2026 The Android Open Source Project
 * Licensed under the Apache License, Version 2.0.
 */
package com.android.settings.deviceinfo.hardwareinfo;

import android.content.Context;
import android.os.Build;
import android.os.SystemProperties;
import android.text.TextUtils;

import com.android.settings.core.BasePreferenceController;

/** Shows the device's commercial/market name when provided by the product. */
public class MarketNamePreferenceController extends BasePreferenceController {

    public MarketNamePreferenceController(Context context, String preferenceKey) {
        super(context, preferenceKey);
    }

    private String getMarketName() {
        String name = SystemProperties.get("ro.product.marketname");
        if (TextUtils.isEmpty(name)) {
            name = SystemProperties.get("ro.product.vendor.marketname");
        }
        if (TextUtils.isEmpty(name)) {
            name = SystemProperties.get("ro.product.model", Build.MODEL);
        }
        return name;
    }

    @Override
    public int getAvailabilityStatus() {
        return TextUtils.isEmpty(getMarketName()) ? UNSUPPORTED_ON_DEVICE : AVAILABLE;
    }

    @Override
    public CharSequence getSummary() {
        return getMarketName();
    }
}
