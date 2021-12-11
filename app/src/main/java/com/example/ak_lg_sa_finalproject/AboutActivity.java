package com.example.ak_lg_sa_finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * This class is used to trigger the activity_about and fragment_about layouts
 * @author Sara Asefi
 * @version 1.0
 */
public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout,new AboutFragment())
                .commit();

    }

    /**
     * this method menu will inflate the option menu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu1,menu);
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
                startActivity(new Intent(this, MainActivity.class));
                return true;
            case R.id.item2:
                startActivity(new Intent(this, ContactsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}