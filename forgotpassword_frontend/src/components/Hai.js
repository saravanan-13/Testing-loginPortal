import React from 'react';
export default class Hai extends React.Component{
    constructor(model){
        super();
        console.log(model);
        console.log(this)
    }
    render(){
        return(
            <div>Security Questions</div>
        );
    }
}