import { Component } from "react"
import Square from "../components/Square"
import "./chessboard.css"
import Piece from "../components/Piece"
export default class Chessboard extends Component {
    constructor(props) {
        super(props)
        let board = this.fenToArr(this.props.fen)
        console.log(board)
        this.state = {
            board
        }

    }
    fenToArr(fen) {
        if (!fen) fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"
        const arr = fen.split(" ");
        const position = arr[0]
        return this.boardFromString(position);
    }
    boardFromString(position) {
        const rows = position.split("/");
        const board = [];
        if (rows.length != 8) {
            throw "Wrong number of rows";
        }
        for (let i = 0; i < rows.length; i++) {
            board[i] = this.rowFromString(rows[i], i);
        }
        return board;
    }
    rowFromString(rowStr, rowIndex) {
        let row = [null, null, null, null, null, null, null, null];
        let column = 0;
        for (let i = 0; i < rowStr.length; i++) {
            let c = rowStr.charAt(i);
            let cVal = parseInt(c)
            if (!isNaN(cVal)) {
                column += cVal;
                continue;
            }
            row[column] = this.pieceFromChar(c, rowIndex, column);
            column++;
        }
        return row;
    }
    pieceFromChar(char, row, col) {
        let isWhite = char <= 'Z'
        char = char.toUpperCase()
        switch (char) {
            case 'K': return <Piece type="king" isWhite={isWhite} />
            case 'Q': return <Piece type="queen" isWhite={isWhite} />
            case 'R': return <Piece type="rook" isWhite={isWhite} />
            case 'N': return <Piece type="knight" isWhite={isWhite} />
            case 'B': return <Piece type="bishop" isWhite={isWhite} />
            case 'P': return <Piece type="pawn" isWhite={isWhite} />
        }
    }
    render() {
        const rows = this.state.board.map((row, index) => {
            return <div className="board-row" key={index}>
                {row.map((piece, col) => { return <Square key={col} piece={piece} row={index} col={col} /> })}
            </div>
        })
        return <div className="chessboard">
            {rows}
        </div>
    }

}