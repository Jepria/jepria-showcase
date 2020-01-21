package com.technology.jep.jepriashowcase.arsenic.client.ui.form.detail;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.technology.jep.jepria.client.ui.WorkstateEnum;
import com.technology.jep.jepria.client.widget.field.multistate.JepTextField;

public class JepTextFieldCustomer extends JepTextField {

    public JepTextFieldCustomer() {
        super();
    }
    
    public JepTextFieldCustomer(String label) {
        super(label);
    }
    
    @UiTemplate("uibinder/JepFieldCustomerUiBinder.ui.xml") 
    public static interface JepMultiStateFieldLayoutUiBinder extends UiBinder<DeckPanel, JepTextFieldCustomer> {}
    final static JepMultiStateFieldLayoutUiBinder uiBinder = GWT.create(JepMultiStateFieldLayoutUiBinder.class);

    @UiField
    VerticalPanel viewPanel2;
    @UiField
    VerticalPanel editablePanel2;
    @UiField
    HTML viewCardLabelCustomer;
    @UiField
    HTML editableCardLabelCustomer;
    
    @Override
    protected DeckPanel getMainWidget() {
        DeckPanel form = uiBinder.createAndBindUi(this);
        return form;
    }
    
    @Override
    public void setFieldLabel(String label) {
        super.setFieldLabel(label);
        viewCardLabelCustomer.setHTML(label);
        editableCardLabelCustomer.setHTML(label);
    }
    
    @Override
    public void setEditable(boolean editable) {
        this.editable = editable;

        if (WorkstateEnum.isEditableState(_workstate) && editable) {
            showWidget(getWidgetIndex(editablePanel2));
        } else {
            showWidget(getWidgetIndex(viewPanel2));
        }
    }
    
    @Override
    protected void onChangeWorkstate(WorkstateEnum newWorkstate) {
        // При смене состояния, очищаем ошибки валидации, если таковые
        // присутствуют.

        clearInvalid();

        // Если произошло переключение из режима Редактирования в режим
        // Просмотра, то обновим значение карты режима Просмотра.

        if (WorkstateEnum.isEditableState(_workstate) && WorkstateEnum.isViewState(newWorkstate)) {
            setViewValue(getValue());
        }
        // Если выставлен признак нередактируемости в режиме Редактирования, то
        // переключаем карту в режим Просмотра.

        if (WorkstateEnum.isEditableState(newWorkstate) && editable) {
            showWidget(getWidgetIndex(editablePanel2));
        } else {
            showWidget(getWidgetIndex(viewPanel2));
        }
    }
    
    @Override
    protected void addEditableCard() {
      editableCard = new TextBox();
      editablePanel2.add(editableCard);
    }
}
