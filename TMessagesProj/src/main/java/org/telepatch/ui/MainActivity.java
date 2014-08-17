package org.telepatch.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

/**
 * Created by user on 17/07/14.
 */
public class MainActivity extends Activity {

    private SharedPreferences pwd_settings;
    private PasswordRequestActivity pwd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
        pwd_settings = getSharedPreferences("password", MODE_PRIVATE);
        if (pwd_settings.getBoolean("EnablePwdRequest", false)) {
            pwd = new PasswordRequestActivity();

        if (pwd.isLocked()) {
            Intent i = new Intent(this, PasswordRequestActivity.class);
            i.putExtra("isFromMain", true);
            startActivity(i);
        }
    }
        //Log.i("xela92", "bloccato? oncreate? " +pwd.isLocked());

    }

    @Override
    public void onResume() {
        super.onResume();
        //Log.i("xela92", "bloccato? onresume?" +pwd.isLocked());
        if (pwd == null || !pwd.isLocked()) {
            Intent start;
            if (getIntent().getExtras() != null) {
                if (getIntent().getExtras().getBoolean("isQuickReply")) {
                    start = new Intent(ApplicationLoader.applicationContext, PopupMainActivity.class);
                } else {
                    start = new Intent(ApplicationLoader.applicationContext, LaunchActivity.class);
                }
            } else {
                start = new Intent(ApplicationLoader.applicationContext, LaunchActivity.class);
                //vero, ma inutile!
                //start.putExtra("isFromMain", true);
            }

            prepareExtras(getIntent(), start);
            finish();
            startActivity(start);
        } else {
            Intent i = new Intent(this, PasswordRequestActivity.class);
            i.putExtra("isFromMain", true);
            startActivity(i);
        }

    }



    public void prepareExtras(Intent from, Intent to) {
        if (from.getExtras() != null) {
            int chat_id = from.getExtras().getInt("chatId");
            int user_id = from.getExtras().getInt("userId");
            int enc_id = from.getExtras().getInt("encId");
            boolean isQuickReply = from.getExtras().getBoolean("isQuickReply");
                if (chat_id != 0) {
                    to.putExtra("chatId", chat_id);
                }
                if (user_id != 0) {
                    to.putExtra("userId", user_id);
                }
            to.putExtra("encId", enc_id);
            to.putExtra("isQuickReply", isQuickReply);
            to.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            to.setAction("com.tmessages.openchat" + Math.random() + Integer.MAX_VALUE);
        }


    }


}

