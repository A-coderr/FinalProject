package com.example.ak_lg_sa_finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * This class is used to implement the home page of the application
 * @author Sara Asefi
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    private Button btnChange;
    private View mainView;
    private Boolean changed = false;

    /**
     * initializing MainActivity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnChange = (Button) findViewById(R.id.btnChangeBackground);
        mainView = (View) findViewById(R.id.constraintLayoutMain);

        //Button for changing background color
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ChangeBackgroundThread().execute("");
            }
        });

        //Foreground service call
        Intent serviceIntent = new Intent(this, ForegroundService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(serviceIntent);
        }
    }

    /**
     * this is Thread to change the background color of the main menu
     */
    public class ChangeBackgroundThread extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            Log.e("Background Thread", "Thread started");
        }

        @Override
        protected Bitmap doInBackground(String... strings) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if(changed == false){
                mainView.setBackgroundResource(R.drawable.background4);
                changed = true;
            }
            else{
                mainView.setBackgroundResource(R.drawable.background5);
                changed = false;
            }
        }
    }

    /**
     * this method menu will inflate the option menu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * this method determine with option of the menu is selected
     * @param item
     * @return selected item
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                startActivity(new Intent(this, ContactsActivity.class));
                return true;
            case R.id.item2:
                startActivity(new Intent(this, AboutActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}