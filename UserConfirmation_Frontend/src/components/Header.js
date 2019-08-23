import React from 'react';
import axios from 'axios';
import Loader from 'react-loader';
export default class Header extends React.Component {

    constructor(props) {
        super(props);
        this.state = { value: '', res: '', email:'',flag: false , btnflag : false , submitbtn : true };

        /*setTimeout(function () {
            this.setState({
                flag: true
            })
        }.bind(this), 5000);
*/
        this.handleChange = this.handleChange.bind(this);
        this.handleEmail= this.handleEmail.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        this.setState({ value: event.target.value });
    }

    handleEmail(event) {
        this.setState({ email: event.target.value });
    }


    handleSubmit(event) {
        //alert('A name was submitted: ' + this.state.value);
        event.preventDefault();
        //Fetch email from uid
        this.setState({flag:true})
        //this.props.match.params.uid
        //var uid = this.props.match.params.uid;
        axios.post('http://localhost:8001/api/v1/sendmail/',{emailID:this.state.email})
            .then(res => { console.log(res); this.temp = res; 
                if(this.temp.data.response === 'Congrats! Your account has been successfully activated...' ||
                this.temp.data.response === 'User already verified'){
                    this.setState({ btnflag : true, res: this.temp.data.response , submitbtn : false})
                }
                else{
                    this.setState({ res: this.temp.data.response , submitbtn : false });
                }
                //this.setState({ res: this.temp.data.response });
            })
            .then(() => {
                setTimeout(function () {
                    this.setState({
                        flag: false
                    })
                }.bind(this), 2000);
                
            }).catch(() =>
            setTimeout(function () {
                this.setState({
                flag: false
             })
             }.bind(this), 2000));;

        /*
         <label>
                    UId:
                    <input type="text" value={this.state.value} onChange={this.handleChange}/>
                    </label>
        */
    }

    render() {
        console.log('In Header.js');
        return (
            <div >
                {
                    this.state.flag ?  <Loader 
                    color="blue"
                    position="absolute"
                    top="25%"
                    /> : 
                    <div>
                        
                        <div className="activatebtn">
                        { this.state.submitbtn ?
                        <form onSubmit={this.handleSubmit}>
                            Email id : <input type="text" id="emailid" name="email" onChange={this.handleEmail}></input>
                            <br></br>
                            <br></br>
                            <input className="btn btn-primary" id="submit" type="submit" value="Activate" />
                        </form>
                        :
                        <div></div>
                        }
                        </div>
                        <div className="confirm_msg">
                        <h3>{this.state.res}</h3>
                        {
                            this.state.btnflag ? <div>
                                To Login click here <a href="http://localhost:8014">Login</a>
                                </div>
                                :
                                <div></div>

                        }
                        </div>
                    </div>
                }
            </div>

        );
    }



}