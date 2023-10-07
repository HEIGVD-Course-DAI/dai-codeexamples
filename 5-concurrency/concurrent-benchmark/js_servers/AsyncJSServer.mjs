import net from 'node:net'

const PORT = process.argv[2] || 3000
const SLEEP_DURATION = 100
const NUM_COMPUTE_ITERATIONS = 200_000 // Twice as many as Java. JavaScript's Math.random is faster.

function sleep(delay) {
    return new Promise((resolve) => { setTimeout(resolve, delay) })
}

// This function is synchronous and blocks the main thread
function workCompute() {
    let result = 0.0
    for (let i = 0; i < NUM_COMPUTE_ITERATIONS; i++) {
        result += Math.random()
    }
    console.log('  ==> Compute finished')
}

// This function is asynchronous and does not block the main thread
async function workSleep(delay) {
    await sleep(delay)
    console.log('  ==> Sleep finished')
}

async function work(data) {
    if (data.includes('/compute')) { workCompute() }
    if (data.includes('/sleep')) { await workSleep(SLEEP_DURATION) }
    return "HTTP/1.0 200 OK\r\nContent-Length: 3\r\nContent-Type: text/plain\r\n\r\nX\r\n"
}

function onConnection(socket) {
    console.log('Client connected')
    socket.setNoDelay(true)
    socket.on('data', (data) => onData(data, socket))
}

async function onData(data, socket) {
    let response = await work(data)
    socket.write(response, () => socket.end())
}

// Start the application
const server = net.createServer()
server.on('connection', onConnection)
console.log(`Started async TCP JavaScript server on port: ${PORT}`)
server.listen(PORT)
