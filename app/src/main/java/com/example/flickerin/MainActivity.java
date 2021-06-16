package com.example.flickerin;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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