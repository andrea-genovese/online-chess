import "bootstrap/dist/css/bootstrap.min.css"
import Chessboard from "./gameLogic/Chessboard.js"

export default function App() {
  return <main className="d-flex justify-content-center align-items-center bg-dark" style={{ minHeight: '100vh' }}>
          <Chessboard/>
  </main>
}