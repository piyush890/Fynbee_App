package com.CFC.fynbee_app;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class All_List extends AppCompatActivity {
    
    private AllList adapterL;
    private EditText Search;
    private ImageView imageView;
    private  String locationI,CategoryI;
    private DatabaseReference reference,reference2;
    private RecyclerView RecyclrerList;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_list);
        getSupportActionBar().hide();
        getWindow().setStatusBarColor(ContextCompat.getColor(All_List.this, R.color.biright));
        Search = findViewById(R.id.Search);


        Search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String i = Search.getText().toString().toUpperCase();
                if (i==null){
                    SearchStudentByDate("");
                }
                else {
                    SearchStudentByDate(i);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




        Intent o = getIntent();
        locationI  = o.getStringExtra("P");
        CategoryI  =  o.getStringExtra("name");

        Toast.makeText(this, ""+locationI+CategoryI, Toast.LENGTH_SHORT).show();


        RecyclrerList = findViewById(R.id.RecyclrerList);

        reference = FirebaseDatabase.getInstance().getReference().child("Returants").child(locationI).child(CategoryI);
        LinearLayoutManager linearLayout = new LinearLayoutManager(All_List.this);

        RecyclrerList.setLayoutManager(linearLayout);


        FirebaseRecyclerOptions<DB> options  = new FirebaseRecyclerOptions.Builder<DB>()
                .setQuery(reference,DB.class)
                .build();




        adapterL = new AllList(options);
        if (adapterL==null){
            Toast.makeText(All_List.this, "Null Value", Toast.LENGTH_SHORT).show();
        }
        RecyclrerList.setAdapter(adapterL);


    }

    @Override
    public void onStart() {
        super.onStart();
        adapterL.startListening();

    }

    @Override
    public void onStop() {
        super.onStop();
        adapterL.stopListening();

    }


    class AllList extends FirebaseRecyclerAdapter<DB, AllList.List> {

        public AllList(@NonNull FirebaseRecyclerOptions<DB> options) {
            super(options);
        }

        @Override
        protected void onBindViewHolder(@NonNull All_List.AllList.List holder, int position, @NonNull DB model) {
           Picasso.get().load(model.getResturantPic1()).into(holder.Item_img);
            holder.edtResName.setText(model.getResturanName());
            holder.Edt_Phone.setText(model.getResturantPhone());

        }

        @NonNull
        @Override
        public AllList.List onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(All_List.this).inflate(R.layout.alllistlayout, parent, false);
            return new List(view);
        }

        class List extends RecyclerView.ViewHolder{
            private ImageView Item_img;
            private TextView edtResName,Edt_Phone;

            public List(@NonNull View itemView) {
                super(itemView);
                edtResName = itemView.findViewById(R.id.edtResName);
                Edt_Phone = itemView.findViewById(R.id.Edt_Phone);
                Item_img = itemView.findViewById(R.id.Item_img);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(All_List.this,AllData.class);
                        i.putExtra("looc",locationI);
                        i.putExtra("Catname", CategoryI);
                        i.putExtra("name",edtResName.getText().toString());
                        startActivity(i);
                    }
                });
            }
        }
    }

    private void SearchStudentByDate(String  s){

        FirebaseRecyclerOptions<DB> options2  = new FirebaseRecyclerOptions.Builder<DB>()
                .setQuery(reference.orderByChild("ResturanName").startAt(s).endAt(s+"\uf8ff"),DB.class)
                .build();

        if (adapterL!=null){
            adapterL = new AllList(options2);
        }
        else{
            Toast.makeText(this, "No DB Found", Toast.LENGTH_SHORT).show();
        }
        adapterL.startListening();
        RecyclrerList.setAdapter(adapterL);


    }
}