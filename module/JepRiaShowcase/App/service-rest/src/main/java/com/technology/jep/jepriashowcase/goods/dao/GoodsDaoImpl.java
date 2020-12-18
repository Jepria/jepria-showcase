package com.technology.jep.jepriashowcase.goods.dao;

import com.technology.jep.jepriashowcase.feature.FeatureFieldNames;
import com.technology.jep.jepriashowcase.goods.dto.*;
import org.jepria.compat.server.dao.ResultSetMapper;
import org.jepria.server.data.DaoSupport;
import org.jepria.server.data.OptionDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import static com.technology.jep.jepriashowcase.goods.dao.GoodsFieldNames.*;

public class GoodsDaoImpl implements GoodsDao {

  private static final String searchSqlQuery =
      "begin  "
          + "? := pkg_jepriashowcase.findGoods("
          + "goodsIdList => ? "
          + ", supplierId => ? "
          + ", goodsName => ? "
          + ", goodsTypeCode => ? "
          + ", goodsSegmentCodeList => ? "
          + ", goodsCatalogIdList => ? "
          + ", maxRowCount => ? "
          + ", operatorId => ? "
          + ");"
          + " end;";

  protected class GoodsResultSetMapper extends ResultSetMapper<GoodsDto> {
    @Override
    public void map(ResultSet rs, GoodsDto record) throws SQLException {
      Integer goodsId = getInteger(rs, GOODS_ID);
      record.setGoodsId(goodsId);
      record.setSupplierId(getInteger(rs, SUPPLIER_ID));
      record.setGoodsName(rs.getString(GOODS_NAME));

      OptionDto<String> goodsTypeCode = new OptionDto<>();
      goodsTypeCode.setName(rs.getString(GOODS_TYPE_NAME));
      goodsTypeCode.setValue(rs.getString(GOODS_TYPE_CODE));
      record.setGoodsTypeCode(goodsTypeCode);

      OptionDto<String> unitCode = new OptionDto<>();
      unitCode.setName(rs.getString(UNIT_CODE));
      unitCode.setValue(rs.getString(UNIT_NAME));
      record.setUnitCode(unitCode);

      OptionDto<String> motivationTypeCode = new OptionDto<>();
      motivationTypeCode.setName(rs.getString(MOTIVATION_TYPE_NAME));
      motivationTypeCode.setValue(rs.getString(MOTIVATION_TYPE_CODE));
      record.setMotivationTypeCode(motivationTypeCode);

      record.setPurchasingPrice(rs.getBigDecimal(PURCHASING_PRICE));

      GoodsDto.File goodsPhoto = new GoodsDto.File();
      goodsPhoto.setExtension(rs.getString(GOODS_PHOTO_EXTENSION));
      goodsPhoto.setMimeType(rs.getString(GOODS_PHOTO_MIME_TYPE));
      record.setGoodsPhoto(goodsPhoto);

      GoodsDto.File goodsPortfolio = new GoodsDto.File();
      goodsPortfolio.setExtension(rs.getString(GOODS_PORTFOLIO_EXTENSION));
      goodsPortfolio.setMimeType(rs.getString(GOODS_PORTFOLIO_MIME_TYPE));
      record.setGoodsPortfolio(goodsPortfolio);

      List<GoodsTreeNodeDto> expandedNodes = new ArrayList<>();
      List<GoodsTreeNodeDto> checkedNodes = new ArrayList<>();
      for (TreeNode node : getExpandedNodesForGoodsCatalog(null, goodsId)) {
        if (node.expanded) {
          expandedNodes.add(node.node);
        }
        if (node.checked) {
          checkedNodes.add(node.node);
        }
      }
      record.setGoodsCatalogIdListExpanded(expandedNodes);
      record.setGoodsCatalogIdListChecked(checkedNodes);
    }
  }

  @Override
  public List<GoodsDto> find(Object template, Integer operatorId) {

    GoodsSearchDto searchDto = (GoodsSearchDto) template;

    final List<GoodsDto> records;

    records = DaoSupport.getInstance().find(searchSqlQuery,
        new GoodsDaoImpl.GoodsResultSetMapper()
        , GoodsDto.class
        , searchDto.getGoodsId()
        , searchDto.getSupplierId()
        , searchDto.getGoodsName()
        , searchDto.getGoodsTypeCode()
        , searchDto.getGoodsSegmentCode() != null ? searchDto.getGoodsSegmentCode().stream().collect(Collectors.joining(",")) : null
        , searchDto.getGoodsCatalogIdList() != null ? searchDto.getGoodsCatalogIdList().stream().map(id -> id.toString()).collect(Collectors.joining(",")) : null
        , searchDto.getMaxRowCount()
        , operatorId);

    return records;
  }

  @Override
  public List<?> findByPrimaryKey(Map<String, ?> primaryKeyMap, Integer operatorId) {

    final List<GoodsDto> records;

    records = DaoSupport.getInstance().find(searchSqlQuery,
        new GoodsDaoImpl.GoodsResultSetMapper()
        , GoodsDto.class
        , primaryKeyMap.get(GOODS_ID)
        , null
        , null
        , null
        , null
        , null
        , null
        , operatorId);

    return records;
  }

  @Override
  public Object create(Object record, Integer operatorId) {

    GoodsCreateDto createDto = (GoodsCreateDto) record;

    String sqlQuery =
        "begin "
            + "? := pkg_jepriashowcase.createGoods("
            + "supplierId => ? "
            + ", goodsName => ? "
            + ", goodsTypeCode => ? "
            + ", unitCode => ? "
            + ", purchasingPrice => ? "
            + ", motivationTypeCode => ? "
            + ", goodsPhotoMimeType => ? "
            + ", goodsPhotoExtension => ? "
            + ", goodsPortfolioMimeType => ? "
            + ", goodsPortfolioExtension => ? "
            + ", operatorId => ? "
            + ");"
            + "end;";

    Integer result = DaoSupport.getInstance().create(
        sqlQuery,
        Integer.class,
        createDto.getSupplierId(),
        createDto.getGoodsName(),
        createDto.getGoodsTypeCode(),
        createDto.getUnitCode(),
        createDto.getPurchasingPrice(),
        createDto.getMotivationTypeCode(),
        createDto.getGoodsPhotoMimeType(),
        createDto.getGoodsPhotoExtension(),
        createDto.getGoodsPortfolioMimeType(),
        createDto.getGoodsPortfolioExtension(),
        operatorId
    );

    return result;
  }

  @Override
  public void update(Map<String, ?> primaryKey, Object record, Integer operatorId) {

    GoodsUpdateDto updateDto = (GoodsUpdateDto) record;

    String sqlQuery =
        "begin "
            +  "pkg_jepriashowcase.updateGoods("
            + "goodsId => ? "
            + ", supplierId => ? "
            + ", goodsName => ? "
            + ", goodsTypeCode => ? "
            + ", unitCode => ? "
            + ", purchasingPrice => ? "
            + ", motivationTypeCode => ? "
            + ", goodsPhotoMimeType => ? "
            + ", goodsPhotoExtension => ? "
            + ", goodsPortfolioMimeType => ? "
            + ", goodsPortfolioExtension => ? "
            + ", operatorId => ? "
            + ");"
            + "end;";

    DaoSupport.getInstance().update(
        sqlQuery,
        Integer.class,
        primaryKey.get(GOODS_ID),
        updateDto.getSupplierId(),
        updateDto.getGoodsName(),
        updateDto.getUnitCode(),
        updateDto.getPurchasingPrice(),
        updateDto.getMotivationTypeCode(),
        updateDto.getGoodsPhotoMimeType(),
        updateDto.getGoodsPhotoExtension(),
        updateDto.getGoodsPortfolioMimeType(),
        updateDto.getGoodsPortfolioExtension(),
        operatorId
    );
  }

  @Override
  public void delete(Map<String, ?> primaryKey, Integer operatorId) {
    String sqlQuery =
        "begin "
            + "pkg_jepriashowcase.deleteGoods("
            + "goodsId => ? "
            + ", operatorId => ? "
            + ");"
            + "end;";

    DaoSupport.getInstance().delete(
        sqlQuery,
        primaryKey.get(GOODS_ID),
        operatorId
    );
  }

  private List<TreeNode> getExpandedNodesForGoodsCatalog(Integer parentGoodsCatalogId, Integer goodsId) {
    List<TreeNode> result = new ArrayList<TreeNode>();
    List<GoodsTreeNodeDto> goodsCatalog = getGoodsCatalog(parentGoodsCatalogId, goodsId);
    for (GoodsTreeNodeDto opt : goodsCatalog) {
      TreeNode node = new TreeNode();
      node.node = opt;
      node.expanded = Boolean.TRUE.equals(opt.getHasDescendantGoodsLink());
      node.checked = Boolean.TRUE.equals(opt.getHasGoodsLink());
      if (node.expanded || node.checked) {
        result.add(node);
        result.addAll(getExpandedNodesForGoodsCatalog(opt.getValue(), goodsId));
      }
    }
    return result;
  }

  @Override
  public List<OptionDto<String>>  getGoodsType() {
    String sqlQuery =
        " begin "
            + " ? := pkg_jepriashowcase.getGoodsType;"
            + " end;";

    return DaoSupport.getInstance().find(
        sqlQuery,
        new ResultSetMapper<OptionDto>() {
          public void map(ResultSet rs, OptionDto dto) throws SQLException {
            dto.setValue(rs.getString(GOODS_TYPE_CODE));
            dto.setName(rs.getString(GOODS_TYPE_NAME));
          }
        },
        OptionDto.class
    );
  }

  @Override
  public List<OptionDto<String>>  getUnit() {
    String sqlQuery =
        " begin "
            + " ? := pkg_jepriashowcase.getUnit;"
            + " end;";

    return DaoSupport.getInstance().find(
        sqlQuery,
        new ResultSetMapper<OptionDto>() {
          public void map(ResultSet rs, OptionDto dto) throws SQLException {
            dto.setValue(rs.getString(UNIT_CODE));
            dto.setName(rs.getString(UNIT_NAME));
          }
        },
        OptionDto.class
    );
  }

  @Override
  public List<OptionDto<String>>  getMotivationType() {
    String sqlQuery =
        " begin "
            + " ? := pkg_jepriashowcase.getMotivationType;"
            + " end;";

    return DaoSupport.getInstance().find(
        sqlQuery,
        new ResultSetMapper<OptionDto>() {
          public void map(ResultSet rs, OptionDto dto) throws SQLException {
            dto.setValue(rs.getString(MOTIVATION_TYPE_CODE));
            dto.setName(rs.getString(MOTIVATION_TYPE_NAME));
          }
        },
        OptionDto.class
    );
  }

  @Override
  public List<GoodsTreeNodeDto> getGoodsCatalog(Integer parentGoodsCatalogId, Integer goodsId) {
    String sqlQuery =
        "begin ? := pkg_jepriashowcase.getgoodscatalog("
            + " parentgoodscatalogid => ?"
            + ", goodsId => ?"
            + ");"
            + " end;";

    List<GoodsTreeNodeDto> result = DaoSupport.getInstance().find(
        sqlQuery,
        new ResultSetMapper<GoodsTreeNodeDto>() {
          public void map(ResultSet rs, GoodsTreeNodeDto dto) throws SQLException {
            dto.setValue(getInteger(rs, GOODS_CATALOG_ID));
            dto.setName(rs.getString(GOODS_CATALOG_NAME));
            dto.setHasChilden(rs.getBoolean(HAS_CHILD));
            dto.setHasGoodsLink(rs.getBoolean(GOODS_LINK));
            dto.setHasDescendantGoodsLink(rs.getBoolean(DESCENDANT_GOODS_LINK));
          }
        },
        GoodsTreeNodeDto.class
        , parentGoodsCatalogId, goodsId);

    return result;
  }

  @Override
  public List<OptionDto<String>> getGoodsSegment() {
    String sqlQuery =
        " begin "
            + " ? := pkg_jepriashowcase.getGoodsSegment;"
            + " end;";

    return DaoSupport.getInstance().find(
        sqlQuery,
        new ResultSetMapper<OptionDto>() {
          public void map(ResultSet rs, OptionDto dto) throws SQLException {
            dto.setValue(rs.getString(GOODS_SEGMENT_CODE));
            dto.setName(rs.getString(GOODS_SEGMENT_NAME));
          }
        },
        OptionDto.class
    );
  }


  class TreeNode {
    GoodsTreeNodeDto node;
    boolean checked;
    boolean expanded;
  }
}
