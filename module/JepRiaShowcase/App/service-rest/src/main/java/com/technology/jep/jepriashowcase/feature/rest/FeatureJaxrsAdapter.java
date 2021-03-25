package com.technology.jep.jepriashowcase.feature.rest;

import com.technology.jep.jepriashowcase.feature.FeatureServerFactory;
import com.technology.jep.jepriashowcase.feature.dto.FeatureCreateDto;
import com.technology.jep.jepriashowcase.feature.dto.FeatureDto;
import com.technology.jep.jepriashowcase.feature.dto.FeatureSearchDto;
import com.technology.jep.jepriashowcase.feature.dto.FeatureUpdateDto;
import org.jepria.server.data.ColumnSortConfigurationDto;
import org.jepria.server.data.OptionDto;
import org.jepria.server.data.SearchResultDto;
import org.jepria.server.service.rest.JaxrsAdapterBase;
import org.jepria.server.service.security.oauth.OAuth;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/feature")
@OAuth
@RolesAllowed({"JrsEditFeature", "JrsAssignResponsibleFeature", "JrsEditAllFeature"})
public class FeatureJaxrsAdapter extends JaxrsAdapterBase {

  protected final FeatureServerFactory serverFactory;
  protected final EntityEndpointAdapter entityEndpointAdapter;
  protected final SearchEndpointAdapter searchEndpointAdapter;
  
  // Inject делаем в конструктор
  @Inject
  public FeatureJaxrsAdapter(FeatureServerFactory serverFactory) {
    this.serverFactory = serverFactory;
    entityEndpointAdapter = new EntityEndpointAdapter(() -> this.serverFactory.getEntityService());
    searchEndpointAdapter = new SearchEndpointAdapter(() -> this.serverFactory.getSearchService(() -> request.getSession()));
  }

//------------ application-specific methods ------------//

  @POST
  @Path("/{featureId}/set-feature-responsible")
  public Response setFeatureResponsible(@PathParam("featureId") Integer featureId, @QueryParam("responsibleId") Integer responsibleId) {
    serverFactory.getService().setFeatureResponsible(featureId, responsibleId, securityContext.getCredential());
    return Response.ok().build();
  }

  @GET
  @Path("/option/feature-operator")
  public Response getFeatureOperator() {
    List<OptionDto<Integer>> result = serverFactory.getService().getFeatureOperator();
    return Response.ok(result).build();
  }

  @GET
  @Path("/option/feature-status")
  public Response getFeatureStatus() {
    List<OptionDto<String>> result = serverFactory.getService().getFeatureStatus();
    return Response.ok(result).build();
  }

  //------------ entity methods ------------//

  @GET
  @Path("/{recordId}")
  public Response getRecordById(@Pattern(regexp = "\\d+", message = "ID must be an integer") @PathParam("recordId") String recordId) {
    FeatureDto result = (FeatureDto) entityEndpointAdapter.getRecordById(recordId);
    return Response.ok(result).build();
  }

  @POST
  public Response create(FeatureCreateDto record) {
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
  public Response update(@Pattern(regexp = "\\d+", message = "ID must be an integer") @PathParam("recordId") String recordId, FeatureUpdateDto record) {
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
    SearchResultDto<FeatureDto> result = searchEndpointAdapter.search(pageSize, page, sortConfiguration, searchRequestDto, cacheControl); 
    return Response.ok(result).build();
  }
  
}
