package udesc.br.rakesfoot.core.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by Ricardo on 10/11/2016.
 */

public class DialogUtils {

    public static AlertDialog.Builder newAlert(Context context, String title, String message) {
        return new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setIcon(android.R.drawable.ic_dialog_alert);
    }

    public static void alert(Context context, String title, String message) {
        newAlert(context, title, message)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(true)
                .create()
                .show();
    }

    public static void alert(Context context, String title, String message, DialogInterface.OnClickListener positive) {
        newAlert(context, title, message)
                .setPositiveButton(android.R.string.yes, positive)
                .create()
                .show();
    }

    public static void alert(Context context, String title, String message, DialogInterface.OnClickListener positive, DialogInterface.OnClickListener negative) {
        newAlert(context, title, message)
                .setPositiveButton(android.R.string.yes, positive)
                .setNegativeButton(android.R.string.no, negative)
                .create()
                .show();
    }

}
