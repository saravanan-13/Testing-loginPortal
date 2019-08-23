import React from 'react';
import '../App.css';
//import {Button , div, label, Input, Form , FormGroup}
//from 'reactstrap';
import Axios from 'axios';
//import { browserHistory } from 'react-router';
//import Axios from 'axios';
// import {FacebookLoginButton} from 'react-social-login-buttons';
import { BrowserRouter as Router, Link } from 'react-router-dom';
//import Welcome from '../components/Welcome'
//import index from '/index'

class Forms extends React.Component {

  constructor() {
    super();
    this.state = {
      username: '',
      hpassword: '',
      fields: {
        username: '',
        hpassword: ''
      },
      errors: {},
      // userObject: {}
      userObject: {
        userID: '',
        passwordHistory: {
          pwd1: ''
        }
      },
      emailIDObject: {
        emailID: '',
        passwordHistory: {
          pwd1: ''
        }
      }

    }
    // this.sendData=this.sendData.bind(this)
    this.eventhandler = this.eventhandler.bind(this);
    this.user = this.user.bind(this);
    this.clear = this.clear.bind(this);
    this.validateEmailorUserID = this.validateEmailorUserID.bind(this);
    this.validateForm = this.validateForm.bind(this);
    this.axiosReponse = this.axiosReponse.bind(this);
  }

  eventhandler(e) {
    //console.log(this.state);
    //this.setState({[e.target.name] : e.target.value})
    let fields = this.state.fields;
    fields[e.target.name] = e.target.value;
    this.setState({
      fields
    });


  }

  validateEmailorUserID(args) {
    var mailformat = /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/
    console.log(`User ${mailformat.test(args)}`)
    return mailformat.test(args) ? true : false
  }



  validateForm() {
    //let userObject = this.state.userObject;
    let userObject = this.state.userObject;
    let emailIDObject = this.state.emailIDObject;
    let fields = this.state.fields;
    let errors = {};
    let formIsValid = true;
    let flag = this.validateEmailorUserID(fields.username);

    if (!fields["username"]) {
      formIsValid = false;
      errors["username"] = <font color="red">*This Field can not be empty</font>
    }
    if (!flag) {
      userObject["userID"] = this.state.fields.username;
      userObject.passwordHistory["pwd1"] = this.state.fields.hpassword;

      if (typeof fields["username"] !== "undefined") {
        if (!fields["username"].match(/^[0-9]*$/)) {
          formIsValid = false;
          errors["username"] = <font color="red">*Please enter valid userID only.</font>
        }
      }
    }
    if (flag) {
      emailIDObject["emailID"] = this.state.fields.username;
      emailIDObject.passwordHistory["pwd1"] = this.state.fields.hpassword;
      if (typeof fields["username"] !== "undefined") {
        //regular expression for email validation
        var pattern = new RegExp(/^(("[\w-\s]+")|([\w-]+(?:\.[\w-]+)*)|("[\w-\s]+")([\w-]+(?:\.[\w-]+)*))(@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][0-9]\.|1[0-9]{2}\.|[0-9]{1,2}\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\]?$)/i);
        if (!pattern.test(fields["username"])) {
          formIsValid = false;
          errors["username"] = <font color="red">*Please enter valid emailID only.</font>
        }
      }
    }

    if (!fields["hpassword"]) {
      formIsValid = false;
      errors["hpassword"] = <font color="red">*This Field can not be empty</font>
    }

    if (typeof fields["hpassword"] !== "undefined") {
      if (!fields["hpassword"].match(/^.*(?=.{8,})(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%&]).*$/)) {
        formIsValid = false;
        errors["hpassword"] = <font color="red">*Please enter Strong and Valid Password</font>
      }
    }

    this.setState({
      errors: errors,

    });
    return formIsValid;

  }
  axiosReponse(url, obj) {
    //console.log(`axiosReposne Called with url: ${url} and Object :${obj.passwordHistory.pwd1}`)
    Axios.post(url, obj)
      .then(
        res => {
          console.log(res.data.loginStatusMessage)
          
      if (res.data.loginStatusMessage === "Authenticated") {
       // alert(res.data.loginStatusMessage)
        // window.location.assign("http://10.150.121.200:8014/welcome");
        window.location.replace("http://localhost:8014/#/welcome");
       // this.props.history.push('/welcome');
      }
      else if((res.data.loginStatusMessage === "Not a confirmed user")) {
        //alert(res.data.loginStatusMessage)
        let uid = res.data.userID;
         window.location.assign(`http://localhost:8002/#/`); 
       // window.location.assign("http://10.150.176.239:8882/#/");       

        // Axios.post('http://localhost:8002',{"userid":uid}).then()


      }
      else if((res.data.loginStatusMessage === "Incorrect password")) {
        //alert(res.data.loginStatusMessage)
        document.getElementById("showError").innerHTML="You have Entered Incorrect Password"
      }
      else{
        //alert(res.data.loginStatusMessage)
        document.getElementById("showError").innerHTML="User Doesn't Exists"
      }
         }

      ).catch(error => {
        console.log(error.response)
      });
  }


  user(arg) {
    arg.preventDefault()
    
    if(this.state.fields.username === "admin@admin.com" && this.state.fields.hpassword === "Admin@123"  )
     {
       //Theta API
      //  window.location.assign("http://10.150.120.170:8021/#")
     window.location.assign("http://localhost:8021/#")
     }
    else{
      //SouL API
    // let url = "http://10.150.223.117:8013/api/authenticate";
    let url = "http://localhost:8013/api/authenticate"
    if (this.validateForm()) {
      // console.log(`email: ${this.state.emailIDObject.emailID}`)
      // console.log(`EmailID Password: ${this.state.emailIDObject.password.pwd1}`)
      // console.log(`UserID: ${this.state.userObject.userID}`)
      // console.log(`UserIDPassword: ${this.state.userObject.password.pwd1}`)

      if (this.validateEmailorUserID(this.state.fields.username)) {
        console.log("In email axios method")
        
        this.axiosReponse(url, this.state.emailIDObject)                                    

      }
      else {
        console.log("In User axios method")
      //this.axiosReponse(url, this.state.userObject);
      this.axiosReponse(url, this.state.userObject)
      }



      let userObject = { passwordHistory: {} }
      let emailIDObject = { emailID: '', passwordHistory: { pwd: '' } }
      
      userObject["userID"] = "";
      userObject["passwordHistory"]["pwd1"] = "";

   
      this.setState({
        userObject: userObject,
        emailIDObject: emailIDObject,
        fields:{
        username: '',
        hpassword: ''
      }});
    }
  }

  }
  
  clear = () => {

    // this.setState({
    //   username: '',
    //   hpassword: ''
    // })
  }


  render() {
    //console.log(this.state);
    return (

      <form className="form" id="form" action="/">
        <div className="text-center">
          <h1 className="text-center">Login Page</h1>


          <label>UserId/Email:</label>
          <input id="uname" type="text" name="username" value={this.state.fields.username} ref="clearUname" onChange={this.eventhandler} placeholder="UserId/Email" required></input>
          <div className="errorMsg">{this.state.errors.username}</div>
          <br />
          <br />
          <label>Password:</label>
          <input id="pwd" type="password" name="hpassword" value={this.state.fields.hpassword} ref="clearPwd" onChange={this.eventhandler} placeholder="Password" required></input>
          <div className="errorMsg">{this.state.errors.hpassword}</div>
          <br />
          <br />
          <button id="login" type="submit" onClick={this.user} >Log in</button>
          <button onClick={this.clear} >Reset</button>

          {/* <div className="text-center pt-3" >or continue with Social Login</div>
        <FacebookLoginButton className="mt-3 mb-3"/> */}
        </div>

       
          <div className="text-center">
            {/* CTC API */}
            {/* <a href="http://10.150.120.186:8006/#">Sign Up</a> */}
            <a href="http://localhost:8006/#">Sign Up</a>
            <span className="p-2">|</span>
            {/* Ureka API */}
            {/* <a href="http://10.150.176.134:8010/#/forgotpassword">Forgot Password</a> */}
            <a href= "http://localhost:8010/#/forgotpassword"> Forgot Password </a>
            <br />
            <br/>
            <br/>
            <hr/>
            <span id="showError"></span>
          </div>


        }
        
    </form>

    )
  }
}

export default Forms;
