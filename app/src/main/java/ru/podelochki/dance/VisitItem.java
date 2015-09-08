package ru.podelochki.dance;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.TypedValue;
import static android.graphics.Color.rgb;
/**
 * Created by Dmitriy.Sivolovskiy on 9/3/2015.
 */
public class VisitItem {

    private String date;
    //private int color;
    int numberOfSessions;
    Context context;

    VisitItem(String date,int numberOfSessions,Context context
    ){
        this.date=date;
       // this.color=color;
        this.numberOfSessions=numberOfSessions;
        this.context=context;
    }

    public String getDate(){
        return date;
    }
    public int getColor(){
        if (numberOfSessions>0){
            return rgb(255,153,153);
        }
        return rgb(192,192,192);
    }
    public int getnumberOfSessions(){
        return numberOfSessions;
    }
    public int getCellHeight(){
        Resources r=context.getResources();
        float mm= TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_MM, 4, r.getDisplayMetrics());
        if (numberOfSessions>0){
            mm= TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_MM, 9, r.getDisplayMetrics());
            return (int)mm;
        }
        return (int)mm;
    }
}
