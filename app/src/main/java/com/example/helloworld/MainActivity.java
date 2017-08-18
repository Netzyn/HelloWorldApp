package com.example.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements HelloWorldClientInterface {

    private VoiceInterface api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        api = VoiceInterface.startVoiceInterface(this, this.getIntent());
    }

    void ShowToast(String msg) {
        Toast toast = Toast.makeText(
                this.getApplicationContext(),
                msg,
                Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        api.onDestroy();
    }

    public void HelloWorld(String sessionId) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ShowToast("Hello received");
            }
        });

        api.SendResponse(sessionId, "REPLACE WITH YOUR MESSAGE");
    }
}
