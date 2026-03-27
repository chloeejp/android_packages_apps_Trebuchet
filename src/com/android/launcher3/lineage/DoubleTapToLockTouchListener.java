// src/com/android/launcher3/lineage/DoubleTapToLockTouchListener.java
package com.android.launcher3.lineage;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.android.launcher3.LauncherPrefs;
import com.android.launcher3.R;

public class DoubleTapToLockTouchListener implements View.OnTouchListener {

    private final GestureDetector mGestureDetector;
    private final Context mContext;

    public DoubleTapToLockTouchListener(Context context) {
        mContext = context;
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                if (isDoubleTapToLockEnabled()) {
                    lockScreen();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    private boolean isDoubleTapToLockEnabled() {
        SharedPreferences prefs = LauncherPrefs.getPrefs(mContext);
        return prefs.getBoolean(DoubleTapToLockPreference.KEY_DOUBLE_TAP_TO_LOCK, false);
    }

    private void lockScreen() {
        // Uses the Accessibility global action to lock the screen (no root needed)
        // Requires the launcher to be registered as an accessibility service, OR
        // we use DevicePolicyManager if admin, OR the PowerManager (requires permission).
        // Best no-root approach: AccessibilityService global action
        DoubleTapToLockAccessibilityService service =
                DoubleTapToLockAccessibilityService.getInstance();
        if (service != null) {
            service.performGlobalAction(
                    AccessibilityService.GLOBAL_ACTION_LOCK_SCREEN);
        }
    }
}
