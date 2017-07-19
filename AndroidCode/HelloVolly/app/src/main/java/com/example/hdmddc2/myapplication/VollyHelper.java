package com.example.hdmddc2.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liyang on 2017/7/19.
 */

public class VollyHelper {

    public static void handleStringRequest(Activity activity) {
        String url = "https://www.douban.com/";
        final TextView textView = (TextView) activity.findViewById(R.id.txt_1);
        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.i("liyang", "response:"+s);
                textView.setText(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.i("liyang", "erro :" + volleyError.getMessage(), volleyError);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("params1", "value1");
                map.put("params2", "value2");
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }


    public static void handleJsonRequest(Activity activity) {
        final TextView textView = (TextView) activity.findViewById(R.id.txt_2);
        String url2 = "http://www.weather.com.cn/data/cityinfo/101010100.html";
        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        JsonObjectRequest request = new JsonObjectRequest(url2, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.i("liyang", "jsonObject:"+jsonObject);
                textView.setText(jsonObject.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.i("liyang", "erro :" + volleyError.getMessage(), volleyError);
            }
        });
        requestQueue.add(request);
    }


    public static void handleImageRequest(Activity activity) {
        final ImageView view = (ImageView) activity.findViewById(R.id.img_3);
        String url3 = "http://img0.imgtn.bdimg.com/it/u=33594923,3823466401&fm=26&gp=0.jpg";
        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        ImageRequest request = new ImageRequest(url3, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                Log.i("liyang", "ImageRequest : " + bitmap);
                view.setImageBitmap(bitmap);
            }
        }, 320, 320, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.i("liyang", "ImageRequest : " + volleyError);
            }
        });
        requestQueue.add(request);
    }


    public static void handleImageLoader(Activity activity) {
        final ImageView view = (ImageView) activity.findViewById(R.id.img_4);
        String url4 = "http://img0.imgtn.bdimg.com/it/u=33594923,3823466401&fm=26&gp=0.jpg";
        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        ImageLoader imageLoader = new ImageLoader(requestQueue, new BitmapCache());

        ImageLoader.ImageListener listener = ImageLoader.getImageListener(view, 0, 0);
        imageLoader.get(url4, listener);
    }


    public static void handleNetWorkImageView(Activity activity) {
        final NetworkImageView view = (NetworkImageView) activity.findViewById(R.id.img_5);
        String url5 = "http://img0.imgtn.bdimg.com/it/u=33594923,3823466401&fm=26&gp=0.jpg";
        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        ImageLoader imageLoader = new ImageLoader(requestQueue, new BitmapCache());
        view.setDefaultImageResId(R.drawable.ic_launcher);
        view.setErrorImageResId(R.drawable.ic_launcher);
        view.setImageUrl(url5, imageLoader);
    }


    public static void handleXmlRequest(Activity activity) {
        final TextView textView = (TextView) activity.findViewById(R.id.txt_6);
        String url6 = "http://flash.weather.com.cn/wmaps/xml/china.xml";
        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        XmlRequest request = new XmlRequest(url6, new Response.Listener<XmlPullParser>() {
            @Override
            public void onResponse(XmlPullParser response) {
                try {
                    int eventType = response.getEventType();
                    while (eventType != XmlPullParser.END_DOCUMENT) {
                        switch (eventType) {
                            case XmlPullParser.START_TAG:
                                String nodeName = response.getName();
                                String name = "";
                                if ("city".equals(nodeName)) {
                                    String pName = response.getAttributeValue(0);
                                    Log.d("liyang", "pName is " + pName);
                                    name = pName + " - ";
                                }
                                textView.setText(name);
                                break;
                        }
                        eventType = response.next();
                    }
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("liyang", error.getMessage(), error);
            }
        });

        requestQueue.add(request);
    }

    public static void handleGsonRequest(Activity activity) {
        final TextView textView = (TextView) activity.findViewById(R.id.txt_7);
        String url7 = "http://www.weather.com.cn/data/sk/101010100.html";
        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        GsonRequest<Weather> gsonRequest = new GsonRequest<Weather>(url7, Weather.class,
                new Response.Listener<Weather>() {
                    @Override
                    public void onResponse(Weather weather) {
                        WeatherInfo weatherInfo = weather.getWeatherinfo();
                        Log.d("liyang", "city is " + weatherInfo.getCity());
                        Log.d("liyang", "temp is " + weatherInfo.getTemp());
                        Log.d("liyang", "time is " + weatherInfo.getTime());

                        textView.setText(weatherInfo.getCity() +" - " + weatherInfo.getTemp() + " - " + weatherInfo.getTime());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("liyang", error.getMessage(), error);
            }
        });
        requestQueue.add(gsonRequest);
    }
}
