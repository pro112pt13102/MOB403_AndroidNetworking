package com.example.thucvuong.asm_mob403;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthProvider;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.UserInfo;
import com.squareup.picasso.Picasso;

public class ProfileFragment extends Fragment {

    private FirebaseAuth auth;
    private FirebaseUser user;
    private TextView tv_username, tv_usermail;
    private String TAG = "GG";
    private Button btn_signout_gg, btn_login, btn_signout;

    private ImageView img_profile, img_GG;
    private EditText edt_email, edt_password;
    private ProgressBar progressBar;

    private GoogleSignInClient mGoogleSignInClient;

    public ProfileFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_profile, container, false);

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        init(view);

        auth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);

        //FirebaseUser user = auth.getCurrentUser();

        /*if (user!=null){
            img_profile.setVisibility(View.VISIBLE);
            UpdateProfileView();

            Toast.makeText(getActivity(), ""+user.getProviderId(), Toast.LENGTH_SHORT).show();
        }*/
        if(auth.getCurrentUser()!=null) {
            for (UserInfo user : FirebaseAuth.getInstance().getCurrentUser().getProviderData()) {
                if (user.getProviderId().equals("password")) {
                    //For linked facebook account
                    //Log.d("xx_xx_provider_info", "User is signed in with Facebook");

                String email = user.getEmail();

                img_profile.setVisibility(View.VISIBLE);

                Picasso.get().load("http://bestnycacupuncturist.com/wp-content/uploads/2016/11/anonymous-avatar-sm.jpg").into(img_profile);
                tv_usermail.setVisibility(View.VISIBLE);
                tv_usermail.setText(email);
                img_GG.setVisibility(View.GONE);
                edt_email.setVisibility(View.GONE);
                edt_password.setVisibility(View.GONE);
                btn_login.setVisibility(View.GONE);
                btn_signout.setVisibility(View.VISIBLE);
                img_GG.setVisibility(View.GONE);

                } else if (user.getProviderId().equals("google.com")) {
                    //For linked Google account
                    img_profile.setVisibility(View.VISIBLE);
                    UpdateProfileView_GG();
                    //Log.d("xx_xx_provider_info", "User is signed in with Google");
                    Toast.makeText(getActivity(), "" + FirebaseAuth.getInstance().getCurrentUser().getProviderData(), Toast.LENGTH_SHORT).show();
                }

            }

        }

        img_GG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignIn_GG();
            }
        });

        btn_signout_gg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignOut();
            }
        });


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edt_email.getText().toString();
                String password = edt_password.getText().toString();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    Toast.makeText(getContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);

                                if(task.isSuccessful()){
                                    Toast.makeText(getContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

                                    FirebaseUser user = auth.getCurrentUser();

                                    String email = user.getEmail();

                                    Toast.makeText(getContext(), ""+email, Toast.LENGTH_SHORT).show();

                                    img_profile.setVisibility(View.VISIBLE);

                                    Picasso.get().load("http://bestnycacupuncturist.com/wp-content/uploads/2016/11/anonymous-avatar-sm.jpg").into(img_profile);
                                    tv_usermail.setVisibility(View.VISIBLE);
                                    tv_usermail.setText(email);
                                    img_GG.setVisibility(View.GONE);
                                    edt_email.setVisibility(View.GONE);
                                    edt_password.setVisibility(View.GONE);
                                    btn_login.setVisibility(View.GONE);
                                    btn_signout.setVisibility(View.VISIBLE);
                                    img_GG.setVisibility(View.GONE);



                                }else{
                                    Toast.makeText(getContext(), "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        btn_signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();

                edt_email.setVisibility(View.VISIBLE);
                edt_password.setVisibility(View.VISIBLE);
                tv_usermail.setVisibility(View.GONE);
                img_GG.setVisibility(View.VISIBLE);
                img_profile.setVisibility(View.GONE);
                btn_login.setVisibility(View.VISIBLE);
                btn_signout.setVisibility(View.GONE);

            }
        });





        return view;



    }

    private void init(View view){
         tv_usermail = view.findViewById(R.id.txt_user_mail);
         tv_username = view.findViewById(R.id.txt_user_name);
         btn_signout_gg = view.findViewById(R.id.buttonSignout_GG);
         img_profile = view.findViewById(R.id.image_profile);
         img_GG = view.findViewById(R.id.imgView_GG);
         progressBar = view.findViewById(R.id.progressBarGG);
         btn_login = view.findViewById(R.id.btn_login);
         edt_email = view.findViewById(R.id.email);
         edt_password = view.findViewById(R.id.password);
         btn_signout = view.findViewById(R.id.buttonSignout);


    }

    private void SignIn_GG(){
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, 101);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == 101) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account){
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        img_profile.setVisibility(View.VISIBLE);

                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            Toast.makeText(getContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

                            UpdateProfileView_GG();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());

                        }

                        // ...
                    }
                });
    }

    private void SignOut(){
        auth.signOut();

        mGoogleSignInClient.signOut().addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(getContext(), "Đã đăng xuất", Toast.LENGTH_SHORT).show();
            }
        });

        btn_signout_gg.setVisibility(View.GONE);
        img_GG.setVisibility(View.VISIBLE);
        tv_usermail.setVisibility(View.GONE);
        tv_username.setVisibility(View.GONE);
        img_profile.setVisibility(View.GONE);
        edt_email.setVisibility(View.VISIBLE);
        edt_password.setVisibility(View.VISIBLE);
        btn_login.setVisibility(View.VISIBLE);
    }

    private void UpdateProfileView_GG(){
        FirebaseUser user = auth.getCurrentUser();

        String email = user.getEmail();
        Uri uri = user.getPhotoUrl();
        String name = user.getDisplayName();

        if(name==null){
            tv_username.setVisibility(View.GONE);
        }
        else{
            tv_username.setVisibility(View.VISIBLE);
            tv_username.setText(name);
        }

        String imgurl = uri.toString();

        Picasso.get().load(imgurl).into(img_profile);

        tv_usermail.setText(email);
        img_GG.setVisibility(View.GONE);
        btn_signout_gg.setVisibility(View.VISIBLE);
        tv_usermail.setVisibility(View.VISIBLE);
        edt_email.setVisibility(View.GONE);
        edt_password.setVisibility(View.GONE);
        btn_login.setVisibility(View.GONE);
    }



}
