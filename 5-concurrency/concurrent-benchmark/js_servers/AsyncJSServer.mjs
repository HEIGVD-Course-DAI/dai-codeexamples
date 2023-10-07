import express from 'express'
import nocache from 'nocache'

const PORT = process.argv[2] || 3000
const SLEEP_DURATION = 100
const NUM_COMPUTE_ITERATIONS = 200_000 // Twice as many as Java. JavaScript's Math.random is faster.

// This function is synchronous and blocks the main thread
function workCompute() {
    let result = 0.0
    for (let i = 0; i < NUM_COMPUTE_ITERATIONS; i++) {
        result += Math.random()
    }
    return result
}

// This function is asynchronous and does not block the main thread
function workSleep(delay) {
    return new Promise((resolve) => setTimeout(resolve, delay))
}

async function work(request) {
    let url = request.url
    if (url.includes('/compute')) { 
        let result = workCompute() 
        console.log(`  ==> Compute finished: ${result}`)
    }
    if (url.includes('/sleep')) { 
        await workSleep(SLEEP_DURATION) 
        console.log('  ==> Sleep finished')
    }
}

// Callback function for incoming client connections
async function onClient(req, res) {
    console.log('Client connected')
    await work(req)
    res.status(200).send('X\r\n')
}

// Callback function when server starts listening for connections
function onListen() {
    console.log(`Started async JavaScript server on port: ${PORT}`)
}

// -----------------------------------------------------------------------------
// Start the application
// -----------------------------------------------------------------------------
const app = express()
app.use(nocache())
app.get('/*', onClient)
app.listen(PORT, onListen)
