package com.CFC.fynbee_app;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    private LoanUser adapter;
    private ImageView imageView;
    private ViewFlipper flipper,flipper2;
    private  String p;
    private DatabaseReference reference,reference2;
    private RecyclerView category_Recycler,ResturantRecycler;
    private int  images [] = {R.drawable.pic1,R.drawable.pic2,R.drawable.pic5};
    private int  image [] = {R.drawable.pic3,R.drawable.pic4,R.drawable.pic5};

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.biright));
        flipper = findViewById(R.id.flipper);
        flipper2 = findViewById(R.id.flipper2);
        SDaTa();

        for (int im:images){
            Flipper(im);
        }
        for (int img:image){
            FlipperI(img);
        }

        Intent i = getIntent();
        p = i.getStringExtra("P");
        Toast.makeText(this, ""+p, Toast.LENGTH_SHORT).show();
        category_Recycler = findViewById(R.id.category_Recycler);

        reference = FirebaseDatabase.getInstance().getReference().child("Category");
        GridLayoutManager linearLayout = new GridLayoutManager(MainActivity.this,3,GridLayoutManager.VERTICAL,false);

        category_Recycler.setLayoutManager(linearLayout);


        FirebaseRecyclerOptions<DB> options  = new FirebaseRecyclerOptions.Builder<DB>()
                .setQuery(reference,DB.class)
                .build();




        adapter = new LoanUser(options);
        if (adapter==null){
            Toast.makeText(MainActivity.this, "Null Value", Toast.LENGTH_SHORT).show();
        }
        category_Recycler.setAdapter(adapter);



    }


    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();

    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();

    }




    class LoanUser extends FirebaseRecyclerAdapter<DB,LoanUser.Loan> {

        public LoanUser(@NonNull FirebaseRecyclerOptions<DB> options) {
            super(options);
        }

        @Override
        protected void onBindViewHolder(@NonNull Loan holder, int position, @NonNull DB model) {
            Picasso.get().load(model.getIcon()).into(holder.icon);
            holder.edt_categoryname.setText(model.getCategoryName());

        }

        @NonNull
        @Override
        public Loan onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.category_layout, parent, false);
            return new Loan(view);
        }

        class Loan extends RecyclerView.ViewHolder{
            private CircleImageView icon;
            private TextView edt_categoryname;
            private String UID;
            private FloatingActionButton floatingActionButton2;

            public Loan(@NonNull View itemView) {
                super(itemView);
                edt_categoryname = itemView.findViewById(R.id.edt_categoryname);
                icon = itemView.findViewById(R.id.icons);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this,All_List.class);
                        i.putExtra("P",p);
                        i.putExtra("name",edt_categoryname.getText().toString());
                        startActivity(i);

                    }
                });


            }
        }
    }

    public void Flipper(int image){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);
        flipper.addView(imageView);
        flipper.setFlipInterval(2000);
        flipper.setAutoStart(true);
        flipper.setInAnimation(this, android.R.anim.slide_in_left);
        flipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }
    public void FlipperI(int image){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);
        flipper2.addView(imageView);
        flipper2.setFlipInterval(2000);
        flipper2.setAutoStart(true);
        flipper2.setInAnimation(this, android.R.anim.slide_in_left);
        flipper2.setOutAnimation(this, android.R.anim.slide_out_right);
    }

    private void SDaTa(){
        AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
        View view = getLayoutInflater().inflate(R.layout.firstlayout, null);
        ImageView close = view.findViewById(R.id.close);
        adb.setView(view);

      AlertDialog  dialog = adb.create();
        dialog.setCancelable(false);
        dialog.show();

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }
}