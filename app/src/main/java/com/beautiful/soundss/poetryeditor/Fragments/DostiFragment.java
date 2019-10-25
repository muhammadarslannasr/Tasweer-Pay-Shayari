package com.beautiful.soundss.poetryeditor.Fragments;

import android.content.Context;
import android.net.Uri;
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


public class DostiFragment extends Fragment {

    private List<Poetry> poetryList;
    private RecyclerView ishqRecyclerView;
    private PoetryRecyclerViewAdapter poetryRecyclerViewAdapter;
    ChoosePoetryActivity choosePoetryActivity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dosti, container, false);


        poetryList = new ArrayList<>();
        ishqRecyclerView =  view.findViewById(R.id.ishqRecyclerView);
        ishqRecyclerView.setHasFixedSize(true);
        ishqRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        poetryList.add(new Poetry("وہ میرے ساتھ چل کر میری مخالفت کرتے ہیں\n" +
                "یہ دوست بھی عجب کمال کرتے ہیں\n"));
        poetryList.add(new Poetry("تم تکلف کو بھی اخلاص سمجھتے ہو فرازؔ\n" +
                "دوست ہوتا نہیں ہر ہاتھ ملانے والا\n"));
        poetryList.add(new Poetry("ذرا سا وقت بدلا ہے ہوا سے دوستی نہ کر\n" +
                "تجھے اُونچا اُڑا کے یہ کہیں آخر گرائے گی\n"));
        poetryList.add(new Poetry("عقیل ایسی کوئی یاری تجھے کیا راس آنی ہے\n" +
                "ہوا کی دوستی تو خاک ہی تیری اُڑئے گی\n"));
        poetryList.add(new Poetry("تم بھی اے دوستو ہجوم کے ساتھ\n" +
                "اصطلاحوں کی رو میں بہتے ہو\n" +
                "یہ جوانی کے چند سپنے ہیں\n" +
                "تم جنھیں میرے شعر کہتے ہو\n"));
        poetryList.add(new Poetry("تجھ سے مل کر تو یہ لگتا ہے کہ اے اجنبی دوست\n" +
                "تو مری پہلی محبت تھی مری آخری دوست\n"));
        poetryList.add(new Poetry("بارش سنگ کا موسم ہے مرے شہر میں تو\n" +
                "تو یہ شیشے سا بدن لے کے کہاں آ گئی دوست\n"));
        poetryList.add(new Poetry("میں اسے عہد شکن کیسے سمجھ لوں جس نے\n" +
                "آخری خط میں یہ لکھا تھا فقط آپ کی دوست\n"));
        poetryList.add(new Poetry("دوست بن کر بھی نہیں ساتھ نبھانے والا\n" +
                "وہی انداز ہے ظالم کا زمانے والا\n"));
        poetryList.add(new Poetry("کیا عدو کیا دوست سب کو بھا گئیں رسوائیاں\n" +
                "کون آ کر ناپتا احساس کی پہنائیاں\n"));



        //poetryRecyclerViewAdapter = new PoetryRecyclerViewAdapter(getContext(), poetryList,choosePoetryActivity.poetry_text);
        poetryRecyclerViewAdapter = new PoetryRecyclerViewAdapter(getContext(), poetryList);
        ishqRecyclerView.setAdapter(poetryRecyclerViewAdapter);
        poetryRecyclerViewAdapter.notifyDataSetChanged();



        return view;
    }


}
