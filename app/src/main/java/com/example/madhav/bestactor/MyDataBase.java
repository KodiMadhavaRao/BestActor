package com.example.madhav.bestactor;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Madhav on 2/9/2017.
 */

public class MyDataBase extends SQLiteOpenHelper
{

    Context c;
    public MyDataBase(Context context)
    {
        super(context, "HeroRating", null, 1);
        c=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
       String qry="create table bestactor(name text primary key,ratting real)";
        db.execSQL(qry);
        Toast.makeText(c, "Table Created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
