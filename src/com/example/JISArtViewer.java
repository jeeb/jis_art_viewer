package com.example;

import android.app.Activity;
import android.os.Bundle;
import java.io.File;
import android.graphics.Typeface;
import android.os.Environment;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import static android.os.Environment.getExternalStorageDirectory;

public class JISArtViewer extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        boolean mExternalStorageAvailable;
        String state = Environment.getExternalStorageState();

        System.out.println("External storage state is: \"" + state + "\"");

        if (Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            // We can read or read and write the media
            mExternalStorageAvailable = true;
        } else {
            // Something else is wrong. It may be one of many other states, but all we need
            //  to know is we can neither read nor write
            mExternalStorageAvailable = false;
        }

        if ( mExternalStorageAvailable ) {
            /*
                Don't want to delete this file when app gets uninstalled
                so using getExternalStorageDirectory.
             */
            File font_file = new File(getExternalStorageDirectory(), "fonts/ipagp-mona.ttf");
            System.out.println("Font file exists?: \"" + font_file.exists() + "\"");
            System.out.println("Font file readable?: \"" + font_file.canRead() + "\"");

            TextView tv = (TextView)findViewById(R.id.aa_textview);

            Typeface face = Typeface.createFromFile(font_file);

            tv.setTypeface(face);
            tv.setMovementMethod(ScrollingMovementMethod.getInstance());
        }
    }
}
