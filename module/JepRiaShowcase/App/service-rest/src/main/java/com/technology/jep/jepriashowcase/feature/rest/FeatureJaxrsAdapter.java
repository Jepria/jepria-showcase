package com.technology.jep.jepriashowcase.feature.rest;

import com.technology.jep.jepriashowcase.feature.FeatureServerFactory;
import com.technology.jep.jepriashowcase.feature.dto.FeatureCreateDto;
import com.technology.jep.jepriashowcase.feature.dto.FeatureDto;
import com.technology.jep.jepriashowcase.feature.dto.FeatureSearchDto;
import com.technology.jep.jepriashowcase.feature.dto.FeatureUpdateDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
  @Operation(summary = "Set responsible operator for the feature",
      tags = {"other"},
      responses = {
          @ApiResponse(responseCode = "201", description = "Created")
      })
  public Response setFeatureResponsible(@PathParam("featureId") Integer featureId, @QueryParam("responsibleId") Integer responsibleId) {
    serverFactory.getService().setFeatureResponsible(featureId, responsibleId, securityContext.getCredential());
    return Response.ok().build();
  }

  @GET
  @Path("/option/feature-operator")
  @Operation(summary = "Get feature operator options",
      tags = {"dict"},
      responses = {
          @ApiResponse(responseCode = "200", description = "successful operation"
              , content = @Content(schema = @Schema(implementation = OptionDto.class)))
      })
  public Response getFeatureOperator() {
    List<OptionDto<Integer>> result = serverFactory.getService().getFeatureOperator();
    return Response.ok(result).build();
  }

  @GET
  @Path("/option/feature-status")
  @Operation(summary = "Get feature operator options",
      tags = {"dict"},
      responses = {
          @ApiResponse(responseCode = "200", description = "Get feature status options"
              , content = @Content(schema = @Schema(implementation = OptionDto.class)))
      })
  public Response getFeatureStatus() {
    List<OptionDto<String>> result = serverFactory.getService().getFeatureStatus();
    return Response.ok(result).build();
  }

  //------------ entity methods ------------//

  @GET
  @Path("/{recordId}")
  @Operation(summary = "Get record by ID",
      tags = {"feature: entity"},
      responses = {
          @ApiResponse(responseCode = "200", description = "Get feature status options"
              , content = @Content(schema = @Schema(implementation = FeatureDto.class)))
      })
  public Response getRecordById(@Pattern(regexp = "\\d+", message = "ID must be an integer") @PathParam("recordId") String recordId) {
    FeatureDto result = (FeatureDto) entityEndpointAdapter.getRecordById(recordId);
    return Response.ok(result).build();
  }

  @POST
  @Operation(summary = "Create record",
      tags = {"feature: entity"},
      responses = {
          @ApiResponse(responseCode = "201", description = "Create")
      })
  public Response create(FeatureCreateDto record) {
    return entityEndpointAdapter.create(record);
  }

  @Operation(summary = "Delete record by ID",
      tags = {"feature: entity"},
      responses = {
          @ApiResponse(responseCode = "200", description = "successful operation")
      })
  @DELETE
  @Path("/{recordId}")
  public Response deleteRecordById(@Pattern(regexp = "\\d+", message = "ID must be an integer") @PathParam("recordId") String recordId) {
    entityEndpointAdapter.deleteRecordById(recordId);
    return Response.ok().build();
  }

  @PUT
  @Path("/{recordId}")
  @Operation(summary = "Update record",
      tags = {"feature: entity"},
      responses = {
          @ApiResponse(responseCode = "200", description = "successful operation")
      })
  public Response update(@Pattern(regexp = "\\d+", message = "ID must be an integer") @PathParam("recordId") String recordId, FeatureUpdateDto record) {
    entityEndpointAdapter.update(recordId, record);
    return Response.ok().build();
  }

  //------------ search methods ------------//

  @GET
  @Path("/search")
  @Operation(summary = "Search",
      tags = {"feature: search"},
      responses = {
          @ApiResponse(responseCode = "200", description = "successful operation"
              , content = @Content(schema = @Schema(implementation = FeatureDto.class)))
      })
  public Response search(
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
