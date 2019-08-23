import React from 'react';
import Header from './components/Header';
//import ConfirmMail from './components/ConfirmMail';
import { shallow, mount, render } from 'enzyme';


const app = shallow(<Header/>);
const wrapper = mount(<Header/>);

const data = {
  Email:'aditisjoshi18@gmail.com',
  
  }
  
describe('Header Component Loading', () => {

  it('should render without throwing an error', () => {
    expect(shallow(<Header />).find('div.activatebtn').exists()).toBe(true)
  })

   
it('Email input type check',() => {

  const input = app.find('input[name="email"]');
  
  expect(input).toHaveLength(1);
  
  expect(input.prop('type')).toEqual('text');
  
  })
  
  it('Email check',() => {

    expect(wrapper.state().email).toEqual('');
    
    const input = wrapper.find('input[name="email"]');
    
    input.simulate('change',{
    
    target:{
    
    name:'email',
    
    value:data.Email
    
    }
    
    })
    
    expect(wrapper.state().email).toEqual(data.Email);
    
    })
    

  it('calls onSubmit function when form is submitted', () => {

    const validate = jest.fn();

    const wrapper = mount(<form onSubmit={validate} />);

    const form = wrapper.find('form');

    form.simulate('submit');

    expect(validate).toHaveBeenCalledTimes(1);

  })

 
    

})

