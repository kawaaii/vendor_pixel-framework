/*
 * Copyright (C) 2022 The PixelExperience Project
 * Copyright (C) 2022 StatixOS
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.systemui.googlebattery;

import android.content.Context;
import android.os.LocaleList;
import android.provider.DeviceConfig;
import android.provider.Settings;
import android.text.format.DateFormat;

import java.util.Locale;

public class AdaptiveChargingManager {
    private static final boolean DEBUG = false;
    private static final String TAG = "AdaptiveChargingManager";

    private Context mContext;
    private boolean mHasSystemFeature = false;

    public AdaptiveChargingManager(Context context) {
        mContext = context;
        mHasSystemFeature =
                mContext.getPackageManager()
                        .hasSystemFeature("com.google.android.feature.ADAPTIVE_CHARGING");
    }

    public interface AdaptiveChargingStatusReceiver {
        void onDestroyInterface();

        void onReceiveStatus(int seconds, String stage);
    }

    private Locale getLocale() {
        LocaleList locales = mContext.getResources().getConfiguration().getLocales();
        return (locales == null || locales.isEmpty()) ? Locale.getDefault() : locales.get(0);
    }

    public String formatTimeToFull(long j) {
        return DateFormat.format(
                        DateFormat.getBestDateTimePattern(
                                getLocale(), DateFormat.is24HourFormat(mContext) ? "Hm" : "hma"),
                        j)
                .toString();
    }

    public boolean hasAdaptiveChargingFeature() {
        return mHasSystemFeature ? isGoogleBatteryServiceAvailable() : false;
    }

    private boolean isGoogleBatteryServiceAvailable() {
        return false;
    }

    public boolean isAvailable() {
        return hasAdaptiveChargingFeature() && shouldShowNotification();
    }

    public boolean shouldShowNotification() {
        return DeviceConfig.getBoolean("adaptive_charging", "adaptive_charging_notification", true);
    }

    public boolean getEnabled() {
        return Settings.Secure.getInt(mContext.getContentResolver(), "adaptive_charging", 1) == 1;
    }

    public void setEnabled(boolean on) {
        Settings.Secure.putInt(mContext.getContentResolver(), "adaptive_charging", on ? 1 : 0);
    }

    public static boolean isStageActive(String stage) {
        return "Active".equals(stage);
    }

    public static boolean isStageEnabled(String stage) {
        return "Enabled".equals(stage);
    }

    public static boolean isStageActiveOrEnabled(String stage) {
        return isStageActive(stage) || isStageEnabled(stage);
    }

    public static boolean isActive(String state, int seconds) {
        return isStageActiveOrEnabled(state) && seconds > 0;
    }

    public boolean setAdaptiveChargingDeadline(int secondsFromNow) {
        return false;
    }

    public void queryStatus(final AdaptiveChargingStatusReceiver adaptiveChargingStatusReceiver) {}
}
