package com.technology.jep.jepriashowcase.arsenic.client.ui.form.detail;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SomeSubClass extends SuperClass {
    public static interface UI extends SuperClass.UI {
        Label extLabel(); 
    } 

    public static class DefaultUI extends SuperClass.DefaultUI implements UI { 
        
        @UiTemplate("uibinder/SomeSubClass.ui.xml") 
        public static interface DefaultUIBinder extends UiBinder<VerticalPanel, DefaultUI> {} 
        
        final static DefaultUIBinder uiBinder = GWT.create(DefaultUIBinder.class); 
        
        @UiField Label _extLabel; 
        public DefaultUI(){ 
            _root = uiBinder.createAndBindUi(this); 
        } 
        
        @Override 
        public Label extLabel() {
                return _extLabel; 
        } 
    } 

    public SomeSubClass() {
        super(new DefaultUI()); 
    } 
    
    public SomeSubClass(UI ui){ 
        super(ui); 
    } 

    @Override 
    protected void init() { 
        ((UI) ui).extLabel().setText("Динамический текст");
        super.init(); 
    }
}
