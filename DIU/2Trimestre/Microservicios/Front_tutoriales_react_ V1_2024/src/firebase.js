// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyDxPlyPbyWSVLZgCAoceXLfEFXI4d6N6ys",
  authDomain: "tutorials-77204.firebaseapp.com",
  projectId: "tutorials-77204",
  storageBucket: "tutorials-77204.firebasestorage.app",
  messagingSenderId: "394430477510",
  appId: "1:394430477510:web:d9ebf9be7e3e6be175d3c7",
  measurementId: "G-0PYQ68YX7K"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const analytics = getAnalytics(app);