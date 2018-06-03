package com.example.cy.note;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;



public class listview_adapter  extends BaseAdapter{
    private List<unit_item> list;
    private Context context;
    private int layout;
    private Callback callback;
    protected Drawable drawable;


    public listview_adapter(List<unit_item> list, Context context, int layout,Callback callback) {
        this.list = list;
        this.context = context;
        this.layout = layout;
        this.callback=callback;

    }
    public  interface  Callback
    {
        public void click_delete(View view);
        public void click_update(View view);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=new ViewHolder();
        view = LayoutInflater.from(context).inflate(layout, null);
        TextView textView_title = view.findViewById(R.id.txt_title);
        viewHolder.imageView_update = view.findViewById(R.id.image_update);
        viewHolder.imageView_delete=view.findViewById(R.id.image_delete);
        TextView textview_time = view.findViewById(R.id.txt_time);
        textView_title.setText(list.get(i).getTitle());
        viewHolder.imageView_update.setImageResource(list.get(i).getUpdate());
        viewHolder.imageView_delete.setImageResource(list.get(i).getDelete());
        textview_time.setText(list.get(i).getTime());
        viewHolder.imageView_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.click_update(view);

            }
        });
        viewHolder.imageView_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.click_delete(view);
            }
        });

        viewHolder.imageView_update.setTag(i);
        viewHolder.imageView_delete.setTag(i);

        return view;
    }
    public static class ViewHolder{
        ImageView imageView_update;
        ImageView imageView_delete;
    }
}
