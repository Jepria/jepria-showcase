package com.technology.jep.jepriashowcase.arsenic.client.ui.form.detail;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget; 

public class SuperClass extends Widget {

    public static interface UI { 
        VerticalPanel root(); 
        Button someButton(); 
        Label someLabel(); 
    } 

    public static class DefaultUI implements UI { 

        @UiTemplate("uibinder/ExampleUiBinderWidget.ui.xml") 
        static interface DefaultUIBinder extends UiBinder<VerticalPanel, DefaultUI> {} 
        final static DefaultUIBinder uiBinder = GWT.create(DefaultUIBinder.class); 

        public VerticalPanel _root; 
        @UiField 
        public Button _someButton; 
        @UiField 
        public Label _someLabel; 

        public DefaultUI(){ 
                _root = uiBinder.createAndBindUi(this); 
        } 
        @Override public VerticalPanel root() { 
                return _root; 
        } 
        @Override public Button someButton() {
                return _someButton; 
        } 
        @Override public Label someLabel() { 
                return _someLabel; 
        }
    } 

    public UI ui; 
    int counter; 

    public SuperClass(){ 
        this(new DefaultUI()); 
    } 

    public SuperClass(UI ui){ 
        assert ui != null : "Provide ui - widgets do not like to shownaked :P"; 

        this.ui = ui; 
        init(); 
    } 

    protected void init() {
        ui.someButton().setStyleName("gwt-Button");
        ui.someLabel().setText("Ни разу не нажали!");
        ui.someButton().setText("Жмите на кнопку");
        setElement(ui.root().getElement());
        someInitActions(); 
    } 

    protected void someInitActions() {
        DOM.sinkEvents(ui.someButton().getElement(), Event.ONCLICK); 
        DOM.setEventListener(ui.someButton().getElement(), new EventListener() { 
                @Override public void onBrowserEvent(Event event) { 
                        counter++; 
                        ui.someLabel().setText("Нажали: " + counter + " раз."); 
                } 
        }); 
    } 
    
}