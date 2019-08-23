import React from 'react';
import logo from './logo.svg';
import './App.css';
import Admin from './components/admin';

function App() {
  return (
    <div>
      <Admin />
    </div>
  );
}

const styleLink = document.createElement("link");
styleLink.rel = "stylesheet";
styleLink.href = "https://cdn.jsdelivr.net/npm/semantic-ui/dist/semantic.min.css";
document.head.appendChild(styleLink);

export default App;

