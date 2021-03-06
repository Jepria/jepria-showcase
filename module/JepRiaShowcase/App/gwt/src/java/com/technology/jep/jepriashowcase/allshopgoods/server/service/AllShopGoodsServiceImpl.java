package com.technology.jep.jepriashowcase.allshopgoods.server.service;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.technology.jep.jepria.server.service.JepDataServiceServlet;
import com.technology.jep.jepriashowcase.allshopgoods.server.AllShopGoodsServerFactory;
import com.technology.jep.jepriashowcase.allshopgoods.server.dao.AllShopGoods;
import com.technology.jep.jepriashowcase.allshopgoods.shared.record.AllShopGoodsRecordDefinition;
import com.technology.jep.jepriashowcase.allshopgoods.shared.service.AllShopGoodsService;
 
@RemoteServiceRelativePath("ProtectedServices/AllShopGoodsService")
public class AllShopGoodsServiceImpl extends JepDataServiceServlet<AllShopGoods> implements AllShopGoodsService  {
 
  private static final long serialVersionUID = 1L;
 
  public AllShopGoodsServiceImpl() {
    super(AllShopGoodsRecordDefinition.instance, AllShopGoodsServerFactory.instance);
  }
}
