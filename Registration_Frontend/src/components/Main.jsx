import React, { Component } from 'react';
import axios from 'axios';

export default class Main extends Component {

    constructor() {
        super();
        this.state = {};
    }

    componentDidMount() {
        axios.get(`http://localhost:8883/`).then(res=>{
            // console.log(res.data);
            this.setState(res.data);
        })
    }

    render() {
        return (
            <div className="App">
                <h1>{this.state.name}</h1>
                <br/>
                <h2>{this.state.email}</h2>
            </div>
        );
    }
}
