# AndroidTool
# 引入
project's build.gradle
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
Add the dependency
```
dependencies {
	        implementation 'com.github.q7938182:AndroidTool:v1.4'
	}
```
# 网络请求
Android okHttp网络请求之Retrofit+Okhttp+RxJava组合  https://www.cnblogs.com/whoislcj/p/5539239.html

使用了Lambda表达式 所以项目中需要 https://www.cnblogs.com/rainboy2010/p/6476076.html

### 使用
* 根据需求配置 Retrofit实例
```
import android.content.Context;
import com.readystatesoftware.chuck.ChuckInterceptor;
import com.yfc.androidu.HttpConnection;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

public class AppConnection {
    private Context context;
    private static AppConnection INSTANCE;
    private HttpConnection httpConnection;
    private static Retrofit retrofit;

    public static final String APP_URL = "url地址";

    private static void initInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new AppConnection();
        }
        INSTANCE.initConnection(context);
    }

    /**
     * 获取配置后的Retrofit实例
     */
    public static Retrofit getClient(Context context) {
        initInstance(context);
        return retrofit;
    }

    private void initConnection(Context context) {
        this.context = context;
        if (httpConnection == null) httpConnection = new HttpConnection();
        //配置网络请求头
        HeaderInterceptor headerInterceptor = new HeaderInterceptor();
        /* 状态栏日志 */
        Interceptor chuckInterceptor = new ChuckInterceptor(context);
        /* Logcat */
        Interceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client;
        //测试实例
        client = httpConnection.getUnsafeClient(headerInterceptor, loggingInterceptor, chuckInterceptor);
        //线上实例
//        client = httpConnection.getUnsafeClient(headerInterceptor);

        retrofit = httpConnection.getRetrofit(client, APP_URL);
    }


    private class HeaderInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request.Builder requestBuilder = chain.request().newBuilder();
            requestBuilder.addHeader("Content-Type", "application/x-www-form-urlencoded");
            requestBuilder.addHeader("Client-Type", "Android");
            Request request = requestBuilder.build();
            return chain.proceed(request);
        }
    }
}
```
* 根据需求编写的服务接口类
```
import io.reactivex.Observable;
import retrofit2.http.POST;
public interface AppService {
    
    @POST("请求接口")
    Observable<返回值实体> 接口名();
}
```
* 请求帮助类
```
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.yfc.androidu.RxExtension;

import io.reactivex.Observable;

public class AppRequestHelper {

    private static AppService getAppService(RxAppCompatActivity activity) {
        return AppConnection.getClient(activity).create(AppService.class);
    }


    public static Observable<返回值实体> 方法名(RxAppCompatActivity activity) {
        return getAppService(activity).接口名().compose(RxExtension.applys(activity));
    }

}
```
* 使用请求地方调用
```
AppRequestHelper.方法名(MainActivity.this)
                .subscribe(返回值 -> {
                  处置返回值
                }, throwable -> throwable.printStackTrace());

    }
```


# StringHelper类
处理基本字符串

# DensityHelper类
单位转换的辅助类

# EditHelper类
输入帮助类

# SharePreferencesHelper类
SharedPreferences 帮助类

# 四级联动地址选择
使用：
```
 x.view().inject(MainActivity.this);
 SelectAddressPop pop = new SelectAddressPop();
 pop.setAddressName(省, 市, 区, 街);
 pop.show(getFragmentManager(), "address");
 pop.setOnSelectAddress(new SelectAddressPop.SelectAddressFinish() {
   @Override
   public void finish(String provinceCode, String cityCode, String areaCode, String townCode) {
      String[] strings = AddressManager.newInstance(MainActivity.this).getAddressName(provinceCode, cityCode, areaCode, townCode);
                        省 = strings[0];
                        市 = strings[1];
                        区 = strings[2];
                        街 = strings[3];
                    }
                });
```
# 卡片布局框架
https://blog.csdn.net/javacainiao931121/article/details/51720807 使用方法 （已集成）

# 图片处理
https://github.com/jeasonlzy/ImagePicker
