package com.example.sakshi.shree;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static final Integer CALL = 0x2;
    static final Integer SMS=0x3;
    final Context context = this;

    EmailFragment emailFragment;
    MantraFragment mantraFragment;
    BhajanFragment bhajanFragment;
    SMSFragment smsFragment;
    GridView gridview;
    GridAdapter gridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        askForPermission(Manifest.permission.CALL_PHONE,CALL);
        askForPermission(Manifest.permission.SEND_SMS,SMS);

        gridview = (GridView) findViewById(R.id.gridview);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.header);


        gridAdapter=new GridAdapter(this,R.layout.grid_layout,getData());
        gridview.setAdapter(gridAdapter);


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //   Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();


                Intent myIntent = null;
                if (position == 0) {
                    myIntent = new Intent(v.getContext(), UddeshyaActivity.class);

                }
                if (position == 1) {
                    myIntent = new Intent(v.getContext(), JankariActivity.class);

                }
                if (position == 2) {
                    myIntent = new Intent(v.getContext(), SadasyataActivity.class);
                }
                if (position == 3) {
                    myIntent = new Intent(v.getContext(), SahyogActivity.class);
                }
                if (position == 4) {
                    myIntent = new Intent(v.getContext(), PhotoActivity.class);
                }
                if (position == 5) {
                    myIntent = new Intent(v.getContext(), VideoActivity.class);
                }
                if (position == 6) {
                    myIntent = new Intent(v.getContext(), SamparkActivity.class);
                }
                if (position == 7) {
                    myIntent = new Intent(v.getContext(), Samachar.class);
                }
                startActivity(myIntent);
            }
        });


        emailFragment = new EmailFragment();
        mantraFragment = new MantraFragment();
        bhajanFragment = new BhajanFragment();
        smsFragment = new SMSFragment();

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                handleBottomNavigationItemSelected(item);
                return true;
            }

            private void handleBottomNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.call:
                        // getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, callFragment).addToBackStack("gridView").commit();

                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                context);


                        // set dialog message
                        alertDialogBuilder
                                .setMessage("Call 9571161414")
                                .setCancelable(false)
                                .setPositiveButton("Call", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // if this button is clicked, close
                                        // current activity

                                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                                        callIntent.setData(Uri.parse("tel:9571161414"));

                                        if (ActivityCompat.checkSelfPermission(MainActivity.this,
                                                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                            return;
                                        }
                                        startActivity(callIntent);


                                        // MainActivity.this.finish();
                                    }
                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // if this button is clicked, just close
                                        // the dialog box and do nothing
                                        dialog.cancel();
                                    }
                                });

                        // create alert dialog
                        AlertDialog alertDialog = alertDialogBuilder.create();

                        // show it
                        alertDialog.show();

                        break;
                    case R.id.sms:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, smsFragment).commit();
                        break;
                    case R.id.email:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, emailFragment).commit();
                        break;
                    case R.id.bhajan:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, bhajanFragment).commit();
                        break;
                    case R.id.mantra:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, mantraFragment).commit();
                        break;
                }

            }
        });
    }


    protected OnBackPressedListener onBackPressedListener;

    public interface OnBackPressedListener {
        void doBack();
    }

    public void setOnBackPressedListener(OnBackPressedListener onBackPressedListener) {
        this.onBackPressedListener = onBackPressedListener;
    }

    @Override
    public void onBackPressed() {
        if (onBackPressedListener != null)
            onBackPressedListener.doBack();
        finish();
    }

    @Override
    protected void onDestroy() {
        onBackPressedListener = null;
        super.onDestroy();

    }


    private void askForPermission(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(MainActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {


                ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);


          //      ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);

        } else {
            Toast.makeText(this, "Permission is already granted.", Toast.LENGTH_SHORT).show();
        }
    }


    private ArrayList<ImageItem> getData() {
        final ArrayList<ImageItem> imageItems = new ArrayList<>();
        TypedArray imgs = getResources().obtainTypedArray(R.array.image_ids);
        for (int i = 0; i < imgs.length(); i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(i, -1));
            imageItems.add(new ImageItem(bitmap));
        }
        return imageItems;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.share) {
            Intent intent = new Intent(MainActivity.this, ShareActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }




}





