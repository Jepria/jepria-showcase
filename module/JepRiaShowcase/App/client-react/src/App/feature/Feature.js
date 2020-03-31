import React from "react";
import {BrowserRouter, Switch, Route} from "react-router-dom";

import "./Feature.css";
import FeatureToolbar from "./components/FeatureToolbar";
import "./header/header.css";
import "./header/_theme_blue/header_theme_blue.css";
import "./normalize.css";
import "./content.css";
import "./field/field.css";
import "./flow-panel.css";
import {Find} from "./pages/Find";
import {Detail} from "./pages/Detail";
import {Edit} from "./pages/Edit";
import {Create} from "./pages/Create";
import {List} from "./pages/List";

function Feature() {
  return (
    <BrowserRouter>
      <div className="jepria-font">
        <header className="header header_theme_blue">
          <ul className="header__module-list">
            <li className="header__module-item header__module-item_selected">
              <span>Запрос функционала</span>
            </li>
          </ul>
        </header>
        <FeatureToolbar/>
        <Switch>
          <Route path="/" exact component={Find}/>
          <Route path="/detail" component={Detail}/>
          <Route path="/edit" component={Edit}/>
          <Route path="/create" component={Create}/>
          <Route path="/list" component={List}/>
        </Switch>
      </div>
    </BrowserRouter>
  );
}

export default Feature;
