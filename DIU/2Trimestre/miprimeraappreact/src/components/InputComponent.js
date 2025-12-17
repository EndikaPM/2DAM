import React,{Component} from "react";

export default class ImputComponent extends Component {
    render(){
        return(
            <div>
                <input type= "text" value={this.props.nombre} onChange={this.props.cambiarNombre}/>
            </div>
        )
    }
}