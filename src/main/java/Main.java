import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Main {

  private static final OkHttpClient client = new Builder()
      .cookieJar(new CookieJar() {
        private final List<Cookie> _cookies = new ArrayList<Cookie>();
        private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<HttpUrl, List<Cookie>>();

        @Override
        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
          System.out.println("cookies: " + cookies.size());
          for (Cookie cookie : cookies) {
            System.out.println(cookie.name() + ": " + cookie.value());
          }
          cookieStore.put(url, cookies);
          _cookies.clear();
          _cookies.addAll(cookies);
        }

        @Override
        public List<Cookie> loadForRequest(HttpUrl url) {
//          List<Cookie> cookies = cookieStore.get(url);
//          return cookies != null ? cookies : new ArrayList<Cookie>();

          return _cookies;
        }
      })
      .connectTimeout(10, TimeUnit.SECONDS)
      .readTimeout(60, TimeUnit.SECONDS)
      .build();

  private static final String IMGUR_CLIENT_ID = "...";
  //提交的数据类型
  private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

  public static void main(String[] args) throws Exception {
    login();

    query();


    //20180517\王敏-141w线路派班模板.xlsx
    for (File file : FileUtils.loadFiles("E:\\Document\\公交集团考勤\\data")) {
      System.out.println(file.getName());
      upload(file);
    }
  }

  public static void query() throws Exception {

    String postBody = "{\"kqz\":null,\"usercx\":\"\",\"pageNumber\":1,\"pageSize\":20}";
    MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");
    Request request = new Request.Builder().url("http://localhost:9080/attend/jcsj/kqry/query")
        .post(RequestBody.create(MEDIA_TYPE_JSON, postBody))
        .build();

    Response response = client.newCall(request).execute();
    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

    System.out.println(response.body().string());

  }

  public static void login() throws Exception {

    String postBody = "{\"userid\":\"superuser\",\"password\":\"db17efa69d8dbd71c9b70d64d5fbde48\"}";
    MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("application/json; charset=utf-8");
    Request request = new Request.Builder().url("http://localhost:9080/attend/login")
        .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, postBody))
        .build();

    Response response = client.newCall(request).execute();
    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

    System.out.println(response.body().string());

  }


  //E:\Document\公交集团考勤\20180502\于静江-车组车次对照.xls

  public static void upload(File file) throws Exception{

    RequestBody fileBody = RequestBody.create(MediaType.parse("application/vnd.ms-excel"), file);

    RequestBody requestBody = new MultipartBody.Builder()
        .setType(MultipartBody.FORM)
///                .addPart(
//                        Headers.of("Content-Disposition", "form-data; name=\"file\"; filename=\"" + fileName + "\""),
//                        RequestBody.create(MEDIA_TYPE_PNG, file))
//                .addPart(
//                        Headers.of("Content-Disposition", "form-data; name=\"imagetype\""),
//                        RequestBody.create(null, imageType))
//                .addPart(
//                        Headers.of("Content-Disposition", "form-data; name=\"userphone\""),
//                        RequestBody.create(null, userPhone))

        .addFormDataPart("file", file.getParentFile().getName() + "_"  + file.getName(), fileBody)
        .addFormDataPart("tradedate", "2018-05-31")
        .build();

    String url = "http://localhost:9080/attend/kqgl/kqsj/import";
    Request request = new Request.Builder().url(url).post(requestBody).build();


    Response response = client.newCall(request).execute();
    String jsonString = response.body().string();

    System.out.println("Response: " + jsonString);

  }
}
