package com.example.flickerin;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;

import androidx.appcompat.app.AppCompatActivity;
import com.example.flickerin.Dashboard.UI.DahboardActivity;
import com.example.flickerin.customcodes.textcustomcodes.textanimation;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MainActivity extends AppCompatActivity {
    textanimation   appname,captions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.mainactivity);

        appname     = (textanimation) findViewById(R.id.appname);
        captions    = (textanimation) findViewById(R.id.captionname);

        /*String appname1 = getResources().getText(R.string.app_name).toString();
        SpannableString spannableString = new SpannableString(appname1);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(getResources().getColor(R.color.colorLight));
        spannableString.setSpan(new RelativeSizeSpan(1.3f), 0,1, 0);
        ForegroundColorSpan colorSpan1 = new ForegroundColorSpan(Color.BLACK);
        spannableString.setSpan(colorSpan,0,1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(colorSpan1, 1, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


        /*TextPaint paint = appname.getPaint();
        float width = paint.measureText(appname1);

        Shader textShader = new LinearGradient(0, 0, width, 0,
                new int[]{
                        Color.parseColor("#ed6ea0"),
                        Color.parseColor("#ff9a44"),
                }, null, Shader.TileMode.CLAMP);
        appname.getPaint().setShader(textShader);



        appname.setText(spannableString);*/
        appname.setCharacterDelay(50);
        appname.animateText(getResources().getText(R.string.app_name));

        captions.setCharacterDelay(50);
        captions.animateText(getResources().getText(R.string.captions));


        new Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        Intent i = new Intent(MainActivity.this, DahboardActivity.class);
                        startActivity(i);
                        finish();
                    }
                }, 5000);

    }


}