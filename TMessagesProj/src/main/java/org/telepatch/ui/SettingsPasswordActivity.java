package org.telepatch.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import org.telepatch.android.LocaleController;
import org.telepatch.messenger.R;
import org.telepatch.ui.Adapters.BaseFragmentAdapter;
import org.telepatch.ui.Views.ActionBar.ActionBarLayer;
import org.telepatch.ui.Views.ActionBar.BaseFragment;

/**
 * Created by user on 19/07/14.
 */
public class SettingsPasswordActivity extends BaseFragment {


        private int passRow;
        private ListView listView;
        private ListAdapter listAdapter;


        //TODO qui inizio a implementare la logica reale.
        private int setPasswordRow;
        private int deletePasswordRequestRow;
        private SharedPreferences pwd_preferences = ApplicationLoader.applicationContext.getSharedPreferences("password", Activity.MODE_PRIVATE);

        @Override
        public View createView(final LayoutInflater inflater, final ViewGroup container) {
            if (fragmentView == null) {
                actionBarLayer.setDisplayHomeAsUpEnabled(true, R.drawable.ic_ab_back);
                actionBarLayer.setBackOverlay(R.layout.updating_state_layout);
                actionBarLayer.setTitle(LocaleController.getString("password protection", R.string.pwd_settings_title));
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
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                        if (i == setPasswordRow) {
                            if (pwd_preferences.getBoolean("EnablePwdRequest", false)) {
                                Intent pwd = new Intent(getParentActivity(), PasswordRequestActivity.class);
                                pwd.putExtra("isSetPassword", true);
                                getParentActivity().startActivity(pwd);
                            } else {
                                Intent pwd = new Intent(getParentActivity(), PasswordSetActivity.class);
                                getParentActivity().startActivity(pwd);
                            }
                        } else if (i == deletePasswordRequestRow) {
                            if (pwd_preferences.getBoolean("EnablePwdRequest", false)) {
                                Intent pwd = new Intent(getParentActivity(), PasswordRequestActivity.class);
                                pwd.putExtra("isDeletePassword", true);
                                getParentActivity().startActivity(pwd);
                            }
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
            passRow = 0;
            setPasswordRow = passRow++;
            deletePasswordRequestRow = passRow++;
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
                if (view == null) {
                    LayoutInflater li = (LayoutInflater) ApplicationLoader.applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view = li.inflate(R.layout.settings_row_button_layout, viewGroup, false);
                    TextView textView = (TextView)view.findViewById(R.id.settings_row_text);
                    View divider = view.findViewById(R.id.settings_row_divider);
                    if (i == setPasswordRow) {
                        textView.setText(R.string.set_pwd);
                        divider.setVisibility(View.VISIBLE);
                    } else if (i == deletePasswordRequestRow) {
                        textView.setText(R.string.del_pwd);
                        divider.setVisibility(View.VISIBLE);
                    }
                }
                return view;

            }
            @Override
            public int getCount() {
                return passRow;
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public int getItemViewType(int i) {
                if (i == setPasswordRow || i == deletePasswordRequestRow) {
                    return 0;
                } else {
                    return 1;
                }
            }
            @Override
            public boolean isEmpty() {
                return false;
            }


        }

    }




