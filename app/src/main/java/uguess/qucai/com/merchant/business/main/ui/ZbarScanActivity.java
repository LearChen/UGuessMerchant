package uguess.qucai.com.merchant.business.main.ui;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;

import org.json.JSONObject;

import java.io.IOException;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zbar.ZBarView;
import uguess.qucai.com.merchant.R;
import uguess.qucai.com.merchant.framework.ui.base.BaseActivity;
import uguess.qucai.com.merchant.framework.ui.helper.Alert;

public class ZbarScanActivity extends BaseActivity implements QRCodeView.ResultHandler {
    private ZBarView mZBarView;

    private MediaPlayer mediaPlayer;
    private boolean playBeep;
    private static final float BEEP_VOLUME = 0.10f;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_scan_zbar);
        mZBarView = (ZBarView) findViewById(R.id.zbarview);
        mZBarView.setResultHandler(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mZBarView.startSpot();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mZBarView.startCamera();
        mZBarView.startSpot();
    }

    @Override
    protected void onStop() {
        mZBarView.stopCamera();
        super.onStop();
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    @Override
    public void handleResult(String result) {
        Log.i("bingo", "result:" + result);
//        if(DEBUG)
//        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        playBeep = true;
        AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);
        if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL)
        {
            playBeep = false;
        }
        initBeepSound();
        if (playBeep && mediaPlayer != null)
        {
            mediaPlayer.start();
        }
        vibrate();
        Intent i = makeRequest(result);
        if(i==null){
            finish();
        }else{
            startActivity(i);
            finish();
        }
        mZBarView.startSpot();
    }

    private Intent makeRequest(String param){
        try {
            Intent i = new Intent();
            JSONObject jr = new JSONObject(param);
            int requestType = jr.optInt("request_type");
            if(requestType==0){
                Alert.Toast(R.string.warning_cannt_rec_barcode);
                this.finish();
            }
            switch (requestType){
                case 3:
                    i.setClass(this,TicketDetailActivity.class);
                    i.putExtra("code",jr.optString("code"));
            }
            return i;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void handleCameraError() {
        Log.e("bingo", "打开相机出错");
    }

    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.start_spot:
//                mZBarView.startSpot();
//                break;
//            case R.id.stop_spot:
//                mZBarView.stopSpot();
//                break;
//            case R.id.start_spot_showrect:
//                mZBarView.startSpotAndShowRect();
//                break;
//            case R.id.stop_spot_hiddenrect:
//                mZBarView.stopSpotAndHiddenRect();
//                break;
//            case R.id.show_rect:
//                mZBarView.showScanRect();
//                break;
//            case R.id.hidden_rect:
//                mZBarView.hiddenScanRect();
//                break;
//            case R.id.start_preview:
//                mZBarView.startCamera();
//                break;
//            case R.id.stop_preview:
//                mZBarView.stopCamera();
//                break;
//        }
    }

    private void initBeepSound()
    {
        if (playBeep && mediaPlayer == null)
        {
            // The volume on STREAM_SYSTEM is not adjustable, and users found it
            // too loud,
            // so we now play on the music stream.
            setVolumeControlStream(AudioManager.STREAM_MUSIC);
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(beepListener);

            AssetFileDescriptor file = getResources().openRawResourceFd(R.raw.beep);
            try
            {
                mediaPlayer.setDataSource(file.getFileDescriptor(), file.getStartOffset(), file.getLength());
                file.close();
                mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
                mediaPlayer.prepare();
            }
            catch (IOException e)
            {
                mediaPlayer = null;
            }
        }
    }

    private final MediaPlayer.OnCompletionListener beepListener = new MediaPlayer.OnCompletionListener()
    {
        public void onCompletion(MediaPlayer mediaPlayer)
        {
            mediaPlayer.seekTo(0);
        }
    };


}