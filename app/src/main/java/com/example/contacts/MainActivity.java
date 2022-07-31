package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<ContactModel> arrContact = new ArrayList<>();
    FloatingActionButton btnOpenDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        arrContact.add(new ContactModel(R.drawable.a,"A","9321411807"));
        arrContact.add(new ContactModel(R.drawable.b,"B","9820101475"));
        arrContact.add(new ContactModel(R.drawable.c,"C","7045724770"));
        arrContact.add(new ContactModel(R.drawable.d,"D","9967909211"));
        arrContact.add(new ContactModel(R.drawable.e,"E","9920101475"));
        arrContact.add(new ContactModel(R.drawable.f,"F","9321405067"));

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,arrContact);
        recyclerView.setAdapter(adapter);

        btnOpenDialog = findViewById(R.id.btnOpenDialog);
        btnOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.dialogbox);

                EditText editText = dialog.findViewById(R.id.editName);
                EditText editText1 = dialog.findViewById(R.id.editNumber);
                Button btnAction = dialog.findViewById(R.id.btnAction);

                btnAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = "";
                        String number = "";
                        if(!editText.getText().toString().equals("")){
                            name = editText.getText().toString();
                        }
                        else{
                            Toast.makeText(MainActivity.this,"Please Enter Contact Name",Toast.LENGTH_LONG).show();
                        }
                        if(!editText1.getText().toString().equals("")){
                            number = editText1.getText().toString();
                        }
                        else{
                            Toast.makeText(MainActivity.this,"Please Enter Contact Number",Toast.LENGTH_LONG).show();
                        }


                        arrContact.add(new ContactModel(R.drawable.a,name,number));
                        adapter.notifyItemInserted(arrContact.size()-1);

                        recyclerView.scrollToPosition(arrContact.size()-1);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });






    }
}