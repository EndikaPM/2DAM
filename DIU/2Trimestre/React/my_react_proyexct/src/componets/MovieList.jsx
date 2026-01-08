import { useEffect } from "react";

function MovieList() {

    const movies = ["Lord of the rings", "Star Wars", "Dune"];

    const htmlMovies = movies.map((movie, index)=>{
        return <p key={movie}>{index +1} - {movie}</p>
    })

    useEffect(()=>{
        console.log("MovieList Mounted");
        
    }, [])//si le ponemos un array vacio solo se monta una vez

    useEffect(()=>{
        return() =>{
            console.log("MovieList Unmonted");
            
        }
        
    }, [])

    return (
        <div>
            <h2>Movies</h2>
        
            {htmlMovies}
        </div>
    )
}

export default MovieList