package com.example.myapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    Button btnstart;
    TextView txtview;
    TextView cellid;
    TextView locid;
    String info;
    String an="ane sag";
    String strphonetype = "";
    String strnetworktype="";
    String ret="";
    static final int PERMISSION_READ_STATE = 123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Start(View view) {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            MyTelephonyManager();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_PHONE_STATE},
                    PERMISSION_READ_STATE);
        }
    }


    public void onRequestPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResult) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResult);
        switch (requestCode) {
            case PERMISSION_READ_STATE: {
                if (grantResult.length >= 0 && grantResult[0] == PackageManager.PERMISSION_GRANTED) {
                    MyTelephonyManager();
                } else {
                    Toast.makeText(this,
                            "You don't have permission", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    private void MyTelephonyManager() {
        TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        int phoneType = manager.getPhoneType();
        switch (phoneType) {
            case (TelephonyManager.PHONE_TYPE_CDMA):
                strphonetype = "CDMA";
                break;

            case (TelephonyManager.PHONE_TYPE_SIP):
                strphonetype = "SIP";
                break;

            case (TelephonyManager.PHONE_TYPE_GSM):
                strphonetype = "GSM";
                break;
            case (TelephonyManager.PHONE_TYPE_NONE):
                strphonetype = "NONE";
                break;
        }

        int networktype = manager.getNetworkType();
        switch (networktype) {
            case (TelephonyManager.NETWORK_TYPE_CDMA):
                strnetworktype = "CDMA";
                break;
            case (TelephonyManager.NETWORK_TYPE_1xRTT):
                strnetworktype = "1xRTT";
                break;
            case (TelephonyManager.NETWORK_TYPE_GSM):
                strnetworktype = "GSM";
                break;
            case (TelephonyManager.NETWORK_TYPE_LTE):
                strnetworktype = "LTE";
                break;
            case (TelephonyManager.NETWORK_TYPE_EDGE):
                strnetworktype = "EGDE";
                break;
            case (TelephonyManager.NETWORK_TYPE_EHRPD):
                strnetworktype = "EHRPD";
                break;
            case (TelephonyManager.NETWORK_TYPE_GPRS):
                strnetworktype = "GPRS";
                break;
            case (TelephonyManager.NETWORK_TYPE_HSDPA):
                strnetworktype = "HSDPA";
                break;
            case (TelephonyManager.NETWORK_TYPE_HSPA):
                strnetworktype = "HSPA";
                break;
            case (TelephonyManager.NETWORK_TYPE_HSPAP):
                strnetworktype = "HSPAP";
                break;
            case (TelephonyManager.NETWORK_TYPE_HSUPA):
                strnetworktype = "HSUPA";
                break;
            case (TelephonyManager.NETWORK_TYPE_EVDO_0):
                strnetworktype = "EVDO_0";
                break;
            case (TelephonyManager.NETWORK_TYPE_EVDO_A):
                strnetworktype = "EVDO_A";
                break;
            case (TelephonyManager.NETWORK_TYPE_EVDO_B):
                strnetworktype = "EVDO_B";
                break;
            case (TelephonyManager.NETWORK_TYPE_IDEN):
                strnetworktype = "IDEN";
                break;
            case (TelephonyManager.NETWORK_TYPE_IWLAN):
                strnetworktype = "IWLAN";
                break;
            case (TelephonyManager.NETWORK_TYPE_TD_SCDMA):
                strnetworktype = "TD_SCDMA";
                break;
            case (TelephonyManager.NETWORK_TYPE_UMTS):
                strnetworktype = "UMTS";
                break;
            case (TelephonyManager.NETWORK_TYPE_UNKNOWN):
                strnetworktype = "UNKNOWN";
                break;
        }

        boolean isRoming = manager.isNetworkRoaming();


        String IMEINumber = manager.getDeviceId();
        String subscribeID = manager.getSubscriberId();
        String simserialnumb = manager.getSimSerialNumber();
        String PhoneNumb = manager.getLine1Number();
        String plmn = manager.getSimOperator();
        String plmnname = manager.getSimOperatorName();
        String networkCountryISO = manager.getNetworkCountryIso();
        String SIMCountryISO = manager.getSimCountryIso();
        String SoftwareVersion = manager.getDeviceSoftwareVersion();
        String VoiceMailNumbers = manager.getVoiceMailNumber();



        TelephonyManager m = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        CellLocation location = m.getCellLocation();
        GsmCellLocation gsmLocation = (GsmCellLocation) location;
        int cellId = gsmLocation.getCid();
        int lac = gsmLocation.getLac();
        ret = "LAC In Hex: " + Integer.toHexString(gsmLocation.getLac()) + " CID In Hex: " + Integer.toHexString(gsmLocation.getCid());


        GsmCellLocation cellLocation = (GsmCellLocation)m.getCellLocation();
        String networkOperator = m.getNetworkOperator();
        String mcc = networkOperator.substring(0, 3);
        String mnc = networkOperator.substring(3);


        int cid = cellLocation.getCid();
        int lacc = cellLocation.getLac();


        info="Here are your Phone Details : \n";
        info+="\n Phone Type: "+strphonetype;
        info+="\n IMSI Number: "+subscribeID;
        info+="\n IMEI Number: "+IMEINumber;
        info+="\n PLMN Number: "+plmn;
        info+="\n PLMN Operator Name: "+plmnname;
        info+="\n MCC Number: "+mcc;
        info+="\n MNC Number: "+mnc;
        info+="\n Network Type Name: "+strnetworktype;
        info+="\n Network country ISO: "+networkCountryISO;
        info+="\n Cell Location: "+cellLocation.toString();
        info+="\n Cell Id:"  + String.valueOf(cid);
        info+="\n Location Area Code: " + String.valueOf(lacc);
        info+="\n Cell info: "+ret;
        info+="\n SIM Serial Number: "+simserialnumb;
        info+="\n SIM country ISO: "+SIMCountryISO;
        info+="\n Phone Number: "+PhoneNumb;
        info+="\n Software Version: "+SoftwareVersion;
        info+="\n Voice Mail Numbers: "+VoiceMailNumbers;
        info+="\n In Roming: "+isRoming;

        btnstart=(Button) findViewById(R.id.btnstart);
        txtview=(TextView) findViewById(R.id.idtextview);
        txtview.setText(info);

    }
}
