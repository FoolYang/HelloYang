package com.example.okhttpdemo;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String url = "https://www.baidu.com/home/other/data/weatherInfo?city=%E5%8C%97%E4%BA%AC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * get请求
     */
    public void getRequest(View view) {
        // 第一步，获取okHttpClient对象
        OkHttpClient client = new OkHttpClient.Builder().build();
        // 第二步，构建Request对象
        Request request = new Request.Builder().url(url).get().build();
        // 第三步，构建Call对象
        Call call = client.newCall(request);
        // 第四步，异步get请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("js_tst", "getRequest e:" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // 得到在子线程
                String result = response.body().string();
                Log.i("js_tst", "getRequest result:" + result);
            }
        });
    }

    /**
     *Post请求提交字符串
     */
    public void postRequestStr(View view) {
        // 第一步，创建OkHttpClient
        OkHttpClient client = new OkHttpClient.Builder().build();
        // 第二步，创建RequestBody
        Map<String, String> map = new HashMap<>();
        map.put("username", "admin");
        map.put("password", "admin");
        JSONObject jsonObject = new JSONObject(map);
        String jsonStr = jsonObject.toString();
        RequestBody requestBodyJson = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonStr);
        // 第三步，创建Request
        Request request = new Request.Builder().
                url("http://www.jianshu.com/")
                .addHeader("contentType", "application/json;charset=utf-8")
                .post(requestBodyJson).build();
        // 第四步，创建Call回调对象
        final Call call = client.newCall(request);
        // 第五步，发起请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("js_tst", "postRequestStr e:" + e.getMessage());

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Log.i("js_tst", "postRequestStr result:" + result);
            }
        });
    }

    /**
     * Post请求提交键值对
     */
    public void postRequestKV(View view) {
        OkHttpClient client = new OkHttpClient(); // OkHttpClient对象
        FormBody formBody = new FormBody.Builder() // 构建FormBody,传入参数
                .add("username", "admin")
                .add("password", "admin")
                .build();
        final Request request = new Request.Builder() // 构建Request,将FormBody作为Post方法的参数传入
                .url("http://www.jianshu.com/")
                .post(formBody)
                .build();
        Call call = client.newCall(request); //  将Request封装为Call
        call.enqueue(new Callback() { // 调用请求,重写回调方法
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("js_tst", "postRequestKV e:" + e.getMessage());

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                Log.i("js_tst", "postRequestKV result:" + result);
            }
        });
    }

    /**
     * post请求提交文件
     * @param view
     */
    public void postRequestUpFile(View view) {
        // 第一步，创建OkHttpClient
        OkHttpClient client = new OkHttpClient.Builder().build();
        // 第二步，创建RequestBody
        File file = new File(Environment.getExternalStorageDirectory(), "1.png");
        RequestBody requestBody = null;
        if (!file.exists()){
            Toast.makeText(this, "文件不存在", Toast.LENGTH_SHORT).show();
        }else{
            requestBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        }

        // 第三步，创建Request
        Request request = new Request.Builder().
                url("http://www.jianshu.com/")
                .addHeader("contentType", "application/json;charset=utf-8")
                .post(requestBody).build();
        // 第四步，创建Call回调对象
        final Call call = client.newCall(request);
        // 第五步，发起请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("js_tst", "postRequestUpFile e:" + e.getMessage());

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Log.i("js_tst", "postRequestUpFile result:" + result);
            }
        });
    }

    /**
     * get请求下载
     */
    public void getDownload(View view) {
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .get()
                .url("https://www.baidu.com/img/bd_logo1.png")
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("js_tst", "getDownload e:" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //拿到字节流
                InputStream is = response.body().byteStream();
                int len = 0;
                File file  = new File(Environment.getExternalStorageDirectory(), "n.png");
                FileOutputStream fos = new FileOutputStream(file);
                byte[] buf = new byte[128];

                while ((len = is.read(buf)) != -1){
                    fos.write(buf, 0, len);
                }
                fos.flush();
                //关闭流
                fos.close();
                is.close();
            }
        });
    }
}
