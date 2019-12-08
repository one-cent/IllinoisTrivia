package com.example.illinoistrivia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.quickstart.auth.R;

public class EnterScreen extends AppCompatActivity implements
        View.OnClickListener {


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_screen);

        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.signIn).setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    private void signInAnonymously() {
        mAuth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            System.out.println("Sign in worked");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(EnterScreen.this, MainActivity.class);
                            MainActivity.user = user;
                            MainActivity.auth = mAuth;
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            System.out.print("Sign in failed! Exception = " + task.getException());
                            Toast.makeText(EnterScreen.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.signIn) {
            signInAnonymously();
        }
    }
}
