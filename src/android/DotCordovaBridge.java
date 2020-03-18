package kr.co.wisetracker;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sdk.wisetracker.base.open.model.User;
import com.sdk.wisetracker.base.tracker.common.WisetrackerLog;
import com.sdk.wisetracker.dox.open.api.DOX;
import com.sdk.wisetracker.dox.open.model.XConversion;
import com.sdk.wisetracker.dox.open.model.XEvent;
import com.sdk.wisetracker.dox.open.model.XIdentify;
import com.sdk.wisetracker.dox.open.model.XProduct;
import com.sdk.wisetracker.dox.open.model.XProperties;
import com.sdk.wisetracker.dox.open.model.XPurchase;
import com.sdk.wisetracker.new_dot.open.DOT;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DotCordovaBridge extends CordovaPlugin {

    private final String TAG = "[CORDOVA]";

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        try {

            Context context = this.cordova.getContext();

            if (action.equals("toast")) {

                String message = args.getString(0);
                Toast.makeText(context, "cordova toast test\nmessage : " + message, Toast.LENGTH_SHORT).show();
                callbackContext.success("toast success");
                return true;

            } else if (action.equals("initialization")) {

                DOT.open("Cordova");
                WisetrackerLog.d(TAG, "initialization");
                callbackContext.success("initialization success");
                return true;

            } else if (action.equals("setUser")) {

                WisetrackerLog.d(TAG, "setUser");
                String json = args.getString(0);
                if (TextUtils.isEmpty(json)) {
                    WisetrackerLog.d(TAG, "receive json data is null");
                    return false;
                }
                WisetrackerLog.d(TAG, "raw data: " + json);
                User user = new Gson().fromJson(json, User.class);
                if (user == null) {
                    WisetrackerLog.d(TAG, "user is null");
                    return false;
                }
                WisetrackerLog.d(TAG, "user data: " + new Gson().toJson(user));
                DOT.setUser(user);
                callbackContext.success("setUser success");
                return true;

            } else if (action.equals("setUserLogout")) {

                WisetrackerLog.d(TAG, "setUserLogout");
                DOT.setUserLogout();
                callbackContext.success("setUserLogout success");
                return true;

            } else if (action.equals("onPlayStart")) {

                WisetrackerLog.d(TAG, "onPlayStart");
                String period = args.getString(0);
                if (TextUtils.isEmpty(period)) {
                    WisetrackerLog.d(TAG, "period is empty");
                    DOT.onPlayStart();
                } else {
                    try {
                        DOT.onPlayStart(Integer.valueOf(period));
                    } catch (Exception e) {
                        DOT.onPlayStart();
                        WisetrackerLog.e(TAG, "onPlayStart exception", e);
                    }
                }
                callbackContext.success("onPlayStart success");
                return true;

            } else if (action.equals("onPlayStop")) {

                WisetrackerLog.d(TAG, "onPlayStop");
                DOT.onPlayStop();
                callbackContext.success("onPlayStop success");
                return true;

            } else if (action.equals("onStartPage")) {

                WisetrackerLog.d(TAG, "onStartPage");
                DOT.onStartPage(null);
                callbackContext.success("onStartPage success");
                return true;

            } else if (action.equals("onStopPage")) {

                WisetrackerLog.d(TAG, "onStopPage");
                DOT.onStopPage();
                callbackContext.success("onStopPage success");
                return true;

            } else if (action.equals("logScreen")) {

                WisetrackerLog.d(TAG, "logScreen");
                String json = args.getString(0);
                if (TextUtils.isEmpty(json)) {
                    WisetrackerLog.d(TAG, "page json is null");
                    return false;
                }
                WisetrackerLog.d(TAG, "page data: " + json);
                Type type = new TypeToken<Map<String, Object>>() {
                }.getType();
                Map<String, Object> pageMap = new Gson().fromJson(json, type);
                if (pageMap == null) {
                    WisetrackerLog.d(TAG, "page map is null");
                    return false;
                }
                DOT.logScreen(pageMap);
                callbackContext.success("setPage success");
                return true;

            } else if (action.equals("logEvent")) {

                WisetrackerLog.d(TAG, "logEvent");
                String json = args.getString(0);
                if (TextUtils.isEmpty(json)) {
                    WisetrackerLog.d(TAG, "conversion json is null");
                    return false;
                }
                WisetrackerLog.d(TAG, "conversion data: " + json);
                Type type = new TypeToken<Map<String, Object>>() {
                }.getType();
                Map<String, Object> conversionMap = new Gson().fromJson(json, type);
                if (conversionMap == null) {
                    WisetrackerLog.d(TAG, "conversion map is null");
                    return false;
                }
                DOT.logEvent(conversionMap);
                callbackContext.success("setConversion success");
                return true;

            } else if (action.equals("logClick")) {

                WisetrackerLog.d(TAG, "logClick");
                String json = args.getString(0);
                if (TextUtils.isEmpty(json)) {
                    WisetrackerLog.d(TAG, "click json is null");
                    return false;
                }
                WisetrackerLog.d(TAG, "click data: " + json);
                Type type = new TypeToken<Map<String, Object>>() {
                }.getType();
                Map<String, Object> clickMap = new Gson().fromJson(json, type);
                if (clickMap == null) {
                    WisetrackerLog.d(TAG, "click map is null");
                    return false;
                }
                DOT.logClick(clickMap);
                callbackContext.success("setClick success");
                return true;

            } else if (action.equals("logPurchase")) {

                WisetrackerLog.d(TAG, "logPurchase");
                String json = args.getString(0);
                if (TextUtils.isEmpty(json)) {
                    WisetrackerLog.d(TAG, "purchase json is null");
                    return false;
                }
                WisetrackerLog.d(TAG, "purchase data: " + json);
                Type type = new TypeToken<Map<String, Object>>() {
                }.getType();
                Map<String, Object> purchaseMap = new Gson().fromJson(json, type);
                if (purchaseMap == null) {
                    WisetrackerLog.d(TAG, "purchase map is null");
                    return false;
                }
                DOT.logPurchase(purchaseMap);
                callbackContext.success("setPurchase success");
                return true;

            } else if (action.equals("groupIdentify")) {

                WisetrackerLog.d(TAG, "groupIdentify");
                String json = args.getString(0);
                if (TextUtils.isEmpty(json)) {
                    WisetrackerLog.d(TAG, "receive json data is null");
                    return false;
                }
                JSONObject jsonObject = new JSONObject(json);
                if (jsonObject == null) {
                    return false;
                }
                WisetrackerLog.d(TAG, "raw data: " + json);

                String key = null;
                String value = null;
                if (jsonObject.has("groups")) {
                    String groups = jsonObject.get("groups").toString();
                    Map<String, String> groupsMap = getGroups(groups);
                    if (groupsMap != null) {
                        Iterator iterator = groupsMap.keySet().iterator();
                        while (iterator.hasNext()) {
                            key = iterator.next().toString();
                            value = groupsMap.get(key);
                            WisetrackerLog.d(TAG, "key: " + key);
                            WisetrackerLog.d(TAG, "value: " + value);
                        }
                    }
                }

                XIdentify xIdentify = null;
                if (jsonObject.has("groupproperties")) {
                    String xIdentifyJson = jsonObject.get("groupproperties").toString();
                    xIdentify = new Gson().fromJson(xIdentifyJson, XIdentify.class);
                    if (xIdentify == null) {
                        WisetrackerLog.d(TAG, "xIdentify is null");
                        return false;
                    }
                    WisetrackerLog.d(TAG, new Gson().toJson(xIdentify));
                }

                DOX.groupIdentify(key, value, xIdentify);
                callbackContext.success("groupIdentify success");
                return true;

            } else if (action.equals("userIdentify")) {

                WisetrackerLog.d(TAG, "userIdentify");
                String json = args.getString(0);
                if (TextUtils.isEmpty(json)) {
                    WisetrackerLog.d(TAG, "receive json data is null");
                    return false;
                }
                WisetrackerLog.d(TAG, "raw data: " + json);
                XIdentify xIdentify = new Gson().fromJson(json, XIdentify.class);
                if (xIdentify == null) {
                    WisetrackerLog.d(TAG, "xIdentify is null");
                    return false;
                }
                WisetrackerLog.d(TAG, "xIdentify data: " + new Gson().toJson(xIdentify));
                DOX.userIdentify(xIdentify);
                callbackContext.success("userIdentify success");
                return true;

            } else if (action.equals("logXEvent")) {

                WisetrackerLog.d(TAG, "logXEvent");
                String json = args.getString(0);
                if (TextUtils.isEmpty(json)) {
                    WisetrackerLog.d(TAG, "receive json data is null");
                    return false;
                }
                WisetrackerLog.d(TAG, "raw data: " + json);
                XEvent xEvent = new Gson().fromJson(json, XEvent.class);
                if (xEvent == null) {
                    WisetrackerLog.d(TAG, "xEvent is null");
                    return false;
                }
                xEvent.setXProperties(getXProperties(json));
                DOX.logXEvent(xEvent);
                WisetrackerLog.d(TAG, "xEvent data: " + new Gson().toJson(xEvent));
                callbackContext.success("logEvent success");
                return true;

            } else if (action.equals("logXConversion")) {

                WisetrackerLog.d(TAG, "logXConversion");
                String json = args.getString(0);
                if (TextUtils.isEmpty(json)) {
                    WisetrackerLog.d(TAG, "receive json data is null");
                    return false;
                }
                WisetrackerLog.d(TAG, "raw data: " + json);
                XConversion xConversion = new Gson().fromJson(json, XConversion.class);
                if (xConversion == null) {
                    WisetrackerLog.d(TAG, "xConversion is null");
                    return false;
                }
                xConversion.setXProperties(getXProperties(json));
                DOX.logXConversion(xConversion);
                WisetrackerLog.d(TAG, "xConversion data: " + new Gson().toJson(xConversion));
                callbackContext.success("logConversion success");
                return true;

            } else if (action.equals("logXPurchase")) {

                WisetrackerLog.d(TAG, "logXPurchase");
                String json = args.getString(0);
                if (TextUtils.isEmpty(json)) {
                    WisetrackerLog.d(TAG, "receive json data is null");
                    return false;
                }
                WisetrackerLog.d(TAG, "raw data: " + json);
                XPurchase xPurchase = new Gson().fromJson(json, XPurchase.class);
                if (xPurchase == null) {
                    WisetrackerLog.d(TAG, "xPurchase is null");
                    return false;
                }
                xPurchase.setXProperties(getXProperties(json));
                setProductXProperties(xPurchase, json);
                DOX.logXPurchase(xPurchase);
                WisetrackerLog.d(TAG, "xPurchase data: " + new Gson().toJson(xPurchase));
                callbackContext.success("logPurchase success");
                return true;

            }

        } catch (Exception e) {
            WisetrackerLog.e(TAG, "get error !!", e);
        }

        return false;

    }

    private Map<String, String> getGroups(String groups) {

        try {

            Type type = new TypeToken<Map<String, String>>() {
            }.getType();
            Map<String, String> groupsMap = new Gson().fromJson(groups, type);
            if (groupsMap == null) {
                return null;
            }
            return groupsMap;

        } catch (Exception e) {
            WisetrackerLog.e(TAG, "get groups error !!", e);
        }

        return null;

    }

    private void setProductXProperties(XPurchase xPurchase, String json) {

        try {

            List<XProduct> xProductList = xPurchase.getProductList();
            if (xProductList == null || xProductList.isEmpty()) {
                return;
            }

            List<XProperties> xPropertiesList = new ArrayList<>();
            JSONObject jsonObject = new JSONObject(json);
            if (jsonObject == null) {
                return;
            }

            JSONArray jsonArray = jsonObject.getJSONArray("product");
            if (jsonArray == null || jsonArray.length() == 0) {
                return;
            }

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject productObject = jsonArray.getJSONObject(i);
                if (productObject == null) {
                    continue;
                }
                XProperties xProperties = getXProperties((productObject.toString()));
                if (xProperties == null) {
                    continue;
                }
                xPropertiesList.add(xProperties);
            }

            for (int i = 0; i < xProductList.size(); i++) {
                xProductList.get(i).setProperties(xPropertiesList.get(i));
            }

        } catch (Exception e) {
            WisetrackerLog.e(TAG, "get product properties error !!", e);
        }

    }

    private XProperties getXProperties(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            if (jsonObject.has("properties")) {
                String properties = jsonObject.get("properties").toString();
                if (TextUtils.isEmpty(properties)) {
                    return null;
                }
                Type type = new TypeToken<Map<String, Object>>() {
                }.getType();
                Map<String, Object> propertiesMap = new Gson().fromJson(properties, type);
                if (propertiesMap == null) {
                    return null;
                }
                XProperties xProperties = new XProperties(propertiesMap);
                return xProperties;
            }
        } catch (Exception e) {
            WisetrackerLog.e(TAG, "get properties error !!", e);
        }
        return null;
    }

}
