import okhttp3.*;
import okhttp3.OkHttpClient.Builder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WebPage {

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
    String html = query();

    Document doc = Jsoup.parse(html);

      Elements links = doc.select("p, b");
      for (Element link : links) {
          String text = link.html();
          if ( "&nbsp;".equalsIgnoreCase(text) || "".equalsIgnoreCase(text) ){
              continue;
          }
          if ( link.tagName().equals("p") ){
              System.out.print("\t");
          }
          System.out.println(link.html());
      }
  }

  public static String query() throws Exception {

    Request request = new Request.Builder().url("http://www.citoni.com:801/test.htm")
        .build();

    Response response = client.newCall(request).execute();
    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

    String _html = response.body().string();

    System.out.println(_html);

    return _html;

  }
}
