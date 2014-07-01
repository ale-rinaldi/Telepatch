/*
 * This is the source code of Telegram for Android v. 1.3.2.
 * It is licensed under GNU GPL v. 2 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Nikolai Kudashov, 2013.
 */

package org.telepatch.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothClass;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.telepatch.PhoneFormat.PhoneFormat;
import org.telepatch.messenger.BuildVars;
import org.telepatch.messenger.ConnectionsManager;
import org.telepatch.messenger.FileLog;
import org.telepatch.messenger.LocaleController;
import org.telepatch.messenger.MessagesController;
import org.telepatch.messenger.MessagesStorage;
import org.telepatch.messenger.NotificationCenter;
import org.telepatch.messenger.R;
import org.telepatch.messenger.RPCRequest;
import org.telepatch.messenger.SerializedData;
import org.telepatch.messenger.TLClassStore;
import org.telepatch.messenger.TLObject;
import org.telepatch.messenger.TLRPC;
import org.telepatch.messenger.UserConfig;
import org.telepatch.messenger.Utilities;
import org.telepatch.objects.MessageObject;
import org.telepatch.objects.PhotoObject;
import org.telepatch.ui.Adapters.BaseFragmentAdapter;
import org.telepatch.ui.Views.ActionBar.ActionBarLayer;
import org.telepatch.ui.Views.ActionBar.BaseFragment;
import org.telepatch.ui.Views.AvatarUpdater;
import org.telepatch.ui.Views.BackupImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

public class sgActivity extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.sgactivity_layout);

        super.onCreate(savedInstanceState);
    }
}
