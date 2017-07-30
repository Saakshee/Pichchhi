package com.example.sakshi.shree;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class SMSFragment extends Fragment implements View.OnClickListener,MainActivity.OnBackPressedListener {

    Button buttonSend;
    EditText textPhoneNo;
    EditText textSMS;
    public SMSFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sm, container, false);
        buttonSend = (Button) view.findViewById(R.id.buttonSend);
        textPhoneNo = (EditText) view.findViewById(R.id.editTextPhoneNo);
        textSMS = (EditText) view.findViewById(R.id.editTextSMS);
        ((MainActivity) getActivity()).setOnBackPressedListener(this);

        buttonSend.setOnClickListener(this);
        return view;
    }
    @Override
    public void onClick(View v) {

        String phoneNo = textPhoneNo.getText().toString();
        String sms = textSMS.getText().toString();

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, sms, null, null);
            Toast.makeText(getActivity(), "SMS Sent!",
                    Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getActivity(),
                    "SMS faild, please try again later!",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }

    @Override
    public void doBack() {
        Intent intent=new Intent(getActivity(),MainActivity.class);
        startActivity(intent);
    }
}