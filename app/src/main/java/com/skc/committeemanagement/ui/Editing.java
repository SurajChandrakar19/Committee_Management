package com.skc.committeemanagement.ui;

import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;


public class Editing {

    public static final int xn=800,ax=0,f = 300,s=350,th=400,fo=450,fiv=500,six=550,bu =300;//use last later between use
    public static void slideToLayout(TextInputLayout textInputLayout1, TextInputLayout textInputLayout2, TextInputLayout textInputLayout3,
                                     TextInputLayout textInputLayout4, TextInputLayout textInputLayout5 , TextInputLayout textInputLayout6)
    {
        textInputLayout1.setTranslationX(xn);
        textInputLayout2.setTranslationX(xn);
        textInputLayout3.setTranslationX(xn);
        textInputLayout4.setTranslationX(xn);
        textInputLayout5.setTranslationX(xn);
        textInputLayout6.setTranslationX(xn);

        textInputLayout1.setAlpha(ax);
        textInputLayout2.setAlpha(ax);
        textInputLayout3.setAlpha(ax);
        textInputLayout4.setAlpha(ax);
        textInputLayout5.setAlpha(ax);
        textInputLayout6.setAlpha(ax);

        textInputLayout1.animate().translationX(ax).alpha(1).setDuration(xn).setStartDelay(f).start();
        textInputLayout2.animate().translationX(ax).alpha(1).setDuration(xn).setStartDelay(s).start();
        textInputLayout3.animate().translationX(ax).alpha(1).setDuration(xn).setStartDelay(th).start();
        textInputLayout4.animate().translationX(ax).alpha(1).setDuration(xn).setStartDelay(fo).start();
        textInputLayout5.animate().translationX(ax).alpha(1).setDuration(xn).setStartDelay(fiv).start();
        textInputLayout6.animate().translationX(ax).alpha(1).setDuration(xn).setStartDelay(six).start();

    }

    public static void slideToLayout(TextInputLayout textInputLayout1, TextInputLayout textInputLayout2, TextInputLayout textInputLayout3,
                                     TextInputLayout textInputLayout4, TextInputLayout textInputLayout5)
    {
        textInputLayout1.setTranslationX(xn);
        textInputLayout2.setTranslationX(xn);
        textInputLayout3.setTranslationX(xn);
        textInputLayout4.setTranslationX(xn);
        textInputLayout5.setTranslationX(xn);

        textInputLayout1.setAlpha(ax);
        textInputLayout2.setAlpha(ax);
        textInputLayout3.setAlpha(ax);
        textInputLayout4.setAlpha(ax);
        textInputLayout5.setAlpha(ax);

        textInputLayout1.animate().translationX(ax).alpha(1).setDuration(xn).setStartDelay(f).start();
        textInputLayout2.animate().translationX(ax).alpha(1).setDuration(xn).setStartDelay(s).start();
        textInputLayout3.animate().translationX(ax).alpha(1).setDuration(xn).setStartDelay(th).start();
        textInputLayout4.animate().translationX(ax).alpha(1).setDuration(xn).setStartDelay(fo).start();
        textInputLayout5.animate().translationX(ax).alpha(1).setDuration(xn).setStartDelay(fiv).start();
    }

    public static void slideToLayout(TextInputLayout textInputLayout1, TextInputLayout textInputLayout2, TextInputLayout textInputLayout3,
                                     TextInputLayout textInputLayout4)
    {
        textInputLayout1.setTranslationX(xn);
        textInputLayout2.setTranslationX(xn);
        textInputLayout3.setTranslationX(xn);
        textInputLayout4.setTranslationX(xn);

        textInputLayout1.setAlpha(ax);
        textInputLayout2.setAlpha(ax);
        textInputLayout3.setAlpha(ax);
        textInputLayout4.setAlpha(ax);

        textInputLayout1.animate().translationX(ax).alpha(1).setDuration(xn).setStartDelay(f).start();
        textInputLayout2.animate().translationX(ax).alpha(1).setDuration(xn).setStartDelay(s).start();
        textInputLayout3.animate().translationX(ax).alpha(1).setDuration(xn).setStartDelay(th).start();
        textInputLayout4.animate().translationX(ax).alpha(1).setDuration(xn).setStartDelay(fo).start();
    }

    public static void slideToLayout(TextInputLayout textInputLayout1, TextInputLayout textInputLayout2, TextInputLayout textInputLayout3)
    {
        textInputLayout1.setTranslationX(xn);
        textInputLayout2.setTranslationX(xn);
        textInputLayout3.setTranslationX(xn);

        textInputLayout1.setAlpha(ax);
        textInputLayout2.setAlpha(ax);
        textInputLayout3.setAlpha(ax);

        textInputLayout1.animate().translationX(ax).alpha(1).setDuration(xn).setStartDelay(f).start();
        textInputLayout2.animate().translationX(ax).alpha(1).setDuration(xn).setStartDelay(s).start();
        textInputLayout3.animate().translationX(ax).alpha(1).setDuration(xn).setStartDelay(th).start();
    }

    public static void slideToLayout(TextInputLayout textInputLayout1, TextInputLayout textInputLayout2)
    {
        textInputLayout1.setTranslationX(xn);
        textInputLayout2.setTranslationX(xn);

        textInputLayout1.setAlpha(ax);
        textInputLayout2.setAlpha(ax);

        textInputLayout1.animate().translationX(ax).alpha(1).setDuration(xn).setStartDelay(f).start();
        textInputLayout2.animate().translationX(ax).alpha(1).setDuration(xn).setStartDelay(s).start();
    }

    public static void slideToLayout(TextInputLayout textInputLayout1)
    {
        textInputLayout1.setTranslationX(xn);

        textInputLayout1.setAlpha(ax);

        textInputLayout1.animate().translationX(ax).alpha(1).setDuration(xn).setStartDelay(bu).start();
    }

    public static void slideToButton(Button textInputLayout1){
        textInputLayout1.setTranslationX(xn);

        textInputLayout1.setAlpha(ax);

        textInputLayout1.animate().translationX(ax).alpha(1).setDuration(xn).setStartDelay(300).start();
    }

    public static void slideToButton(Button textInputLayout1,Button textInputLayout2){
        textInputLayout1.setTranslationX(xn);
        textInputLayout2.setTranslationX(xn);

        textInputLayout1.setAlpha(ax);
        textInputLayout2.setAlpha(ax);

        textInputLayout1.animate().translationX(ax).alpha(1).setDuration(xn).setStartDelay(f).start();
        textInputLayout2.animate().translationX(ax).alpha(1).setDuration(xn).setStartDelay(s).start();
    }

}
