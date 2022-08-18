import { Component } from "react"
import Square from "../components/Square"
import "./chessboard.css"
import King from "./Pieces/King"
import Queen from "./Pieces/Queen"
import Rook from "./Pieces/Rook"
import Knight from "./Pieces/Knight"
import Bishop from "./Pieces/Bishop"
import Pawn from "./Pieces/Pawn"

export default class Chessboard extends Component {
    constructor(props) {
        super(props)
        let board = this.fenToArr(this.props.fen)
        this.state = {
            board,
            highlighted: []
        }

    }
    fenToArr(fen) {
        if (!fen) fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"
        const arr = fen.split(" ");
        const position = arr[0]
        return this.boardFromString(position);
    }
    highlight = (row, col) => {
        this.setState(prevState => {
            const highlighted = prevState.highlighted
            highlighted.push({row, col})
            console.log(highlighted.toString());
            return {highlighted}
        })
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
        const row = [null, null, null, null, null, null, null, null];
        let column = 0;
        for (let i = 0; i < rowStr.length; i++) {
            const c = rowStr.charAt(i);
            const cVal = parseInt(c)
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
            case 'K': return <King isWhite={isWhite} row={row} column={col} board={this} highlight={this.highlight} />
            case 'Q': return <Queen isWhite={isWhite} row={row} column={col} board={this} highlight={this.highlight} />
            case 'R': return <Rook isWhite={isWhite} row={row} column={col} board={this} highlight={this.highlight} />
            case 'N': return <Knight isWhite={isWhite} row={row} column={col} board={this} highlight={this.highlight} />
            case 'B': return <Bishop isWhite={isWhite} row={row} column={col} board={this} highlight={this.highlight} />
            case 'P': return <Pawn isWhite={isWhite} row={row} column={col} board={this} highlight={this.highlight} />
        }
    }
    render() {
        const rows = this.state.board.map((squares, row) => {
            return <div className="board-row" key={row}>
                {squares.map((piece, col) => {
                    return <Square key={col}
                        piece={piece}
                        row={row}
                        col={col}
                        highlighted={this.state.highlighted.findIndex(val => val.row === row && val.col === val.col) != -1} />
                })}
            </div>
        })

        return <div className="chessboard">
            {rows}
        </div>
    }

}