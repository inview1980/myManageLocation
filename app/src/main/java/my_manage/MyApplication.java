package my_manage;

import android.app.Application;
import android.content.Intent;

//import com.lzy.okgo.OkGo;
//import com.lzy.okgo.cookie.CookieJarImpl;
//import com.lzy.okgo.cookie.store.MemoryCookieStore;
//import com.lzy.okgo.interceptor.HttpLoggingInterceptor;

import my_manage.tool.PageUtils;
import my_manage.tool.database.DbBase;
import my_manage.password_box.R;
import my_manage.ui.rent_manage.MyService;
//import okhttp3.OkHttpClient;

/**
 * @author inview
 * @Date 2020/7/2 8:17
 * @Description :
 */
public class MyApplication extends Application {
    public static String   DBFilePath;
//    public static String ConnectionUrl="http://172.16.205.81:8080";

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化OkGo配置
//        initOkGo();
        //初始化数据库
        DBFilePath = getFilesDir().getAbsolutePath() + "/" + getString(R.string.rentalFileName);
        DbBase.createCascadeDB(this, DBFilePath);
        //初始化系统参数
        PageUtils.isApkInDebug(this);
        //读取配置文件
//        readConfig(getExternalFilesDir(null).getAbsolutePath() + "/" + getString(R.string.configFile));
        //开启服务
        Intent intent = new Intent(this, MyService.class);
        intent.putExtra("path", DBFilePath);
        startService(intent);
    }

//    @SuppressLint("CommitPrefEdits")
//    private void readConfig(String path) {
//        try{
//            SharedPreferences sps =getSharedPreferences(path, Context.MODE_PRIVATE);
//            if(!new File(path).exists()){
//                //配置文件不存在
//                sps.edit().putString("ConnectionUrl",ConnectionUrl);
//                sps.edit().apply();
//            }else {
//                //读取配置文件的内容
//                ConnectionUrl=sps.getString("ConnectionUrl",ConnectionUrl);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


//    private void initOkGo() {
//        //---------这里给出的是示例代码,告诉你可以这么传,实际使用的时候,根据需要传,不需要就不传-------------//
////        HttpHeaders headers = new HttpHeaders();
////        headers.put("commonHeaderKey1", "commonHeaderValue1");    //header不支持中文，不允许有特殊字符
////        headers.put("commonHeaderKey2", "commonHeaderValue2");
////        HttpParams params = new HttpParams();
////        params.put("commonParamsKey1", "commonParamsValue1");     //param支持中文,直接传,不要自己编码
////        params.put("commonParamsKey2", "这里支持中文参数");
//        //----------------------------------------------------------------------------------------//
//
//        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        //log相关
//        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
//        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);        //log打印级别，决定了log显示的详细程度
//        loggingInterceptor.setColorLevel(Level.INFO);                               //log颜色级别，决定了log在控制台显示的颜色
//        builder.addInterceptor(loggingInterceptor);                                 //添加OkGo默认debug日志
//        //第三方的开源库，使用通知显示当前请求的log，不过在做文件下载的时候，这个库好像有问题，对文件判断不准确
//        //builder.addInterceptor(new ChuckInterceptor(this));
//
//        //超时时间设置，默认60秒
////        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);      //全局的读取超时时间
////        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);     //全局的写入超时时间
//        builder.connectTimeout(1000, TimeUnit.MILLISECONDS);   //全局的连接超时时间
//
//        //自动管理cookie（或者叫session的保持），以下几种任选其一就行
//        //builder.cookieJar(new CookieJarImpl(new SPCookieStore(this)));            //使用sp保持cookie，如果cookie不过期，则一直有效
////        builder.cookieJar(new CookieJarImpl(new DBCookieStore(this)));              //使用数据库保持cookie，如果cookie不过期，则一直有效
//        builder.cookieJar(new CookieJarImpl(new MemoryCookieStore()));            //使用内存保持cookie，app退出后，cookie消失
//
//        //https相关设置，以下几种方案根据需要自己设置
//        //方法一：信任所有证书,不安全有风险
////        HttpsUtils.SSLParams sslParams1 = HttpsUtils.getSslSocketFactory();
//        //方法二：自定义信任规则，校验服务端证书
////        HttpsUtils.SSLParams sslParams2 = HttpsUtils.getSslSocketFactory(new SafeTrustManager());
//        //方法三：使用预埋证书，校验服务端证书（自签名证书）
//        //HttpsUtils.SSLParams sslParams3 = HttpsUtils.getSslSocketFactory(getAssets().open("srca.cer"));
//        //方法四：使用bks证书和密码管理客户端证书（双向认证），使用预埋证书，校验服务端证书（自签名证书）
//        //HttpsUtils.SSLParams sslParams4 = HttpsUtils.getSslSocketFactory(getAssets().open("xxx.bks"), "123456", getAssets().open("yyy.cer"));
////        builder.sslSocketFactory(sslParams1.sSLSocketFactory, sslParams1.trustManager);
//        //配置https的域名匹配规则，详细看demo的初始化介绍，不需要就不要加入，使用不当会导致https握手失败
////        builder.hostnameVerifier(new SafeHostnameVerifier());
//
//        // 其他统一的配置
//        // 详细说明看GitHub文档：https://github.com/jeasonlzy/
//        OkGo.getInstance().init(this)                           //必须调用初始化
//                .setOkHttpClient(builder.build())               //建议设置OkHttpClient，不设置会使用默认的
////                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
////                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
//                .setRetryCount(1);                               //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
////                .addCommonHeaders(headers)                      //全局公共头
////                .addCommonParams(params);                       //全局公共参数
//    }
}
