package com.example.madhav.bestactor;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    ImageView imageView;
    RatingBar ratingBar;
    Button saveDetails,viewDetails;
    String heros[]={"Select Hero","Mahesh Babu","Nagarjuna","Prabhas","Ram Charan","Allu Arjun","Nani","Rana Daggubati","Nithin","Varun Tej","Manchu Manoj"};
    int heroImages[]={R.drawable.mahesh,R.drawable.nagarjuna,R.drawable.prabhas,R.drawable.ramcharan,R.drawable.alluarjun,R.drawable.nani,R.drawable.rana,R.drawable.nitin,R.drawable.varun,R.drawable.manoj};
    float heroRating=0;
    int heroPosition=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner=(Spinner)findViewById(R.id.spinner);
        imageView=(ImageView)findViewById(R.id.imageview);
        ratingBar=(RatingBar)findViewById(R.id.ratingBar);
        saveDetails=(Button)findViewById(R.id.button);
        viewDetails=(Button)findViewById(R.id.button2);
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,heros);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if(position==0)
                {
                    imageView.setImageResource(0);

                }
                else
                {

                    imageView.setImageResource(heroImages[position-1]);
                }
                heroPosition=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser)
            {
               heroRating=rating;
                if(rating>0)
                {
                    Toast.makeText(MainActivity.this, ""+heroRating, Toast.LENGTH_SHORT).show();
                }

            }
        });
        saveDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDataBase md=new MyDataBase(MainActivity.this);
                SQLiteDatabase db=md.getWritableDatabase();
                if(heroPosition==0)
                {
                    Toast.makeText(MainActivity.this, "Select Actor and give rating ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    try {

                        String qry="insert into bestactor values('"+heros[heroPosition]+"',"+heroRating+")";
                        db.execSQL(qry);
                        Toast.makeText(MainActivity.this, "values Inserted into Db "+heros[heroPosition]+" "+heroRating, Toast.LENGTH_SHORT).show();
                    }
                    catch (SQLiteConstraintException sce)
                    {
                        Log.e("Already Stored", "true");
                        Toast.makeText(MainActivity.this, "Values not inserted dublicate values", Toast.LENGTH_SHORT).show();
                    }
                }

                ratingBar.setRating(0);
                imageView.setImageResource(0);
                spinner.setSelection(0);
                heroPosition=0;
            }
        });

        viewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,HeroAwards.class);
                startActivity(i);
                ratingBar.setRating(0);
                imageView.setImageResource(0);
                spinner.setSelection(0);
                heroPosition=0;
            }
        });

    }
}
