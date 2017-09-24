package com.example.lee.demo.Scratch;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;


public class PinterestJavaDemo {
    private static final String TAG = "TAG";

    PinterestJavaDemo() {
        javaVariable = "I am a variable!";
    }




    // *****************************************************
    // region Concise
    // *****************************************************

    private static void sampleClickListener(View view) {
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "A click listener declared in Java");
            }
        });
    }

    // *****************************************************
    // endregion Concise
    // *****************************************************




    // *****************************************************
    // region Interoperable
    // *****************************************************


    // Interoperable 1
    private String javaVariable;

    void setJavaVariable(String str) {
        javaVariable = str;
    }

    String getJavaVariable() {
        return javaVariable;
    }



    // Interoperable 2
    @NonNull
    public static String aJavaMethodThatReturnsSomething() {
        return "A string";
    }

    public static void aJavaMethodThatNeedsAnArg(String str) {
        Log.d(TAG, "Here is a string! " + str);
    }

    // *****************************************************
    // endregion Interoperable
    // *****************************************************

}
