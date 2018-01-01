package com.org.tijarah.structure;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    EditText editTextFullName, editTextFullAddress, editTextFullAddress1, editTextEmail, editTextPassword, editTextContact;
    Button btnSignUp;

    FirebaseAuth firebaseAuth;
    private FirebaseDatabase fbdb;
    private DatabaseReference dbr;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        fbdb = fbdb.getInstance();
        dbr = fbdb.getReference();

        editTextFullName = (EditText) findViewById(R.id.editTextFullName);
        editTextFullAddress = (EditText) findViewById(R.id.editTextFullAddress);
        editTextFullAddress1 = (EditText) findViewById(R.id.editTextFullAddress1);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextContact = (EditText) findViewById(R.id.editTextContact);

        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        progressDialog = new ProgressDialog(SignupActivity.this);

        firebaseAuth = FirebaseAuth.getInstance();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!TextUtils.isEmpty(editTextFullName.getText().toString()) && !TextUtils.isEmpty(editTextFullAddress.getText().toString())
                        && !TextUtils.isEmpty(editTextEmail.getText().toString()) && !TextUtils.isEmpty(editTextPassword.getText().toString())) {

                    progressDialog.setMessage("Please Wait, We are signing you up");
                    progressDialog.show();

                    firebaseAuth.createUserWithEmailAndPassword(editTextEmail.getText().toString(), editTextPassword.getText().toString())
                            .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                Toast.makeText(SignupActivity.this, "Success", Toast.LENGTH_SHORT).show();

                               /* FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                                dbr.child("Users").push();
                                dbr.setValue(currentUser.getUid());
                                dbr.child("Users").child(currentUser.getUid()).push();
                                dbr.setValue(new User(editTextFullName.getText().toString(),editTextFullAddress.getText().toString(),
                                        editTextFullAddress1.getText().toString(), editTextContact.getText().toString()));
*/
                            } else {
                                Toast.makeText(SignupActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                            }

                            progressDialog.dismiss();
                        }
                    });
                }
                else {
                    Toast.makeText(SignupActivity.this, "Please complete the form", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
