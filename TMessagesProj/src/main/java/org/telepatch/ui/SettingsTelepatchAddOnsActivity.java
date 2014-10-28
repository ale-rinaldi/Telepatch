package org.telepatch.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.telepatch.android.LocaleController;
import org.telepatch.messenger.R;
import org.telepatch.ui.Adapters.BaseFragmentAdapter;
import org.telepatch.ui.Views.ActionBar.ActionBarLayer;
import org.telepatch.ui.Views.ActionBar.BaseFragment;

public class SettingsTelepatchAddOnsActivity extends BaseFragment {
    private int addOnsRow;
    private ListView listView;
    private ListAdapter listAdapter;


    //TODO qui inizio a implementare la logica reale.
    private int micHackRow;
    //private int vibCustomRow;
    private SharedPreferences addons_preferences = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0);

    @Override
    public View createView(final LayoutInflater inflater, final ViewGroup container) {
        if (fragmentView == null) {
            actionBarLayer.setDisplayHomeAsUpEnabled(true, R.drawable.ic_ab_back);
            actionBarLayer.setBackOverlay(R.layout.updating_state_layout);
            actionBarLayer.setTitle(LocaleController.getString("Telepatch Add-Ons", R.string.telepatchAddOns));

            actionBarLayer.setActionBarMenuOnItemClick(new ActionBarLayer.ActionBarMenuOnItemClick() {
                @Override
                public void onItemClick(int id) {
                    if (id == -1) {
                        finishFragment();
                    }
                }
            });

            fragmentView = inflater.inflate(R.layout.settings_layout, container, false);
            listAdapter = new ListAdapter(getParentActivity());
            listView = (ListView) fragmentView.findViewById(R.id.listView);
            listView.setAdapter(listAdapter);
            listView.setVisibility(View.VISIBLE);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                boolean enabled = false;
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                    if (i == micHackRow) {
                        enabled = addons_preferences.getBoolean("microphoneHack", true);
                        addons_preferences.edit().putBoolean("microphoneHack", !enabled).commit();
                        listView.invalidateViews();
                   /* } else if (i == vibCustomRow) {
                        presentFragment(new VibratorCustomizer());
                    }
                    */
                    }
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

    @Override
    public boolean onFragmentCreate() {
        super.onFragmentCreate();
        addOnsRow = 0;
        micHackRow = addOnsRow++;
       // vibCustomRow = addOnsRow++;
        return true;
    }

    @Override
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        fixLayout();
    }



    private void fixLayout() {
        ViewTreeObserver obs = fragmentView.getViewTreeObserver();
        obs.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                fragmentView.getViewTreeObserver().removeOnPreDrawListener(this);

                if (listView != null) {
                }
                return false;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        fixLayout();
    }
    private class ListAdapter extends BaseFragmentAdapter {
        private Context mContext;

        public ListAdapter(Context context) {
            mContext = context;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {

            if (getItemViewType(i) == 0 ) {

                if (view == null) {
                    LayoutInflater li = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view = li.inflate(R.layout.settings_row_check_layout, viewGroup, false);
                }
                ImageView checkButton = (ImageView) view.findViewById(R.id.settings_row_check_button);
                TextView textView = (TextView) view.findViewById(R.id.settings_row_text);
                View divider = view.findViewById(R.id.settings_row_divider);
                boolean enabled = false;
                if (i == micHackRow) {
                    checkButton.setVisibility(View.VISIBLE);
                    enabled = addons_preferences.getBoolean("microphoneHack", true);
                    textView.setText(R.string.mic_hack);
                    divider.setVisibility(View.VISIBLE);
                }

                if (enabled) {
                    checkButton.setImageResource(R.drawable.btn_check_on);
                } else {
                    checkButton.setImageResource(R.drawable.btn_check_off);
                }
            } else {
                if (view == null) {
                    LayoutInflater li = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view = li.inflate(R.layout.settings_row_check_layout, viewGroup, false);
                }
                ImageView checkButton = (ImageView) view.findViewById(R.id.settings_row_check_button);
                TextView textView = (TextView) view.findViewById(R.id.settings_row_text);
                View divider = view.findViewById(R.id.settings_row_divider);

              /*  if (i == vibCustomRow) {
                    checkButton.setVisibility(View.INVISIBLE);
                    textView.setText(R.string.vibrate_customizer_title);
                    divider.setVisibility(View.VISIBLE);
                } */
            }

            return view;

        }
        @Override
        public int getCount() {
            return addOnsRow;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }
        @Override
        public boolean isEnabled(int i) {
            return i == micHackRow;
            //|| i == vibCustomRow;
        }

        @Override
        public int getItemViewType(int i) {
            if (i == micHackRow) {
                return 0;
           /* } else if (i == vibCustomRow){
                return 1;
            */}  else {
                return 2;
            }
        }
        @Override
        public boolean isEmpty() {
            return false;
        }


    }

}