package com.beautiful.soundss.poetryeditor.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beautiful.soundss.poetryeditor.Activities.ChoosePoetryActivity;
import com.beautiful.soundss.poetryeditor.Adapters.PoetryRecyclerViewAdapter;
import com.beautiful.soundss.poetryeditor.Model.Poetry;
import com.beautiful.soundss.poetryeditor.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class BeWafaFragment extends Fragment {


    private List<Poetry> poetryList;
    private RecyclerView ishqRecyclerView;
    private PoetryRecyclerViewAdapter poetryRecyclerViewAdapter;
    ChoosePoetryActivity choosePoetryActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_be_wafa, container, false);

        poetryList = new ArrayList<>();
        ishqRecyclerView =  view.findViewById(R.id.ishqRecyclerView);
        ishqRecyclerView.setHasFixedSize(true);
        ishqRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        poetryList.add(new Poetry("وہ دیکھنے مجھے آنا تو چاہتا ہوگا \n" +
//                "مگر زمانے کی باتوں سے ڈر گیا ہوگا .. ...."));
//        poetryList.add(new Poetry("مدرسہ یا دیر تھا یا کعبہ یا بت خانہ تھا \n" +
//                "ہم سبھی مہمان تھے واں تو ہی صاحب خانہ تھا .. ...."));
//        poetryList.add(new Poetry("کچھ کہوں، کچھ سنوں، ذرا ٹھہرو\n" +
//                "ابھی زندوں میں ہوں، ذرا ٹھہرو.. ...."));
//        poetryList.add(new Poetry("بے نام سا یہ درد ٹھہر کیوں نہیں جاتا \n" +
//                "جو بیت گیا ہے وہ گزر کیوں نہیں جاتا .. ...."));
//        poetryList.add(new Poetry("وہ دیکھنے مجھے آنا تو چاہتا ہوگا \n" +
//                "مگر زمانے کی باتوں سے ڈر گیا ہوگا .. ...."));
//        poetryList.add(new Poetry("مدرسہ یا دیر تھا یا کعبہ یا بت خانہ تھا \n" +
//                "ہم سبھی مہمان تھے واں تو ہی صاحب خانہ تھا .. ...."));
//        poetryList.add(new Poetry("کچھ کہوں، کچھ سنوں، ذرا ٹھہرو\n" +
//                "ابھی زندوں میں ہوں، ذرا ٹھہرو.. ...."));
//        poetryList.add(new Poetry("بے نام سا یہ درد ٹھہر کیوں نہیں جاتا \n" +
//                "جو بیت گیا ہے وہ گزر کیوں نہیں جاتا .. ...."));
//        poetryList.add(new Poetry("وہ دیکھنے مجھے آنا تو چاہتا ہوگا \n" +
//                "مگر زمانے کی باتوں سے ڈر گیا ہوگا .. ...."));
//        poetryList.add(new Poetry("مدرسہ یا دیر تھا یا کعبہ یا بت خانہ تھا \n" +
//                "ہم سبھی مہمان تھے واں تو ہی صاحب خانہ تھا .. ...."));
//        poetryList.add(new Poetry("کچھ کہوں، کچھ سنوں، ذرا ٹھہرو\n" +
//                "ابھی زندوں میں ہوں، ذرا ٹھہرو.. ...."));
//        poetryList.add(new Poetry("بے نام سا یہ درد ٹھہر کیوں نہیں جاتا \n" +
//                "جو بیت گیا ہے وہ گزر کیوں نہیں جاتا .. ...."));
//        poetryList.add(new Poetry("وہ دیکھنے مجھے آنا تو چاہتا ہوگا \n" +
//                "مگر زمانے کی باتوں سے ڈر گیا ہوگا .. ...."));
//        poetryList.add(new Poetry("مدرسہ یا دیر تھا یا کعبہ یا بت خانہ تھا \n" +
//                "ہم سبھی مہمان تھے واں تو ہی صاحب خانہ تھا .. ...."));
//        poetryList.add(new Poetry("کچھ کہوں، کچھ سنوں، ذرا ٹھہرو\n" +
//                "ابھی زندوں میں ہوں، ذرا ٹھہرو.. ...."));
//        poetryList.add(new Poetry("بے نام سا یہ درد ٹھہر کیوں نہیں جاتا \n" +
//                "جو بیت گیا ہے وہ گزر کیوں نہیں جاتا .. ...."));
//        poetryList.add(new Poetry("وہ دیکھنے مجھے آنا تو چاہتا ہوگا \n" +
//                "مگر زمانے کی باتوں سے ڈر گیا ہوگا .. ...."));
//        poetryList.add(new Poetry("مدرسہ یا دیر تھا یا کعبہ یا بت خانہ تھا \n" +
//                "ہم سبھی مہمان تھے واں تو ہی صاحب خانہ تھا .. ...."));
//        poetryList.add(new Poetry("کچھ کہوں، کچھ سنوں، ذرا ٹھہرو\n" +
//                "ابھی زندوں میں ہوں، ذرا ٹھہرو.. ...."));
//        poetryList.add(new Poetry("بے نام سا یہ درد ٹھہر کیوں نہیں جاتا \n" +
//                "جو بیت گیا ہے وہ گزر کیوں نہیں جاتا .. ...."));

        poetryList.add(new Poetry("بے وفا نا تھا معصوم بہت تھا\n" +
                "اک شخص جو رستے میں ہی چھوڑ گیا مجھ کو\n"));
        poetryList.add(new Poetry("اداس بہت تھا وہ بچھڑنے سے پہلے\n" +
                "انکھیں بھی شرارا لہجہ بھی شرارا\n"));
        poetryList.add(new Poetry("ملا جو صدیوں بعد تو بدلا بہت تھا\n" +
                "سوچیں بھی انگارا الفاظ بھی انگارا\n"));
        poetryList.add(new Poetry("کچھ اس طرح بے وفائی کی روایت رکھی اس نے\n" +
                "جذبے بھی تماشا محبت بھی تماشا\n"));
        poetryList.add(new Poetry("بے وفائی کرکے نکلوں یا وفا کر جاؤں گا\n" +
                "شہر کو ہر ذائقے سے آشنا کر جاؤں گا\n"));
        poetryList.add(new Poetry("کبھی مت ریت پہ لکھنا کوئی حرفِ وفا ایسا\n" +
                "ہوا لفظوں کی اِس ترتیب کو آخر مٹائے گی\n"));
        poetryList.add(new Poetry("بیوفا وقت تھا ، تم تھے، یا مقدر میرا \n" +
                "بات اتنی ہے کہ انجام جدائی نکلا\n"));
        poetryList.add(new Poetry("اے زندگی جب اسی سے وفا کر نہ سکی تو\n" +
                "پھر تو بتا کی تھج سے وفا کیا کریں گے ہم\n"));


        //poetryRecyclerViewAdapter = new PoetryRecyclerViewAdapter(getContext(), poetryList,choosePoetryActivity.poetry_text);
        poetryRecyclerViewAdapter = new PoetryRecyclerViewAdapter(getContext(), poetryList);
        ishqRecyclerView.setAdapter(poetryRecyclerViewAdapter);
        poetryRecyclerViewAdapter.notifyDataSetChanged();


        return view;
    }

}
