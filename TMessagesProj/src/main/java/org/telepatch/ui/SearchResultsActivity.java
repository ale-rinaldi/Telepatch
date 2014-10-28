package org.telepatch.ui;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.telepatch.android.LocaleController;
import org.telepatch.messenger.R;
import org.telepatch.messenger.TLRPC;
import org.telepatch.ui.Adapters.BaseFragmentAdapter;
import org.telepatch.ui.Views.ActionBar.ActionBarLayer;
import org.telepatch.ui.Views.ActionBar.BaseFragment;

import java.util.ArrayList;



public class SearchResultsActivity extends BaseFragment
{
    private ListAdapter listAdapter;
    private ArrayList<String> listArray;
    private ListView listView;
    private TLRPC.messages_Messages mess;
    private int count;
    private Bundle args;

    /**
     * constructor. Args is deprecated? May be useful later.
     * @param mess Messages array
     * @param args
     */
    public SearchResultsActivity(TLRPC.messages_Messages mess, Bundle args) {
        this.args = args;
        if (mess == null) {
            Log.e("xela92", "messaggi non pervenuti");
        } else {
            count = 0;
            this.mess = mess;
            listArray = new ArrayList<String>();
            for (TLRPC.Message m : getMessages().messages) {
                listArray.add(m.message);
                count++;
            }

        }




    }

    @Override
    public View createView(final LayoutInflater inflater, final ViewGroup container) {
        if (fragmentView == null) {
            actionBarLayer.setDisplayHomeAsUpEnabled(true, R.drawable.ic_ab_back);
            actionBarLayer.setBackOverlay(R.layout.updating_state_layout);
            actionBarLayer.setTitle(LocaleController.getString("SearchResults", R.string.search_results));
            actionBarLayer.setActionBarMenuOnItemClick(new ActionBarLayer.ActionBarMenuOnItemClick() {
                @Override
                public void onItemClick(int id) {
                    if (id == -1) {
                        finishFragment();
                    }
                }
            });

            fragmentView = inflater.inflate(R.layout.settings_layout, container, false);

            listAdapter = new ListAdapter(ApplicationLoader.applicationContext);
            listView = (ListView) fragmentView.findViewById(R.id.listView);
            //listView.setBackgroundDrawable(ApplicationLoader.cachedWallpaper);
            listView.setAdapter(listAdapter);
            listView.setVisibility(View.VISIBLE);


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                    //TODO Vai al punto dove si trova quel messaggio
                    ArrayList<Integer> id = new ArrayList<Integer>();

                    //finishFragment();

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

    /**
     * Gets Messages from search results
     * @return Messages messages array from search results
     */
    private TLRPC.messages_Messages getMessages() {
        return mess;
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
            }
            TextView textView = (TextView)view.findViewById(R.id.settings_row_text);
            View divider = view.findViewById(R.id.settings_row_divider);
            if (listArray.get(i).length() >= 70) {
              textView.setText(listArray.get(i).substring(0, 70).concat("[...]"));
            } else {
               textView.setText(listArray.get(i));
            }


            return view;

        }
        @Override
        public int getCount() {
            return count;
        }

        @Override
        public int getItemViewType(int i) {
            if (this.getItem(i) instanceof String) {
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






