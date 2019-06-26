package kr.co.wisetracker.tracker.DotCordovaBridge;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sdk.wisetracker.base.common.base.BaseLogUtil;
import com.sdk.wisetracker.base.model.User;
import com.sdk.wisetracker.dot.open.api.DOT;
import com.sdk.wisetracker.dot.open.model.Click;
import com.sdk.wisetracker.dot.open.model.Conversion;
import com.sdk.wisetracker.dot.open.model.Page;
import com.sdk.wisetracker.dot.open.model.Purchase;
import com.sdk.wisetracker.dox.open.api.DOX;
import com.sdk.wisetracker.dox.open.model.XConversion;
import com.sdk.wisetracker.dox.open.model.XEvent;
import com.sdk.wisetracker.dox.open.model.XIdentify;
import com.sdk.wisetracker.dox.open.model.XRevenue;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

public class DotCordovaBridge extends CordovaPlugin {

    private final String TAG = "[CORDOVA]";

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        try {

            Context context = this.cordova.getContext();

            if (action.equals("toast")) {

                BaseLogUtil.getInstance().d(TAG, "toast test");
                String message = args.getString(0);
                BaseLogUtil.getInstance().d(TAG, "toast test message : " + message);
                Toast.makeText(context, "cordova toast test\nmessage : " + message, Toast.LENGTH_SHORT).show();
                callbackContext.success("toast success");
                return true;

            } else if (action.equals("initialization")) {

                BaseLogUtil.getInstance().d(TAG, "initialization");
                callbackContext.success("initialization success");
                DOT.initialization(context);
                return true;

            } else if (action.equals("setUser")) {

                BaseLogUtil.getInstance().d(TAG, "setUser");
                String json = args.getString(0);
                if (TextUtils.isEmpty(json)) {
                    BaseLogUtil.getInstance().d(TAG, "receive json data is null");
                    return false;
                }
                User user = new Gson().fromJson(json, User.class);
                if (user == null) {
                    BaseLogUtil.getInstance().d(TAG, "user is null");
                    return false;
                }
                DOT.setUser(user);
                callbackContext.success("setUser success");
                return true;

            } else if (action.equals("setUserLogout")) {

                BaseLogUtil.getInstance().d(TAG, "setUserLogout");
                DOT.setUserLogout();
                callbackContext.success("setUserLogout success");
                return true;

            } else if (action.equals("onStartPage")) {

                BaseLogUtil.getInstance().d(TAG, "onStartPage");
                DOT.onStartPage(context);
                callbackContext.success("onStartPage success");
                return true;

            } else if (action.equals("onStopPage")) {

                BaseLogUtil.getInstance().d(TAG, "onStopPage");
                DOT.onStopPage(context);
                callbackContext.success("onStopPage success");
                return true;

            } else if (action.equals("setPage")) {

                BaseLogUtil.getInstance().d(TAG, "setPage");
                String json = args.getString(0);
                if (TextUtils.isEmpty(json)) {
                    BaseLogUtil.getInstance().d(TAG, "receive json data is null");
                    return false;
                }
                Page page = new Gson().fromJson(json, Page.class);
                if (page == null) {
                    BaseLogUtil.getInstance().d(TAG, "page is null");
                    return false;
                }
                DOT.setPage(page);
                callbackContext.success("setPage success");
                return true;

            } else if (action.equals("setConversion")) {

                BaseLogUtil.getInstance().d(TAG, "setConversion");
                String json = args.getString(0);
                if (TextUtils.isEmpty(json)) {
                    BaseLogUtil.getInstance().d(TAG, "receive json data is null");
                    return false;
                }
                Conversion conversion = new Gson().fromJson(json, Conversion.class);
                if (conversion == null) {
                    BaseLogUtil.getInstance().d(TAG, "conversion is null");
                    return false;
                }
                DOT.setConversion(conversion);
                callbackContext.success("setConversion success");
                return true;

            } else if (action.equals("setClick")) {

                BaseLogUtil.getInstance().d(TAG, "setClick");
                String json = args.getString(0);
                if (TextUtils.isEmpty(json)) {
                    BaseLogUtil.getInstance().d(TAG, "receive json data is null");
                    return false;
                }
                Click click = new Gson().fromJson(json, Click.class);
                if (click == null) {
                    BaseLogUtil.getInstance().d(TAG, "click is null");
                    return false;
                }
                DOT.setClick(click);
                callbackContext.success("setClick success");
                return true;

            } else if (action.equals("setPurchase")) {

                BaseLogUtil.getInstance().d(TAG, "setPurchase");
                String json = args.getString(0);
                if (TextUtils.isEmpty(json)) {
                    BaseLogUtil.getInstance().d(TAG, "receive json data is null");
                    return false;
                }
                Purchase purchase = new Gson().fromJson(json, Purchase.class);
                if (purchase == null) {
                    BaseLogUtil.getInstance().d(TAG, "purchase is null");
                    return false;
                }
                DOT.setPurchase(purchase);
                callbackContext.success("setPurchase success");
                return true;

            } else if (action.equals("groupIdentify")) {

                BaseLogUtil.getInstance().d(TAG, "groupIdentify");
                String key = args.getString(0);
                String value = args.getString(1);
                String json = args.getString(2);
                if (TextUtils.isEmpty(json)) {
                    BaseLogUtil.getInstance().d(TAG, "receive json data is null");
                    return false;
                }
                XIdentify xIdentify = new Gson().fromJson(json, XIdentify.class);
                DOX.groupIdentify(key, value, xIdentify);
                callbackContext.success("groupIdentify success");
                return true;

            } else if (action.equals("userIdentify")) {

                BaseLogUtil.getInstance().d(TAG, "userIdentify");
                String json = args.getString(0);
                if (TextUtils.isEmpty(json)) {
                    BaseLogUtil.getInstance().d(TAG, "receive json data is null");
                    return false;
                }
                XIdentify xIdentify = new Gson().fromJson(json, XIdentify.class);
                DOX.userIdentify(xIdentify);
                callbackContext.success("userIdentify success");
                return true;

            } else if (action.equals("logEvent")) {

                BaseLogUtil.getInstance().d(TAG, "logEvent");
                String json = args.getString(0);
                if (TextUtils.isEmpty(json)) {
                    BaseLogUtil.getInstance().d(TAG, "receive json data is null");
                    return false;
                }
                XEvent xEvent = new Gson().fromJson(json, XEvent.class);
                DOX.logEvent(xEvent);
                callbackContext.success("logEvent success");
                return true;

            } else if (action.equals("logConversion")) {

                BaseLogUtil.getInstance().d(TAG, "logConversion");
                String json = args.getString(0);
                if (TextUtils.isEmpty(json)) {
                    BaseLogUtil.getInstance().d(TAG, "receive json data is null");
                    return false;
                }
                XConversion xConversion = new Gson().fromJson(json, XConversion.class);
                DOX.logConversion(xConversion);
                callbackContext.success("logConversion success");
                return true;

            } else if (action.equals("logRevenue")) {

                BaseLogUtil.getInstance().d(TAG, "logRevenue");
                String json = args.getString(0);
                if (TextUtils.isEmpty(json)) {
                    BaseLogUtil.getInstance().d(TAG, "receive json data is null");
                    return false;
                }
                XRevenue xRevenue = new Gson().fromJson(json, XRevenue.class);
                DOX.logRevenue(xRevenue);
                callbackContext.success("logRevenue success");
                return true;

            }

        } catch (Exception e) {
            BaseLogUtil.getInstance().e(TAG, "get error !!", e);
        }

        return false;

    }


}
