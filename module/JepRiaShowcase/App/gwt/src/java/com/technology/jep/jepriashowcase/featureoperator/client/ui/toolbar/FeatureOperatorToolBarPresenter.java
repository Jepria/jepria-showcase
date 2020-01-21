package com.technology.jep.jepriashowcase.featureoperator.client.ui.toolbar;
 
import com.google.gwt.place.shared.Place;
import com.technology.jep.jepria.client.ui.eventbus.plain.PlainEventBus;
import com.technology.jep.jepria.client.ui.plain.StandardClientFactory;
import com.technology.jep.jepria.client.ui.toolbar.ToolBarPresenter;
import com.technology.jep.jepria.client.ui.toolbar.ToolBarView;
import com.technology.jep.jepriashowcase.featureoperator.shared.service.FeatureOperatorServiceAsync;
 
public class FeatureOperatorToolBarPresenter<V extends ToolBarView, E extends PlainEventBus, S extends FeatureOperatorServiceAsync, F extends StandardClientFactory<E, S>>
  extends ToolBarPresenter<V, E, S, F> {
 
   public FeatureOperatorToolBarPresenter(Place place, F clientFactory) {
    super(place, clientFactory);
  }
 
}
