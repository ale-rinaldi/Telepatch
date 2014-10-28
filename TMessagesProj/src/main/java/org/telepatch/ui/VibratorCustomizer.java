package org.telepatch.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import org.telepatch.android.LocaleController;
import org.telepatch.messenger.R;
import org.telepatch.ui.Views.ActionBar.ActionBarLayer;
import org.telepatch.ui.Views.ActionBar.BaseFragment;

public class VibratorCustomizer extends BaseFragment {

    private Vibrator vib;
    private String pattern_text;
    private int[] pattern_ms;
    private TextView pattern_tv;
    private boolean canContinue = true;
    private int count = 0;
    private final static int VIB_LONG = 1000;
    private final static int VIB_SHORT = 500;
    private final static int VIB_PAUSE = 500;
    private Animation anim = AnimationUtils.loadAnimation(ApplicationLoader.applicationContext, R.anim.button_press_zoom);
    private SharedPreferences vib_prefs;


    public VibratorCustomizer() {
        vib = (Vibrator) ApplicationLoader.applicationContext.getSystemService(Context.VIBRATOR_SERVICE);
        pattern_text = "";
        pattern_ms = new int[10];
        vib_prefs = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0);
    }

    @Override
public View createView(final LayoutInflater inflater, final ViewGroup container){
    if (fragmentView == null) {
        actionBarLayer.setDisplayHomeAsUpEnabled(true, R.drawable.ic_ab_back);
        actionBarLayer.setBackOverlay(R.layout.updating_state_layout);
        actionBarLayer.setTitle(LocaleController.formatString("Vibrator Customizer", R.string.vibrate_customizer_title));
        actionBarLayer.setActionBarMenuOnItemClick(new ActionBarLayer.ActionBarMenuOnItemClick() {
            @Override
            public void onItemClick(int id) {
                if (id == -1) {
                    finishFragment();
                }
            }
        });
        fragmentView = inflater.inflate(R.layout.vibrator_customizer_layout, container, false);
        pattern_tv = (TextView) fragmentView.findViewById(R.id.vibration_pattern_tv);
        Button circle_big = (Button) fragmentView.findViewById(R.id.vibrate_circle_big);
        circle_big.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(anim);
                if (count == 4) {
                    canContinue = false;
                    Log.i("xela92", "ecco i valori in ordine: ");
                    for (int i = 0; i <= 8 ; i++) {
                        Log.i("xela92", " " + pattern_ms[i]);
                    }
                }
                if (canContinue) {
                    pattern_ms[count] = VIB_LONG;
                    pattern_ms[count+1] = 0;
                    if (pattern_text.equals("")) {
                        pattern_text = pattern_text.concat(LocaleController.formatString("long", R.string.longpress));
                    } else {
                        pattern_text = pattern_text.concat(" - ").concat(LocaleController.formatString("long", R.string.longpress));
                    }
                    pattern_tv.setText(pattern_text);
                    Log.i("xela92", pattern_text + "[ " + pattern_ms[count] +" ]");
                    vib.cancel();
                    vib.vibrate(1000);
                    count+=2;
                }


            }
        });
        Button circle_little = (Button) fragmentView.findViewById(R.id.vibrate_circle_little);
        circle_little.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(anim);
                if (count == 8) {
                    canContinue = false;
                    Log.i("xela92", "ecco i valori in ordine: ");
                    for (int i = 0; i <= 8; i++) {
                        Log.i("xela92", " " + pattern_ms[i]);
                    }
                }
                if (canContinue) {
                    pattern_ms[count] = VIB_SHORT;
                    pattern_ms[count+1] = 0;
                    if (pattern_text.equals("")) {
                        pattern_text = pattern_text.concat(LocaleController.formatString("short", R.string.shortpress));
                    } else {
                        pattern_text = pattern_text.concat(" - ").concat(LocaleController.formatString("short", R.string.shortpress));
                    }
                    pattern_tv.setText(pattern_text);
                    Log.i("xela92", pattern_text + "[ " + pattern_ms[count] +" ]");
                    vib.cancel();
                    vib.vibrate(500);
                    count+=2;
                }


            }
        });


        Button buttonPause = (Button) fragmentView.findViewById(R.id.vibrate_circle_pause);

        buttonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count == 8) {
                    canContinue = false;
                    Log.i("xela92", "ecco i valori in ordine: ");
                    for (int i = 0; i <= 4; i++) {
                        Log.i("xela92", " " + pattern_ms[i]);
                    }
                }
                if (canContinue) {
                    pattern_ms[count] = VIB_PAUSE;
                    if (pattern_text.equals("")) {
                        pattern_text = pattern_text.concat(LocaleController.formatString("pause", R.string.vibrate_pause));
                    } else {
                        pattern_text = pattern_text.concat(" - ").concat(LocaleController.formatString("pause", R.string.vibrate_pause));
                    }
                    pattern_tv.setText(pattern_text);
                    Log.i("xela92", pattern_text + "[ " + pattern_ms[count] +" ]");
                    vib.cancel();
                    count++;
                }
            }
        });
        Button buttonUndo = (Button) fragmentView.findViewById(R.id.cancel_button);

        buttonUndo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = 0;
                pattern_text = "";
                pattern_tv.setText(pattern_text);
                canContinue = true;
            }
        });
        Button buttonSave = (Button) fragmentView.findViewById(R.id.save_button);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    SharedPreferences.Editor editor = vib_prefs.edit();
                    String pattern_to_save = "";
                    for (int i : pattern_ms) {
                        pattern_to_save = pattern_to_save.concat(i+" ");
                    }
                    Log.i("xela92", "pattern to save: "+pattern_to_save);
                    editor.putString("vibration", pattern_to_save);
                    editor.commit();

            }
        });
    } else {
        ViewGroup parent = (ViewGroup) fragmentView.getParent();
        if (parent != null) {
            parent.removeView(fragmentView);
        }
    }
    return fragmentView;

}





}