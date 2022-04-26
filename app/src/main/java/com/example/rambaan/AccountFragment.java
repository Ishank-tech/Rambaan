package com.example.rambaan;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment {

    Button log, reach;

    TextInputLayout fullname, email, phoneNo, password, dob, gender;
    TextView fullNameLabel, usernameLabel;

    String user_username, user_gender, user_dob, user_name, user_email, user_phoneNo, user_password;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AccountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccountFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_account, container, false);

        fullname = rootView.findViewById(R.id.full_name_profile);
        email = rootView.findViewById(R.id.email_profile);
        dob = rootView.findViewById(R.id.Dob_profile);
        gender = rootView.findViewById(R.id.gender_profile);
        phoneNo = rootView.findViewById(R.id.phone_no_profile);
        password = rootView.findViewById(R.id.password_profile);
        fullNameLabel = rootView.findViewById(R.id.full_name_field);
        usernameLabel = rootView.findViewById(R.id.username_field);
        showAllUserData();

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(getContext());
        if(signInAccount != null){
            fullname.getEditText().setText(signInAccount.getDisplayName());
            email.getEditText().setText(signInAccount.getEmail());
            fullNameLabel.setText(signInAccount.getDisplayName());
            usernameLabel.setText(signInAccount.getGivenName());
        }

        log = rootView.findViewById(R.id.log);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity().getApplicationContext(),Startup_for_login_signup.class);
                startActivity(intent);
            }
        });

        reach = rootView.findViewById(R.id.imageButton);
        reach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

    private void showAllUserData() {

//        Intent intent = getIntent();
        Bundle bundle = this.getArguments();
        if(bundle != null) {
            user_username = bundle.getString("Username");
            user_gender = bundle.getString("Gender");
            user_dob = bundle.getString("DOB");
            user_name = bundle.getString("Fullname");
            user_email = bundle.getString("Email");
            user_phoneNo = bundle.getString("PhoneNo");
            user_password = bundle.getString("Password");

            fullNameLabel.setText(user_name);
            usernameLabel.setText(user_username);
            fullname.getEditText().setText(user_name);
            gender.getEditText().setText(user_gender);
            dob.getEditText().setText(user_dob);
            email.getEditText().setText(user_email);
            phoneNo.getEditText().setText(user_phoneNo);
            password.getEditText().setText(user_password);
        }

    }

    public void Reach(View view){

    }

    public void Logout(View view){

    }

}