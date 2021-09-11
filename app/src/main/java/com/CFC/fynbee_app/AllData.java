package com.CFC.fynbee_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

public class AllData extends AppCompatActivity {
private TextView RestaurantName,RestaurantPhone,RestaurantDescription,
        BusniessNumber,OwnerEmail,OwnerName,Address;
private ImageView Restaurant1,Restaurant2,Restaurant3,Restaurant4;
    private DatabaseReference reference;
    private String loco,catname,name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_data);
        getSupportActionBar().hide();
        RestaurantName = findViewById(R.id.RestaurantName);
        RestaurantPhone = findViewById(R.id.RestaurantPhone);
        RestaurantDescription = findViewById(R.id.RestaurantDescription);
        BusniessNumber = findViewById(R.id.BusniessNumber);
        OwnerName = findViewById(R.id.OwnerName);
        OwnerEmail = findViewById(R.id.OwnerEmail);
        Restaurant1 = findViewById(R.id.Restaurant1);
        Restaurant2 = findViewById(R.id.Restaurant2);
        Restaurant3 = findViewById(R.id.Restaurant3);
        Restaurant4 = findViewById(R.id.Restaurant4);
        Address = findViewById(R.id.Address);

        Intent i = getIntent();
        loco = i.getStringExtra("looc");
        catname = i.getStringExtra("Catname");
        name = i.getStringExtra("name");
        reference = FirebaseDatabase.getInstance().getReference().child("Returants").child(loco).child(catname).child(name);
        Toast.makeText(this, ""+loco+catname+name, Toast.LENGTH_SHORT).show();
      GetAllData();
    }
    private void GetAllData(){
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                DB db = new DB();
                db.setDescription(snapshot.child("Description").getValue().toString());
                db.setResturanName(snapshot.child("ResturanName").getValue().toString());
                db.setResturantPhone(snapshot.child("ResturantPhone").getValue().toString());
                db.setResturantPic1(snapshot.child("ResturantPic1").getValue().toString());
                db.setResturantPic2(snapshot.child("ResturantPic2").getValue().toString());
                db.setResturantPic3(snapshot.child("ResturantPic3").getValue().toString());
                db.setResturantPic4(snapshot.child("ResturantPic4").getValue().toString());
                db.setBusniessNumber(snapshot.child("BusniessNumber").getValue().toString());
                db.setOwnerEmail(snapshot.child("OwnerEmail").getValue().toString());
                db.setOwnerName(snapshot.child("OwnerName").getValue().toString());
                db.setAddress(snapshot.child("Address").getValue().toString());

                String name = db.getResturanName();
                String des = db.getDescription();
                String Phone = db.getResturantPhone();
                String Img1 = db.getResturantPic1();
                String Img2 = db.getResturantPic2();
                String Img3 = db.getResturantPic3();
                String Img4 = db.getResturantPic4();
                String busniessnumber = db.getBusniessNumber();
                String ownername = db.getOwnerName();
                String owneremail = db.getOwnerEmail();
                String address = db.getAddress();
                Toast.makeText(AllData.this, ""+busniessnumber, Toast.LENGTH_SHORT).show();

                RestaurantName.setText(name);
                RestaurantDescription.setText(des);
                RestaurantPhone.setText("Phone: "+Phone);
                BusniessNumber.setText("What'sApp Number: "+busniessnumber);
                OwnerName.setText("Owner Name: "+ownername);
                OwnerEmail.setText("Owner Email: "+owneremail);
                Address.setText(address);
                Picasso.get().load(Img1).into(Restaurant1);
                Picasso.get().load(Img2).into(Restaurant2);
                Picasso.get().load(Img3).into(Restaurant3);
                Picasso.get().load(Img4).into(Restaurant4);

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(AllData.this, ""+error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}