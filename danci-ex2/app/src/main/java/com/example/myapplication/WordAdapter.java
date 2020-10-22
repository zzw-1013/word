package com.example.myapplication;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.ViewHolder> {
    //用于存储查询出的数据wordvalue
    private List<WordValue> words;
    private int solution;

    WordDetailFragment fragment;

    //构造方法，获取对应控件
    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView word;
        View wordlistView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            word = (TextView)itemView.findViewById(R.id.tv_word);
            wordlistView = itemView;
        }
    }

    public WordAdapter(List<WordValue> words, int solution) {
        this.words = words;
        this.solution= solution;
    }


    public interface OnItemClickListener{
        void onItemClick(int postion);
    }
    /**
     * 创建viweholder实例
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, final int viewType) {

        //获取view对象
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wordlist_item,parent,false);
        //ViewHolder holder = new ViewHolder(view);
        //获取viewholder用于绑定点击事件
        final ViewHolder holder = new ViewHolder(view);
        //点击当前单词跳转到详细信息界面
        holder.wordlistView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取当前位置
                int position = holder.getAdapterPosition();
                //通过位置找到相应javabean
                WordValue wordValue = words.get(position);
                //intent跳转
                if (viewType == 0) {
                    //竖屏情况，则跳转
                    Intent intent = new Intent(view.getContext(),WorddetailsActivity.class);
                    intent.putExtra("bean",wordValue);
                    view.getContext().startActivity(intent);
                }
                else if (viewType == 1){
                    //横屏情况
                    //将当前的wordvalue对象赋值进入工具类保存，跨文件通信
//                    WordStorage.storeValue = wordValue;
                    WordDetailFragment fragment = new WordDetailFragment(wordValue);
//                    fragment.getFragmentManager().beginTransaction()
//                            .replace(R.id.worddetail,fragment).commit();
//                    fragment.run();
//                    setFragment(fragment);

                }
            }
        });

        return holder;
    }

    /**
     * 对子项进行赋值
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        WordValue wordValue = words.get(position);
        holder.word.setText(wordValue.getWord());
//        WordDetailFragment fragment = new WordDetailFragment(wordValue);
//        this.setFragment(fragment);
//        public void onclick(View view){
//            if()
//        }

    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    public void setFragment(WordDetailFragment fragment){this.fragment = fragment;}
    public WordDetailFragment getFragment(){return this.fragment;}

}
