package com.example.contacts;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    Context context;
    ArrayList<ContactModel> arrContact;

    RecyclerViewAdapter(Context context,ArrayList<ContactModel> arrContact){
        this.context = context;
        this.arrContact = arrContact;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contact_row,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.imageView.setImageResource(arrContact.get(position).img);
        holder.textname.setText(arrContact.get(position).name);
        holder.textnumber.setText(arrContact.get(position).number);

        holder.llrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialogbox);

                EditText editText = dialog.findViewById(R.id.editName);
                EditText editText1 = dialog.findViewById(R.id.editNumber);
                Button btnAction = dialog.findViewById(R.id.btnAction);

                btnAction.setText("Update");
                TextView textView = dialog.findViewById(R.id.txtTitle);
                textView.setText("Update Contact");

                editText.setText(arrContact.get(position).name);
                editText1.setText(arrContact.get(position).number);

                btnAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = "";
                        String number = "";
                        if(!editText.getText().toString().equals("")){
                            name = editText.getText().toString();
                        }
                        else{
                            Toast.makeText(context,"Please Enter Contact Name",Toast.LENGTH_LONG).show();
                        }
                        if(!editText1.getText().toString().equals("")){
                            number = editText1.getText().toString();
                        }
                        else{
                            Toast.makeText(context,"Please Enter Contact Number",Toast.LENGTH_LONG).show();
                        }
                        arrContact.set(position,new ContactModel(R.drawable.f,name,number));
                        notifyItemChanged(position);

                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        holder.llrow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setTitle("Delete Contact")
                        .setMessage("Are you sure you want to delete")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                arrContact.remove(position);
                                notifyItemRemoved(position);

                            }
                        })

                        .setNegativeButton("no", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.show();
                return true;
            }
        });


    }

    @Override
    public int getItemCount() {
        return arrContact.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textname,textnumber;
        LinearLayout llrow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.img);
            textname = itemView.findViewById(R.id.textName);
            textnumber = itemView.findViewById(R.id.textNumber);
            llrow = itemView.findViewById(R.id.linear1);
        }
    }
}
