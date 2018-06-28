package com.gavel;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class EnvinfoWeb {

  private static final String CONTEXT_PATH = "http://113.140.66.227:8111/envinfo_web";

  private static Logger LOG = Logger.getLogger(EnvinfoWeb.class);



  public static void main(String[] args) throws Exception {

    login();

    Map<String, String> devMap = getDevMap("<div class=\"dtree\">\n" +
            "<div class=\"dTreeNode\"><img id=\"id0\" src=\"../envinfo_web/images/img/base.gif\" alt=\"\">废气</div><div id=\"dd0\" class=\"clip\" style=\"display:block;\"><div class=\"dTreeNode\"><img src=\"../envinfo_web/images/img/join.gif\" alt=\"\"><img id=\"id1\" src=\"../envinfo_web/images/img/page.gif\" alt=\"\"><a id=\"sd1\" class=\"node\" href=\"enterprisePgasR.do?op=getDefaultChart&amp;devid=YQ6105810062&amp;flag=1\" target=\"dataframe1\" onclick=\"d.s('1');\">5号高炉炉前除尘燃烧排口</a></div><div class=\"dTreeNode\"><img src=\"../envinfo_web/images/img/join.gif\" alt=\"\"><img id=\"id2\" src=\"../envinfo_web/images/img/page.gif\" alt=\"\"><a id=\"sd2\" class=\"node\" href=\"enterprisePgasR.do?op=getDefaultChart&amp;devid=YQ6105810061&amp;flag=1\" target=\"dataframe1\" onclick=\"d.s('2');\">450烧结除尘燃烧排口</a></div><div class=\"dTreeNode\"><img src=\"../envinfo_web/images/img/join.gif\" alt=\"\"><img id=\"id3\" src=\"../envinfo_web/images/img/page.gif\" alt=\"\"><a id=\"sd3\" class=\"node\" href=\"enterprisePgasR.do?op=getDefaultChart&amp;devid=YQ6105810076&amp;flag=1\" target=\"dataframe1\" onclick=\"d.s('3');\">炼钢新区转炉二次除尘出口</a></div><div class=\"dTreeNode\"><img src=\"../envinfo_web/images/img/join.gif\" alt=\"\"><img id=\"id4\" src=\"../envinfo_web/images/img/page.gif\" alt=\"\"><a id=\"sd4\" class=\"node\" href=\"enterprisePgasR.do?op=getDefaultChart&amp;devid=YQ6105810072&amp;flag=1\" target=\"dataframe1\" onclick=\"d.s('4');\">炼铁1,2号高炉炉后除尘排口</a></div><div class=\"dTreeNode\"><img src=\"../envinfo_web/images/img/join.gif\" alt=\"\"><img id=\"id5\" src=\"../envinfo_web/images/img/page.gif\" alt=\"\"><a id=\"sd5\" class=\"node\" href=\"enterprisePgasR.do?op=getDefaultChart&amp;devid=YQ6105810071&amp;flag=1\" target=\"dataframe1\" onclick=\"d.s('5');\">炼铁3号高炉炉后除尘出口</a></div><div class=\"dTreeNode\"><img src=\"../envinfo_web/images/img/join.gif\" alt=\"\"><img id=\"id6\" src=\"../envinfo_web/images/img/page.gif\" alt=\"\"><a id=\"sd6\" class=\"node\" href=\"enterprisePgasR.do?op=getDefaultChart&amp;devid=YQ6105810067&amp;flag=1\" target=\"dataframe1\" onclick=\"d.s('6');\">265㎡烧结机尾除尘出口</a></div><div class=\"dTreeNode\"><img src=\"../envinfo_web/images/img/join.gif\" alt=\"\"><img id=\"id7\" src=\"../envinfo_web/images/img/page.gif\" alt=\"\"><a id=\"sd7\" class=\"node\" href=\"enterprisePgasR.do?op=getDefaultChart&amp;devid=YQ6105810066&amp;flag=1\" target=\"dataframe1\" onclick=\"d.s('7');\">400㎡烧结机尾除尘出口</a></div><div class=\"dTreeNode\"><img src=\"../envinfo_web/images/img/join.gif\" alt=\"\"><img id=\"id8\" src=\"../envinfo_web/images/img/page.gif\" alt=\"\"><a id=\"sd8\" class=\"node\" href=\"enterprisePgasR.do?op=getDefaultChart&amp;devid=YQ6105810051&amp;flag=1\" target=\"dataframe1\" onclick=\"d.s('8');\">450烧结脱硫出口</a></div><div class=\"dTreeNode\"><img src=\"../envinfo_web/images/img/join.gif\" alt=\"\"><img id=\"id9\" src=\"../envinfo_web/images/img/page.gif\" alt=\"\"><a id=\"sd9\" class=\"node\" href=\"enterprisePgasR.do?op=getDefaultChart&amp;devid=YQ6105810050&amp;flag=1\" target=\"dataframe1\" onclick=\"d.s('9');\">450烧结脱硫入口</a></div><div class=\"dTreeNode\"><img src=\"../envinfo_web/images/img/join.gif\" alt=\"\"><img id=\"id10\" src=\"../envinfo_web/images/img/page.gif\" alt=\"\"><a id=\"sd10\" class=\"node\" href=\"enterprisePgasR.do?op=getDefaultChart&amp;devid=YQ6105810047&amp;flag=1\" target=\"dataframe1\" onclick=\"d.s('10');\">炼铁厂3号高炉热风炉</a></div><div class=\"dTreeNode\"><img src=\"../envinfo_web/images/img/join.gif\" alt=\"\"><img id=\"id11\" src=\"../envinfo_web/images/img/page.gif\" alt=\"\"><a id=\"sd11\" class=\"node\" href=\"enterprisePgasR.do?op=getDefaultChart&amp;devid=YQ6105810046&amp;flag=1\" target=\"dataframe1\" onclick=\"d.s('11');\">炼铁厂4号高炉热风炉</a></div><div class=\"dTreeNode\"><img src=\"../envinfo_web/images/img/join.gif\" alt=\"\"><img id=\"id12\" src=\"../envinfo_web/images/img/page.gif\" alt=\"\"><a id=\"sd12\" class=\"node\" href=\"enterprisePgasR.do?op=getDefaultChart&amp;devid=YQ6105810032&amp;flag=1\" target=\"dataframe1\" onclick=\"d.s('12');\">265烧结烟气脱硫出口</a></div><div class=\"dTreeNode\"><img src=\"../envinfo_web/images/img/join.gif\" alt=\"\"><img id=\"id13\" src=\"../envinfo_web/images/img/page.gif\" alt=\"\"><a id=\"sd13\" class=\"node\" href=\"enterprisePgasR.do?op=getDefaultChart&amp;devid=YQ6105810031&amp;flag=1\" target=\"dataframe1\" onclick=\"d.s('13');\">400烧结烟气脱硫入口</a></div><div class=\"dTreeNode\"><img src=\"../envinfo_web/images/img/join.gif\" alt=\"\"><img id=\"id14\" src=\"../envinfo_web/images/img/page.gif\" alt=\"\"><a id=\"sd14\" class=\"node\" href=\"enterprisePgasR.do?op=getDefaultChart&amp;devid=YQ6105810030&amp;flag=1\" target=\"dataframe1\" onclick=\"d.s('14');\">400烧结烟气脱硫出口</a></div><div class=\"dTreeNode\"><img src=\"../envinfo_web/images/img/join.gif\" alt=\"\"><img id=\"id15\" src=\"../envinfo_web/images/img/page.gif\" alt=\"\"><a id=\"sd15\" class=\"node\" href=\"enterprisePgasR.do?op=getDefaultChart&amp;devid=YQ6105810011&amp;flag=1\" target=\"dataframe1\" onclick=\"d.s('15');\">265烧结烟气脱硫入口</a></div><div class=\"dTreeNode\"><img src=\"../envinfo_web/images/img/join.gif\" alt=\"\"><img id=\"id16\" src=\"../envinfo_web/images/img/page.gif\" alt=\"\"><a id=\"sd16\" class=\"node\" href=\"enterprisePgasR.do?op=getDefaultChart&amp;devid=YQ6105810077&amp;flag=1\" target=\"dataframe1\" onclick=\"d.s('16');\">炼钢老区转炉二次除尘出口</a></div><div class=\"dTreeNode\"><img src=\"../envinfo_web/images/img/join.gif\" alt=\"\"><img id=\"id17\" src=\"../envinfo_web/images/img/page.gif\" alt=\"\"><a id=\"sd17\" class=\"node\" href=\"enterprisePgasR.do?op=getDefaultChart&amp;devid=YQ6105810075&amp;flag=1\" target=\"dataframe1\" onclick=\"d.s('17');\">炼铁1#高炉炉后除尘出口</a></div><div class=\"dTreeNode\"><img src=\"../envinfo_web/images/img/join.gif\" alt=\"\"><img id=\"id18\" src=\"../envinfo_web/images/img/page.gif\" alt=\"\"><a id=\"sd18\" class=\"node\" href=\"enterprisePgasR.do?op=getDefaultChart&amp;devid=YQ6105810070&amp;flag=1\" target=\"dataframe1\" onclick=\"d.s('18');\">炼铁4号高炉炉后除尘出口</a></div><div class=\"dTreeNode\"><img src=\"../envinfo_web/images/img/join.gif\" alt=\"\"><img id=\"id19\" src=\"../envinfo_web/images/img/page.gif\" alt=\"\"><a id=\"sd19\" class=\"node\" href=\"enterprisePgasR.do?op=getDefaultChart&amp;devid=YQ6105810069&amp;flag=1\" target=\"dataframe1\" onclick=\"d.s('19');\">炼铁2号高炉炉前除尘出口</a></div><div class=\"dTreeNode\"><img src=\"../envinfo_web/images/img/join.gif\" alt=\"\"><img id=\"id20\" src=\"../envinfo_web/images/img/page.gif\" alt=\"\"><a id=\"sd20\" class=\"node\" href=\"enterprisePgasR.do?op=getDefaultChart&amp;devid=YQ6105810068&amp;flag=1\" target=\"dataframe1\" onclick=\"d.s('20');\">炼铁1号高炉炉前除尘出口</a></div><div class=\"dTreeNode\"><img src=\"../envinfo_web/images/img/join.gif\" alt=\"\"><img id=\"id21\" src=\"../envinfo_web/images/img/page.gif\" alt=\"\"><a id=\"sd21\" class=\"node\" href=\"enterprisePgasR.do?op=getDefaultChart&amp;devid=YQ6105810065&amp;flag=1\" target=\"dataframe1\" onclick=\"d.s('21');\">炼铁4号高炉炉前除尘出口</a></div><div class=\"dTreeNode\"><img src=\"../envinfo_web/images/img/join.gif\" alt=\"\"><img id=\"id22\" src=\"../envinfo_web/images/img/page.gif\" alt=\"\"><a id=\"sd22\" class=\"node\" href=\"enterprisePgasR.do?op=getDefaultChart&amp;devid=YQ6105810064&amp;flag=1\" target=\"dataframe1\" onclick=\"d.s('22');\">炼铁3号高炉炉前除尘出口</a></div><div class=\"dTreeNode\"><img src=\"../envinfo_web/images/img/joinbottom.gif\" alt=\"\"><img id=\"id23\" src=\"../envinfo_web/images/img/page.gif\" alt=\"\"><a id=\"sd23\" class=\"node\" href=\"enterprisePgasR.do?op=getDefaultChart&amp;devid=YQ6105810063&amp;flag=1\" target=\"dataframe1\" onclick=\"d.s('23');\">5号高炉炉后除尘燃烧排口</a></div></div></div>");
    if ( devMap!=null && devMap.size()>0 ){
      for (String name : devMap.keySet()) {
        Map<String, String> params = queryParamsParser(devMap.get(name));
        loadData(name, params.get("devid"));
        System.out.println("");
      }
    }

  }

  private static Map<String, String> getDevMap(String html) {

    Map<String, String> devMap = new HashMap<String, String>();

    Document document = Jsoup.parse(html);

    String name = "";
    Element root = document.select("div.dTreeNode").first();
    if ( root!=null ){
      name = root.text();
    }

    LOG.info(name);
    Elements wsListlinks = document.select("a.node");
    for (Element link : wsListlinks) {
      devMap.put(name + "-" + link.text(), link.attr("href"));
      LOG.info("\t" + name + "-" + link.text() + " ==> " + link.attr("href"));
    }
    return devMap;
  }

  /**
   * 解析query参数名称和值
   * @param uri
   * @return
   */
  public static Map<String, String> queryParamsParser(String uri){
    if (StringUtils.isBlank(uri)){
      return Collections.EMPTY_MAP;
    }

    String queryString = uri;
    if ( uri.contains("?") ){
      queryString = StringUtils.substringAfter(uri, "?");
    }
    LOG.info(queryString);
    if (StringUtils.isBlank(queryString)){
      return Collections.EMPTY_MAP;
    }

    String[] params = queryString.split("&");
    if ( params==null || params.length==0 ){
      return Collections.EMPTY_MAP;
    }

    Map<String, String> paramMap = new HashMap<String, String>();
    for (String param : params) {
      if ( !param.contains("=") ){
        LOG.info("Param: " + param + ": 格式不正确");
        continue;
      }
      String[] values = param.split("=");
      if ( values==null || values.length<1 || values.length>2 ){
        LOG.info("Param: " + param + ": 格式不正确");
        continue;
      }
      paramMap.put(values[0], values.length==1?"":values[1]);
    }

    LOG.info(paramMap);

    //enterprisePgasR.do?op=getDefaultChart&amp;devid=YQ6105810065&amp;flag=1

    return paramMap;
  }

  /**
   * 获取数据
   */
  public static void loadData(String name, String devid) throws Exception {
    Map<String, String> formData = new HashMap<String, String>();
    formData.put("op", "toList");
    formData.put("devid", devid);
    formData.put("pageResult.pageNo", "1");
    formData.put("pageResult.orderBy", "");
    formData.put("pageResult.sort", "asc");
    formData.put("pageResult.pageSize", "150");


    LOG.info("Name: " + name + ": Devid: " + devid);

    String html = Http.post(CONTEXT_PATH + "/enterprisePgasR.do", formData);
    FileUtils.write(new File(name + "-" + devid + "1.html"), html, "UTF-8");

    StringBuilder content = new StringBuilder();
    Document doc = Jsoup.parse(html);
    Elements links = doc.select("table.table tr");
    Elements tds = links.get(0).select("td");
    for (Element td : tds) {
      String text = td.text();
      content.append(org.apache.commons.lang3.StringUtils.trimToEmpty(text)).append(", ");
    }
    content.append("\n");

    for (int i = 1; i < links.size(); i++) {
      Element tr = links.get(i);
      tds = tr.select("td");
      for (Element td : tds) {
        content.append(org.apache.commons.lang3.StringUtils.trimToEmpty(td.text())).append(", ");
      }
      content.append("\n");
    }

    LOG.info("数据: \n" + content.toString());
    FileUtils.write(new File(name + "-" + devid), content, "UTF-8", true);

  }

  /**
   * 系统登录
   * @throws Exception
   */
  public static boolean login() throws Exception {

    Map<String, String> formData = new HashMap<String, String>();
    formData.put("op", "login");
    formData.put("item.usrName", "陕西龙门钢铁有限责任公司");
    formData.put("item.usrPassword", "5182359");

    String responseText = Http.post(CONTEXT_PATH + "/loginAndRegister.do", formData);

    return StringUtils.contains(responseText, "导航菜单");
  }

}
