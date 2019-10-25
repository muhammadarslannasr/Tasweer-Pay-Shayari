package com.beautiful.soundss.poetryeditor.Fragments;

import android.os.Bundle;
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


public class KhushiFragmentPoetry extends Fragment {
    private List<Poetry> poetryList;
    private RecyclerView ishqRecyclerView;
    private PoetryTextRecyclerViewAdapter poetryRecyclerViewAdapter;
    PoetryChooseFragmentsActivity poetryChooseFragmentsActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_khushi, container, false);
        poetryList = new ArrayList<>();
        ishqRecyclerView =  view.findViewById(R.id.ishqRecyclerView);
        ishqRecyclerView.setHasFixedSize(true);
        ishqRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        poetryList.add(new Poetry("زندگی کی سختیوں میں راحت تلاش کرتے ہیں\n" +
                "مل جائے جس بات سے خوشی وہ موقع تلاش کرتے ہیں\n"));
        poetryList.add(new Poetry("نئے دیوانوں کو دیکھیں تو خوشی ہوتی ہے \n" +
                "ہم بھی ایسے ہی تھے جب آئے تھے ویرانے میں\n"));
        poetryList.add(new Poetry("وہ سر خوشی دے کہ زندگی کو شباب سے بہرہ یاب کر دے\n" +
                "مرے خیالوں میں رنگ بھر دے مرے لہو کو شراب کر دے\n"));
        poetryList.add(new Poetry("مسرور بھی ہوں خوش بھی ہوں لیکن خوشی نہیں\n" +
                "تیرے بغیر زیست تو ہے زندگی نہیں\n"));
        poetryList.add(new Poetry("لا غم ہی ڈال دے مرے دست سوال میں\n" +
                "میں کیا کروں خوشی کو جو تیری خوشی نہیں\n"));
        poetryList.add(new Poetry("خوشی سے رنج کا بدلا یہاں نہیں ملتا\n" +
                "وہ مل گئے تو مجھے آسماں نہیں ملتا\n"));
        poetryList.add(new Poetry("کیا رو کے مانگنی ہے خوشی کے لیے دعا\n" +
                "سب دوستوں کی خیر، سبھی کے لیے دعا\n"));
        poetryList.add(new Poetry("نہیں کہ دل میں ہمیشہ خوشی بہت آئی\n" +
                "کبھی ترستے رہے اور کبھی بہت آئی\n"));



        poetryRecyclerViewAdapter = new PoetryTextRecyclerViewAdapter(getContext(), poetryList);
        ishqRecyclerView.setAdapter(poetryRecyclerViewAdapter);
        poetryRecyclerViewAdapter.notifyDataSetChanged();


        return view;
    }


}
