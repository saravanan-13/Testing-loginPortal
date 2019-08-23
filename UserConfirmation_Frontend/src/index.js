import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import * as serviceWorker from './serviceWorker';
import Header from './components/Header';
//import ConfirmMail from './components/ConfirmMail';
import {BrowserRouter as Router, Route } from "react-router-dom";
import history from './history.js';

const router = (
    <Router history={history}>
        <div>
            <Route exact path="/" component={App}/>
            <Route path="/confirmmail/:uid" component={Header}/>
        </div>
    </Router>
)

ReactDOM.render(router, document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
