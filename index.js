const { app, BrowserWindow } = require('electron');
const http = require('http');
const fs = require('fs');
const path = require('path');

let mainWindow;

function createWindow() {
    mainWindow = new BrowserWindow({
        width: 800,
        height: 600,
        webPreferences: {
            nodeIntegration: true // Enable Node.js integration in the renderer process
        }
    });

    mainWindow.loadURL('http://localhost:3000/index.html'); // Load the app from the local server

    const server = http.createServer((req, res) => {
        let filePath = '.' + req.url;

        if (filePath === './') {
            filePath = './index.html'; // Serve index.html by default
        }

        filePath = path.join(__dirname, 'src', filePath);

        console.log(`Request received for: ${req.url}`);
        console.log(`Serving file: ${filePath}`);

        serveFile(filePath, res);
    });

    const port = 3000;
    server.listen(port, () => {
        console.log(`Server running on port ${port}`);
        console.log(`Serving files from: ${path.join(__dirname, 'src')}`);
    });
}

function serveFile(filePath, res) {
    fs.readFile(filePath, (err, content) => {
        if (err) {
            handleFileError(err, res);
        } else {
            const extname = path.extname(filePath);
            const contentType = getContentType(extname);
            serveContent(content, contentType, res);
        }
    });
}

function handleFileError(err, res) {
    if (err.code === 'ENOENT') {
        res.writeHead(404);
        res.end('404 - Not Found');
    } else {
        res.writeHead(500);
        res.end(`Server Error: ${err.code}`);
    }
}

function serveContent(content, contentType, res) {
    res.writeHead(200, { 'Content-Type': contentType });
    res.end(content, 'utf-8');
}

function getContentType(extname) {
    switch (extname) {
        case '.js':
            return 'text/javascript';
        case '.css':
            return 'text/css';
        default:
            return 'text/html';
    }
}

app.whenReady().then(createWindow);

app.on('window-all-closed', () => {
    if (process.platform !== 'darwin') {
        app.quit();
    }
});

app.on('activate', () => {
    if (BrowserWindow.getAllWindows().length === 0) {
        createWindow();
    }
});
