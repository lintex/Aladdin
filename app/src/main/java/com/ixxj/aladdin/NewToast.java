package com.ixxj.aladdin;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by lintex on 16/3/13.
 */

public class NewToast {
    private static Toast toast = null;

    public static void showMessage(final Context act, final String msg) {
        showMessage(act, msg, Toast.LENGTH_SHORT);
    }

    public static void showMessageLong(final Context act, final String msg) {
        showMessage(act, msg, Toast.LENGTH_LONG);
    }

    public static void showMessage(final Context act, final int msg) {
        showMessage(act, msg, Toast.LENGTH_SHORT);
    }

    public static void showMessageLong(final Context act, final int msg) {
        showMessage(act, msg, Toast.LENGTH_LONG);
    }

    public static void showMessage(final Context act, final int msg, final int len) {
        toast = Toast.makeText(act, msg, len);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }


    public static void showMessage(final Context act, final String msg, final int len) {
        toast = Toast.makeText(act, msg, len);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}