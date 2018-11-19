package com.example.userpc.pisangku;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvSignin;
    EditText etEmail, etPassword;
    Button btDaftar;
    String email, password;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private DatabaseReference databaseReference;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() != null){
            firebaseUser = firebaseAuth.getCurrentUser();
            if(firebaseUser.isEmailVerified()) {
                finish();
                startActivity(new Intent(this, home.class));
            }
        }

        progressDialog = new ProgressDialog(this);

        tvSignin = findViewById(R.id.tv_signin);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btDaftar = findViewById(R.id.bt_daftar);

        btDaftar.setOnClickListener(this);
        tvSignin.setOnClickListener(this);

        databaseReference = FirebaseDatabase.getInstance().getReference();

    }

    private void userRegister(){
        email = etEmail.getText().toString().trim();
        password = etPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(MainActivity.this, "Please enter your email...", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(MainActivity.this, "Please enter your password...", Toast.LENGTH_SHORT).show();
            return;
        }
        if(password.length()<8){
            Toast.makeText(MainActivity.this,"The given password is invalid.[Password should be at least 8 characters.]", Toast.LENGTH_SHORT).show();
            return;}

        progressDialog.setMessage("Registering...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Daftar Berhasil, Silahkan verifikasi email anda", Toast.LENGTH_LONG).show();

//                    UserInformation userInformation = new UserInformation(name, phone);
//                    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
//                    databaseReference.child(firebaseUser.getUid()).setValue(userInformation);

                    //finish();
                    startActivity(new Intent(getApplicationContext(), status.class));
                }else{
                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(MainActivity.this, "Email sudah terdaftar", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.bt_daftar){
            userRegister();
        }
        if(view.getId() == R.id.tv_signin){
            finish();
            startActivity(new Intent(this, login.class));
        }
    }
}
