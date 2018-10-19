package com.example.userpc.pisangku;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity implements View.OnClickListener {

    TextView tvSignup;
    EditText etEmail, etPassword;
    Button btMasuk;
    String email, password;
    private FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    Task task;

    //private static final int PER_LOGIN = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(), home.class));

            //Start Login
          //  startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().setAllowNewEmail.build(), PER_LOGIN);
        }

        progressDialog = new ProgressDialog(this);

        tvSignup = findViewById(R.id.tv_signup);
        etEmail = findViewById(R.id.et_email1);
        etPassword = findViewById(R.id.et_password1);
        btMasuk = findViewById(R.id.bt_masuk);

        btMasuk.setOnClickListener(this);
        tvSignup.setOnClickListener(this);
    }

    //final FirebaseUser user = firebaseAuth.getCurrentUser();
      //  user.sendEmailVerification().addOnCompleteListener(this, new OnCompleteListener()
   // {
     //   @Override
    //public void OnComplete(@NonNull Task task;
      //  task;
        //task) {
         //   findViewById(R.id.btn_send).setEnabled(true);
        //if (task.isSuccessful()) {
          //  Toast.makeText(status.this "Verification sent to " + user.getEmail(), Toast.LENGTH_SHORT).show();}
           // else {
           // Log.e(TAG, "send Email Verification", task.getException());
           // Toast.makeText(status.this "Failed to send verification email.", Toast.LENGTH_SHORT).show();
       // }
       // }
   // });
   // @Override
    //protected void onActivityResult(int requetCode, int resultCode, Intent data){
      //  if(
        //        requetCode == PER_LOGIN)
       // {
         //   handleSignInResponse(resultCode,data);
           // return;
        //}
    //}

    //private void handleSignInResponse(int resultCode, Intent data) {
      //  if(resultCode == RESULT_OK)
        //{
          //  Intent newActivity = new Intent(login.this,status.class);
            //startActivity(newActivity);
            //finish();
            //return;
        //}
        //else
          //  Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT).show();
    //}


    private void userLogin(){
        email = etEmail.getText().toString().trim();
        password = etPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(login.this, "Please enter your email...", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(login.this, "Please enter your password...", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Logging in...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if(task.isSuccessful()){
                    finish();
                    startActivity(new Intent(getApplicationContext(), home.class));
                }else {
                    Toast.makeText(login.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.bt_masuk){
            userLogin();
        }
        if(view.getId() == R.id.tv_signup){
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}
