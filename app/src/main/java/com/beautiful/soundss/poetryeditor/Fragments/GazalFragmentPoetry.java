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


/**
 * A simple {@link Fragment} subclass.
 */
public class GazalFragmentPoetry extends Fragment {


    private List<Poetry> poetryList;
    private RecyclerView ishqRecyclerView;
    private PoetryTextRecyclerViewAdapter poetryRecyclerViewAdapter;
    PoetryChooseFragmentsActivity poetryChooseFragmentsActivity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gazal, container, false);
        poetryList = new ArrayList<>();
        ishqRecyclerView =  view.findViewById(R.id.ishqRecyclerView);
        ishqRecyclerView.setHasFixedSize(true);
        ishqRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        poetryList.add(new Poetry("جگنو کی روشنی ہے کاشانۂ چمن میں" +
                "یا شمع جل رہی ہے پھولوں کی انجمن میں\n" +
                "آیا ہے آسماں سے اڑ کر کوئی ستارہ\n" +
                "یا جان پڑ گئی ہے مہتاب کی کرن میں\n" +
                "یا شب کی سلطنت میں دن کا سفیر آیا\n" +
                "غربت میں آ کے چمکا گمنام تھا وطن میں\n" +
                "تکمہ کوئی گرا ہے مہتاب کی قبا کا\n" +
                "ذرہ ہے یا نمایاں سورج کے پیرہن میں\n" +
                "حسن قدیم کی اک پوشیدہ یہ جھلک تھی\n" +
                "لے آئی جس کو قدرت خلوت سے انجمن میں\n" +
                "چھوٹے سے چاند میں ہے ظلمت بھی روشنی بھی\n" +
                "نکلا کبھی گہن سے آیا کبھی گہن میں\n" +
                "\n" +
                "علامہ اقبال"));
        poetryList.add(new Poetry("کیسی ہے عجب رات یہ کیسا ہے عجب شور\n" +
                "صحرا ہی نہیں گھر بھی مچاتا ہے عجب شور\n" +
                "اک درد کی آندھی مجھے رکھتی ہے ہراساں\n" +
                "اک آس کا جھونکا بھی اڑاتا ہے عجب شور\n" +
                "روشن ہے کسی آنکھ میں تاریکیٔ احوال\n" +
                "اک طاق تمنا میں دہکتا ہے عجب شور\n" +
                "اک شخص مرے آئنۂ دل کے مقابل\n" +
                "خاموش ہے لیکن پس چہرہ ہے عجب شور\n" +
                "آتی ہے بہت دور سے پازیب کی آواز\n" +
                "پھر میری سماعت میں چہکتا ہے عجب شور\n" +
                "میں ضبط کو ہر رخ سے سپر کرتا ہوں لیکن\n" +
                "آنکھوں سے مری جھانکتا رہتا ہے عجب شور\n" +
                "شاید ترا حصہ ہے کنارے کی خموشی\n" +
                "معلوم تجھے کیا تہ دریا ہے عجب شور\n" +
                "\n" +
                "خواجہ رضی حیدر"));
        poetryList.add(new Poetry("ہر شے مسافر ہر چیز راہی\n" +
                "کیا چاند تارے کیا مرغ و ماہی\n" +
                "تو مرد میداں تو میر لشکر\n" +
                "نوری حضوری تیرے سپاہی\n" +
                "کچھ قدر اپنی تو نے نہ جانی\n" +
                "یہ بے سوادی یہ کم نگاہی\n" +
                "دنیائے دوں کی کب تک غلامی\n" +
                "یا راہبی کر یا پادشاہی\n" +
                "پیر حرم کو دیکھا ہے میں نے\n" +
                "کردار بے سوز گفتار واہی\n" +
                "\n" +
                "علامہ اقبال"));
        poetryList.add(new Poetry("کیا چاند تارے کیا مرغ و ماہی\n" +
                "تو مرد میداں تو میر لشکر\n" +
                "نوری حضوری تیرے سپاہی\n" +
                "کچھ قدر اپنی تو نے نہ جانی\n" +
                "یہ بے سوادی یہ کم نگاہی\n" +
                "دنیائے دوں کی کب تک غلامی\n" +
                "یا راہبی کر یا پادشاہی\n" +
                "پیر حرم کو دیکھا ہے میں نے\n" +
                "کردار بے سوز گفتار واہی\n" +
                "\n" +
                "علامہ اقبال"));
        poetryList.add(new Poetry("ہم نے ہی لوٹنے کا ارادہ نہیں کی" +
                "اس نے بھی بھول جانے کا وعدہ نہیں کیا\n" +
                "دکھ اوڑھتے نہیں کبھی جشن طرب میں ہم\n" +
                "ملبوس دل کو تن کا لبادہ نہیں کیا\n" +
                "جو غم ملا ہے بوجھ اٹھایا ہے اس کا خود\n" +
                "سر زیر بار ساغر و بادہ نہیں کیا\n" +
                "کار جہاں ہمیں بھی بہت تھے سفر کی شام\n" +
                "اس نے بھی التفات زیادہ نہیں کیا\n" +
                "آمد پہ تیری عطر و چراغ و سبو نہ ہوں\n" +
                "اتنا بھی بود و باش کو سادہ نہیں کیا\n" +
                "\n" +
                "پروین شاکر"));
        poetryList.add(new Poetry("کچھ لوگ تغیر سے ابھی کانپ رہے ہیں" +
                "ہم ساتھ چلے تو ہیں مگر ہانپ رہے ہیں\n" +
                "نعروں سے سیاست کی حقیقت نہیں چھپتی\n" +
                "عریاں ہے بدن لاکھ اسے ڈھانپ رہے ہیں\n" +
                "کیا بات ہے شہروں میں سمٹ آئے ہیں سارے\n" +
                "جنگل میں تو گنتی کے ہی کچھ سانپ رہے ہیں\n" +
                "مکڑی کہیں مکھی کو گرفتار نہ کر لے\n" +
                "وہ شوخ نگاہوں سے مجھے بھانپ رہے ہیں\n" +
                "یہ جھوٹ ہے یا سچ ہے سمجھ میں نہیں آتا\n" +
                "سچ بولنے میں ہونٹ مرے کانپ رہے ہیں\n" +
                "\n" +
                "آل احمد سرور"));
        poetryList.add(new Poetry("ڈھب ہیں تیرے سے باغ میں گل کے" +
                "بو گئی کچھ دماغ میں گل کے\n" +
                "جائے روغن دیا کرے ہے عشق\n" +
                "خون بلبل چراغ میں گل کے\n" +
                "دل تسلی نہیں صبا ورنہ\n" +
                "جلوے سب ہیں گے داغ میں گل کے\n" +
                "اس حدیقے کے عیش پر مت جا\n" +
                "مے نہیں ہے ایاغ میں گل کے\n" +
                "سیر کر میرؔ اس چمن کی شتاب\n" +
                "ہے خزاں بھی سراغ میں گل کے\n" +
                "\n" +
                "میر تقی میر"));

        poetryList.add(new Poetry(
                "کل ہلکی ہلکی بارش تھی \n" +
                "کل سرد ہوا کا رقص بھی تھا\n" +
                "کل پھول بھی نکھرے نکھرے تھے \n" +
                "کل ان میں آپکا عکس بھی تھا\n" +
                "کل بادل کالے گہرے تھے \n" +
                "کل چاند پہ لاکھوں پہرے تھے\n" +
                "کچھ ٹکڑے آپ کی یادوں کے \n" +
                "بڑی دیر سے دل میں ٹھہرے تھے\n" +
                "کل یادیں اُلجھی اُلجھی تھیں \n" +
                "کل تک یہ نہ سلجھی تھیں\n" +
                "کل یاد بہت تم آئے تھے \n" +
                "کل یاد بہت تم آئے تھے"));



        poetryRecyclerViewAdapter = new PoetryTextRecyclerViewAdapter(getContext(), poetryList);
        ishqRecyclerView.setAdapter(poetryRecyclerViewAdapter);
        poetryRecyclerViewAdapter.notifyDataSetChanged();
        return view;
    }

}
