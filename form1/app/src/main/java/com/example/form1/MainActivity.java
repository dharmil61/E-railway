package com.example.form1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.solver.widgets.Snapshot;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class MainActivity extends AppCompatActivity {
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



    }

    public void login(View view) {
       final String sap =   ((EditText) findViewById(R.id.sap)).getText().toString();
       final String pass = ((EditText) findViewById(R.id.password)).getText().toString();
        Log.i("sap",sap);

        FirebaseApp.initializeApp(this);
        ref = FirebaseDatabase.getInstance().getReference().child("user");
        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String cmp = snapshot.child("sap").getValue().toString();
                    String passcmp = snapshot.child("pass").getValue().toString();

                    Log.i("ref",cmp);
                    if(sap.equals(cmp) && pass.equals(passcmp)){

                        Intent intent=new Intent(getApplicationContext(),second.class);
                        intent.putExtra("id",cmp);
                        startActivity(intent);

                    }
                    else{
                        Toast.makeText(getApplicationContext(),"failed",Toast.LENGTH_SHORT).show();
                    }
                }}


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

}
