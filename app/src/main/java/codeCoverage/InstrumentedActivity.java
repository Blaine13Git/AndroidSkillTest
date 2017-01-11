package codeCoverage;

/**
 * Created by fc on 17-1-10.
 */

import android.annotation.SuppressLint;
import android.util.Log;

import com.windsing.androidskilltest.MainActivity;

@SuppressLint("NewApi")
public class InstrumentedActivity extends MainActivity {

    public static String TAG = "IntrumentedPlayer";

    public FinishListener getFinishListener() {
        return mListener;
    }

    public void setFinishListener(FinishListener mListener) {
        this.mListener = mListener;
    }

    private FinishListener mListener;

    @Override
    public void onDestroy() {
        super.onDestroy();
        super.finish();

        Log.d(TAG + ".InstrumentedActivity", "onDestroy()");

        if (mListener != null) {
            mListener.onActivityFinished();
        }
    }

}