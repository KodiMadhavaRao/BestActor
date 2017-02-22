package com.example.madhav.bestactor;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Madhav on 2/10/2017.
 */

public class MyBaseAdapter extends BaseAdapter
{
   ArrayList heroName;
    ArrayList rating;
    Context context;
    int heroImages[]={R.drawable.mahesh,R.drawable.nagarjuna,R.drawable.prabhas,R.drawable.ramcharan,R.drawable.alluarjun,R.drawable.nani,R.drawable.rana,R.drawable.nitin,R.drawable.varun,R.drawable.manoj};
    public MyBaseAdapter(Context c, ArrayList a1, ArrayList a2)
    {
        context=c;
        heroName=a1;
        this.rating=a2;
    }
    @Override
    public int getCount() {
        Toast.makeText(context, ""+heroName.size(), Toast.LENGTH_SHORT).show();
        return heroName.size();
    }

    @Override
    public Object getItem(int position) {
        return heroName.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Activity a=(Activity)context;
        View v=a.getLayoutInflater().inflate(R.layout.style,parent,false);
        ImageView iv=(ImageView)v.findViewById(R.id.hero_image);
        TextView tv=(TextView)v.findViewById(R.id.hero_name);
        RatingBar rb=(RatingBar)v.findViewById(R.id.hero_ratting);
        iv.setImageResource(heroImages[position]);
        tv.setText(""+heroName.get(position));
        Float f=(Float)rating.get(position);
        rb.setRating(f);
        return v;
    }
}
