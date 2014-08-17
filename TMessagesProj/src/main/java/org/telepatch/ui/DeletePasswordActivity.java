package org.telepatch.ui;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.telepatch.messenger.R;

/**
 * Created by user on 21/07/14.
 */
public class DeletePasswordActivity extends Activity {

        private SharedPreferences pwd_preferences = ApplicationLoader.applicationContext.getSharedPreferences("password", Activity.MODE_PRIVATE);
        private SharedPreferences.Editor editor;

        @Override

        public void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.password_remove_layout);
            setTitle("Telepatch - " + ApplicationLoader.applicationContext.getResources().getString(R.string.del_pwd));
            Button okButton = (Button) findViewById(R.id.del_button);
            okButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editor = pwd_preferences.edit();
                    editor.putBoolean("EnablePwdRequest", false);
                    editor.putInt("password", 0);
                    editor.commit();
                    finish();
                }
            });
        }

    }

