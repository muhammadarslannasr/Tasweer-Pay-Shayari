package com.beautiful.soundss.poetryeditor.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ArslanNasr on 10/19/2018.
 */

public class PoetrySingleton {

    private static PoetrySingleton mInstance;
    private List<Poetry> splashPoetryList = null;

    public static PoetrySingleton getInstance() {
        if(mInstance == null)
            mInstance = new PoetrySingleton();

        return mInstance;
    }

    private PoetrySingleton() {
        splashPoetryList = new ArrayList<Poetry>();
        splashPoetryList.add(new Poetry("بے وفا نا تھا معصوم بہت تھا\n" +
                "اک شخص جو رستے میں ہی چھوڑ گیا مجھ کو\n"));
        splashPoetryList.add(new Poetry("اداس بہت تھا وہ بچھڑنے سے پہلے\n" +
                "انکھیں بھی شرارا لہجہ بھی شرارا\n"));
        splashPoetryList.add(new Poetry("ملا جو صدیوں بعد تو بدلا بہت تھا\n" +
                "سوچیں بھی انگارا الفاظ بھی انگارا\n"));
        splashPoetryList.add(new Poetry("کچھ اس طرح بے وفائی کی روایت رکھی اس نے\n" +
                "جذبے بھی تماشا محبت بھی تماشا\n"));
        splashPoetryList.add(new Poetry("بے وفائی کرکے نکلوں یا وفا کر جاؤں گا\n" +
                "شہر کو ہر ذائقے سے آشنا کر جاؤں گا\n"));
        splashPoetryList.add(new Poetry("کبھی مت ریت پہ لکھنا کوئی حرفِ وفا ایسا\n" +
                "ہوا لفظوں کی اِس ترتیب کو آخر مٹائے گی\n"));
        splashPoetryList.add(new Poetry("بیوفا وقت تھا ، تم تھے، یا مقدر میرا \n" +
                "بات اتنی ہے کہ انجام جدائی نکلا\n"));
        splashPoetryList.add(new Poetry("اے زندگی جب اسی سے وفا کر نہ سکی تو\n" +
                "پھر تو بتا کی تھج سے وفا کیا کریں گے ہم\n"));

    }
    // retrieve array from anywhere
    public List<Poetry> getSplashPoetryList() {
        return this.splashPoetryList;
    }
}
