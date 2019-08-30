package kr.co.wisetracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.sdk.wisetracker.dot.open.api.DOT;

public class DeepLinkActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDeepLink(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setDeepLink(intent);
    }

    private void setDeepLink(Intent intent) {
        try {
            Log.i("[wisetracker]", "set sdk by deep link!!");
            if (intent == null || intent.getData() == null) {
                Log.i("[wisetracker]", "intent is null !!");
                return;
            }
            DOT.setDeepLink(this, getIntent());
            moveToMainActivity();
        } catch (Exception e) {
            Log.e("[wisetracker]", "set sdk error !!", e);
        }
    }

    private void moveToMainActivity() {
        try {
            Intent intent = getPackageManager().getLaunchIntentForPackage(getPackageName());
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        } catch (Exception e) {
            Log.i("[wisetracker]", "move to main activity error !!", e);
        }
    }

}