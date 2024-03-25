package com.skc.committeemanagement;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;


import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.skc.committeemanagement.databinding.ActivityMainBinding;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


public class MainActivity extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;
    public ActivityMainBinding binding;
    FirebaseAuth mAuth;
    boolean doubleBackPress = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarMain.toolbar);
        mAuth = FirebaseAuth.getInstance();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);


//        name.setText(getIntent90.getStringExtra("email").toString());
//        pass.setText("UID :"+getIntent().getStringExtra("uid").toString());

//        binding.navView.findViewById(R.id.nav_logout).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FirebaseAuth.getInstance().signOut();
//                startActivity(new Intent(MainActivity.this,LoginMainActivity.class));
//            }
//        });

        binding.appBarMain.massage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_dashboard, R.id.action_nav_dashboard_to_nav_candidate, R.id.action_nav_dashboard_to_nav_pay_money,
                R.id.action_nav_dashboard_to_nav_receive, R.id.action_nav_dashboard_to_nav_pay_manage, R.id.action_nav_dashboard_to_nav_pay_monthly,
                R.id.action_nav_dashboard_to_nav_add_member, R.id.action_nav_dashboard_to_nav_list_member, R.id.action_nav_dashboard_to_nav_pay_manage,
                R.id.action_nav_dashboard_to_nav_setting)
                .setOpenableLayout(drawer)
                .build();

        //navigation ko jo control karega
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int log = item.getItemId();
        if(log == R.id.action_logout){
            AlertDialog.Builder alertBox = new AlertDialog.Builder(MainActivity.this);
        alertBox.setTitle("Log Out");
        alertBox.setMessage("Do you want to logout");
        alertBox.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                finishAffinity();
                mAuth.signOut();
                Intent intent = new Intent(MainActivity.this, NewLoginPage.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        alertBox.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertBox.show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
    super.onBackPressed();
////    if (doubleBackPress){
////        finishAffinity();
////    }else {
////        doubleBackPress = true;
////        Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();
////        Handler handler=new Handler(Looper.getMainLooper());
////        handler.postDelayed(new Runnable() {
////            @Override
////            public void run() {
////                doubleBackPress = false;
////            }
////        },1500);
////    }
////        AlertDialog.Builder alertBox = new AlertDialog.Builder(MainActivity.this);
////        alertBox.setTitle("Exit App");
////        alertBox.setMessage("Do you want to exit app");
////        alertBox.setPositiveButton("YES", new DialogInterface.OnClickListener() {
////            @Override
////            public void onClick(DialogInterface dialogInterface, int i) {
////                finishAffinity();
////            }
////        });
////
////        alertBox.setNegativeButton("NO", new DialogInterface.OnClickListener() {
////            @Override
////            public void onClick(DialogInterface dialogInterface, int i) {
////                dialogInterface.dismiss();
////            }
////        });
////        alertBox.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null){
            Intent intent = new Intent(MainActivity.this, NewLoginPage.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    }
}