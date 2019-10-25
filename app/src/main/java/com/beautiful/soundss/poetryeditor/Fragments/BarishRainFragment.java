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
public class BarishRainFragment extends Fragment {


    private List<Poetry> poetryList;
    private RecyclerView ishqRecyclerView;
    private PoetryRecyclerViewAdapter poetryRecyclerViewAdapter;
    ChoosePoetryActivity choosePoetryActivity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_barish_rain, container, false);

        poetryList = new ArrayList<>();
        ishqRecyclerView =  view.findViewById(R.id.ishqRecyclerView);
        ishqRecyclerView.setHasFixedSize(true);
        ishqRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        poetryList.add(new Poetry("اور بازار سے کیا لے جاؤں \n" +
                "پہلی بارش کا مزا لے جاؤں\n"));
        poetryList.add(new Poetry("بارش شراب عرش ہے یہ سوچ کر عدمؔ \n" +
                "بارش کے سب حروف کو الٹا کے پی گیا\n"));
        poetryList.add(new Poetry("عجب پر لطف منظر دیکھتا رہتا ہوں بارش میں \n" +
                "بدن جلتا ہے اور میں بھیگتا رہتا ہوں بارش میں\n"));
        poetryList.add(new Poetry("ہمیں کیا معلوم تھا کہ \n" +
                "یہ موسم یوں رو پڑے گا\n" +
                "ہم نے تو آسماں کو بس \n" +
                "اپنی داستان سنائی ہے\n"));
        poetryList.add(new Poetry("\nموسم کی ادا آج بھی پر کیف ہے لیکن"+
                "اک گزری ہوئی برسات کی حسرت نہیں جاتی\n"));
        poetryList.add(new Poetry("اب کے بارش میں تو یہ کار زیاں ہونا ہی تھا\n" +
                "اپنی کچی بستیوں کو بے نشاں ہونا ہی تھا\n"));
        poetryList.add(new Poetry("اب بھی برسات کی راتوں میں بدن ٹوٹتا ہے\n" +
                "جاگ اٹھتی ہیں عجب خواہشیں انگڑائی کی\n"));
        poetryList.add(new Poetry("تپش اور بڑھ گئی ہے ان چند بوندوں کے بعد \n" +
                "کالے سیّاہ بادل نے بھی یوں ہی بہلایا مجھے\n"));
        poetryList.add(new Poetry("رم جھم رم جھم برس رہی ہے \n" +
                "یاد تمھاری قطرا قطرا\n"));
        poetryList.add(new Poetry("بارش اور محبت دونوں ہی بہت یادگار ہوتے ہیں \n" +
                "فرق صرف اتنا ہے \n" +
                "بارش میں جسم بھیگ جاتا ہے اور محبت میں آنکھیں\n"));
        poetryList.add(new Poetry("اس نے بارش میں بھی کھڑکی کھول کے دیکھا نہیں\n" +
                "بھیگنے والوں کو کل کیا کیا پریشانی ہوئی\n"));
        poetryList.add(new Poetry("کیا روگ دے گئی ہے یہ نٰئے موسم کی بارش \n" +
                "مجھے یاد آ آرہے ہیں مجھے بھول جانے والے\n"));
        poetryList.add(new Poetry("ٹوٹ پڑتی تھیں گھٹائیں جن کی آنکھیں دیکھ کر\n" +
                "وہ بھری برسات میں ترسے ہیں پانی کے لیے\n"));
        poetryList.add(new Poetry("ساتھ بارش میں لیے پھرتے ہو اس کو انجمؔ\n" +
                "تم نے اس شہر میں کیا آگ لگانی ہے کوئی\n"));
        poetryList.add(new Poetry("نکل گئے ہیں جو بادل برسنے والے تھے\n" +
                "یہ شہر آب کو ترسے گا چشم تر کے بغیر\n"));
        poetryList.add(new Poetry("میں وہ صحرا جسے پانی کی ہوس لے ڈوبی\n" +
                "تو وہ بادل جو کبھی ٹوٹ کے برسا ہی نہیں\n"));
        poetryList.add(new Poetry("میں چپ کراتا ہوں ہر شب امڈتی بارش کو\n" +
                "مگر یہ روز گئی بات چھیڑ دیتی ہے\n"));



        //poetryRecyclerViewAdapter = new PoetryRecyclerViewAdapter(getContext(), poetryList,choosePoetryActivity.poetry_text);
        poetryRecyclerViewAdapter = new PoetryRecyclerViewAdapter(getContext(), poetryList);
        ishqRecyclerView.setAdapter(poetryRecyclerViewAdapter);
        poetryRecyclerViewAdapter.notifyDataSetChanged();


        return view;
    }

}
