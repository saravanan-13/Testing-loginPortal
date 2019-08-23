import React, {Component} from 'react';

import './App.css';
import Forms from './components/Forms'
// import {Button , Form , FormGroup, label, Input, FormFeedback}
// from 'reactstrap';
//import Axios from 'axios';
//import ReactDOM from 'react-dom';



class App extends Component {

  state={
    userData: []
  }
 
 render()
 {
   return (
    
      <Forms />

   )
 }

}

export default App;
