package com.technology.jep.jepriashowcase.goods.rest;

import com.technology.jep.jepriashowcase.feature.dto.FeatureDto;
import com.technology.jep.jepriashowcase.feature.dto.FeatureSearchDto;
import com.technology.jep.jepriashowcase.goods.GoodsServerFactory;
import com.technology.jep.jepriashowcase.goods.GoodsServerFactoryImpl;
import com.technology.jep.jepriashowcase.goods.dto.GoodsCreateDto;
import com.technology.jep.jepriashowcase.goods.dto.GoodsDto;
import com.technology.jep.jepriashowcase.goods.dto.GoodsSearchDto;
import com.technology.jep.jepriashowcase.goods.dto.GoodsUpdateDto;
import org.jepria.server.data.ColumnSortConfigurationDto;
import org.jepria.server.data.SearchRequestDto;
import org.jepria.server.data.SearchResultDto;
import org.jepria.server.service.rest.ExtendedResponse;
import org.jepria.server.service.rest.JaxrsAdapterBase;
import org.jepria.server.service.security.HttpBasic;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/goods")
@HttpBasic(passwordType = HttpBasic.PASSWORD)
@RolesAllowed({"JrsEditGoods"})
public class GoodsJaxrsAdapter extends JaxrsAdapterBase {

  protected final GoodsServerFactory serverFactory;
  protected final EntityEndpointAdapter entityEndpointAdapter;
  protected final SearchEndpointAdapter searchEndpointAdapter;
  
  @Inject
  public GoodsJaxrsAdapter(GoodsServerFactory serverFactory) {
    this.serverFactory = serverFactory;
    entityEndpointAdapter = new EntityEndpointAdapter(() -> this.serverFactory.getEntityService());
    searchEndpointAdapter = new SearchEndpointAdapter(() -> this.serverFactory.getSearchService(() -> request.getSession()));
  }


//------------ application-specific methods ------------//


//  @GET
//  @Path("/option/Goods-status")
//  public Response getGoodsStatus() {
////    List<OptionDto<String>> result = serverFactory.getService().getGoodsStatus();
////    return Response.ok(result).build();
//  }

  //------------ entity methods ------------//

  @GET
  @Path("/{recordId}")
  public Response getRecordById(@Pattern(regexp = "\\d+", message = "ID must be an integer") @PathParam("recordId") String recordId) {
    GoodsDto result = (GoodsDto) entityEndpointAdapter.getRecordById(recordId);
    return Response.ok(result).build();
  }

  @POST
  public Response create(GoodsCreateDto record) {
    return entityEndpointAdapter.create(record);
  }

  @DELETE
  @Path("/{recordId}")
  public Response deleteRecordById(@Pattern(regexp = "\\d+", message = "ID must be an integer") @PathParam("recordId") String recordId) {
    entityEndpointAdapter.deleteRecordById(recordId);
    return Response.ok().build();
  }

  @PUT
  @Path("/{recordId}")
  public Response update(@Pattern(regexp = "\\d+", message = "ID must be an integer") @PathParam("recordId") String recordId, GoodsUpdateDto record) {
    entityEndpointAdapter.update(recordId, record);
    return Response.ok().build();
  }

  //------------ search methods ------------//

  @GET
  @Path("/search")
  public Response searchSimple(
          @QueryParam("pageSize") Integer pageSize,
          @QueryParam("page") Integer page,
          @QueryParam("sort") List<ColumnSortConfigurationDto> sortConfiguration,
          @BeanParam FeatureSearchDto searchRequestDto,
          @HeaderParam("Cache-Control") String cacheControl
  ) {
    SearchResultDto<GoodsDto> result = searchEndpointAdapter.search(pageSize, page, sortConfiguration, searchRequestDto, cacheControl);
    return Response.ok(result).build();
  }
  
}
