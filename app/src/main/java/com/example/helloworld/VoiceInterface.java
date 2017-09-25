package com.example.helloworld;

import android.content.Intent;
import android.util.Log;

import com.netzyn.api.SaApi;
import com.netzyn.api.SaApiInterface;

    public class VoiceInterface implements SaApiInterface {
        private final String TAG = "VoiceInterface";
        private final String AppName = "CHANGE_TO_APP_NAME";

        HelloWorldClientInterface client;
        private SaApi api = null;

        static private VoiceInterface Instance = null;
        static VoiceInterface startVoiceInterface(HelloWorldClientInterface client, Intent intent) {
            if (Instance == null) {
                Instance = new VoiceInterface(client, intent);
            }
            else
            {
                Instance.client = client;
            }
            return Instance;
        }

        public void onDestroy() {
            client = null;
        }

        VoiceInterface(HelloWorldClientInterface client, Intent intent)
        {
            this.client = client;
            api = new SaApi(intent, this);
        }
        public void SaEventVoice(String user, String device, String app, final String sessionId, String intent,
                          final String parm1, final String parm2, final String parm3, final String parm4, final String parm5)
        {
            if (client == null) {
                api.SaVoiceResponse(sessionId, "tell", "Application is not ready.");
                return;
            }

            if (intent.compareToIgnoreCase("hello") == 0) {
                client.HelloWorld(sessionId);
            }
        }
        public void SendResponse(String sessionId, String msg) {
            api.SaVoiceResponse(sessionId, "ask", msg);
        }

        public void SaEventLog(String var1)
        {
            Log.i(TAG, "Event Log: " + var1);
        }

        public void SaEventConnected()
        {
            Log.i(TAG, "Event Connected");
        }

        public void SaEventError(String var1)
        {
            Log.i(TAG, "Event Error: " + var1);
        }
    }
