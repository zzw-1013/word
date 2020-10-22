package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WordDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WordDetailFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "id";
//    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
//    private String mParam2;
    private WordValue word;

    public WordDetailFragment() {
        // Required empty public constructor
    }
    public WordDetailFragment(WordValue word){
        this.word=word;
    }
    public void run(){
        this.getFragmentManager().beginTransaction()
                .replace(R.id.worddetail,this).commit();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WordDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WordDetailFragment newInstance(String param1, String param2) {
        WordDetailFragment fragment = new WordDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);

//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_word_detail, container, false);
        TextView textViewWord=(TextView)view.findViewById(R.id.tv_word);
        TextView textViewMeaning=(TextView)view.findViewById(R.id.et_wordmean);
        TextView textViewsentence=(TextView)view.findViewById(R.id.et_sentence);
        TextView textViewsentenceCHS=(TextView)view.findViewById(R.id.et_sentenceCHS);

        textViewWord.setText(this.word.getWord());
        textViewMeaning.setText(this.word.getInterpret());
        textViewsentence.setText(this.word.getSentTrans());
        textViewsentenceCHS.setText(this.word.getSentOrig());

        return view;
    }

//    public interface myListener(WordValue mword){
//        public void myWord(this.word);
//    }
}