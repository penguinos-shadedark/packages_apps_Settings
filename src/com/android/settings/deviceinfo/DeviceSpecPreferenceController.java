/*
 * SPDX-FileCopyrightText: 2026 Penguin Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.android.settings.deviceinfo;

import android.content.Context;
import android.text.TextUtils;

import com.android.settings.R;
import com.android.settings.core.BasePreferenceController;

/**
 * Shows one hardware spec in About phone. The value comes from a string resource
 * that devices set through an overlay, and the preference is hidden when it is empty.
 */
public class DeviceSpecPreferenceController extends BasePreferenceController {

    private static final String KEY_DEVICE = "device_spec_device";
    private static final String KEY_PROCESSOR = "device_spec_processor";
    private static final String KEY_BATTERY = "device_spec_battery";
    private static final String KEY_SCREEN = "device_spec_screen";

    public DeviceSpecPreferenceController(Context context, String preferenceKey) {
        super(context, preferenceKey);
    }

    @Override
    public int getAvailabilityStatus() {
        return TextUtils.isEmpty(getSummary()) ? UNSUPPORTED_ON_DEVICE : AVAILABLE;
    }

    @Override
    public CharSequence getSummary() {
        final int resId = getValueResId(getPreferenceKey());
        return resId == 0 ? null : mContext.getString(resId);
    }

    private static int getValueResId(String key) {
        switch (key) {
            case KEY_DEVICE:
                return R.string.aospa_device_message;
            case KEY_PROCESSOR:
                return R.string.aospa_processor_code_message;
            case KEY_BATTERY:
                return R.string.aospa_battery_type_message;
            case KEY_SCREEN:
                return R.string.aospa_screen_message;
            default:
                return 0;
        }
    }
}
