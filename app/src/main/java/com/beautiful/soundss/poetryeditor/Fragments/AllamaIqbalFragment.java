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
public class AllamaIqbalFragment extends Fragment {

    private List<Poetry> poetryList;
    private RecyclerView ishqRecyclerView;
    private PoetryRecyclerViewAdapter poetryRecyclerViewAdapter;
    ChoosePoetryActivity choosePoetryActivity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_allama_iqbal, container, false);


        poetryList = new ArrayList<>();
        ishqRecyclerView =  view.findViewById(R.id.ishqRecyclerView);
        ishqRecyclerView.setHasFixedSize(true);
        ishqRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        poetryList.add(new Poetry("ستاروں سے آگے جہاں اور بھی ہیں\n" +
                "ابھی عشق کے امتحاں اور بھی ہیں\n"));
        poetryList.add(new Poetry("تو شاہیں ہے پرواز ہے کام تیرا\n" +
                "ترے سامنے آسماں اور بھی ہیں\n"));
        poetryList.add(new Poetry("ہنسی آتی ہے مجھے حسرت انسان پر\n" +
                "گناھ کرتا ھے خود ، لعنت بھیجتا ھے شیطان پر\n"));
        poetryList.add(new Poetry("ہے یاد مجھے نکتۂ سلمان خوش آہنگ \n" +
                "دنیا نہیں مردان جفاکش کے لیے تنگ \n"));
        poetryList.add(new Poetry("چیتے کا جگر چاہیئے شاہیں کا تجس\n" +
                "جی سکتے ہیں بے روشنی دانش و فرہنگ \n"));
       poetryList.add(new Poetry("کر بلبل و طاؤس کی تقلید سے توبہ\n" +
               "بلبل فقط آواز ہے طاؤس فقط رنگ \n"));
       poetryList.add(new Poetry("اک شرع مسلمانی اک جذب مسلمانی\n" +
               "ہے جذب مسلمانی سر فلک الافلاک \n"));
       poetryList.add(new Poetry("فارغ تو نہ بیٹھے گا محشر میں جنوں میرا\n" +
               "یا اپنا گریباں چاک یا دامن یزداں چاک\n"));
       poetryList.add(new Poetry("یقیں پیدا کر اے ناداں یقیں سے ہاتھ آتی ہے\n" +
               "وہ درویشی کہ جس کے سامنے جھکتی ہے فغفوری \n"));
       poetryList.add(new Poetry("فقیران حرم کے ہاتھ اقبالؔ آ گیا کیونکر\n" +
               "میسر میر و سلطاں کو نہیں شاہین کافوری \n"));
       poetryList.add(new Poetry("ہوا نہ زور سے اس کے کوئی گریباں چاک\n" +
               "اگرچہ مغربیوں کا جنوں بھی تھا چالاک \n"));
       poetryList.add(new Poetry("عروج آدم خاکی کے منتظر ہیں تمام\n" +
               "یہ کہکشاں یہ ستارے یہ نیلگوں افلاک\n "));
       poetryList.add(new Poetry("زمانہ عقل کو سمجھا ہوا ہے مشعل راہ\n" +
               "کسے خبر کہ جنوں بھی ہے صاحب ادراک \n"));
       poetryList.add(new Poetry("ہزار خوف ہو لیکن زباں ہو دل کی رفیق\n" +
               "یہی رہا ہے ازل سے قلندروں کا طریق\n"));
       poetryList.add(new Poetry("کمال جوش جنوں میں رہا میں گرم طواف\n" +
               "خدا کا شکر سلامت رہا حرم کا غلاف\n"));



        //poetryRecyclerViewAdapter = new PoetryRecyclerViewAdapter(getContext(), poetryList,choosePoetryActivity.poetry_text);
        poetryRecyclerViewAdapter = new PoetryRecyclerViewAdapter(getContext(), poetryList);
        ishqRecyclerView.setAdapter(poetryRecyclerViewAdapter);
        poetryRecyclerViewAdapter.notifyDataSetChanged();

        return view;
    }

}
