package org.telepatch.ui;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.telepatch.messenger.FileLog;
import org.telepatch.android.LocaleController;
import org.telepatch.messenger.R;
import org.telepatch.ui.Adapters.BaseFragmentAdapter;
import org.telepatch.ui.Views.ActionBar.ActionBarLayer;
import org.telepatch.ui.Views.ActionBar.BaseFragment;
import org.telepatch.ui.Views.AvatarUpdater;


/**
 * Created by user on 12/07/14.
 */

class LinkMovementMethodMy extends LinkMovementMethod {
    @Override
    public boolean onTouchEvent(TextView widget, Spannable buffer, MotionEvent event) {
        try {
            return super.onTouchEvent(widget, buffer, event);
        } catch (Exception e) {
            FileLog.e("tmessages", e);
        }
        return false;
    }
}


    public class SettingsThemeChooserActivity extends BaseFragment {

        private int themeRow;
        private ListView listView;
        private ListAdapter listAdapter;
        private AvatarUpdater avatarUpdater = new AvatarUpdater();


        //TODO qui inizio a implementare la logica reale.
        private int themeDefaultRow;
        private int themeRedRow;
        private int themeGreenRow;
        private int themeYellowRow;
        private int themeFucsiaRow;
        private int themeVioletRow;
        private int themeBlueRow;
        private int themePurpleRow;
        private int themeMaterialGreenRow;


        @Override
        public View createView(final LayoutInflater inflater, final ViewGroup container) {
            if (fragmentView == null) {
                actionBarLayer.setDisplayHomeAsUpEnabled(true, R.drawable.ic_ab_back);
                actionBarLayer.setBackOverlay(R.layout.updating_state_layout);
                actionBarLayer.setTitle(LocaleController.getString("themeChooser", R.string.themeChooser));
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
                SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", Activity.MODE_PRIVATE);
                final SharedPreferences.Editor editor = preferences.edit();
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                        Context context = ApplicationLoader.applicationContext;
                        if (i == themeDefaultRow) {
                            editor.putInt("theme", 0);
                            editor.commit();
                            restartApp();
                        } else if (i == themeRedRow) {
                            editor.putInt("theme", 1);
                            editor.commit();
                            restartApp();
                        } else if (i == themeGreenRow) {
                            editor.putInt("theme", 2);
                            editor.commit();
                            restartApp();
                        } else if (i == themeFucsiaRow) {
                            editor.putInt("theme", 3);
                            editor.commit();
                            restartApp();
                        } else if (i == themeYellowRow) {
                            editor.putInt("theme", 4);
                            editor.commit();
                            restartApp();
                        } else if (i == themeVioletRow) {
                            editor.putInt("theme", 5);
                            editor.commit();
                            restartApp();
                        } else if (i == themeBlueRow) {
                            editor.putInt("theme", 6);
                            editor.commit();
                            restartApp();
                        } else if (i == themePurpleRow) {
                            editor.putInt("theme", 7);
                            editor.commit();
                            restartApp();
                        } else if (i == themeMaterialGreenRow) {
                            editor.putInt("theme", 8);
                            editor.commit();
                            restartApp();
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
            themeRow = 0;
            themeDefaultRow = themeRow++;
            themeRedRow = themeRow++;
            themeGreenRow = themeRow++;
            themeFucsiaRow = themeRow++;
            themeYellowRow = themeRow++;
            themeVioletRow = themeRow++;
            themeBlueRow = themeRow++;
            themePurpleRow = themeRow++;
            themeMaterialGreenRow = themeRow++;
            return true;
        }

        @Override
        public void onFragmentDestroy() {
            super.onFragmentDestroy();
        }


        public void restartApp() {
            Context context = ApplicationLoader.applicationContext;
            Intent mStartActivity = new Intent(context, LaunchActivity.class);
            int mPendingIntentId = 123456;
            PendingIntent mPendingIntent = PendingIntent.getActivity(context, mPendingIntentId, mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
            AlarmManager mgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
            System.exit(0);
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
                    if (i == themeDefaultRow) {
                        textView.setText(R.string.theme_default);
                        divider.setVisibility(View.VISIBLE);
                    } else if (i == themeRedRow) {
                        textView.setText(R.string.theme_red);
                        divider.setVisibility(View.VISIBLE);
                    } else if (i == themeGreenRow) {
                        textView.setText(R.string.theme_green);
                        divider.setVisibility(View.VISIBLE);
                    } else if (i == themeFucsiaRow) {
                        textView.setText(R.string.theme_fucsia);
                        divider.setVisibility(View.VISIBLE);
                    } else if (i == themeYellowRow) {
                        textView.setText(R.string.theme_yellow);
                        divider.setVisibility(View.VISIBLE);
                    } else if (i == themeVioletRow) {
                        textView.setText(R.string.theme_violet);
                        divider.setVisibility(View.VISIBLE);
                    } else if (i == themeBlueRow) {
                        textView.setText(R.string.theme_blue);
                        divider.setVisibility(View.VISIBLE);
                    } else if (i == themePurpleRow) {
                        textView.setText(R.string.theme_purple);
                        divider.setVisibility(View.VISIBLE);
                    } else if (i == themeMaterialGreenRow) {
                        textView.setText(R.string.theme_material_green);
                        divider.setVisibility(View.VISIBLE);
                    }
                }
                return view;

            }
            @Override
            public int getCount() {
                return themeRow;
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public int getItemViewType(int i) {
                if (i == themeDefaultRow || i == themeRedRow || i == themeGreenRow
                    || i == themeFucsiaRow || i == themeYellowRow || i == themeVioletRow
                        ||i == themeBlueRow ||i == themePurpleRow || i == themeMaterialGreenRow) {
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


