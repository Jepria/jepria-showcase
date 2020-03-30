import React, { useState } from "react";
import "./toolbar/buttons/button.css";
import "./toolbar/toolbar.css";
import btn_add from "./toolbar/buttons/add.png";
import btn_delete from "./toolbar/buttons/delete.png";
import btn_edit from "./toolbar/buttons/edit.png";
import btn_save from "./toolbar/buttons/save.png";
import btn_search from "./toolbar/buttons/search.png";
import btn_view from "./toolbar/buttons/view.png";
import { NavLink } from "react-router-dom";

import { useLocation } from "react-router-dom";

function FeatureToolbar() {
  let location = useLocation();
  console.log(location.pathname);
  return (
    <div className="toolbar">
      <nav className="toolbar__button-list">
        <NavLink to={"/create"}>
          <button
            disabled={location.pathname === "/create"}
            className="toolbar__button"
          >
            <img src={btn_add} alt="" />
          </button>
        </NavLink>

        <button
          className="toolbar__button"
          disabled={location.pathname !== "/create"}
        >
          <img src={btn_save} alt="" />
        </button>

        <NavLink to={"/edit"}>
          <button
            disabled={location.pathname === "/edit"}
            className="toolbar__button"
          >
            <img src={btn_edit} alt="" />
          </button>
        </NavLink>

        <button className="toolbar__button">
          <img src={btn_delete} alt="" />
        </button>

        <NavLink to={"/detail"}>
          <button
            disabled={location.pathname === "/detail"}
            className="toolbar__button"
          >
            <img src={btn_view} alt="" />
          </button>
        </NavLink>

        <div className="toobar__separator" />

        <button className="toolbar__button">Список</button>

        <NavLink to={"/"}>
          <button
            disabled={location.pathname === "/"}
            className="toolbar__button"
          >
            <img src={btn_search} alt="" />
          </button>
        </NavLink>

        <button className="toolbar__button">Найти</button>
      </nav>
    </div>
  );
}

export default FeatureToolbar;
