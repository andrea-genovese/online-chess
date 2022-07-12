var stompClient = null;
let gameId = null;
let playerId = null;

async function createGame() {
    const response = await fetch('/api/create?color=white', { method: 'POST' })
    const responseObj = await response.json()
    console.log(responseObj);
    gameId = responseObj.gameId
    playerId = responseObj.playerId
}
function connect() {
    var socket = new SockJS('/connect');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, frame => {
        console.log('Connected: ' + frame);
        stompClient.subscribe(`/game/${gameId}`, msg => console.log(msg));
        stompClient.send(`/app/showUp/${gameId}`, {}, playerId)
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }

    console.log("Disconnected");
}

function move() {
    stompClient.send("/app/playMove/" + gameId);
}
