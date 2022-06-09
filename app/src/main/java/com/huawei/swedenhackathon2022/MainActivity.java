package com.huawei.swedenhackathon2022;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.huawei.hms.common.ApiException;
import com.huawei.hms.push.HmsMessaging;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.textView);

        setAutoInitEnabled(true);


        try {
            checkHMS();
            textView.setText("setup OK");
        } catch (ApiException e) {
            e.printStackTrace();
            textView.setText("setup Failed");
        }

    }

    private void checkHMS() throws ApiException {
        testHmsCorePresence();
        testAccountByRequestingPushNotificationsToken();
    }

    private void testAccountByRequestingPushNotificationsToken() throws ApiException {

        new Thread() {
            @Override
            public void run() {
                try {

                    String pushToken = HmsUtils.getPushNotificationsToken(MainActivity.this);

                } catch (ApiException e) {

                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void testHmsCorePresence() {
        if (HmsUtils.isHmsAvailable(MainActivity.this)) {
            //Toast.makeText(this, "Please make sure you have HMS Core installed on the test device.", Toast.LENGTH_LONG);
        }
    }

    private void setAutoInitEnabled(final boolean isEnable) {
        if (isEnable) {
            // Enable automatic initialization.
            HmsMessaging.getInstance(this).setAutoInitEnabled(true);
        } else {
            // Disable automatic initialization.
            HmsMessaging.getInstance(this).setAutoInitEnabled(false);
        }
    }
}