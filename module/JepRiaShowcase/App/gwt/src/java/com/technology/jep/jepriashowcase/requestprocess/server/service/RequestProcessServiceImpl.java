package com.technology.jep.jepriashowcase.requestprocess.server.service;
 
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.technology.jep.jepria.server.service.JepDataServiceServlet;
import com.technology.jep.jepriashowcase.requestprocess.server.RequestProcessServerFactory;
import com.technology.jep.jepriashowcase.requestprocess.server.dao.RequestProcess;
import com.technology.jep.jepriashowcase.requestprocess.shared.record.RequestProcessRecordDefinition;
import com.technology.jep.jepriashowcase.requestprocess.shared.service.RequestProcessService;
 
@RemoteServiceRelativePath("ProtectedServices/RequestProcessService")
public class RequestProcessServiceImpl extends JepDataServiceServlet<RequestProcess> implements RequestProcessService  {
 
  private static final long serialVersionUID = 1L;
 
  public RequestProcessServiceImpl() {
    super(RequestProcessRecordDefinition.instance, RequestProcessServerFactory.instance);
  }
}
