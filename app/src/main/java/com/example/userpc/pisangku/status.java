package com.example.userpc.pisangku;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class status extends AppCompatActivity {

    TextView txt_mail, txt_status, txt_uid;
    Button btn_send, btn_refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        txt_mail = (TextView) findViewById(R.id.txt_mail);
        txt_uid = (TextView) findViewById(R.id.txt_uid);
        txt_status = (TextView) findViewById(R.id.txt_status);

        btn_send = (Button) findViewById(R.id.btn_send);
        btn_refresh = (Button) findViewById(R.id.btn_refresh);

        //set Event
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_send.setEnabled(false);

                FirebaseAuth.getInstance().getCurrentUser()
                        .sendEmailVerification()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                btn_send.setEnabled(true);

                                if (task.isSuccessful())
                                    Toast.makeText(status.this, "Verification email sent to : " + FirebaseAuth.getInstance().getCurrentUser().getEmail(), Toast.LENGTH_SHORT).show();

                                else
                                    Toast.makeText(status.this, "Failed to send verification email", Toast.LENGTH_SHORT).show();

                            }
                        });
            }
        });
        btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().getCurrentUser()
                        .reload()
                        .addOnCompleteListener( new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                setInfo();
                            }
                        });
            }
        });
    }
    private void setInfo() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        txt_mail.setText(new StringBuilder("EMAIL : ").append(user.getEmail()));
        txt_uid.setText(new StringBuilder("UID : ").append(user.getUid()));
        txt_status.setText(new StringBuilder("STATUS : ").append(String.valueOf(user.isEmailVerified())));
    }
}