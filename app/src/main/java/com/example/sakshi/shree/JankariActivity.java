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
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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

import static com.example.sakshi.shree.R.id.imageView;

public class JankariActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String URL = "\n" + "\n" + "http://pichchhi.yidemo.in/app/index.php?action=parichay&sadhu_name=Pushpdant%20Sagar%20Ji&diksha_date=05/07/1997&diksha_place=Dewas&diksha_guru_name=PushpDantSagarJi&sadhu_details=It%20is%20a%20long%20established%20fact%20that%20a%20reader%20will%20be%20distracted%20by%20the%20readable%20content%20of%20a%20page%20when%20looking%20at%20its%20layout&sadhu_post=acharya&contact_number=8547147852&grahasth_jeevan_details=lorem%20ipsum&old_name=Amit%20Jain&dob=25/03/1990&place=Hyderabad&mother_name=Shalini%20Jain&father_name=Manoj%20Jain&marital_status=married";
    public static final String KEY_SADHUNAME = "sadhu_name";
    //    public static final String KEY_ID="insert_id";
    public static final String KEY_DIKSHA = "diksha_date";
    public static final String KEY_PLACE1 = "diksha_place";
    public static final String KEY_GURU = "diksha_guru_name";
    public static final String KEY_DETAILS = "sadhu_details";
    public static final String KEY_POST = "sadhu_post";
    public static final String KEY_CONTACT = "contact_number";
    public static final String KEY_JEEVAN = "grahasth_jeevan_details";
    public static final String KEY_OLDNAME = "old_name";
    public static final String KEY_DOB = "dob";
    public static final String KEY_PLACE2 = "place";
    public static final String KEY_MNAME = "mother_name";
    public static final String KEY_FNAME = "father_name";
    public static final String KEY_STATUS = "marital_status";
    public static final String KEY_IMAGE = "sadhu_image";

    AutoCompleteTextView SadhuName, DikshaDate, DikshaPlace, DikshaGuru, SadhuDetails, SadhuPost, ContactNumber, OldName, Dob, Place, Mname, Fname, MaritalStatus;
    Button submit, browse;
    RadioButton Available, NotAvailable;
    RadioGroup radioGroup;
    ImageView mImageView;
    private Bitmap bitmap;

    private int PICK_IMAGE_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jankari);
        SadhuName = (AutoCompleteTextView) findViewById(R.id.txt1);
        DikshaDate = (AutoCompleteTextView) findViewById(R.id.txt2);
        DikshaPlace = (AutoCompleteTextView) findViewById(R.id.txt3);
        DikshaGuru = (AutoCompleteTextView) findViewById(R.id.txt4);
        SadhuDetails = (AutoCompleteTextView) findViewById(R.id.txt5);
        SadhuPost = (AutoCompleteTextView) findViewById(R.id.txt6);
        ContactNumber = (AutoCompleteTextView) findViewById(R.id.txt7);
        OldName = (AutoCompleteTextView) findViewById(R.id.txt8);
        Dob = (AutoCompleteTextView) findViewById(R.id.txt9);
        Place = (AutoCompleteTextView) findViewById(R.id.txt10);
        Mname = (AutoCompleteTextView) findViewById(R.id.txt11);
        Fname = (AutoCompleteTextView) findViewById(R.id.txt12);
        MaritalStatus = (AutoCompleteTextView) findViewById(R.id.txt13);
        submit = (Button) findViewById(R.id.btn);
        radioGroup = (RadioGroup) findViewById(R.id.rg);
        Available = (RadioButton) findViewById(R.id.rd1);
        NotAvailable = (RadioButton) findViewById(R.id.rd2);
        browse = (Button) findViewById(R.id.browse);
        mImageView = (ImageView) findViewById(R.id.brow_img);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.header);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId == R.id.rd1) {
                    Toast.makeText(getApplicationContext(), "Available", Toast.LENGTH_LONG).show();
                } else if (checkedId == R.id.rd2) {
                    Toast.makeText(getApplicationContext(), "Not Available", Toast.LENGTH_LONG).show();
                }
            }
        });

        submit.setOnClickListener(this);
        browse.setOnClickListener(this);
    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }


    private void registerUser() {

        final String sadhu_name = SadhuName.getText().toString().trim();
        final String diksha_date = DikshaDate.getText().toString().trim();
        final String diksha_place = DikshaPlace.getText().toString().trim();
        final String diksha_guru_name = DikshaGuru.getText().toString().trim();
        final String sadhu_details = SadhuDetails.getText().toString().trim();
        final String sadhu_post = SadhuPost.getText().toString().trim();
        final String contact_number = ContactNumber.getText().toString().trim();
        final String old_name = OldName.getText().toString().trim();
        final String dob = Dob.getText().toString().trim();
        final String place = Place.getText().toString().trim();
        final String mother_name = Mname.getText().toString().trim();
        final String father_name = Fname.getText().toString().trim();
        final String marital_status = MaritalStatus.getText().toString().trim();
        final String grahasth_jeevan_details = String.valueOf(radioGroup.getCheckedRadioButtonId());
        //final String sadhu_image = getStringImage(bitmap);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(JankariActivity.this, "Deails Added Successfully", Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(JankariActivity.this, (CharSequence) error, Toast.LENGTH_LONG).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                 String sadhu_image = getStringImage(bitmap);
                Map<String, String> params = new HashMap<String, String>();
                params.put(KEY_IMAGE, sadhu_image);
                params.put(KEY_SADHUNAME, sadhu_name);
                params.put(KEY_DIKSHA, diksha_date);
                params.put(KEY_PLACE1, diksha_place);
                params.put(KEY_GURU, diksha_guru_name);
                params.put(KEY_DETAILS, sadhu_details);
                params.put(KEY_POST, sadhu_post);
                params.put(KEY_OLDNAME, old_name);
                params.put(KEY_DOB, dob);
                params.put(KEY_PLACE2, place);
                params.put(KEY_MNAME, mother_name);
                params.put(KEY_FNAME, father_name);
                params.put(KEY_STATUS, marital_status);
                params.put(KEY_CONTACT, contact_number);
                params.put(KEY_JEEVAN, grahasth_jeevan_details);


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
                mImageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public void AfterResponse() {
        Intent i = new Intent(JankariActivity.this, MainActivity.class);
        startActivity(i);

        finish();
    }


    @Override
    public void onClick(View v) {
        if (v == submit) {
            registerUser();

            if (isOnline()) {

                if (SadhuName.length() > 0) {

                    if (DikshaDate.length() <= 10 && DikshaDate.length() > 0) {

                        if (DikshaPlace.length() > 0) {

                            if (DikshaGuru.length() > 0) {

                                if (SadhuDetails.length() > 0) {

                                    if (SadhuPost.length() > 0) {

                                        if (ContactNumber.length() == 10) {

                                            if (OldName.length() > 0) {

                                                if (Dob.length() > 0) {

                                                    if (Place.length() > 0) {

                                                        if (Mname.length() > 0) {

                                                            if (Fname.length() > 0) {

                                                                if (MaritalStatus.length() > 0) {

                                                                    if (radioGroup.isEnabled() == false) {

                                                                    } else {
                                                                        radioGroup.requestFocus();
                                                                    }
                                                                } else {
                                                                    MaritalStatus.requestFocus();
                                                                    MaritalStatus.setError("Please Enter Marital Status");

                                                                }
                                                            } else {
                                                                Fname.requestFocus();
                                                                Fname.setError("Please Enter Father's Name ");
                                                            }

                                                        } else {
                                                            Mname.requestFocus();
                                                            Mname.setError("Please Enter Mother's Name");

                                                        }
                                                    } else {
                                                        Place.requestFocus();
                                                        Place.setError("Please Enter Birth Place");

                                                    }
                                                } else {
                                                    Dob.requestFocus();
                                                    Dob.setError("Please Enter Birthdate");

                                                }

                                            } else {
                                                OldName.requestFocus();
                                                OldName.setError("Please Enter Old Name");
                                            }

                                        } else {
                                            ContactNumber.requestFocus();
                                            ContactNumber.setError("Please Enter Contact Number");

                                        }
                                    } else {
                                        SadhuPost.requestFocus();
                                        SadhuPost.setError("Please Enter Acharya Pad");
                                    }
                                } else {
                                    SadhuDetails.requestFocus();
                                    SadhuDetails.setError("Please Enter Acharya Details");
                                }
                            } else {
                                DikshaGuru.requestFocus();
                                DikshaGuru.setError("Please Enter Diksha Guru Name");
                            }
                        } else {
                            DikshaPlace.requestFocus();
                            DikshaPlace.setError("Please Enter Diksha place");
                        }
                    } else {
                        DikshaDate.requestFocus();
                        DikshaDate.setError("Please enter Diksha Date");
                    }
                } else {
                    SadhuName.requestFocus();
                    SadhuName.setError("Please Enter Acharya Name");
                }


          //      registerUser();
            } else {
                Toast.makeText(JankariActivity.this, "Network isn't available", Toast.LENGTH_LONG).show();
            }}
            if (v == browse) {
                showFileChooser();

            }


    }
}