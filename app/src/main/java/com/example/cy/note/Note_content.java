package com.example.cy.note;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Note_content extends AppCompatActivity {
    protected Button btn_determine;
    protected Button btn_cancel;
    protected TextView textView;
    protected EditText editText_title;
    protected noteDB noteDB;
    protected SQLiteDatabase db;
    protected  Bundle bundle;
    protected EditText editText;
    protected Date date_begin;
    protected Date date_end;
    protected Note_interface note_interface;
    protected String id;
    protected int  value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_content);
        inview();
        getContent();
        setListern();
    }
    /*获取系统时间，添加时间戳至首页笔记列表。更新笔记内容*/
    private void getContent() {

        bundle= getIntent().getExtras();
        if(bundle.getString(note_interface.NOTE_TYPE).equals("0")==true)
        {   value=0;
            bundle.getString("content");
            date_begin=new Date();
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String current_data= format.format(date_begin);
            textView.setText(current_data);
        }
        else if ((bundle.getString(note_interface.NOTE_TYPE).equals("1")==true))
        {   value=1;
            id=bundle.getString(note_interface.NOTE_ID);
            Log.e("tag", id );
            String content=  bundle.getString(note_interface.NOTE_CONTENT);
            String title=bundle.getString(note_interface.NOTE_TITLE);
            String time=bundle.getString(note_interface.NOTE_TIME);
            editText_title.setText(title);
            editText.setText(content);
            textView.setText(time);

        }
    }
    /*替换新的笔记内容&更新最后操作的时间戳*/
    private void setListern() {
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_determine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content=editText.getText().toString();
                String title=editText_title.getText().toString();
                date_end=new Date();
                SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String current_date=  format.format(date_end);
                if(value==0) {
                    String sql = "SELECT COUNT(*) FROM note";
                    SQLiteStatement statement = db.compileStatement(sql);
                    long count = statement.simpleQueryForLong();
                    String sql_ = "insert into " + Note_interface.NOTE_TABLE + " values" + "(" + count + "," + "'" + title + "'" + "," + "'" + content + "'" + "," + "'" + current_date + "')";
                    db.execSQL(sql_);

                }
                else if(value==1)
                {      String update_determine="update  note set content='"+content+"' where id="+id;
                    String update_determine_="update  note set title='"+title+"' where id="+id;
                    String update_determine__="update  note set time='"+current_date+"' where id="+id;
                    db.execSQL(update_determine);
                    db.execSQL(update_determine_);
                    db.execSQL(update_determine__);
                    Toast.makeText(Note_content.this,"更新成功",Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent();
                setResult(2, intent);
                finish();

            }
        });
    }

    private void inview() {
        btn_cancel=findViewById(R.id.btn_cancel);
        btn_determine=findViewById(R.id.btn_determine);
        noteDB=new noteDB(Note_content.this);
        db=noteDB.getWritableDatabase();
        textView=findViewById(R.id.content_txt_time);
        editText=findViewById(R.id.edit_note);
        editText_title=findViewById(R.id.edit_note_title);
        note_interface=new Note_interface();


    }
}
