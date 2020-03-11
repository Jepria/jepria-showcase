import React from "react";
import "./button/button.css";
import "./toolbar/toolbar.css";
import btn_add from "./button/add.png";
import btn_delete from "./button/delete.png";
import btn_edit from "./button/edit.png";
import btn_save from "./button/save.png";
import btn_search from "./button/search.png";
import btn_view from "./button/view.png";

function FeatureToolbar() {
  return (
    <div class="header__toolbar toolbar toolbar_theme_blue">
      <div class="toolbar__button-list">
        <button class="toolbar__button button">
          <img src={btn_add} alt="" />
        </button>
        <button class="toolbar__button button">
          <img src={btn_save} alt="" />
        </button>
        <button class="toolbar__button button">
          <img src={btn_edit} alt="" />
        </button>
        <button class="toolbar__button button">
          <img src={btn_delete} alt="" />
        </button>
        <button class="toolbar__button button">
          <img src={btn_view} alt="" />
        </button>
        <button class="toolbar__button button">
          <img src={btn_search} alt="" />
        </button>
        <button class="toolbar__button button">
          <span class="text text_size_s">Найти</span>
        </button>
      </div>
    </div>
  );
}

export default FeatureToolbar;
