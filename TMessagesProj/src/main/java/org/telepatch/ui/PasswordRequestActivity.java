package org.telepatch.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import org.telepatch.messenger.R;

/**
 * Created by user on 17/07/14.
 */
public class PasswordRequestActivity extends Activity {
    private SharedPreferences pwd_preferences = ApplicationLoader.applicationContext.getSharedPreferences("password", Activity.MODE_PRIVATE);
    private int storedPassword;
    private static boolean locked = true;
    private Context context = this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
        setContentView(R.layout.password_request_layout);

        this.setTitle("Telepatch - " + ApplicationLoader.applicationContext.getResources().getString(R.string.pwd_request_title));

        final EditText editText = (EditText) findViewById(R.id.password);
                storedPassword = pwd_preferences.getInt("password", 0);
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    if (storedPassword == editable.toString().hashCode()) {
                        Bundle extras = getIntent().getExtras();
                        if (extras != null) {
                            if (extras.getBoolean("isDeletePassword", false)) {
                                Intent delete = new Intent(context, DeletePasswordActivity.class);
                                startActivity(delete);
                            }
                            if (extras.getBoolean("isSetPassword", false)) {
                                Intent set = new Intent(context, PasswordSetActivity.class);
                                startActivity(set);
                            }
                        }
                        Log.i("xela92", "autenticato");
                        unlock();
                        finish();
                    }

                }
            });
    }

    public boolean isLocked() {
        return locked;
    }

    public void lock() {
        locked = true;
    }

    public void unlock() {
        locked = false;
    }
    @Override
    public void onBackPressed() {
        if (getIntent().getExtras() != null) {
            if (getIntent().getExtras().getBoolean("isFromMain")) {
                //Log.i("xela92", "viene dalla main");
                moveTaskToBack(true);
            } else {
                super.onBackPressed();
            }
        }
    }
}

