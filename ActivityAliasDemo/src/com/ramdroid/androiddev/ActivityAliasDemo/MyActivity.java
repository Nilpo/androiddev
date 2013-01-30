package com.ramdroid.androiddev.ActivityAliasDemo;

/**
 *    Copyright 2013 by Ronald Ammann (ramdroid)

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;

import java.util.List;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    private void toggleActivityAlias(String name, int icon, boolean enabled)
    {
        ComponentName activityAlias = new ComponentName(getPackageName(), getPackageName() + ".MyActivity-" + name);

        int componentEnabledState = enabled ?
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED :
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED;

        getPackageManager().setComponentEnabledSetting(activityAlias, componentEnabledState, PackageManager.DONT_KILL_APP);

        if (enabled) {
            getActionBar().setIcon(icon);
        }
    }

    private void restartLauncher()
    {
        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        String lastPackageName = "";

        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        List<ResolveInfo> resolves = getPackageManager().queryIntentActivities(intent, PackageManager.GET_INTENT_FILTERS );

        for (ResolveInfo info : resolves) {
            if (!info.activityInfo.packageName.equals(lastPackageName)) {
                lastPackageName = info.activityInfo.packageName;
                am.killBackgroundProcesses(info.activityInfo.packageName);
            }
        }
    }

    public void onClickGreen(View v)
    {
        toggleActivityAlias("green", R.drawable.ic_launcher_green, true);
        toggleActivityAlias("blue", R.drawable.ic_launcher_blue, false);
        toggleActivityAlias("red", R.drawable.ic_launcher_red, false);
        restartLauncher();
    }

    public void onClickBlue(View v)
    {
        toggleActivityAlias("green", R.drawable.ic_launcher_green, false);
        toggleActivityAlias("blue", R.drawable.ic_launcher_blue, true);
        toggleActivityAlias("red", R.drawable.ic_launcher_red, false);
        restartLauncher();
    }

    public void onClickRed(View v)
    {
        toggleActivityAlias("green", R.drawable.ic_launcher_green, false);
        toggleActivityAlias("blue", R.drawable.ic_launcher_blue, false);
        toggleActivityAlias("red", R.drawable.ic_launcher_red, true);
        restartLauncher();
    }
}
