package uguess.qucai.com.merchant.business.common.ui.wxapi;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.view.View;

import java.io.ByteArrayOutputStream;


/**
 * MMAlert.java是启程APP的微信分享提示类。
 *
 * @author 金玉龙
 * @version 1.0 2015/6/30
 * @create 2015/6/30 14:31
 * @modify 2015/6/30 14:31
 */


public final class MMAlert {

    private static final int VALUSE = 100;

    public interface OnAlertSelectId {
        void onClick(int whichButton);
    }

    private MMAlert() {

    }

    public static AlertDialog showAlert(final Context context, final String title, final View view, final String ok, final String cancel, final DialogInterface.OnClickListener lOk,
                                        final DialogInterface.OnClickListener lCancel) {
        if (context instanceof Activity && ((Activity) context).isFinishing()) {
            return null;
        }

        final Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setView(view);
        builder.setPositiveButton(ok, lOk);
        builder.setNegativeButton(cancel, lCancel);
        final AlertDialog alert = builder.create();
        alert.show();
        return alert;
    }

    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(CompressFormat.PNG, VALUSE, output);
        if (needRecycle) {
            bmp.recycle();
        }

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
