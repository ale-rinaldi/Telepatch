package org.telepatch.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.telepatch.android.LocaleController;
import org.telepatch.messenger.R;

/**
 * Created by user on 17/07/14.
 */
public class PasswordSetActivity extends Activity {
    private SharedPreferences pwd_preferences = ApplicationLoader.applicationContext.getSharedPreferences("password", Activity.MODE_PRIVATE);
    private SharedPreferences.Editor editor;
    private Context context = this;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_set_layout);
        setTitle("Telepatch - " + ApplicationLoader.applicationContext.getResources().getString(R.string.pwd_set_title));
        final EditText firstEditText = (EditText) findViewById(R.id.password_set);
        final EditText secondEditText = (EditText) findViewById(R.id.password_set_check);
        Button okButton = (Button) findViewById(R.id.ok_button);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firstEditText.getText().toString().equals(secondEditText.getText().toString()) && firstEditText.getText().length() > 0) {
                    editor = pwd_preferences.edit();
                    editor.putInt("password", firstEditText.getText().toString().hashCode());
                    editor.putBoolean("EnablePwdRequest", true);
                    editor.commit();
                    finish();
                } else if (!firstEditText.getText().toString().equals(secondEditText.getText().toString())) {
                    if (firstEditText.getText().toString().equals("") || secondEditText.getText().toString().equals("")) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle(LocaleController.getString("empty title", R.string.pwd_empty_title));
                        builder.setMessage(LocaleController.getString("empty", R.string.pwd_empty));
                        builder.setPositiveButton(LocaleController.getString("ok", R.string.OK), new DialogInterface.OnClickListener(
                        ) {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) { }
                        });
                        builder.show();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle(LocaleController.getString("error title", R.string.pwd_set_error_title));
                        builder.setMessage(LocaleController.getString("error", R.string.pwd_set_error));
                        builder.setPositiveButton(LocaleController.getString("ok", R.string.OK), new DialogInterface.OnClickListener(
                        ) {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) { }
                        });
                        builder.show();
                    }
                } else if (firstEditText.getText().toString().equals("") || secondEditText.getText().toString().equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle(LocaleController.getString("empty title", R.string.pwd_empty_title));
                    builder.setMessage(LocaleController.getString("empty", R.string.pwd_empty));
                    builder.setPositiveButton(LocaleController.getString("ok", R.string.OK), new DialogInterface.OnClickListener(
                    ) {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) { }
                    });
                    builder.show();
                }
            }
        });

    }

}
