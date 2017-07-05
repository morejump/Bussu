package com.example.morejump.bussu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Firebase01 extends AppCompatActivity {
    Button btnGetData, btnAddUser;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("User");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase01);
        init();
    }
    private void writeNewUser(String userId, String name, String email) {
        User user = new User(name, email,0);
        myRef.setValue(user);
    }
    private  void init(){
        btnGetData= (Button) findViewById(R.id.btnGetData);
        btnAddUser= (Button) findViewById(R.id.btnAddUser);
        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User("thao","email", 0);
                //myRef.push().setValue(user);
                myRef.child("KnV0LT1srPgkgYX8es0").child("Count").setValue("69");
            }
        });
        btnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query myTopPostsQuery= myRef.orderByChild("email").startAt("email").endAt("email\uf8ff").limitToFirst(100);
                myTopPostsQuery.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Toast.makeText(Firebase01.this, "list count: "+dataSnapshot.getChildrenCount() , Toast.LENGTH_SHORT).show();
                        for (DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                            User user= postSnapshot.getValue(User.class);
                            Log.d("value", "email: "+user.email +"    name:  "+ user.name+" count: "+user.Count);
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
        //writeNewUser("id","thao handsome","myemail");
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
               /* User value = dataSnapshot.getValue(User.class);
                Toast.makeText(Firebase01.this, "new value:   " +value.email+"  name :"+value.name, Toast.LENGTH_SHORT).show();*/
                //Log.d("thaohandsome", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("thaohandsome", "Failed to read value.", error.toException());
            }
        });

    }
}
