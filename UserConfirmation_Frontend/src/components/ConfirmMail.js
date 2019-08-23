import React from 'react';
import axios from 'axios';

export default class ConfirmMail extends React.Component {

    constructor(){
        super();
        this.state = {
            res : ""
        }
    }

    componentDidMount(){
        console.log('In Component');
        console.log(this.props);
        axios.get('http://localhost:8881/api/v1/confirmmail/' + this.props.match.params.uid)
        .then(response => {console.log(response.data.response);  this.setState({res : response.data.response})});
    }

    render(){
        console.log('In Render');
        console.log(this.props);
        //this.id = this.props.match.params;
        //console.log(`id is ${id}`);
        return (
            <div >
                <h1>{this.state.res}</h1>
            </div>
        );
    }

}