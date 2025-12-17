import { useState } from 'react';
import './App.css';

function App() {

  const[count, setCount] = useState(0);

  return (
    <div className="container-counter">
      <div className="counter">
        <h1>Contador Pulsaciones</h1>
        <p>Has pulsado {count} veces.</p>
      </div>
      <div className="buttons">
        <button className='incButton' onClick={() => setCount(count + 1)}>Pulsame para +</button>
        <p> </p>
        <button className='incButton' onClick={() => setCount(count - 1)}>Pulsame para -</button>
      </div>
    </div>
  );
}

export default App;
