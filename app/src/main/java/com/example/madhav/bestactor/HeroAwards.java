package com.example.madhav.bestactor;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;


public class HeroAwards extends AppCompatActivity {
    ArrayList a1=new ArrayList();
    ArrayList a2=new ArrayList();
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_awards);
        MyDataBase md=new MyDataBase(this);
        ListView lv=(ListView)findViewById(R.id.lv1);
        SQLiteDatabase db=md.getWritableDatabase();
        String qry="select * from bestactor";
        Cursor c=db.rawQuery(qry,null);
        if(c.moveToFirst())
        {
            do {
                String heroName=c.getString(0);
                Float rating=c.getFloat(1);
               a1.add(i,heroName);
                a2.add(i,rating);

                i++;
            }while(c.moveToNext());
        }
       MyBaseAdapter mb=new MyBaseAdapter(this,a1,a2);
        lv.setAdapter(mb);
    }
}
