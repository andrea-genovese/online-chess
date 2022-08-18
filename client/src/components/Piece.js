import { Component } from "react";

export default class Piece extends Component {
    constructor(props) {
        super(props)
        this.state = {
            position: { row: props.row, column: props.column }
        }
        //this.logRookMoves()    
    }
    logRookMoves(){
        if(this.props.row === 3 && this.props.column === 4) {
           
            this.getRookMoves().forEach(({dest}) => {
           
                this.props.highlight(dest.row, dest.column)
            })
        }
    }
    render() {
        return <img className="piece" src={`/pieces/${this.props.isWhite ? 'light' : 'dark'}_${this.__proto__.constructor.name}.svg`}></img>
    }
    shouldContinue(moves, r, c) {
        const p = this.props.board.state.board[r][c]
 
        if (p && p.isWhite === this.props.isWhite) {
            return false
        }
        moves.push({ start: this.state.position, dest: { row: r, column: c } })
        if (p) {
            return false
        }
        return true
    }
    getBishopMoves() {
        let row = this.row
        let col = this.column
        const moves = []
        //diagonale basso-destra
        for (let r = row + 1, c = col + 1; r < 8 && c < 8; r++, c++) {
            if (!this.shouldContinue(moves, r, c)) {
                break;
            }
        }
        //diagonale basso sinistra
        for (let r = row + 1, c = col - 1; r < 8 && c >= 0; r++, c--) {
            if (!this.shouldContinue(moves, r, c)) {
                break;
            }
        }
        //diagonale alto destra
        for (let r = row - 1, c = col + 1; r >= 0 && c < 8; r--, c++) {
            if (!this.shouldContinue(moves, r, c)) {
                break;
            }
        }
        //diagonale alto sinistra
        for (let r = row - 1, c = col - 1; r >= 0 && c >= 0; r--, c--) {
            if (!this.shouldContinue(moves, r, c)) {
                break;
            }
        }
        return moves
    }
    getRookMoves() {
        const moves = []
        const row = this.state.position.row
        const col = this.state.position.column
        
        for (let r = row + 1; r < 8; r++) {
            if(!this.shouldContinue(moves, r, col)){
                break
            }
        }
        for (let r = row - 1; r >= 0; r--) {
            if(!this.shouldContinue(moves, r, col)){
                break
            }
        }
        for (let c = col + 1; c < 8; c++) {
            if(!this.shouldContinue(moves, row, c)){
                break
            }
        }
        for (let c = col - 1; c >= 0; c--) {
            if(!this.shouldContinue(moves, row, col)){
                break
            }
        }
        return moves
        
        
        
    }
}
