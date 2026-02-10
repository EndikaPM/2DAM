import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

function App() {
  const [texto, setTexto] = useState('')
  const [modoOscuro, setModoOscuro] = useState(true)

  return (
    <div className={`app ${modoOscuro ? 'modo-oscuro' : 'modo-claro'}`}>
      {/* Header con logo y toggle de modo */}
      <header className="header">
        <div className="logo-container">
          <div className="logo">
            <svg viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
              <circle cx="12" cy="12" r="10" stroke="white" strokeWidth="2"/>
              <path d="M12 6V12L16 14" stroke="white" strokeWidth="2" strokeLinecap="round"/>
              <circle cx="12" cy="12" r="1.5" fill="white"/>
            </svg>
          </div>
          <h1 className="titulo">Timely</h1>
        </div>
        <button 
          className="toggle-modo"
          onClick={() => setModoOscuro(!modoOscuro)}
          title={modoOscuro ? 'Cambiar a modo claro' : 'Cambiar a modo oscuro'}
        >
          {modoOscuro ? (
            <svg viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
              <circle cx="12" cy="12" r="4" fill="currentColor"/>
              <path d="M12 2v2m0 16v2M4.93 4.93l1.41 1.41m11.32 11.32l1.41 1.41M2 12h2m16 0h2M4.93 19.07l1.41-1.41m11.32-11.32l1.41-1.41" stroke="currentColor" strokeWidth="2" strokeLinecap="round"/>
            </svg>
          ) : (
            <svg viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
              <path d="M21 12.79A9 9 0 1 1 11.21 3 7 7 0 0 0 21 12.79z" fill="currentColor"/>
            </svg>
          )}
        </button>
      </header>

      <div className="container">
      {/* Columna izquierda - 1/3 del espacio */}
      <div className="columna-izquierda">
        <button className="boton-centro">
          Fichar Entrada / Salida
        </button>
      </div>

      {/* Columna derecha - 2/3 del espacio */}
      <div className="columna-derecha">
        {/* Ventana que muestra el texto */}
        <div className="ventana-texto">
          {texto || 'Investigar cómo hacer un chat con WebSocket y Socket.io'}
        </div>

        {/* Contenedor inferior con botón y campo de texto */}
        <div className="contenedor-inferior">
          <button className="boton-accion">
            Enviar
          </button>
          <input 
            type="text" 
            className="campo-texto"
            value={texto}
            onChange={(e) => setTexto(e.target.value)}
            placeholder="Investigar cómo hacer un chat con WebSocket y Socket.io"
          />
        </div>
      </div>
    </div>
    </div>
  )
}

export default App
