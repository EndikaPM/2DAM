import './App.css';
import BodyComponents from './Components/BodyComponents';
import { initializeApp } from "firebase/app";


function App() {
const firebaseConfig = {
  apiKey: "AIzaSyAQx1EcWxSbEc5oP6x1ejyDp5aTq0Uhfdo",
  authDomain: "calculadorareact-32ebf.firebaseapp.com",
  projectId: "calculadorareact-32ebf",
  storageBucket: "calculadorareact-32ebf.firebasestorage.app",
  messagingSenderId: "361713999783",
  appId: "1:361713999783:web:eb8fcdeac48afa23d52082"
};
const app = initializeApp(firebaseConfig);


  return (
    <>
      <BodyComponents></BodyComponents>
    </>
  );
}

export default App;
