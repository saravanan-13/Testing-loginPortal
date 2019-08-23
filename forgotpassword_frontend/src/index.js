import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import * as serviceWorker from './serviceWorker';
import {Router,Route} from 'react-router-dom';
import ForgotPassword from './components/ForgotPassword';
import Hai from './components/Hai';
import Login from './components/Login';
import Selected from './components/Selected';
import Setpassword from './components/Setpassword';
import  history  from './history.js';

const router = (
    <Router history={history}>
        <div>
            <Route exact path="/" component={App}></Route>
            <Route exact path="/forgotpassword" component={ForgotPassword}></Route>
            <Route exact path="/forgotpassword/Setpassword" component={Setpassword}></Route>
            <Route exact path="/hai" component={Hai}></Route>
            <Route exact path="/selected" component={Selected}></Route>
            <Route exact path="/Login" component={Login}></Route>
            
        </div>
    </Router>
)

ReactDOM.render(router, document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
