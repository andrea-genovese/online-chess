import { Component } from "react";

export default class Piece extends Component {
    render(){
        return <img className="piece" src={`/pieces/${this.props.isWhite ? 'light' : 'dark'}_${this.props.type}.svg`}></img>
    }
}