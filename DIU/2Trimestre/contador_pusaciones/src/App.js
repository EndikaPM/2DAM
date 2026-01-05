import { Component, useState } from 'react';
import './App.css';
import CntadorComponent from './components/CntadorComponent';

function App() {

  const[count, setCount] = useState(0);

    return (
      <CntadorComponent></CntadorComponent>
    );
}

export default App;
