package uguess.qucai.com.merchant.framework.ui.component;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.Map;

import uguess.qucai.com.merchant.R;

;

public class Loading {

    private Dialog mProgressDlg = null;

    private Map<String, Boolean> mState = new HashMap<String, Boolean>();

    public void start(final Activity activity) {

        if (mProgressDlg != null) {
            return;
        }

        // state为空，说明只有一个因素导致开始停止，所以不做判断
        if (!mState.isEmpty()) {

            // start前就已经设置完stop标志位，不在start
            if (isAllLoad()) {
                return;
            }
        }
        mProgressDlg = new OuserProgressDiaog(activity);
        mProgressDlg.setOnCancelListener(new DialogInterface.OnCancelListener() {

            @Override
            public void onCancel(DialogInterface dialog) {
                mProgressDlg = null;
            }
        });
        mProgressDlg.show();

    }

    public void forceStop() {
        mState.clear();
        if (mProgressDlg != null) {
            mProgressDlg.dismiss();
            mProgressDlg = null;
        }
    }

    public void stop() {
        if (mProgressDlg == null) {
            return;
        }
        if (!isAllLoad()) {
            return;
        }
        mState.clear();
        mProgressDlg.dismiss();
        mProgressDlg = null;
    }

    public boolean isShow() {
        return mProgressDlg != null;
    }

    public void setStartFlag(String key) {
        mState.put(key, true);
    }

    public void setStopFlag(String key) {
        mState.put(key, false);
    }

    /**
     * 判断是否所有数据都已经加载完成
     *
     * @return
     */
    public boolean isAllLoad() {
        for (boolean state : mState.values()) {
            if (state) {
                return false;
            }
        }
        return true;
    }

    public class OuserProgressDiaog extends Dialog {
        public OuserProgressDiaog(Activity activity) {
            super(activity, R.style.Dialog);

            Display display = activity.getWindowManager().getDefaultDisplay();
            WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
            layoutParams.width = display.getWidth() / 2;
            layoutParams.height = layoutParams.width;
            layoutParams.gravity = Gravity.CENTER;
            getWindow().setAttributes(layoutParams);

            View contentView = LayoutInflater.from(activity).inflate(R.layout.layout_progress_dialog, null);
            setContentView(contentView);
            ImageView loadingImg = (ImageView) contentView.findViewById(R.id.loading_process);
            startAnimation(loadingImg);

//            RotateAnimation animation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF,
//                    0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//            animation.setRepeatCount(-1);
//            animation.setDuration(500);
//            loadingImg.setAnimation(animation);
        }
    }

    /**
     * 开启动画
     */
    private void startAnimation(ImageView loadingImg) {
        loadingImg.setVisibility(View.VISIBLE);
        loadingImg.setImageResource(R.drawable.ic_loading_asc);
        ((AnimationDrawable) loadingImg.getDrawable()).start();
    }

    /**
     * 停止动画
     */
    private void stopAnimation(AnimationDrawable mAnimationDrawable, ImageView loadingImg) {
        loadingImg.setVisibility(View.GONE);
        ((AnimationDrawable) loadingImg.getDrawable()).stop();
    }

}
