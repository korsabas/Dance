package ru.podelochki.dance;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import static android.graphics.Color.rgb;

public class MainActivity extends AppCompatActivity {

    static String TAG = "Scanner APP";
    private TextView mUserTextView;
    private TextView mUserTextView4;
    private ImageView mImageView;
    //private int[] colors={rgb(100,120,200),rgb(200,255,255),rgb(255,200,255),rgb(255,255,200),rgb(200,255,255),rgb(255,200,255),rgb(255,255,200),rgb(200,255,255),rgb(255,200,255),rgb(255,255,200),rgb(200,255,255),rgb(255,200,255),rgb(255,255,200)};

    VisitAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdapter = new VisitAdapter(getApplicationContext());




        //visitsView.setAd


       // for (int i=0; i<colors.length; i++)
       // {
       //     VisitItem item=new VisitItem(dates[i],colors[i]);
       //     mAdapter.add(item);

        //}





        final Button scanButton=(Button) findViewById(R.id.scan);
        final Button manageButton=(Button) findViewById(R.id.manage);
        mUserTextView=(TextView) findViewById(R.id.textView);
        mImageView=(ImageView) findViewById(R.id.imageView);
        mUserTextView4=(TextView) findViewById(R.id.textView4);
        scanButton.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {


                /*getPerson("1101");*/
            try {
                new IntentIntegrator(MainActivity.this).initiateScan();
            } catch (Exception e){
                Log.e(TAG,e.toString());
            }
                }
        });


        manageButton.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                try {
                    Intent manageIntent=new Intent(MainActivity.this,ManageActivity.class);
                    startActivity(manageIntent);
                } catch (Exception e){
                    Log.e(TAG,e.toString());
                }
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (resultCode == RESULT_OK) {

        String code = data.getStringExtra("SCAN_RESULT");
        mUserTextView.setText(code);
        getPerson(code);

    }
    }

    private void getPerson(String barcode){
        switch (Integer.parseInt(barcode)){
            case 1101:
                mUserTextView4.setText("Dmitry");
                mImageView.setImageResource(R.drawable.dmitry);
                String[] dates={"mon","tue","wen","thu","fri","sut","sun","mon","tue","wen","thu","fri","sat"};
                int[] sessions={1,0,0,0,1,0,1,0,1,0,0,1,1};
                LinearLayout visits=(LinearLayout) findViewById(R.id.visitLayout);
                for (int i=0; i<dates.length; i++)
                {
                    VisitItem item=new VisitItem(dates[i],sessions[i],getApplicationContext());
                    mAdapter.add(item);
                    visits.addView(mAdapter.getView(i, null, visits));
                }
                break;
            default:
                mUserTextView4.setText("Unknown");
                mImageView.setImageResource(R.drawable.unknown);
                //String[] dates={"mon","tue","wen","thu","fri","sut","sun","mon","tue","wen","thu","fri","sat"};
                //int[] sessions={1,0,0,0,1,0,1,0,1,0,0,1,1};
        }
    }
}
