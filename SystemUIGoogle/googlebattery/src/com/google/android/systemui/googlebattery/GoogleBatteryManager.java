package com.google.android.systemui.googlebattery;

import android.os.IBinder;
import android.util.Log;

import vendor.google.google_battery.IGoogleBattery;

public final class GoogleBatteryManager {
    public static final boolean DEBUG = Log.isLoggable("GoogleBatteryManager", 3);

    public static void destroyHalInterface(
            IGoogleBattery iGoogleBattery, IBinder.DeathRecipient deathRecipient) {}

    public static IGoogleBattery initHalInterface(IBinder.DeathRecipient deathRecipient) {
        return null;
    }
}
