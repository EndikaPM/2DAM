import { use, useEffect, useState } from 'react'
import './App.css'
import HederComponet from './componets/HederComponet'
import ButtonComponet from './componets/ButtonComponet'
import Login from './componets/login'
import MovieList from './componets/MovieList'
import AnimalList from './componets/AnimalList'
import MemeList from './componets/MemeList'

function App() {



  let myPlaceHolder = "Escribe aquí"
  const condition = true;
  //let number = 0;
  const [number, setNumber] = useState(0);
  const [myValue, setMyValue] = useState("")
  const[greeting, setGreeting] = useState("Bienvenidos a mi web!")
  const links = {
    home: "Home",
    blog: "Blog",
    news: "News",
    contact: "Contact Us"
  }

  const [user, setUser] = useState({}) 


  /*  useEffect(()=>{
    console.log("Ejecucion cada vez que se renderiza un compoene");
  })*/

  /*  useEffect(()=>{
      console.log("Ejecucion con cada cambio de la variable reactiva user")
    }, [user])*/

  const addUsersInfo =(userInfo)=>{
    console.log(userInfo);
    setUser(userInfo)
  }

  const addOne = () =>{
    //number++; nunca debemos editar el valor directamente siempre desde su funcion
    setNumber (number  + 1); 
    console.log(number);
  }

  const sayHello = () =>{
    console.log("Hello!")
  }

  const handleChange = (e) =>{
    console.log(e.target.value);
  }  

  const [showMovies, setShowMovies] = useState(true);

  return (
    <>

{/*El nombre de este debe ser el mismo que el Siempre el mismo par que lo reconozca*/}
    <HederComponet saludos={greeting} links={links} ></HederComponet>

    <main className='main-content'>

      <MemeList></MemeList>
    {/*
    {user.userName && <h2 onClick={sayHello}>Hola {user.userName}</h2>}
    <Login handleLogin={addUsersInfo} ></Login>
  
    <br/>
  <button onClick={()=> setShowMovies(!showMovies) }>Toggle Movies</button>
  {showMovies && <MovieList></MovieList>} 
  <AnimalList></AnimalList>

    
    <h2 onClick={addOne}>Number: {number}</h2>

    <br />
    <input value={myValue} placeholder={myPlaceHolder} type="text"  onChange={handleChange} />
    <br />
    <br />
    <ButtonComponet></ButtonComponet>
      {condition && <h2>La condicion se cumple</h2>}
    {!condition && <h2>La condicion no se cumple</h2>}


    {condition ? (<h2>La condicion se cumple</h2>) : <h2>La condicion no se cumple</h2>}
    descomentar para mas poner lo demas*/}

    </main>

    </>
  )
}

export default App
