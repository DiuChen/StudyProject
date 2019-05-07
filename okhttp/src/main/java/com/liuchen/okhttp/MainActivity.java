package com.liuchen.okhttp;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.liuchen.okhttp.update.UpdateUtil;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 参考资料:https://www.jianshu.com/p/da4a806e599b
 * https://www.jianshu.com/p/1133389c1f75
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.btn4)
    Button btn4;
    @BindView(R.id.btn5)
    Button btn5;

    private static final String TAG = "MainActivity";
    private static final String URL = "https://wanandroid.com/ ";
    private OkHttpClient okHttpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Log.d(TAG, "onCreate: ");

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("interceptor", UnicodeUtil.unicodeToString(message));
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new MyInterceptor())
                .addInterceptor(interceptor)
                .build();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    /**
     * 异步GET请求
     * 调用Call#enqueue(Callback)方法
     */
    private void btn1() {
        final Request request = new Request.Builder()
                .url(URL + "wxarticle/chapters/json")
                .get()//默认就是GET请求，可以不写
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "onResponse: " + response.body().string());
            }
        });
    }

    /**
     * 同步GET请求
     * 调用Call#execute()方法
     * Android3.0 以后不允许在主线程访问网络
     */
    private void btn2() {
        final Request request = new Request.Builder()
                .url(URL + "wxarticle/chapters/json")
                .build();
        final Call call = okHttpClient.newCall(request);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = call.execute();
                    Log.d(TAG, "run: " + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * POST方式提交String
     */
    private void btn3() {
        MediaType mediaType = MediaType.parse("text/x-markdown; charset=utf-8");
        RequestBody requestBody = RequestBody.create(mediaType, "这是内容");
        MultipartBody.Builder multiBuilder = new MultipartBody.Builder();
        multiBuilder.addFormDataPart("username", "17770843708");
        multiBuilder.addFormDataPart("password", "123456");
        FormBody formBody = new FormBody.Builder()
                .add("username", "17770843708")
                .add("password", "123456")
                .build();
        Request request = new Request.Builder()
                .url(URL + "user/login")
                .post(multiBuilder.build())
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "请求:");
                Log.d(TAG, "请求头:");
                Headers requestHeaders = call.request().headers();
                for (int i = 0; i < requestHeaders.size(); i++) {
                    Log.d(TAG, requestHeaders.name(i) + ":" + requestHeaders.value(i));
                }
                String s;
                Log.d(TAG, "请求头内容:" + call.request().body().contentType().toString());
                Log.d(TAG, "响应:");
                Log.d(TAG, response.protocol() + " " + response.code() + " " + response.message());
                Log.d(TAG, "响应头:");
                Headers responseHeaders = response.headers();
                for (int i = 0; i < responseHeaders.size(); i++) {
                    Log.d(TAG, responseHeaders.name(i) + ":" + responseHeaders.value(i));
                }
                Log.d(TAG, "响应内容:" + response.body().string());
            }
        });
    }

    /**
     * POST提交文件
     */
    private void btn4() {
        MediaType mediaType = MediaType.parse("text/x-markdown; charset=utf-8");
        RequestBody requestBody = RequestBody.create(mediaType, "这是内容");
    }

    private void btn5() {
        final Request request = new Request.Builder()
                .url("https://my-test-1253832037.cos.ap-chengdu.myqcloud.com/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20190416155556.jpg")
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = response.body().byteStream();

                String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/111.jpg";
                OutputStream os = null;
                long contentLength = response.body().contentLength();
                long progress = 0;
                byte data[] = new byte[1024 * 8];
                int len;
                try {
                    os = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
                    while ((len = is.read(data)) != -1) {
                        os.write(data, 0, len);
                        progress += len;
                        Log.d(TAG, "onResponse: " + progress + " " + contentLength);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        if (os != null) {
                            os.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void btn6() {
        new UpdateUtil.Builder()
                .setActivity(this)
                .setUrl("https://send-message-1253832037.cos.ap-chengdu.myqcloud.com/MessageToHer/%E5%B0%8F%E5%8F%AF%E7%88%B1%E7%9A%84%E4%B8%93%E5%B1%9E%E6%97%A5%E5%8E%86.apk")
                .build()
                .start();
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                btn1();
                break;
            case R.id.btn2:
                btn2();
                break;
            case R.id.btn3:
                btn3();
                break;
            case R.id.btn4:
                btn4();
                break;
            case R.id.btn5:
                btn6();
                break;
        }
    }
}
