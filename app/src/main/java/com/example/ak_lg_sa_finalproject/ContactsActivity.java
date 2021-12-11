package com.example.ak_lg_sa_finalproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * This class is used to read data from database and populate data into recycler view
 * it also allows user to insert new data into data base using floating action button
 * and through a dialog box
 * @author Sara Asefi
 * @version 1.0
 */
public class ContactsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private ContactAdapter contactAdapter;
    private ArrayList<PersonModel> contactArrayList;

    /**
     * initializing ContactsActivity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        //getting reference to recyclerview and floatingAction button
        floatingActionButton = findViewById(R.id.floatingActionButton);
        recyclerView = findViewById(R.id.recyclerView);

        DbOperations dataBaseHelper = new DbOperations(ContactsActivity.this);

        contactArrayList = new ArrayList<>();
        contactArrayList = (ArrayList<PersonModel>) dataBaseHelper.viewRecords();

        //creating an adapter and passing contacts data
        contactAdapter = new ContactAdapter(contactArrayList, this);

        //Creating a layout manager for recycler view with vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                                                LinearLayoutManager.VERTICAL, false);

        //Setting Layout manager to position the items
        recyclerView.setLayoutManager(linearLayoutManager);

        contactAdapter.setOnItemClickListener(new ContactAdapter.ClickListener<PersonModel>() {
            @Override
            public void onItemClick(PersonModel data) {
                //storing values of data object in intent's Extra
                Intent intent = new Intent(ContactsActivity.this, Edit_Delete_Item.class);
                intent.putExtra("ID", data.getId());
                intent.putExtra("FirstName", data.getFirstName());
                intent.putExtra("LastName", data.getLastName());
                intent.putExtra("Phone", data.getPhoneNumber());
                intent.putExtra("Email", data.getEmail());
                intent.putExtra("Note", data.getNote());

                startActivity(intent);
            }
        });

        /**
         * this method will wire the listener to the add button (+)
         */
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialAlertDialogBuilder builder =
                        new MaterialAlertDialogBuilder(ContactsActivity.this);
                // inflate the Custom Layout for adding Person records and set it to the Alert Dialog Builder
                View v = getLayoutInflater().inflate(R.layout.add_alert_dialog, null);
                builder.setView(v);
                AlertDialog dialog = builder.show();

                //Get Reference to all widgets from the Custom Layout
                EditText edtFName = v.findViewById(R.id.editFName);
                EditText edtLName = v.findViewById(R.id.editLName);
                EditText edtPhone = v.findViewById(R.id.editPhone);
                EditText edtEmail = v.findViewById(R.id.editeEmail);
                EditText edtNote = v.findViewById(R.id.editNote);
                Button btnAdd = v.findViewById(R.id.buttonaddUpdate);

                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String f_name = edtFName.getText().toString();
                        String l_name = edtLName.getText().toString();
                        String phone = edtPhone.getText().toString();
                        String email = edtEmail.getText().toString();
                        String note = edtNote.getText().toString();

                        PersonModel person;
                        //creating an instance of DbOperations and calling addRecord method
                        DbOperations dbOperations = new DbOperations(ContactsActivity.this);

                        try {
                            person = new PersonModel(f_name, l_name, phone, email, note);
                            dbOperations.addRecord(person);

                            Toast.makeText(getApplicationContext(), "Added new contact " +
                                    person.getFirstName() + " " + person.getLastName()
                                    , Toast.LENGTH_SHORT).show();

                            //resetting EditText widgets
                            edtFName.setText("");
                            edtLName.setText("");
                            edtPhone.setText("");
                            edtEmail.setText("");
                            edtNote.setText("");

                        }
                        catch (Exception e) {
                            Toast.makeText(getApplicationContext(),"Error Adding new contact!",
                                    Toast.LENGTH_SHORT).show();
                        }

                        dialog.dismiss();

                        //refreshing the ContactsActivity
                        finish();
                        startActivity(getIntent());
                    }
                });
            }
        });

        // Attaching the adapter to the recyclerview to populate items
        recyclerView.setAdapter(contactAdapter);
    }

    /**
     * this method menu will inflate the option menu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu2,menu);
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
                startActivity(new Intent(this, AboutActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}