import "./square.css"
export default function Square(props) {
    return <div className={"square "+((props.row+props.col) % 2 === 0 ? "white" : "black")}>
        {props.piece}
    </div>
}