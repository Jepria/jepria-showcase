package com.technology.jep.jepriashowcase.arsenic.client.ui.form.detail;
 
import static com.technology.jep.jepria.client.JepRiaClientConstant.JepImages;
import static com.technology.jep.jepria.shared.field.JepFieldNames.MAX_ROW_COUNT;
import static com.technology.jep.jepriashowcase.arsenic.client.ArsenicAutomationConstant.ARSENIC_CHECKBOX_SWITCH_ALBL_ID;
import static com.technology.jep.jepriashowcase.arsenic.client.ArsenicAutomationConstant.ARSENIC_CHECKBOX_SWITCH_EDTB_ID;
import static com.technology.jep.jepriashowcase.arsenic.client.ArsenicAutomationConstant.ARSENIC_CHECKBOX_SWITCH_ENBL_ID;
import static com.technology.jep.jepriashowcase.arsenic.client.ArsenicAutomationConstant.ARSENIC_CHECKBOX_SWITCH_VSBL_ID;
import static com.technology.jep.jepriashowcase.arsenic.client.ArsenicAutomationConstant.ARSENIC_JEP_CHECKBOX_FIELD_ID;
import static com.technology.jep.jepriashowcase.arsenic.client.ArsenicAutomationConstant.ARSENIC_JEP_COMBOBOX_FIELD_3CH_RELOADING_ID;
import static com.technology.jep.jepriashowcase.arsenic.client.ArsenicAutomationConstant.ARSENIC_JEP_COMBOBOX_FIELD_DURABLE_ID;
import static com.technology.jep.jepriashowcase.arsenic.client.ArsenicAutomationConstant.ARSENIC_JEP_COMBOBOX_FIELD_NOTLAZY_ID;
import static com.technology.jep.jepriashowcase.arsenic.client.ArsenicAutomationConstant.ARSENIC_JEP_COMBOBOX_FIELD_RELOADING_ID;
import static com.technology.jep.jepriashowcase.arsenic.client.ArsenicAutomationConstant.ARSENIC_JEP_COMBOBOX_FIELD_SIMPLE_ID;
import static com.technology.jep.jepriashowcase.arsenic.client.ArsenicAutomationConstant.ARSENIC_JEP_DATE_FIELD_ID;
import static com.technology.jep.jepriashowcase.arsenic.client.ArsenicAutomationConstant.ARSENIC_JEP_DATE_YEAR_MONTH_ONLY_FIELD_ID;
import static com.technology.jep.jepriashowcase.arsenic.client.ArsenicAutomationConstant.ARSENIC_JEP_DUAL_LIST_FIELD_ID;
import static com.technology.jep.jepriashowcase.arsenic.client.ArsenicAutomationConstant.ARSENIC_JEP_LIST_FIELD_CHECKALL_ID;
import static com.technology.jep.jepriashowcase.arsenic.client.ArsenicAutomationConstant.ARSENIC_JEP_LIST_FIELD_ID;
import static com.technology.jep.jepriashowcase.arsenic.client.ArsenicAutomationConstant.ARSENIC_JEP_LONG_FIELD_ID;
import static com.technology.jep.jepriashowcase.arsenic.client.ArsenicAutomationConstant.ARSENIC_JEP_MONEY_FIELD_ID;
import static com.technology.jep.jepriashowcase.arsenic.client.ArsenicAutomationConstant.ARSENIC_JEP_NUMBER_FIELD_ID;
import static com.technology.jep.jepriashowcase.arsenic.client.ArsenicAutomationConstant.ARSENIC_JEP_TEXT_AREA_FIELD_ID;
import static com.technology.jep.jepriashowcase.arsenic.client.ArsenicAutomationConstant.ARSENIC_JEP_TEXT_FIELD_ID;
import static com.technology.jep.jepriashowcase.arsenic.client.ArsenicAutomationConstant.ARSENIC_JEP_TREE_FIELD_CASC_ID;
import static com.technology.jep.jepriashowcase.arsenic.client.ArsenicAutomationConstant.ARSENIC_JEP_TREE_FIELD_ID;
import static com.technology.jep.jepriashowcase.arsenic.client.ArsenicAutomationConstant.ARSENIC_JEP_TREE_FIELD_NODES_ID;
import static com.technology.jep.jepriashowcase.arsenic.client.ArsenicAutomationConstant.ARSENIC_MAX_ROW_COUNT_ID;
import static com.technology.jep.jepriashowcase.arsenic.client.ArsenicClientConstant.arsenicImages;
import static com.technology.jep.jepriashowcase.arsenic.client.ArsenicClientConstant.arsenicText;
import static com.technology.jep.jepriashowcase.arsenic.shared.field.ArsenicFieldNames.DETAILFORM_CHECKBOX_SWITCH_ALBL;
import static com.technology.jep.jepriashowcase.arsenic.shared.field.ArsenicFieldNames.DETAILFORM_CHECKBOX_SWITCH_EDTB;
import static com.technology.jep.jepriashowcase.arsenic.shared.field.ArsenicFieldNames.DETAILFORM_CHECKBOX_SWITCH_ENBL;
import static com.technology.jep.jepriashowcase.arsenic.shared.field.ArsenicFieldNames.DETAILFORM_CHECKBOX_SWITCH_VSBL;
import static com.technology.jep.jepriashowcase.arsenic.shared.field.ArsenicFieldNames.DETAILFORM_JEP_CHECKBOX_FIELD;
import static com.technology.jep.jepriashowcase.arsenic.shared.field.ArsenicFieldNames.DETAILFORM_JEP_COMBOBOX_FIELD_3CH_RELOADING;
import static com.technology.jep.jepriashowcase.arsenic.shared.field.ArsenicFieldNames.DETAILFORM_JEP_COMBOBOX_FIELD_DURABLE;
import static com.technology.jep.jepriashowcase.arsenic.shared.field.ArsenicFieldNames.DETAILFORM_JEP_COMBOBOX_FIELD_NOTLAZY;
import static com.technology.jep.jepriashowcase.arsenic.shared.field.ArsenicFieldNames.DETAILFORM_JEP_COMBOBOX_FIELD_RELOADING;
import static com.technology.jep.jepriashowcase.arsenic.shared.field.ArsenicFieldNames.DETAILFORM_JEP_COMBOBOX_FIELD_SIMPLE;
import static com.technology.jep.jepriashowcase.arsenic.shared.field.ArsenicFieldNames.DETAILFORM_JEP_DATE_FIELD;
import static com.technology.jep.jepriashowcase.arsenic.shared.field.ArsenicFieldNames.DETAILFORM_JEP_DATE_YEAR_MONT_ONLY_FIELD;
import static com.technology.jep.jepriashowcase.arsenic.shared.field.ArsenicFieldNames.DETAILFORM_JEP_DUAL_LIST_FIELD;
import static com.technology.jep.jepriashowcase.arsenic.shared.field.ArsenicFieldNames.DETAILFORM_JEP_LIST_FIELD;
import static com.technology.jep.jepriashowcase.arsenic.shared.field.ArsenicFieldNames.DETAILFORM_JEP_LIST_FIELD_CHECKALL;
import static com.technology.jep.jepriashowcase.arsenic.shared.field.ArsenicFieldNames.DETAILFORM_JEP_LONG_FIELD;
import static com.technology.jep.jepriashowcase.arsenic.shared.field.ArsenicFieldNames.DETAILFORM_JEP_MONEY_FIELD;
import static com.technology.jep.jepriashowcase.arsenic.shared.field.ArsenicFieldNames.DETAILFORM_JEP_NUMBER_FIELD;
import static com.technology.jep.jepriashowcase.arsenic.shared.field.ArsenicFieldNames.DETAILFORM_JEP_TEXT_AREA_FIELD;
import static com.technology.jep.jepriashowcase.arsenic.shared.field.ArsenicFieldNames.DETAILFORM_JEP_TEXT_FIELD;
import static com.technology.jep.jepriashowcase.arsenic.shared.field.ArsenicFieldNames.DETAILFORM_JEP_TREE_FIELD;
import static com.technology.jep.jepriashowcase.arsenic.shared.field.ArsenicFieldNames.DETAILFORM_JEP_TREE_FIELD_CASC;
import static com.technology.jep.jepriashowcase.arsenic.shared.field.ArsenicFieldNames.DETAILFORM_JEP_TREE_FIELD_NODES;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.FontWeight;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineHTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.technology.jep.jepria.client.ui.form.detail.DetailFormView;
import com.technology.jep.jepria.client.ui.form.detail.StandardDetailFormViewImpl;
import com.technology.jep.jepria.client.widget.event.JepEvent;
import com.technology.jep.jepria.client.widget.event.JepEventType;
import com.technology.jep.jepria.client.widget.event.JepListener;
import com.technology.jep.jepria.client.widget.field.multistate.JepCheckBoxField;
import com.technology.jep.jepria.client.widget.field.multistate.JepComboBoxField;
import com.technology.jep.jepria.client.widget.field.multistate.JepDateField;
import com.technology.jep.jepria.client.widget.field.multistate.JepDualListField;
import com.technology.jep.jepria.client.widget.field.multistate.JepIntegerField;
import com.technology.jep.jepria.client.widget.field.multistate.JepListField;
import com.technology.jep.jepria.client.widget.field.multistate.JepLongField;
import com.technology.jep.jepria.client.widget.field.multistate.JepMoneyField;
import com.technology.jep.jepria.client.widget.field.multistate.JepMultiStateField;
import com.technology.jep.jepria.client.widget.field.multistate.JepNumberField;
import com.technology.jep.jepria.client.widget.field.multistate.JepTextAreaField;
import com.technology.jep.jepria.client.widget.field.multistate.JepTextField;
import com.technology.jep.jepria.client.widget.field.multistate.JepTreeField;
import com.technology.jep.jepria.client.widget.field.tree.TreeField.CheckCascade;
import com.technology.jep.jepria.client.widget.field.tree.TreeField.CheckNodes;
import com.technology.jep.jepria.shared.field.option.JepOption;

public class ArsenicDetailFormViewImpl extends StandardDetailFormViewImpl implements DetailFormView {  
 
  public ArsenicDetailFormViewImpl() {
    HorizontalPanel title = new HorizontalPanel();
    title.getElement().getStyle().setMarginTop(30, Unit.PX);
    title.getElement().getStyle().setMarginLeft(30, Unit.PX);
    title.getElement().getStyle().setMarginBottom(30, Unit.PX);
    Image image = new Image(arsenicImages.chemical());
    image.getElement().getStyle().setMarginRight(30, Unit.PX);
    title.add(image);
    title.add(new HTML(arsenicText.titleText()));
    panel.add(title);
    
    
    // group: checkboxes switching field states
    JepCheckBoxField checkBox_switchVsbl = new JepCheckBoxField(
        ARSENIC_CHECKBOX_SWITCH_VSBL_ID, arsenicText.detail_jepCheckBox_switch_vsbl()) {{
          setLabelWidth(250);
          setFieldWidth(50);
        }};
    JepCheckBoxField checkBox_switchEnbl = new JepCheckBoxField(
        ARSENIC_CHECKBOX_SWITCH_ENBL_ID, arsenicText.detail_jepCheckBox_switch_enbl()) {{
          setLabelWidth(250);
          setFieldWidth(50);
        }};
    JepCheckBoxField checkBox_switchEdtb = new JepCheckBoxField(
        ARSENIC_CHECKBOX_SWITCH_EDTB_ID, arsenicText.detail_jepCheckBox_switch_edtb()) {{
          setLabelWidth(250);
          setFieldWidth(50);
        }};
    JepCheckBoxField checkBox_switchAlbl = new JepCheckBoxField(
        ARSENIC_CHECKBOX_SWITCH_ALBL_ID, arsenicText.detail_jepCheckBox_switch_albl()) {{
          setLabelWidth(250);
          setFieldWidth(50);
        }};
    HorizontalPanel checkBoxPanel = new HorizontalPanel();
    
    checkBoxPanel.add(checkBox_switchVsbl);
    checkBoxPanel.add(checkBox_switchEnbl);
    checkBoxPanel.add(checkBox_switchEdtb);
    checkBoxPanel.add(checkBox_switchAlbl);
    panel.add(checkBoxPanel);
    
    
    // group: texts, nums, checkbox
    JepTextField textField = new JepTextField(ARSENIC_JEP_TEXT_FIELD_ID, arsenicText.detail_jepTextField()) {{
      setLabelWidth(200);
    }};
    JepTextAreaField textAreaField = new JepTextAreaField(ARSENIC_JEP_TEXT_AREA_FIELD_ID, arsenicText.detail_jepTextAreaField()) {{
      setLabelWidth(200);
    }};
    VerticalPanel textFieldPanel = new VerticalPanel();
    textFieldPanel.add(textField);
    textFieldPanel.add(textAreaField);
    
    JepLongField longField = new JepLongField(ARSENIC_JEP_LONG_FIELD_ID, arsenicText.detail_jepLongField()) {{
      setLabelWidth(200);
    }};
    
    JepMoneyField moneyField = new JepMoneyField(ARSENIC_JEP_MONEY_FIELD_ID, arsenicText.detail_jepMoneyField()) {{
      setLabelWidth(200);
    }};
//    moneyField.setMaxLength(10);

    JepNumberField numberField = new JepNumberField(ARSENIC_JEP_NUMBER_FIELD_ID, arsenicText.detail_jepNumberField()) {{
      setLabelWidth(200);
    }};
    JepDateField dateField = new JepDateField(ARSENIC_JEP_DATE_FIELD_ID, arsenicText.detail_jepDateField()) {{
      setLabelWidth(200);
    }};
    
    // ------------CHECK DATE FIELD ------------
    
    JepDateField datePickerMonthYearsOnly = new JepDateField(ARSENIC_JEP_DATE_YEAR_MONTH_ONLY_FIELD_ID, "Непростой JepDateField") {{
        setLabelWidth(200);
    }};
    datePickerMonthYearsOnly.setNavigationPanel(JepDateField.FORMAT_DAYS_AND_MONTH_AND_YEAR_TIME, true);
    datePickerMonthYearsOnly.setMinYear(2007);
    datePickerMonthYearsOnly.setMaxYear(2053);
    
    JepDateField datePickerMonthYearsOnly1 = new JepDateField(ARSENIC_JEP_DATE_YEAR_MONTH_ONLY_FIELD_ID, "Непростой1 JepDateField") {{
      setLabelWidth(200);
    }};
    datePickerMonthYearsOnly1.setNavigationPanel(JepDateField.FORMAT_DAYS_AND_MONTH_AND_YEAR, true);
    JepDateField datePickerMonthYearsOnly2 = new JepDateField(ARSENIC_JEP_DATE_YEAR_MONTH_ONLY_FIELD_ID, "Непростой2 JepDateField") {{
      setLabelWidth(200);
    }};
    datePickerMonthYearsOnly2.setNavigationPanel(JepDateField.FORMAT_MONTH_AND_YEAR_ONLY, true);
    JepDateField datePickerMonthYearsOnly3 = new JepDateField(ARSENIC_JEP_DATE_YEAR_MONTH_ONLY_FIELD_ID, "Непростой3 JepDateField") {{
      setLabelWidth(200);
    }};
    datePickerMonthYearsOnly3.setNavigationPanel(JepDateField.FORMAT_YEAR_ONLY, true);
    
    JepDateField datePickerMonthYearsOnly4 = new JepDateField(ARSENIC_JEP_DATE_YEAR_MONTH_ONLY_FIELD_ID, "Непростой4 JepDateField") {{
      setLabelWidth(200);
    }};
    datePickerMonthYearsOnly4.setNavigationPanel(JepDateField.FORMAT_DAYS_AND_MONTH_AND_YEAR_TIME, false);
    datePickerMonthYearsOnly4.setMinYear(2007);
    datePickerMonthYearsOnly4.setMaxYear(2053);
    
    JepDateField datePickerMonthYearsOnly5 = new JepDateField(ARSENIC_JEP_DATE_YEAR_MONTH_ONLY_FIELD_ID, "Непростой5 JepDateField") {{
      setLabelWidth(200);
    }};
    datePickerMonthYearsOnly5.setNavigationPanel(JepDateField.FORMAT_DAYS_AND_MONTH_AND_YEAR, false);
    JepDateField datePickerMonthYearsOnly6 = new JepDateField(ARSENIC_JEP_DATE_YEAR_MONTH_ONLY_FIELD_ID, "Непростой6 JepDateField") {{
      setLabelWidth(200);
    }};
    datePickerMonthYearsOnly6.setNavigationPanel(JepDateField.FORMAT_MONTH_AND_YEAR_ONLY, false);
    JepDateField datePickerMonthYearsOnly7 = new JepDateField(ARSENIC_JEP_DATE_YEAR_MONTH_ONLY_FIELD_ID, "Непростой7 JepDateField") {{
      setLabelWidth(200);
    }};
    datePickerMonthYearsOnly7.setNavigationPanel(JepDateField.FORMAT_YEAR_ONLY, true);    
    // ----------DATE FIELD --------------------
    
    VerticalPanel numFieldPanel = new VerticalPanel();
    numFieldPanel.add(longField);
    numFieldPanel.add(moneyField);
    numFieldPanel.add(numberField);
    numFieldPanel.add(dateField);
    numFieldPanel.add(datePickerMonthYearsOnly);
    numFieldPanel.add(datePickerMonthYearsOnly1);
    numFieldPanel.add(datePickerMonthYearsOnly2);
    numFieldPanel.add(datePickerMonthYearsOnly3);
    numFieldPanel.add(datePickerMonthYearsOnly4);
    numFieldPanel.add(datePickerMonthYearsOnly5);
    numFieldPanel.add(datePickerMonthYearsOnly6);
    numFieldPanel.add(datePickerMonthYearsOnly7);
    
    
    JepCheckBoxField checkBoxField = new JepCheckBoxField(ARSENIC_JEP_CHECKBOX_FIELD_ID, arsenicText.detail_jepCheckBoxField()) {{
      setLabelWidth(200);
    }};
    
    HorizontalPanel textAndNumPanel = new HorizontalPanel();
    textAndNumPanel.getElement().getStyle().setPaddingTop(30, Unit.PX);
    textAndNumPanel.add(textFieldPanel);
    textAndNumPanel.add(numFieldPanel);
    textAndNumPanel.add(checkBoxField);
    panel.add(textAndNumPanel);
    
    
    // group: combos, dual
    JepComboBoxField comboBoxFieldNotLazy = new JepComboBoxField(ARSENIC_JEP_COMBOBOX_FIELD_NOTLAZY_ID,
        createInfoIcon(arsenicText.detail_jepComboBoxField_notlazy_hint()) + new InlineHTML("&nbsp;") +
        arsenicText.detail_jepComboBoxField_notlazy()) {{
      setLabelWidth(300);
    }};
    
    comboBoxFieldNotLazy.markInvalid("Errors example");
    
    JepComboBoxField comboBoxFieldSimple = new JepComboBoxField(ARSENIC_JEP_COMBOBOX_FIELD_SIMPLE_ID,
        createInfoIcon(arsenicText.detail_jepComboBoxField_simple_hint()) + new InlineHTML("&nbsp;") +
        arsenicText.detail_jepComboBoxField_simple()) {{
      setLabelWidth(300);
    }};
    JepComboBoxField comboBoxFieldDurable = new JepComboBoxField(ARSENIC_JEP_COMBOBOX_FIELD_DURABLE_ID,
        createInfoIcon(arsenicText.detail_jepComboBoxField_durable_hint()) + new InlineHTML("&nbsp;") +
        arsenicText.detail_jepComboBoxField_durable()) {{
      setLabelWidth(300);
    }};
    JepComboBoxField comboBoxFieldReloading = new JepComboBoxField(ARSENIC_JEP_COMBOBOX_FIELD_RELOADING_ID,
        createInfoIcon(arsenicText.detail_jepComboBoxField_reloading_hint()) + new InlineHTML("&nbsp;") +
        arsenicText.detail_jepComboBoxField_reloading()) {{
      setLabelWidth(300);
      setEmptyText(arsenicText.startTyping());
    }};
    JepComboBoxField comboBoxField3chReloading = new JepComboBoxField(ARSENIC_JEP_COMBOBOX_FIELD_3CH_RELOADING_ID,
        createInfoIcon(arsenicText.detail_jepComboBoxField_reloading_hint()) + new InlineHTML("&nbsp;") + 
        arsenicText.detail_jepComboBoxField_3ch_reloading()) {{
      setLabelWidth(300);
      setEmptyText(arsenicText.startTyping3ch());
    }};
    VerticalPanel comboPanel = new VerticalPanel();
    comboPanel.add(comboBoxFieldNotLazy);
    comboPanel.add(comboBoxFieldSimple);
    comboPanel.add(comboBoxFieldDurable);
    comboPanel.add(comboBoxFieldReloading);
    comboPanel.add(comboBoxField3chReloading);
    
    JepDualListField dualListField = new JepDualListField(ARSENIC_JEP_DUAL_LIST_FIELD_ID, arsenicText.detail_jepDualListField()) {{
      getElement().getStyle().setPaddingTop(30, Unit.PX);
      setLabelWidth(200);
      setFieldWidth(420);
    }};
    HorizontalPanel comboAndDualPanel = new HorizontalPanel();
    comboAndDualPanel.getElement().getStyle().setPaddingTop(30, Unit.PX);
    comboAndDualPanel.add(comboPanel);
    comboAndDualPanel.add(dualListField);
    panel.add(comboAndDualPanel);
    
    
    // group: lists
    JepListField listField = new JepListField(ARSENIC_JEP_LIST_FIELD_ID, arsenicText.detail_jepListField()) {{
      setLabelWidth(200);
    }};
    
    JepListField listFieldCheckAll = new JepListField(ARSENIC_JEP_LIST_FIELD_CHECKALL_ID, arsenicText.detail_jepListField_checkAll()) {{
      setSelectAllCheckBoxVisible(true);
      setLabelWidth(200);
    }};
    HorizontalPanel listFieldPanel = new HorizontalPanel();
    listFieldPanel.getElement().getStyle().setPaddingTop(30, Unit.PX);
    listFieldPanel.add(listField);
    listFieldPanel.add(listFieldCheckAll);
    panel.add(listFieldPanel);
    
    
    // group: trees
    JepTreeField treeField = new JepTreeField(ARSENIC_JEP_TREE_FIELD_ID, arsenicText.detail_jepTreeField()) {{
      setCheckNodes(CheckNodes.LEAF);
//      setSelectAllCheckBoxVisible(true);
      setLabelWidth(200);
    }};
    JepTreeField treeFieldNodes = new JepTreeField(ARSENIC_JEP_TREE_FIELD_NODES_ID, arsenicText.detail_jepTreeField_nodes()) {{
      setSelectAllCheckBoxVisible(true);
      setLabelWidth(200);
    }};
    JepTreeField treeFieldCasc = new JepTreeField(ARSENIC_JEP_TREE_FIELD_CASC_ID, arsenicText.detail_jepTreeField_casc()) {{
      setCheckStyle(CheckCascade.PARENTS);
      setSelectAllCheckBoxVisible(true);
      setLabelWidth(200);
    }};
    HorizontalPanel treeFieldPanel = new HorizontalPanel();
    treeFieldPanel.getElement().getStyle().setPaddingTop(30, Unit.PX);
    treeFieldPanel.add(treeField);
    treeFieldPanel.add(treeFieldNodes);
    treeFieldPanel.add(treeFieldCasc);
    panel.add(treeFieldPanel);
    
    JepIntegerField integerField = new JepIntegerField(ARSENIC_MAX_ROW_COUNT_ID, arsenicText.detail_maxRowCount()) {{
      setLabelWidth(200);
      getElement().getStyle().setPaddingTop(30, Unit.PX);
    }};
    panel.add(integerField);
    
    SomeSubClass exampleChildUiBinder = new SomeSubClass();
    SuperClass exampleSuperUiBinder = new SuperClass();
    HTMLPanel  layoutPanelUiBinder = new HTMLPanel("Пример переопределения структуры виджета посредствам механизмов UiBinder в потомке");
    
    HorizontalPanel hPanel = new HorizontalPanel();
    VerticalPanel vPanel = new VerticalPanel();
    Label label = new Label("Предок");
    label.getElement().getStyle().setFontWeight(FontWeight.BOLD);
    vPanel.add(label);
    vPanel.add(exampleSuperUiBinder);
    vPanel.getElement().getStyle().setMargin(5, Unit.PX);
    hPanel.add(vPanel);
    
    vPanel = new VerticalPanel();
    label = new Label("Потомок");
    label.getElement().getStyle().setFontWeight(FontWeight.BOLD);
    vPanel.add(label);
    vPanel.add(exampleChildUiBinder);
    vPanel.getElement().getStyle().setMargin(5, Unit.PX);
    
    hPanel.add(vPanel);
    layoutPanelUiBinder.add(hPanel);
    layoutPanelUiBinder.getElement().getStyle().setMargin(15, Unit.PX);
    layoutPanelUiBinder.getElement().getStyle().setBorderStyle(BorderStyle.SOLID);
    layoutPanelUiBinder.getElement().getStyle().setBorderColor("blue");
    layoutPanelUiBinder.getElement().getStyle().setBorderWidth(1f, Unit.PX);
    layoutPanelUiBinder.getElement().getStyle().setPadding(10, Unit.PX);

    panel.add(layoutPanelUiBinder);
    
    
    JepComboBoxField fieldOrientedExample = new JepComboBoxField("Choose orientation");
    fieldOrientedExample.addListener(JepEventType.CHANGE_SELECTION_EVENT, new JepListener() {
        @Override
        public void handleEvent(JepEvent event) {
            if ((boolean) JepOption.getValue(fieldOrientedExample.getValue())) {
                
                setVerticalOrientationFields(fieldOrientedExample);
                
                setVerticalOrientationFields(checkBox_switchVsbl);
                setVerticalOrientationFields(checkBox_switchEnbl);
                setVerticalOrientationFields(checkBox_switchEdtb);
                setVerticalOrientationFields(checkBox_switchAlbl);
                setVerticalOrientationFields(textField);
                setVerticalOrientationFields(textAreaField);
                setVerticalOrientationFields(longField);
                setVerticalOrientationFields(moneyField);
                setVerticalOrientationFields(numberField);
                setVerticalOrientationFields(dateField);
                setVerticalOrientationFields(datePickerMonthYearsOnly);
                setVerticalOrientationFields(comboBoxFieldNotLazy);
                setVerticalOrientationFields(comboBoxFieldSimple);
                setVerticalOrientationFields(comboBoxFieldDurable);
                setVerticalOrientationFields(comboBoxFieldReloading);
                setVerticalOrientationFields(comboBoxField3chReloading);
                setVerticalOrientationFields(dualListField);
                setVerticalOrientationFields(checkBoxField);
                setVerticalOrientationFields(listField);
                setVerticalOrientationFields(listFieldCheckAll);
                setVerticalOrientationFields(treeField);
                setVerticalOrientationFields(treeFieldNodes);
                setVerticalOrientationFields(treeFieldCasc);
                setVerticalOrientationFields(integerField);
            } else {
                setHorizontalOrientationFields(fieldOrientedExample);
                
                setHorizontalOrientationFields(checkBox_switchVsbl);
                checkBox_switchVsbl.setLabelWidth(250);
                checkBox_switchVsbl.setFieldWidth(50);
                
                setHorizontalOrientationFields(checkBox_switchEnbl);
                checkBox_switchEnbl.setLabelWidth(250);
                checkBox_switchEnbl.setFieldWidth(50);
                
                setHorizontalOrientationFields(checkBox_switchEdtb);
                checkBox_switchEdtb.setLabelWidth(250);
                checkBox_switchEdtb.setFieldWidth(50);
                
                setHorizontalOrientationFields(checkBox_switchAlbl);
                checkBox_switchAlbl.setLabelWidth(250);
                checkBox_switchAlbl.setFieldWidth(50);
                
                setHorizontalOrientationFields(textField);
                textField.setLabelWidth(200);
                
                setHorizontalOrientationFields(textAreaField);
                textAreaField.setLabelWidth(200);
                
                setHorizontalOrientationFields(longField);
                longField.setLabelWidth(200);
                
                setHorizontalOrientationFields(moneyField);
                moneyField.setLabelWidth(200);
                
                setHorizontalOrientationFields(numberField);
                numberField.setLabelWidth(200);
                
                setHorizontalOrientationFields(dateField);
                dateField.setLabelWidth(200);
                
                setHorizontalOrientationFields(datePickerMonthYearsOnly);
                datePickerMonthYearsOnly.setLabelWidth(200);
                
                setHorizontalOrientationFields(comboBoxFieldNotLazy);
                comboBoxFieldNotLazy.setLabelWidth(300);
                
                setHorizontalOrientationFields(comboBoxFieldSimple);
                comboBoxFieldSimple.setLabelWidth(300);
                
                setHorizontalOrientationFields(comboBoxFieldDurable);
                comboBoxFieldDurable.setLabelWidth(300);
                
                setHorizontalOrientationFields(comboBoxFieldReloading);
                comboBoxFieldReloading.setLabelWidth(300);
                
                setHorizontalOrientationFields(comboBoxField3chReloading);
                comboBoxField3chReloading.setLabelWidth(300);
                
                setHorizontalOrientationFields(dualListField);
                dualListField.getElement().getStyle().setPaddingTop(30, Unit.PX);
                dualListField.setLabelWidth(200);
                dualListField.setFieldWidth(420);
                
                setHorizontalOrientationFields(checkBoxField);
                checkBoxField.setLabelWidth(200);
                
                setHorizontalOrientationFields(listField);
                listField.setLabelWidth(200);
                
                setHorizontalOrientationFields(listFieldCheckAll);
                listFieldCheckAll.setLabelWidth(200);
                
                setHorizontalOrientationFields(treeField);
                treeField.setCheckNodes(CheckNodes.LEAF);
                treeField.setLabelWidth(200);
              
                setHorizontalOrientationFields(treeFieldNodes);
                treeFieldNodes.setSelectAllCheckBoxVisible(true);
                treeFieldNodes.setLabelWidth(200);
                
                setHorizontalOrientationFields(treeFieldCasc);
                treeFieldCasc.setCheckStyle(CheckCascade.PARENTS);
                treeFieldCasc.setLabelWidth(200);
                
                setHorizontalOrientationFields(integerField);
                integerField.setLabelWidth(200);
                integerField.getElement().getStyle().setPaddingTop(30, Unit.PX);
            }
        }
    });
    
    List<JepOption> list = new ArrayList<JepOption>();
    list.add(new JepOption("vertical", true));
    list.add(new JepOption("horizontal", false));
    fieldOrientedExample.setOptions(list, false);
    panel.add(fieldOrientedExample);
    
    fields.put(DETAILFORM_CHECKBOX_SWITCH_VSBL, checkBox_switchVsbl);
    fields.put(DETAILFORM_CHECKBOX_SWITCH_ENBL, checkBox_switchEnbl);
    fields.put(DETAILFORM_CHECKBOX_SWITCH_EDTB, checkBox_switchEdtb);
    fields.put(DETAILFORM_CHECKBOX_SWITCH_ALBL, checkBox_switchAlbl);
    
    fields.put(DETAILFORM_JEP_TEXT_FIELD, textField);
    fields.put(DETAILFORM_JEP_TEXT_AREA_FIELD, textAreaField);
    fields.put(DETAILFORM_JEP_LONG_FIELD, longField);
    fields.put(DETAILFORM_JEP_MONEY_FIELD, moneyField);
    fields.put(DETAILFORM_JEP_NUMBER_FIELD, numberField);
    fields.put(DETAILFORM_JEP_DATE_FIELD, dateField);
    fields.put(DETAILFORM_JEP_DATE_YEAR_MONT_ONLY_FIELD, datePickerMonthYearsOnly);
    fields.put(DETAILFORM_JEP_COMBOBOX_FIELD_NOTLAZY, comboBoxFieldNotLazy);
    fields.put(DETAILFORM_JEP_COMBOBOX_FIELD_SIMPLE, comboBoxFieldSimple);
    fields.put(DETAILFORM_JEP_COMBOBOX_FIELD_DURABLE, comboBoxFieldDurable);
    fields.put(DETAILFORM_JEP_COMBOBOX_FIELD_RELOADING, comboBoxFieldReloading);
    fields.put(DETAILFORM_JEP_COMBOBOX_FIELD_3CH_RELOADING, comboBoxField3chReloading);
    fields.put(DETAILFORM_JEP_DUAL_LIST_FIELD, dualListField);
    fields.put(DETAILFORM_JEP_CHECKBOX_FIELD, checkBoxField);
    fields.put(DETAILFORM_JEP_LIST_FIELD, listField);
    fields.put(DETAILFORM_JEP_LIST_FIELD_CHECKALL, listFieldCheckAll);
    
    fields.put(DETAILFORM_JEP_TREE_FIELD, treeField);
    fields.put(DETAILFORM_JEP_TREE_FIELD_NODES, treeFieldNodes);
    fields.put(DETAILFORM_JEP_TREE_FIELD_CASC, treeFieldCasc);
    
    fields.put(MAX_ROW_COUNT, integerField);
  }
  
  private String createInfoIcon(String title){
    Anchor link = new Anchor();
    link.setTitle(title);
    //выравнивание ссылки по правому краю (на ссылки не распространяется действие text-align)
    //необходимо использовать атрибут float
    link.addStyleName("x-tool-expand-north");
    Image copyImage = new Image(JepImages.help());
    copyImage.getElement().getStyle().setMarginBottom(-4, Unit.PX);
    link.getElement().appendChild(copyImage.getElement());
    
    return link.toString();
  }
  
  private void setVerticalOrientationFields(JepMultiStateField field) {
      // set CSS class for vertical orientation
      field.getViewPanel().setStyleName("jepRia-vPanelStyle");
      field.getEditablePanel().setStyleName("jepRia-vPanelStyle");
      
      // remove an attributes as non appropriate for view Card
      field.getViewCardLabel().getElement().removeAttribute("align");
      field.getViewCardLabel().getElement().removeClassName("jepRia-MultiStateField-Label");
      
      // remove an attributes as non appropriate for editable card
      field.getEditableCardLabel().getElement().removeAttribute("align");
      field.getEditableCardLabel().getElement().removeClassName("jepRia-MultiStateField-Label");
      
  }
  
  private void setHorizontalOrientationFields(JepMultiStateField field) {
      // set CSS class for horizontal orientation
      field.getViewPanel().setStyleName("jepRia-hPanelStyle");
      field.getEditablePanel().setStyleName("jepRia-hPanelStyle");
      
      // remove an attributes as non appropriate for view Card
      field.getViewCardLabel().getElement().removeAttribute("align");
      field.getViewCardLabel().getElement().addClassName("jepRia-MultiStateField-Label");
      
      // remove an attributes as non appropriate for editable card
      field.getEditableCardLabel().getElement().removeAttribute("align");
      field.getEditableCardLabel().getElement().addClassName("jepRia-MultiStateField-Label");
  }
  
}