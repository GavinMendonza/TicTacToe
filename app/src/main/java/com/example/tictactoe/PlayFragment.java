package com.example.tictactoe;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tictactoe.Models.Users;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class PlayFragment extends Fragment {

    public PlayFragment() {
        // Required empty public constructor
    }


    private final DatabaseReference mDatabase = FirebaseDatabase.getInstance("https://auth1-14f60-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
    TextView _tv;
    FirebaseAuth _auth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_play, container, false);
        _tv=view.findViewById(R.id.tvForHello);
        _auth = FirebaseAuth.getInstance();
        String userId = _auth.getCurrentUser().getUid();




        Task<DataSnapshot> userdata=mDatabase.child("Users").child(userId).get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.e("firebase", "Error getting data", task.getException());
                Toast.makeText(getActivity().getApplicationContext(), "data read unsuccessful", Toast.LENGTH_SHORT).show();
            }
            else {
                Log.d("firebase", String.valueOf(task.getResult().getValue()));
                Users _userdata=task.getResult().getValue(Users.class); //For some reason this doesnt work if it is a global variable here...
//                Toast.makeText(getActivity().getApplicationContext(),_userdata.getUserName(), Toast.LENGTH_SHORT).show();
                _tv.setText("Welcome "+_userdata.getUserName()+"!");
            }
        });
        return view;
    }
}