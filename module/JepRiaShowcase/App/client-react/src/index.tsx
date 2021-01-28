import "react-app-polyfill/ie11";
import "react-app-polyfill/stable";
import React from "react";
import ReactDOM from "react-dom";
import { Provider } from "react-redux";
import axios from 'axios'
import { OAuthWebContext, OAuthSecuredFragment } from "@jfront/oauth-ui"
import "./index.css";
import App from "./App";
import * as serviceWorker from "./serviceWorker";
import "./i18n";
import { store } from "./app/store";

ReactDOM.render(
  // <React.StrictMode>
  <OAuthWebContext
    clientId={"JepRiaShowcase"} //client_id приложения
    redirectUri={"/JepRiaShowcase/react/oauth"} //Библиотека по умолчанию использует следующий формат URL’а scheme:[//authority]/context_path/oauth 
    oauthContextPath={"/oauth/api"} //Ссылка на Context path сервисов oauth
    axiosInstance={axios}
    configureAxios
  >
    <Provider store={store}>
      <OAuthSecuredFragment>
        <App />
      </OAuthSecuredFragment>
    </Provider>
  </OAuthWebContext>,
  // </React.StrictMode>
  document.getElementById("root")
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
