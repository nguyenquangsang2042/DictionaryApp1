package com.example.dictionaryapp.Common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dictionaryapp.Model.Word_Constructor;
import com.example.dictionaryapp.R;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
    public CustomAdapter(Context context, List<Word_Constructor> list_word) {
        this.context = context;
        this.list_word = list_word;
    }

    private Context context;
    private List<Word_Constructor> list_word;
    @Override
    public int getCount() {
        return list_word.size();
    }

    @Override
    public Object getItem(int i) {
        return list_word.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 1;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1=inflater.inflate(R.layout.itemword_layout,null);
        TextView txtMean=(TextView)view1.findViewById(R.id.txtMean);
        TextView txtWord=(TextView)view1.findViewById(R.id.txtWord);
        txtMean.setText(String.valueOf(list_word.get(i).getMean()));
        txtWord.setText(String.valueOf(list_word.get(i).getWord()));
        return view1;



    }
}
