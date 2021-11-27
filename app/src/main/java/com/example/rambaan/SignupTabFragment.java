package com.example.rambaan;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;

public class SignupTabFragment extends Fragment {

    TextInputLayout fullname, email, username, password;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tab_fragment,container,false);

        fullname = root.findViewById(R.id.signup_full_name);
        email = root.findViewById(R.id.signup_email);
        username = root.findViewById(R.id.signup_username);
        password = root.findViewById(R.id.signup_password);

        String _fullname = fullname.getEditText().getText().toString();
        String _email = email.getEditText().getText().toString();
        String _username = username.getEditText().getText().toString();
        String _pass = password.getEditText().getText().toString();

        Button nextScreen = root.findViewById(R.id.next_screen);
        nextScreen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(!validateFullname() | !validateEmail() | validatePassword() | validateUsername()){
                    return;
                }

                Intent intent = new Intent(getActivity(),nextSignupScreen.class);
                intent.putExtra("Name",_fullname);
                intent.putExtra("Email",_email);
                intent.putExtra("Username",_username);
                intent.putExtra("Password",_pass);
                startActivity(intent);
            }
        });
        return root;
    }

    private boolean validateFullname(){
        String val = fullname.getEditText().getText().toString().trim();

        if(val.isEmpty()){
            fullname.setError("Field cannot be empty!");
            return false;
        }
        else{
            fullname.setError(null);
            fullname.setErrorEnabled(false);
            return true;
        }

    }

    private boolean validateEmail(){
        String val = email.getEditText().getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9._]+@[a-z]+\\.+[a-z]+";

        if(val.isEmpty()){
            email.setError("Field cannot be empty!");
            return false;
        }
        else if(!val.matches(checkEmail)){
            email.setError("Invalid Email!");
            return false;
        }
        else{
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }

    }

    private boolean validatePassword(){
        String val = password.getEditText().getText().toString().trim();
        String checkPassword = "^"+
                //"(?=.*[a-z])"+
                "(?=.*[0-9])"+
                //"(?=.*[A-Z])"+
                "(?=.*[a-zA-Z])"+
                "(?=.*[@#$%^&+=])"+
                //"(?=\\S+$)"+
                ".{4,}"+
                "$";

        if(val.isEmpty()){
            password.setError("Field cannot be empty!");
            return false;
        }
        else if(!val.matches(checkPassword)){
            password.setError("Password is too weak!");
            return false;
        }
        else{
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }

    }

    private Boolean validateUsername(){
        String val = username.getEditText().getText().toString();
        String checkspaces = "\\A\\w{1,20}\\z";

        if(val.isEmpty()){
            username.setError("Field cannot be empty");
            return false;
        }
        else if(val.length()>20){
            username.setError("Username is too large!");
            return false;
        }
        else if(!val.matches(checkspaces)){
            username.setError("No whitespaces are allowed!");
            return false;
        }
        else{
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }

}
