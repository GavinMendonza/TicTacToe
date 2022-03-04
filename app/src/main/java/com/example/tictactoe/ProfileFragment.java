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

public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        // Required empty public constructor
    }




    private final DatabaseReference mDatabase = FirebaseDatabase.getInstance("https://auth1-14f60-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
    FirebaseAuth _auth;
    TextView _tvProfileName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        _tvProfileName=view.findViewById(R.id.tv_profileUN);
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

                //your code starts here (use _userdata getters to get data)
                _tvProfileName.setText(_userdata.getUserName());
                //your code ends here
            }
        });
        return view;
    }
}