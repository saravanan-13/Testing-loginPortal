import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import { shallow,mount } from 'enzyme';
import {sum} from './components/math';
import ForgotPassword from './components/ForgotPassword';
import Setpassword from './components/Setpassword';
import Securityquestions from './components/Securityquestions';
import OTPtoMail from './components/OTPtoMail';

it('renders without crashing', () => {
  const div = document.createElement('div');
  ReactDOM.render(<App />, div);
  ReactDOM.unmountComponentAtNode(div);
});

describe('Examining the syntax of Jest tests', () => {
   
  it('sums numbers', () => {
      expect(1 + 2).toEqual(3);
      expect(2 + 2).toEqual(4);
   });

   it('sum numbers from function',()=>{
      expect(sum(2,31)).toEqual(33);
   });
});

describe('First React component test with Enzyme', () => {
  it('renders without crashing', () => {
     shallow(<App />);
   });
});

describe('Test Button component', () => {
  it('Test click event', () => {
    const button = mount(<ForgotPassword/>);
    const ema = button.find('input').at(0);
    //ema.value="mamidid@gmail.com";
    expect(button.state().alltrue).toEqual(true);
    ema.instance().value="mamid";
    ema.simulate('change');
    ema.simulate('keyup');
    console.log(button.state());
    button.find('.bbb').simulate('click');
    expect(button.state().alltrue).toEqual(false);
  });
});

describe('Test Button component', () => {
  it('Test click event', () => {
    const button = mount(<ForgotPassword/>);
    const ema = button.find('input').at(0);
    //ema.value="mamidid@gmail.com";
    expect(button.state().alltrue).toEqual(true);
    ema.instance().value="mamidid@gmail.com";
    ema.simulate('change');
    ema.simulate('keyup');
    console.log(button.state());
    button.find('.bbb').simulate('click');
    expect(button.state().alltrue).toEqual(true);
  });
});

it('test field event',()=>{
  const email=mount(<ForgotPassword/>);
  const e = email.find('input').at(0);
  expect(e.instance().value).toEqual("");
  e.instance().value="mamidid@gmail.com";
  expect(e.instance().value).toEqual("mamidid@gmail.com");
});

it('test field event',()=>{
  const otp=mount(<OTPtoMail/>);
  const o=otp.find('input').at(0);
  expect(o.instance().value).toEqual("");
  o.simulate('change');
  o.instance().value="password";
  otp.find('.bbb').simulate('click');
  expect(o.instance().value).toEqual("password");
  //window.location.href = '/auth/'
  //expect(window.location.href).toEqual('http://localhost:8886/auth/');

});

it('Security questions check',()=>{
  const secure=mount(<Securityquestions/>);
  const s=secure.find('input').at(0);
  const e=secure.find('input').at(1);
  expect(s.instance().value).toEqual("");
  s.instance().value="mahesh";
  e.instance().value="nassar"
  console.log(secure.state());
  //secure.find('.bbb').simulate('click');
  expect(s.instance().value).toEqual("mahesh");
  expect(e.instance().value).toEqual("nassar");
});
it('For SetPassword check',()=>{
  const secure=mount(<Setpassword/>);
  const s=secure.find('input').at(0);
  const e=secure.find('input').at(1);
  expect(s.instance().value).toEqual("");
  s.instance().value="mahesh";
  e.instance().value="mahesh";
  console.log(secure.state());
 
  expect(s.instance().value).toEqual(e.instance().value);
});