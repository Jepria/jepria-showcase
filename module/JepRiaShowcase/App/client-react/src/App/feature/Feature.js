import React from "react";
import FeatureToolbar from "./FeatureToolbar";
import "./header/header.css";
import "./header/_theme_blue/header_theme_blue.css";
import "./normalize.css";
import "./content.css";
import "./field/field.css";
import "./flow-panel.css";

function Feature() {
  return (
    <div>
      <header class="header header_theme_blue">
        <ul class="header__module-list">
          <li class="header__module-item header__module-item_selected">
            <span>Запрос функционала</span>
          </li>
        </ul>
        <FeatureToolbar />
      </header>

      <body class="featureBody content">
        <div class="float-panel">
          <div class="field content__item">
            <label class="field__label label">Идентификатор</label>
            <input class="field__input input input_text" />
          </div>
        </div>
        {/* <div class="float-panel__item">
          <input type="checkbox"></input>
          <input type="checkbox"></input>
          <input type="checkbox"></input>
        </div> */}
      </body>
    </div>
  );
}

export default Feature;
