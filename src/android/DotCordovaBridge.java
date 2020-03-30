package kr.co.wisetracker;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sdk.wisetracker.base.open.model.User;
import com.sdk.wisetracker.base.tracker.common.log.WiseLog;
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
                WiseLog.d("initialization");
                callbackContext.success("initialization success");
                return true;

            } else if (action.equals("setUser")) {

                WiseLog.d("setUser");
                String json = args.getString(0);
                if (TextUtils.isEmpty(json)) {
                    WiseLog.d("receive json data is null");
                    return false;
                }
                WiseLog.d("raw data: " + json);
                User user = new Gson().fromJson(json, User.class);
                if (user == null) {
                    WiseLog.d("user is null");
                    return false;
                }
                WiseLog.d("user data: " + new Gson().toJson(user));
                DOT.setUser(user);
                callbackContext.success("setUser success");
                return true;

            } else if (action.equals("setUserLogout")) {

                WiseLog.d("setUserLogout");
                DOT.setUserLogout();
                callbackContext.success("setUserLogout success");
                return true;

            } else if (action.equals("onPlayStart")) {

                WiseLog.d("onPlayStart");
                String period = args.getString(0);
                if (TextUtils.isEmpty(period)) {
                    WiseLog.d("period is empty");
                    DOT.onPlayStart();
                } else {
                    try {
                        DOT.onPlayStart(Integer.valueOf(period));
                    } catch (Exception e) {
                        DOT.onPlayStart();
                        WiseLog.e(e);
                    }
                }
                callbackContext.success("onPlayStart success");
                return true;

            } else if (action.equals("onPlayStop")) {

                WiseLog.d("onPlayStop");
                DOT.onPlayStop();
                callbackContext.success("onPlayStop success");
                return true;

            } else if (action.equals("onStartPage")) {

                WiseLog.d("onStartPage");
                DOT.onStartPage(null);
                callbackContext.success("onStartPage success");
                return true;

            } else if (action.equals("onStopPage")) {

                WiseLog.d("onStopPage");
                DOT.onStopPage();
                callbackContext.success("onStopPage success");
                return true;

            } else if (action.equals("logScreen")) {

                WiseLog.d("logScreen");
                String json = args.getString(0);
                if (TextUtils.isEmpty(json)) {
                    WiseLog.d("page json is null");
                    return false;
                }
                WiseLog.d("page data: " + json);
                Type type = new TypeToken<Map<String, Object>>() {
                }.getType();
                Map<String, Object> pageMap = new Gson().fromJson(json, type);
                if (pageMap == null) {
                    WiseLog.d("page map is null");
                    return false;
                }
                DOT.logScreen(pageMap);
                callbackContext.success("setPage success");
                return true;

            } else if (action.equals("logEvent")) {

                WiseLog.d("logEvent");
                String json = args.getString(0);
                if (TextUtils.isEmpty(json)) {
                    WiseLog.d("conversion json is null");
                    return false;
                }
                WiseLog.d("conversion data: " + json);
                Type type = new TypeToken<Map<String, Object>>() {
                }.getType();
                Map<String, Object> conversionMap = new Gson().fromJson(json, type);
                if (conversionMap == null) {
                    WiseLog.d("conversion map is null");
                    return false;
                }
                DOT.logEvent(conversionMap);
                callbackContext.success("setConversion success");
                return true;

            } else if (action.equals("logClick")) {

                WiseLog.d("logClick");
                String json = args.getString(0);
                if (TextUtils.isEmpty(json)) {
                    WiseLog.d("click json is null");
                    return false;
                }
                WiseLog.d("click data: " + json);
                Type type = new TypeToken<Map<String, Object>>() {
                }.getType();
                Map<String, Object> clickMap = new Gson().fromJson(json, type);
                if (clickMap == null) {
                    WiseLog.d("click map is null");
                    return false;
                }
                DOT.logClick(clickMap);
                callbackContext.success("setClick success");
                return true;

            } else if (action.equals("logPurchase")) {

                WiseLog.d("logPurchase");
                String json = args.getString(0);
                if (TextUtils.isEmpty(json)) {
                    WiseLog.d("purchase json is null");
                    return false;
                }
                WiseLog.d("purchase data: " + json);
                Type type = new TypeToken<Map<String, Object>>() {
                }.getType();
                Map<String, Object> purchaseMap = new Gson().fromJson(json, type);
                if (purchaseMap == null) {
                    WiseLog.d("purchase map is null");
                    return false;
                }
                DOT.logPurchase(purchaseMap);
                callbackContext.success("setPurchase success");
                return true;

            } else if (action.equals("groupIdentify")) {

                WiseLog.d("groupIdentify");
                String json = args.getString(0);
                if (TextUtils.isEmpty(json)) {
                    WiseLog.d("receive json data is null");
                    return false;
                }
                JSONObject jsonObject = new JSONObject(json);
                if (jsonObject == null) {
                    return false;
                }
                WiseLog.d("raw data: " + json);

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
                            WiseLog.d("key: " + key);
                            WiseLog.d("value: " + value);
                        }
                    }
                }

                XIdentify xIdentify = null;
                if (jsonObject.has("groupproperties")) {
                    String xIdentifyJson = jsonObject.get("groupproperties").toString();
                    xIdentify = new Gson().fromJson(xIdentifyJson, XIdentify.class);
                    if (xIdentify == null) {
                        WiseLog.d("xIdentify is null");
                        return false;
                    }
                    WiseLog.d(new Gson().toJson(xIdentify));
                }

                DOX.groupIdentify(key, value, xIdentify);
                callbackContext.success("groupIdentify success");
                return true;

            } else if (action.equals("userIdentify")) {

                WiseLog.d("userIdentify");
                String json = args.getString(0);
                if (TextUtils.isEmpty(json)) {
                    WiseLog.d("receive json data is null");
                    return false;
                }
                WiseLog.d("raw data: " + json);
                XIdentify xIdentify = new Gson().fromJson(json, XIdentify.class);
                if (xIdentify == null) {
                    WiseLog.d("xIdentify is null");
                    return false;
                }
                WiseLog.d("xIdentify data: " + new Gson().toJson(xIdentify));
                DOX.userIdentify(xIdentify);
                callbackContext.success("userIdentify success");
                return true;

            } else if (action.equals("logXEvent")) {

                WiseLog.d("logXEvent");
                String json = args.getString(0);
                if (TextUtils.isEmpty(json)) {
                    WiseLog.d("receive json data is null");
                    return false;
                }
                WiseLog.d("raw data: " + json);
                XEvent xEvent = new Gson().fromJson(json, XEvent.class);
                if (xEvent == null) {
                    WiseLog.d("xEvent is null");
                    return false;
                }
                xEvent.setXProperties(getXProperties(json));
                DOX.logXEvent(xEvent);
                WiseLog.d("xEvent data: " + new Gson().toJson(xEvent));
                callbackContext.success("logEvent success");
                return true;

            } else if (action.equals("logXConversion")) {

                WiseLog.d("logXConversion");
                String json = args.getString(0);
                if (TextUtils.isEmpty(json)) {
                    WiseLog.d("receive json data is null");
                    return false;
                }
                WiseLog.d("raw data: " + json);
                XConversion xConversion = new Gson().fromJson(json, XConversion.class);
                if (xConversion == null) {
                    WiseLog.d("xConversion is null");
                    return false;
                }
                xConversion.setXProperties(getXProperties(json));
                DOX.logXConversion(xConversion);
                WiseLog.d("xConversion data: " + new Gson().toJson(xConversion));
                callbackContext.success("logConversion success");
                return true;

            } else if (action.equals("logXPurchase")) {

                WiseLog.d("logXPurchase");
                String json = args.getString(0);
                if (TextUtils.isEmpty(json)) {
                    WiseLog.d("receive json data is null");
                    return false;
                }
                WiseLog.d("raw data: " + json);
                XPurchase xPurchase = new Gson().fromJson(json, XPurchase.class);
                if (xPurchase == null) {
                    WiseLog.d("xPurchase is null");
                    return false;
                }
                xPurchase.setXProperties(getXProperties(json));
                setProductXProperties(xPurchase, json);
                DOX.logXPurchase(xPurchase);
                WiseLog.d("xPurchase data: " + new Gson().toJson(xPurchase));
                callbackContext.success("logPurchase success");
                return true;

            }

        } catch (Exception e) {
            WiseLog.e(e);
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
            WiseLog.e(e);
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
            WiseLog.e(e);
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
            WiseLog.e(e);
        }
        return null;
    }

}
