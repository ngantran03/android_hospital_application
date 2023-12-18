package com.example.groupproject.profile;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.groupproject.LogIn;
import com.example.groupproject.R;

import java.util.logging.Logger;

public class ProfileFragment extends Fragment {
    private Button profile, paymentMethod, favorite, helpCenter, logOut;
    private TextView username;
    private Bundle args;
    private String user, userid;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        username = view.findViewById(R.id.username);
        profile = view.findViewById(R.id.profile); paymentMethod = view.findViewById(R.id.payment); favorite = view.findViewById(R.id.favorite); helpCenter = view.findViewById(R.id.help); logOut = view.findViewById(R.id.logout);

        args = getArguments();
        if (args != null && args.containsKey("username") && args.containsKey("userid")) {
            user = args.getString("username");
            userid = args.getString("userid");
            username = view.findViewById(R.id.username); username.setText(user);
        }

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), YourProfile.class);
                intent.putExtra("userid", userid);
                startActivity(intent);
            }
        });

//      TODO: implement payment method, favorite, helpcenter

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LogIn.class);
                startActivity(intent);
            }
        });


        return view;
    }
}