import React, { useState } from "react";
import calculate from "./calculate";

function BodyComponents() {
    const [cal, setCal] = useState({
        total : null,
        next : null,
        operation : null
});
    const[valor, setValor] = useState("0");
    const handlerNumberClick = (e) => {

        const resultado = calculate(cal, e);
        setCal({
            ...cal,
            ...resultado
        });

        setValor(cal.next || cal.total || "0");
    }

    return (
        <div className="numeros-calculadora">
            <table>
                <thead>
                    <th colSpan={4}><h2 className="pantalla">{valor}</h2></th>
                </thead>
                <tbody>
                    <tr>
                        <td><button onClick={()=>handlerNumberClick("AC")}>AC</button></td>
                        <td><button onClick={()=>handlerNumberClick("+/-")}>+/-</button></td>
                        <td><button onClick={()=>handlerNumberClick("%")}>%</button></td>
                        <td><button className="operaciones" onClick={()=>handlerNumberClick("÷")}>÷</button></td>
                    </tr>
                    <tr>
                        <td><button onClick={() => handlerNumberClick("7")}>7</button></td>
                        <td><button onClick={() => handlerNumberClick("8")}>8</button></td>
                        <td><button onClick={() => handlerNumberClick("9")}>9</button></td>
                        <td><button className="operaciones" onClick={()=>handlerNumberClick("x")}>x</button></td>
                    </tr>
                    <tr>
                        <td><button onClick={() => handlerNumberClick("4")}>4</button></td>
                        <td><button onClick={() => handlerNumberClick("5")}>5</button></td>
                        <td><button onClick={() => handlerNumberClick("6")}>6</button></td>
                        <td><button className="operaciones" onClick={() => handlerNumberClick("-")}>-</button></td>
                    </tr>
                    <tr>
                        <td><button onClick={() => handlerNumberClick("1")}>1</button></td>
                        <td><button onClick={() => handlerNumberClick("2")}>2</button></td>
                        <td><button onClick={() => handlerNumberClick("3")}>3</button></td>
                        <td><button className="operaciones" onClick={() => handlerNumberClick("+")}>+</button></td>
                    </tr>
                    <tr>
                        <td colSpan={2}><button className="botn-cero" onClick={() => handlerNumberClick("0")}>0</button></td>
                        <td><button onClick={()=>handlerNumberClick(".")}>.</button></td>
                        <td><button className="operaciones" onClick={()=>handlerNumberClick("=")}>=</button></td>
                    </tr>
                </tbody>
            </table>
        </div>
    )
}

export default BodyComponents