import React, { useState } from "react";
import * as math from "mathjs";

function BodyComponents() {

    const [num, setNum] = useState({
        first: null,
        second: null,
        operation: null
    });

    const [pantalla, setPantalla] = useState("0");

    const handlerNumberClick = (e) => {
        if (e === "AC") {
            setPantalla("0");
            setNum({
                first: null,
                second: null,
                operation: null
            });
            return;
        }

        if (e === "+" || e === "-" || e === "*" || e === "/") {
            if (num.first !== null && num.second!== null && num.operation !== null) {
                const res = math.evaluate(`${num.first} ${num.operation} ${num.second}`);
                
                setPantalla( String(res));
                setNum({
                    first: String(res),
                    second: null,
                    operation: e
                });
            }else{
                setNum({
                    first: num.first,
                    second: null,
                    operation: e
                });
            }

            return;
        }

        if (e === "=") {
            if (num.first !== null && num.second !== null && num.operation !== null) {
                const res = math.evaluate(`${num.first} ${num.operation} ${num.second}`);
                
                setPantalla( String(res));
                setNum({
                    first: String(res),
                    second: null,
                    operation: null
                });
            }
        }

        if (e === ".") {
            // Determinamos qué número estamos editando
            const numeroActual = num.operation === null ? (num.first || "0") : (num.second || "0");

            if (!numeroActual.includes(".")) {
                const nuevoValor = numeroActual + ".";
                setPantalla(nuevoValor); // Actualizamos visualmente

                if (num.operation === null) {
                    setNum({
                        first: nuevoValor,
                        second: num.second,
                        operation: num.operation
                    });
                } else {
                    setNum({
                        first: num.first,
                        second: nuevoValor,
                        operation: num.operation
                    });
                }
            }
            return;
        }
        // CAMBIO DE SIGNO
        if (e === "+/-") {
            setPantalla((prev) => (prev.startsWith("-") ? prev.slice(1) : "-" + prev));
            const numeroActual = num.operation === null ? (num.first || "0") : (num.second || "0");

            if (num.operation === null) {
                if (numeroActual.startsWith("-")) {
                    const nuevoValor = numeroActual.slice(1);
                    setNum({
                        first: nuevoValor,
                        second: null,
                        operation: null
                    });
                } else {
                    const nuevoValor = "-" + numeroActual;
                    setNum({
                        first: nuevoValor,
                        second: null,
                        operation: null
                    });
                }
            } else {
                if (numeroActual.startsWith("-")) {
                    const nuevoValor = numeroActual.slice(1);
                    setNum({
                        first: num.first,
                        second: nuevoValor,
                        operation: num.operation
                    });
                } else {
                    const nuevoValor = "-" + numeroActual;
                    setNum({
                        first: num.first,
                        second: nuevoValor,
                        operation: num.operation
                    });
                }
            }
            return;
        }

        if (!isNaN(e)) {
            if (num.operation === null) {
                // Editando el primer número: Concatenamos el valor anterior con el nuevo
                const nuevoValor = (num.first === null || num.first === "0") ? e : num.first + e;
                setPantalla(nuevoValor);
                setNum({
                    first: nuevoValor,
                    second: num.second,
                    operation: null
                });
            } else {
                // Editando el segundo número
                const nuevoValor = (num.second === null || num.second === "0") ? e : num.second + e;
                setPantalla(nuevoValor);
                setNum({
                    first: num.first,
                    second: nuevoValor,
                    operation: num.operation
                });
            }
        }

        if (e === "%") {
            const numeroActual = num.operation === null ? (num.first || "0") : (num.second || "0");
            const nuevoValor = (math.evaluate(`${numeroActual} / 100`));
            setPantalla(nuevoValor);

            if (num.operation === null) {
                setNum({
                    first: String(nuevoValor),
                    second: null,
                    operation: null
                });
            } else {
                setNum({
                    first:  num.first,
                    second: String(nuevoValor),
                    operation: num.operation
                });
            }
            return;
        }
    }

    return (
        <div className="numeros-calculadora">
            <table>
                <thead>
                    <th colSpan={4}><h2 className="pantalla">{pantalla}</h2></th>
                </thead>
                <tbody>
                    <tr>
                        <td><button onClick={() => handlerNumberClick("AC")}>AC</button></td>
                        <td><button onClick={() => handlerNumberClick("+/-")}>+/-</button></td>
                        <td><button onClick={() => handlerNumberClick("%")}>%</button></td>
                        <td><button className="operaciones" onClick={() => handlerNumberClick("/")}>÷</button></td>
                    </tr>
                    <tr>
                        <td><button onClick={() => handlerNumberClick("7")}>7</button></td>
                        <td><button onClick={() => handlerNumberClick("8")}>8</button></td>
                        <td><button onClick={() => handlerNumberClick("9")}>9</button></td>
                        <td><button className="operaciones" onClick={() => handlerNumberClick("*")}>x</button></td>
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
                        <td><button onClick={() => handlerNumberClick(".")}>.</button></td>
                        <td><button className="operaciones" onClick={() => handlerNumberClick("=")}>=</button></td>
                    </tr>
                </tbody>
            </table>
        </div>
    )
}

export default BodyComponents;
