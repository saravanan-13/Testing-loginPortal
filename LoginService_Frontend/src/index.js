import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import * as serviceWorker from './serviceWorker';
import Welcome from './components/welcome'
import { Route, Router } from 'react-router-dom'
import 'bootstrap/dist/css/bootstrap.css';
import history from './history.js'

//import BrowserRouter from 'browser-router'

const routing = (
    <Router history = {history} >
      <div>
        <Route exact path="/" component={App} />
        <Route exact path='/welcome' component={Welcome} />
      </div>
    </Router>
  )
  ReactDOM.render(
    routing, document.getElementById('root'));
// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
