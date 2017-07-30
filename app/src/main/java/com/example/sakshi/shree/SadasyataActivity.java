package com.example.sakshi.shree;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SadasyataActivity extends AppCompatActivity implements View.OnClickListener{
public static final String URL_Sadasyata="http://pichchhi.yidemo.in/app/index.php?action=sadasyata&full_name=Linda&fathers_name=Lim&post=Buisness&dob=12/06/1994&city=Indore&address=xyz&whatsapp_number=9632587414&mobile_number=8541785258&emailid=9632587414&member_suggestion=lorem%20ipsum&initiative=Y&online_booking=Y&vihar_details=Y";

    public static final String KEY_FULLNAME = "full_name";
    //    public static final String KEY_ID="insert_id";
    public static final String KEY_FATHERNAME = "fathers_name";
    public static final String KEY_POST1 = "post";
    public static final String KEY_DOB1 = "dob";
    public static final String KEY_CITY = "city";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_NUMBER = "whatsapp_number";
    public static final String KEY_SADIMAGE = "sadasya_image";
    public static final String KEY_MOBILE = "mobile_number";
    public static final String KEY_MAIL="emailid";
    public static final String KEY_SUGGESTION = "member_suggestion";
    public static final String KEY_INIT="initiative";
    public static final String KEY_BOOKING = "online_booking";
    public static final String KEY_VIHAR="vihar_details";

AutoCompleteTextView mName,Fname,mDob,mCity,mAddress,mWNumber,mMobile,mEmail,mSuggestion,mPad;
    RadioGroup init,booking,vihar;
    RadioButton RB1,RB2,RB3,RB4,RB5,RB6;
    Button button,brow;
    ImageView s_img;
    private Bitmap bitmap;

    private int PICK_IMAGE_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sadasyata);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.header);

        mName=(AutoCompleteTextView)findViewById(R.id.name);

        Fname=(AutoCompleteTextView)findViewById(R.id.parent);
        mDob=(AutoCompleteTextView)findViewById(R.id.date);
        mCity=(AutoCompleteTextView)findViewById(R.id.city);
        mPad=(AutoCompleteTextView)findViewById(R.id.pad);
        mAddress=(AutoCompleteTextView)findViewById(R.id.address);
        mWNumber=(AutoCompleteTextView)findViewById(R.id.number);
        mMobile=(AutoCompleteTextView)findViewById(R.id.mobile);
        mEmail=(AutoCompleteTextView)findViewById(R.id.email);
        mSuggestion=(AutoCompleteTextView)findViewById(R.id.details);
        init=(RadioGroup)findViewById(R.id.rg2);
        booking=(RadioGroup)findViewById(R.id.rg3);
        vihar=(RadioGroup)findViewById(R.id.rg4);
        RB1=(RadioButton)findViewById(R.id.rd3);
        RB2=(RadioButton)findViewById(R.id.rd4);
        RB3=(RadioButton)findViewById(R.id.rd5);
        RB4=(RadioButton)findViewById(R.id.rd6);
        RB5=(RadioButton)findViewById(R.id.rd7);
        RB6=(RadioButton)findViewById(R.id.rd8);
        button=(Button)findViewById(R.id.btn1);
        brow=(Button)findViewById(R.id.brow);
        s_img=(ImageView)findViewById(R.id.s_img);

        init.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId == R.id.rd3) {
                    Toast.makeText(getApplicationContext(), "Yes", Toast.LENGTH_LONG).show();
                } else if (checkedId == R.id.rd4) {
                    Toast.makeText(getApplicationContext(), "No", Toast.LENGTH_LONG).show();
                }
            }
        });
        booking.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId == R.id.rd5) {
                    Toast.makeText(getApplicationContext(), "Yes", Toast.LENGTH_LONG).show();
                } else if (checkedId == R.id.rd6) {
                    Toast.makeText(getApplicationContext(), "No", Toast.LENGTH_LONG).show();
                }
            }
        });
        vihar.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId == R.id.rd7) {
                    Toast.makeText(getApplicationContext(), "Yes", Toast.LENGTH_LONG).show();
                } else if (checkedId == R.id.rd8) {
                    Toast.makeText(getApplicationContext(), "No", Toast.LENGTH_LONG).show();
                }
            }
        });
        brow.setOnClickListener(this);
        button.setOnClickListener(this);
    }
    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void SubmitUser() {

        final String full_name = mName.getText().toString().trim();
        final String fathers_name = Fname.getText().toString().trim();
        final String post = mPad.getText().toString().trim();
        final String dob=mDob.getText().toString().trim();
        final String city = mCity.getText().toString().trim();
        final String address=mAddress.getText().toString().trim();
        final String whatsapp_number = mWNumber.getText().toString().trim();
        final String mobile_number=mMobile.getText().toString().trim();
        final String emailid=mEmail.getText().toString().trim();
        final String member_suggestion=mSuggestion.getText().toString().trim();
        final String initiative= String.valueOf(init.getCheckedRadioButtonId());
        final String online_booking= String.valueOf(booking.getCheckedRadioButtonId());
        final String vihar_details= String.valueOf(vihar.getCheckedRadioButtonId());


        StringRequest stringRequest= new StringRequest(Request.Method.POST, URL_Sadasyata, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(SadasyataActivity.this," Details Added Successfully",Toast.LENGTH_LONG).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SadasyataActivity.this,error.toString(),Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                String sadasya_image = getStringImage(bitmap);
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_SADIMAGE,sadasya_image);
                params.put(KEY_FULLNAME,full_name);
                params.put(KEY_FATHERNAME,fathers_name);
                params.put(KEY_POST1,post);
                params.put(KEY_CITY,city);
                params.put(KEY_ADDRESS,address);
                params.put(KEY_MOBILE,mobile_number);
                params.put(KEY_NUMBER,whatsapp_number);
                params.put(KEY_DOB1,dob);
                params.put(KEY_MAIL,emailid);
                params.put(KEY_SUGGESTION,member_suggestion);
                params.put(KEY_VIHAR,vihar_details);
                params.put(KEY_INIT,initiative);
                params.put(KEY_BOOKING,online_booking);


                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Setting the Bitmap to ImageView
                s_img.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @Override
    public void onClick(View v) {
        if (v == button) {
            //SubmitUser();

            if (isOnline()) {
                if (mName.length() > 0) {

                    if (Fname.length() > 0) {

                        if (mDob.length() > 0) {

                            if (mCity.length() > 0) {

                                if (mPad.length() > 0) {

                                    if (mAddress.length() > 0) {

                                        if (mWNumber.length() == 10) {

                                            if (mMobile.length() == 10) {

                                                if (mEmail.length() > 0) {

                                                    if (mSuggestion.length() > 0) {

                                                        if (init.isEnabled() == false) {

                                                            if (vihar.isEnabled() == false) {

                                                                if (booking.isEnabled() == false) {


                                                                } else {
                                                                    booking.requestFocus();

                                                                }
                                                            } else {
                                                                vihar.requestFocus();

                                                            }

                                                        } else {
                                                            init.requestFocus();


                                                        }
                                                    } else {
                                                        mSuggestion.requestFocus();
                                                        mSuggestion.setError("Please Enter Suggestions ");

                                                    }
                                                } else {
                                                    mEmail.requestFocus();
                                                    mEmail.setError("Please Enter EmailId");

                                                }

                                            } else {
                                                mMobile.requestFocus();
                                                mMobile.setError("Please Enter Mobile Number");
                                            }

                                        } else {
                                            mWNumber.requestFocus();
                                            mWNumber.setError("Please Enter WhatsApp Number");

                                        }
                                    } else {
                                        mAddress.requestFocus();
                                        mAddress.setError("Please Enter Address");
                                    }
                                } else {
                                    mPad.requestFocus();
                                    mPad.setError("Please Enter Pad");
                                }
                            } else {
                                mCity.requestFocus();
                                mCity.setError("Please Enter City");
                            }
                        } else {
                            mDob.requestFocus();
                            mDob.setError("Please Enter Birth Date");
                        }
                    } else {
                        Fname.requestFocus();
                        Fname.setError("Please Enter Father's Name");
                    }
                } else {
                    mName.requestFocus();
                    mName.setError("Please Enter Name");
                }


            SubmitUser();
        }}
        if (v == brow) {
            showFileChooser();
        }
    }

}


