package com.example.flickerin.customcodes.textcustomcodes;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.TextView;

public class textanimation extends androidx.appcompat.widget.AppCompatTextView {

    private CharSequence    mText;
    private int             mIndex;
    private long            mDelay = 500;
    private Handler         mHandler = new Handler();

    public textanimation(Context context) {
        super(context);
    }

    public textanimation(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    private Runnable characterAdder = new Runnable() {
        @Override
        public void run() {
            setText(mText.subSequence(0, mIndex++));
            if(mIndex <= mText.length()) {
                mHandler.postDelayed(characterAdder, mDelay);
            }
        }
    };

    public void animateText(CharSequence text) {
        mText = text;
        mIndex = 0;

        setText("");
        mHandler.removeCallbacks(characterAdder);
        mHandler.postDelayed(characterAdder, mDelay);
    }

    public void setCharacterDelay(long millis) {
        mDelay = millis;
    }

}
