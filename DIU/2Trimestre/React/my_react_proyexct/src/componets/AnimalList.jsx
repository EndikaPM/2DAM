import React, { Component } from 'react'

const listAnimal = [
    {   
        id:0,
        name: "Gato",
        img: "https://imgs.search.brave.com/0CKztN3aV-zJOKJiLNZplljHzVLFB-mlI0vysye_VDk/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly9pY2hl/Zi5iYmNpLmNvLnVr/L2FjZS93cy82NDAv/Y3BzcHJvZHBiL2Qy/MmQvbGl2ZS8yYmM2/NDczMC1jYzUwLTEx/ZjAtOWZiNS01ZjNh/MzcwM2EzNjUuanBn/LndlYnA"
    },
    {
        id: 1,
        name: "Perro",
        img: "https://imgs.search.brave.com/ynFiAX4tx4GQmuolq9qkgWY8sJxxYxiA7heEsRiIPcA/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly9jb250/ZW50Lm5hdGlvbmFs/Z2VvZ3JhcGhpYy5j/b20uZXMvbWVkaW8v/MjAyNC8wOS8yMy9w/ZXJyby10cmlzdGUt/aXN0b2NrLWtlcmtl/el85NDkwMmYyN18y/NDA5MjMxNDIzNThf/ODAweDgwMC5qcGc"
    },
    {
        id: 3,
        name: "Pajaro",
        img: "https://imgs.search.brave.com/K5IA8kQjlPS-uanpIkJjBZ8acglRp8MB2NO7g16-CYM/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly9tZWRp/YS5pc3RvY2twaG90/by5jb20vaWQvMTQ5/NTI1MzgwNC9waG90/by9iaXJkLWNhcmRp/bmFsLmpwZz9zPTYx/Mng2MTImdz0wJms9/MjAmYz14QklWYWJx/dWpGVjV5YUJTZHlR/Y09vbVMwMDBLUlBs/dDhXQkJuX3ZlekhZ/PQ"
    },
];

const htmlAnimales = listAnimal.map((animal)=>{
    return (
        <li key={animal.id}>
            <h3>{animal.name }</h3>
            <img src={animal.img} alt="imagen animales" width={200}/>
        </li>
    )
})

export default class AnimalList extends Component {
    render() {
        return (
            <section>
                <h2>Animales:</h2>
                <ul>
                    {htmlAnimales}
                </ul>
            </section>
        )
    }
}
