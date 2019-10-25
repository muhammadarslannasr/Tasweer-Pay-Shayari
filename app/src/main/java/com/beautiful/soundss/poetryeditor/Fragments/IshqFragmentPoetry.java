package com.beautiful.soundss.poetryeditor.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.beautiful.soundss.poetryeditor.Activities.ChoosePoetryActivity;
import com.beautiful.soundss.poetryeditor.Activities.PoetryChooseFragmentsActivity;
import com.beautiful.soundss.poetryeditor.Adapters.PoetryRecyclerViewAdapter;
import com.beautiful.soundss.poetryeditor.Adapters.PoetryTextRecyclerViewAdapter;
import com.beautiful.soundss.poetryeditor.Model.Poetry;
import com.beautiful.soundss.poetryeditor.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class IshqFragmentPoetry extends Fragment {

    private List<Poetry> poetryList;
    private RecyclerView ishqRecyclerView;
    private PoetryTextRecyclerViewAdapter poetryRecyclerViewAdapter;
    PoetryChooseFragmentsActivity poetryChooseFragmentsActivity;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ishq, container, false);
        poetryList = new ArrayList<>();
        ishqRecyclerView =  view.findViewById(R.id.ishqRecyclerView);
        ishqRecyclerView.setHasFixedSize(true);
        ishqRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        poetryList.add(new Poetry("سب میرا عشق دیکھ کے لیتے ہیں تیرا نام\n" +
                "میں بھی ترے جمال کی پہچان ہو گی\n"));
        poetryList.add(new Poetry("انکار میں وہ لذت، اقرار میں کہاں ہے؟ \n" +
                "بڑھتا ہے شوق \"غالب\" تیرے نہیں نہیں میں.\n"));
        poetryList.add(new Poetry("عشق نے نکما بنا دیا غالب ورنہ\n" +
                "ہم بھی بھڑے کام کے آدمی تھے\n"));
        poetryList.add(new Poetry("وہ کہتے ہیں عشق کی وضاعت کریں\n" +
                "ہم کہتے ہیں فقط محبوب کی اطاعت کریں\n"));
        poetryList.add(new Poetry("حُسن یوسف جیسا ہو یا صورت بلال جیسی\n" +
                "عشق اگر روح سے ہو تو ہر روپ کمال ہوتا ہے\n"));
        poetryList.add(new Poetry("کہیں عشق سجدے میں گر گیا\n" +
                "کہیں عشق سجدے سے پهر گیا\n"));
        poetryList.add(new Poetry("میری آنکھوں میں جو خون اترا ہے\n" +
                "تیرے عشق کا جنون چڑھا ہے\n"));
        poetryList.add(new Poetry("کوئی زندگی کی آزمائشوں سے گزرا\n" +
                "کوئی عشق کا روگ لگا بیٹھا\n"));
        poetryList.add(new Poetry("وہ ازل سے ہی مجھ میں تھا موجود\n" +
                "عشق نے تو صرف آگاہی دی ہے بس\n"));
        poetryList.add(new Poetry("کر سجدہ عشق عبادت دا\n" +
                "جدوں ہتھ چاسیں رب من جا سی\n"));
        poetryList.add(new Poetry("دیدار میں اک طرفہ دیدار نظر آیا\n" +
                "ہر بار چھپا کوئی ہر بار نظر آی\n"));
        poetryList.add(new Poetry("تیرے جیسا کوئی ملا ہی نہیں\n" +
                "کیسے ملتا کہیں پہ تھا ہی نہیں\n"));
        poetryList.add(new Poetry("عشق بت میں کفر کا مجھ کو ادب کرنا پڑا \n" +
                "جو برہمن نے کہا آخر وہ سب کرنا پڑا \n"));
        poetryList.add(new Poetry("چلتی سانسوں کو جام کرنے لگا\n" +
                "وہ نظر سے کلام کرنے لگ\n"));
        poetryList.add(new Poetry("جو گرفتار محبت ہیں یہ ان سے پوچھو\n" +
                "ناز کیا چیز ہے کیا چیز ادا ہوتی ہے\n"));



        poetryRecyclerViewAdapter = new PoetryTextRecyclerViewAdapter(getContext(), poetryList);
        ishqRecyclerView.setAdapter(poetryRecyclerViewAdapter);
        poetryRecyclerViewAdapter.notifyDataSetChanged();


        return view;
    }

}
