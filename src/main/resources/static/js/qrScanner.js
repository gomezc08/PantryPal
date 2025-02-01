// script.js file

function domReady(fn) {
    if (
        document.readyState === "complete" ||
        document.readyState === "interactive"
    ) {
        setTimeout(fn, 1000);
    } else {
        document.addEventListener("DOMContentLoaded", fn);
    }
}

domReady(function () {

    // If found you qr code
    function onScanSuccess(decodeText, decodeResult) {
        alert("You QR is : " + decodeText);
        console.log("Sending scanned data to server...", decodeText);
        
        // Send the QR code data to Spring Boot server
        fetch('/api/scan', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ qrData: decodeText })
        })
        .then(response => response.json())
        .then(data => console.log("Response from server:", data))
        .catch(error => console.error('Error:', error));
    }    

    let htmlscanner = new Html5QrcodeScanner(
        "my-qr-reader",
        { 
            fps: 10, 
            qrbox: 250,
            // Add supported formats for various barcode types!
            formatsToSupport: [
                Html5QrcodeSupportedFormats.EAN_13,
                Html5QrcodeSupportedFormats.EAN_8,
                Html5QrcodeSupportedFormats.UPC_A,
                Html5QrcodeSupportedFormats.UPC_E,
                Html5QrcodeSupportedFormats.CODE_128,
                Html5QrcodeSupportedFormats.CODE_39,
                Html5QrcodeSupportedFormats.CODE_93,
                Html5QrcodeSupportedFormats.CODABAR
            ]
        }
    );
    htmlscanner.render(onScanSuccess);
});