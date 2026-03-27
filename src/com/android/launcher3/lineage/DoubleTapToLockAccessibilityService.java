// src/com/android/launcher3/lineage/DoubleTapToLockAccessibilityService.java
package com.android.launcher3.lineage;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;

public class DoubleTapToLockAccessibilityService extends AccessibilityService {

    private static DoubleTapToLockAccessibilityService sInstance;

    public static DoubleTapToLockAccessibilityService getInstance() {
        return sInstance;
    }

    @Override
    public void onServiceConnected() {
        super.onServiceConnected();
        sInstance = this;
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        // Not needed
    }

    @Override
    public void onInterrupt() {
        // Not needed
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        sInstance = null;
    }
}
