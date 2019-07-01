package kr.co.wisetracker;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
import com.sdk.wisetracker.dox.open.model.XProduct;
import com.sdk.wisetracker.dox.open.model.XProperties;
import com.sdk.wisetracker.dox.open.model.XPurchase;

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
                BaseLogUtil.getInstance().d(TAG, "raw data: " + json);
                User user = new Gson().fromJson(json, User.class);
                if (user == null) {
                    BaseLogUtil.getInstance().d(TAG, "user is null");
                    return false;
                }
                BaseLogUtil.getInstance().d(TAG, "user data: " + new Gson().toJson(user));
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
                BaseLogUtil.getInstance().d(TAG, "raw data: " + json);
                Page page = new Gson().fromJson(json, Page.class);
                if (page == null) {
                    BaseLogUtil.getInstance().d(TAG, "page is null");
                    return false;
                }
                BaseLogUtil.getInstance().d(TAG, "page data: " + new Gson().toJson(page));
                DOT.onStartPage(context);
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
                BaseLogUtil.getInstance().d(TAG, "raw data: " + json);
                Conversion conversion = new Gson().fromJson(json, Conversion.class);
                if (conversion == null) {
                    BaseLogUtil.getInstance().d(TAG, "conversion is null");
                    return false;
                }
                BaseLogUtil.getInstance().d(TAG, "conversion data: " + new Gson().toJson(conversion));
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
                BaseLogUtil.getInstance().d(TAG, "raw data: " + json);
                Click click = new Gson().fromJson(json, Click.class);
                if (click == null) {
                    BaseLogUtil.getInstance().d(TAG, "click is null");
                    return false;
                }
                BaseLogUtil.getInstance().d(TAG, "click data: " + new Gson().toJson(click));
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
                BaseLogUtil.getInstance().d(TAG, "raw data: " + json);
                Purchase purchase = new Gson().fromJson(json, Purchase.class);
                if (purchase == null) {
                    BaseLogUtil.getInstance().d(TAG, "purchase is null");
                    return false;
                }
                BaseLogUtil.getInstance().d(TAG, "purchase data: " + new Gson().toJson(purchase));
                DOT.setPurchase(purchase);
                callbackContext.success("setPurchase success");
                return true;

            } else if (action.equals("groupIdentify")) {

                BaseLogUtil.getInstance().d(TAG, "groupIdentify");
                String json = args.getString(0);
                if (TextUtils.isEmpty(json)) {
                    BaseLogUtil.getInstance().d(TAG, "receive json data is null");
                    return false;
                }
                JSONObject jsonObject = new JSONObject(json);
                if (jsonObject == null) {
                    return false;
                }
                BaseLogUtil.getInstance().d(TAG, "raw data: " + json);

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
                            BaseLogUtil.getInstance().d(TAG, "key: " + key);
                            BaseLogUtil.getInstance().d(TAG, "value: " + value);
                        }
                    }
                }

                XIdentify xIdentify = null;
                if (jsonObject.has("groupproperties")) {
                    String xIdentifyJson = jsonObject.get("groupproperties").toString();
                    xIdentify = new Gson().fromJson(xIdentifyJson, XIdentify.class);
                    if (xIdentify == null) {
                        BaseLogUtil.getInstance().d(TAG, "xIdentify is null");
                        return false;
                    }
                    BaseLogUtil.getInstance().d(TAG, new Gson().toJson(xIdentify));
                }

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
                BaseLogUtil.getInstance().d(TAG, "raw data: " + json);
                XIdentify xIdentify = new Gson().fromJson(json, XIdentify.class);
                if (xIdentify == null) {
                    BaseLogUtil.getInstance().d(TAG, "xIdentify is null");
                    return false;
                }
                BaseLogUtil.getInstance().d(TAG, "xIdentify data: " + new Gson().toJson(xIdentify));
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
                BaseLogUtil.getInstance().d(TAG, "raw data: " + json);
                XEvent xEvent = new Gson().fromJson(json, XEvent.class);
                if (xEvent == null) {
                    BaseLogUtil.getInstance().d(TAG, "xEvent is null");
                    return false;
                }
                xEvent.setXProperties(getXProperties(json));
                DOX.logEvent(xEvent);
                BaseLogUtil.getInstance().d(TAG, "xEvent data: " + new Gson().toJson(xEvent));
                callbackContext.success("logEvent success");
                return true;

            } else if (action.equals("logConversion")) {

                BaseLogUtil.getInstance().d(TAG, "logConversion");
                String json = args.getString(0);
                if (TextUtils.isEmpty(json)) {
                    BaseLogUtil.getInstance().d(TAG, "receive json data is null");
                    return false;
                }
                BaseLogUtil.getInstance().d(TAG, "raw data: " + json);
                XConversion xConversion = new Gson().fromJson(json, XConversion.class);
                if (xConversion == null) {
                    BaseLogUtil.getInstance().d(TAG, "xConversion is null");
                    return false;
                }
                xConversion.setXProperties(getXProperties(json));
                DOX.logConversion(xConversion);
                BaseLogUtil.getInstance().d(TAG, "xConversion data: " + new Gson().toJson(xConversion));
                callbackContext.success("logConversion success");
                return true;

            } else if (action.equals("logPurchase")) {

                BaseLogUtil.getInstance().d(TAG, "logPurchase");
                String json = args.getString(0);
                if (TextUtils.isEmpty(json)) {
                    BaseLogUtil.getInstance().d(TAG, "receive json data is null");
                    return false;
                }
                BaseLogUtil.getInstance().d(TAG, "raw data: " + json);
                XPurchase xPurchase = new Gson().fromJson(json, XPurchase.class);
                if (xPurchase == null) {
                    BaseLogUtil.getInstance().d(TAG, "xPurchase is null");
                    return false;
                }
                xPurchase.setXProperties(getXProperties(json));
                setProductXProperties(xPurchase, json);
                DOX.logPurchase(xPurchase);
                BaseLogUtil.getInstance().d(TAG, "xPurchase data: " + new Gson().toJson(xPurchase));
                callbackContext.success("logPurchase success");
                return true;

            }

        } catch (Exception e) {
            BaseLogUtil.getInstance().e(TAG, "get error !!", e);
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
            BaseLogUtil.getInstance().e(TAG, "get groups error !!", e);
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
            BaseLogUtil.getInstance().e(TAG, "get product properties error !!", e);
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
            BaseLogUtil.getInstance().e(TAG, "get properties error !!", e);
        }
        return null;
    }

}
