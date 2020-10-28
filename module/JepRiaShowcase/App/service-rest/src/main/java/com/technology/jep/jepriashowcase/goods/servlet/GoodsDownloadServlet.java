package com.technology.jep.jepriashowcase.goods.servlet;

import org.jepria.compat.server.download.DownloadServlet;

import java.util.HashMap;

import static com.technology.jep.jepriashowcase.goods.dao.GoodsFieldNames.GOODS_PHOTO;
import static com.technology.jep.jepriashowcase.goods.dao.GoodsFieldNames.GOODS_PORTFOLIO;
import static org.jepria.compat.shared.JepRiaConstant.BINARY_FILE;

public class GoodsDownloadServlet extends DownloadServlet {

  public GoodsDownloadServlet() {
    super("jrs_goods", new HashMap<String, String>(){{
      put(GOODS_PHOTO, GOODS_PHOTO);
      put(GOODS_PORTFOLIO, GOODS_PORTFOLIO);
    }}, new HashMap<String, String>(){{
      put(GOODS_PHOTO, BINARY_FILE);
      put(GOODS_PORTFOLIO, BINARY_FILE);
    }}, "jdbc/ITMDS");
  }
}
