import React, {Component} from "react";

export default class CntadorComponent extends Component {

    constructor(props) {
        super(props);
        this.state = {
            count: 0
        };
    }

    incrementar = () => {
        this.setState({ count: this.state.count + 1 });
    }

    decrementar = () => {
        this.setState({ count: this.state.count - 1 });
    }

    resetear = () => {
        this.setState({ count: 0 });
    }
    
    render() {
        return (
        <div className="container-counter">
            <div className="counter">
                <h1 className='titulo'>Contador Pulsaciones</h1>
                <p>Has pulsado: {this.state.count} veces.</p>
            </div>
            <div className="buttons">
                <button className='incButton' onClick={this.incrementar}>Pulsame para +</button>
                <button className='incButton' onClick={this.resetear}>Reset</button>
                <button className='incButton' onClick={this.decrementar}>Pulsame para -</button>
            </div>
        </div>
        )
    }
}