import "react-app-polyfill/ie11";
import "react-app-polyfill/stable";
import React from "react";
import ReactDOM from "react-dom";
import {Provider} from "react-redux";
import axios from 'axios'
import {OAuthWebContext, OAuthSecuredFragment} from "@jfront/oauth-ui"
import "./index.css";
import App from "./App";
import * as serviceWorker from "./serviceWorker";
import "./i18n";
import {store} from "./app/store";
import {UserContextProvider} from "@jfront/oauth-user";

ReactDOM.render(
  // <React.StrictMode>
  <OAuthWebContext
    clientId={"JepRiaShowcase"} //client_id приложения
    redirectUri={`${process.env.NODE_ENV === 'development' ? "http://localhost:3000/oauth" : `/JepRiaShowcase/react/oauth`}`}
    oauthContextPath={`${process.env.NODE_ENV === 'development' ? 'http://localhost:8080/oauth/api' : `/oauth/api`}`}
    axiosInstance={axios}
    configureAxios
  >
    <Provider store={store}>
      <UserContextProvider
        baseUrl={`${process.env.NODE_ENV === 'development' ? 'http://localhost:8080/JepRiaShowcase/api' : `/JepRiaShowcase/api`}`}>
        <OAuthSecuredFragment>
          <App/>
        </OAuthSecuredFragment>
      </UserContextProvider>
    </Provider>
  </OAuthWebContext>,
  // </React.StrictMode>
  document.getElementById("root")
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
