package com.example.form1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.form1.R.id.class1;
import static com.example.form1.R.id.radioGroup;

public class second extends AppCompatActivity {

    DatabaseReference ref;
    ListView listView;
    String firstname;
    String lname="";
    String college="";
    String home="";
    String dest="";
    String sap="";
    TextView tv;
    RadioGroup rg;
    RadioButton r;
    EditText etname,etlname,etcllg,ethome,etdest;
    Intent intent;
    String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second);
         rg = (RadioGroup) findViewById(R.id.radioGroup);
        etname  = (EditText)findViewById(R.id.fne);
        etlname  = (EditText)findViewById(R.id.lne);
        etcllg  = (EditText)findViewById(R.id.cname);
        ethome  = (EditText)findViewById(R.id.from);
        etdest  = (EditText)findViewById(R.id.dest);
        Bundle bundle = getIntent().getExtras();
        message = bundle.getString("id");
        Log.i("id",message);

        FirebaseApp.initializeApp(this);
        ref = FirebaseDatabase.getInstance().getReference().child("user");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.i("message1","message");
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    firstname=dataSnapshot.child(message).child("name").getValue().toString();
                Log.i("fn",firstname);
                lname=dataSnapshot.child(message).child("lname").getValue().toString();
                college=dataSnapshot.child(message).child("college").getValue().toString();
                home=dataSnapshot.child(message).child("home").getValue().toString();
                dest=dataSnapshot.child(message).child("dest").getValue().toString();
                sap=dataSnapshot.child(message).child("sap").getValue().toString();
                etname.setText(firstname);
                etlname.setText(lname);
                etcllg.setText(college);
                ethome.setText(home);
                etdest.setText(dest);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



//    public void radio(View view) {
//        boolean checked = ((RadioButton) view).isChecked();
//      Intent intent = new Intent(this,qr.class);
//      String str="";
//
//        switch(view.getId()) {
//            case R.id.class1:
//                str="FIRST CLASS";
//
//                break;
//
//            case R.id.class2:
//
//                str="SECOND CLASS";
//
//        }
//        intent.putExtra("class",str);
//
//    }

    public void generate(View view) {
       // boolean checked = ((RadioButton) view).isChecked();
        RadioGroup radiogroup = (RadioGroup) findViewById(R.id.radioGroup);
        RadioButton rb = (RadioButton) radiogroup.findViewById(radiogroup.getCheckedRadioButtonId());

        Intent intent = new Intent(this,qr.class);
        String str="";

        switch(rb.getId()) {
            case R.id.class1:
                str="FIRST CLASS";

                break;

            case R.id.class2:

                str="SECOND CLASS";

        }
        intent.putExtra("class",str);
        intent.putExtra("id",message);

        startActivity(intent);
    }
}
