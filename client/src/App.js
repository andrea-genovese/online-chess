import "bootstrap/dist/css/bootstrap.min.css"
import Chessboard from "./gameLogic/Chessboard.js"

export default function App() {
  return <main className="d-flex justify-content-center align-items-center bg-dark" style={{ minHeight: '100vh' }}>
          <Chessboard fen="rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1" />
  </main>
}