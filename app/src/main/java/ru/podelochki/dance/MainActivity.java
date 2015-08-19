package ru.podelochki.dance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    static String TAG = "Scanner APP";
    private TextView mUserTextView;
    private TextView mUserTextView4;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button scanButton=(Button) findViewById(R.id.scan);
        final Button manageButton=(Button) findViewById(R.id.manage);
        mUserTextView=(TextView) findViewById(R.id.textView);
        mImageView=(ImageView) findViewById(R.id.imageView);
        mUserTextView4=(TextView) findViewById(R.id.textView4);
        scanButton.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
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
        switch (barcode){
            case "4604386002625":
                mUserTextView4.setText("Dmitry");
                mImageView.setImageResource(R.drawable.dmitry);
            default:
                mUserTextView4.setText("Unknown");
                mImageView.setImageResource(R.drawable.unknown);
        }
    }
}
