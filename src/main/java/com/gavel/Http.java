package com.gavel;

import okhttp3.*;
import okhttp3.OkHttpClient.Builder;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Http {

  private static Logger LOG = Logger.getLogger(Http.class);

  private static final OkHttpClient client = new Builder()
      .cookieJar(new CookieJar() {
        private final List<Cookie> _cookies = new ArrayList<Cookie>();
        private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<HttpUrl, List<Cookie>>();

        @Override
        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
          LOG.info("cookies: " + cookies.size());
          for (Cookie cookie : cookies) {
            LOG.info(cookie.name() + ": " + cookie.value());
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



  public static String get(String url) throws Exception {

    Request request = new Request.Builder().url(url).build();
    Response response = client.newCall(request).execute();
    LOG.info("Status Code: " + response.code() + ": " + url);
    if (!response.isSuccessful())
      throw new IOException("Unexpected code " + response);
    return response.body().string();
  }

  public static String post(String url, Map<String, String> params) throws Exception {
    FormBody.Builder builder = new FormBody.Builder();
    if ( params!=null && params.size()>0){
      for (String name : params.keySet()) {
        builder.add(name, params.get(name));
      }
    }

    Request request = new Request.Builder()
                          .url(url).addHeader("Content-Type", "application/x-www-form-urlencoded")
                          .post(builder.build()).build();
    Response response = client.newCall(request).execute();
    LOG.info("Status Code: " + response.code() + ": " + url);
    if (!response.isSuccessful()) {
      throw new IOException("Unexpected code " + response);
    }
    return response.body().string();
  }



}
