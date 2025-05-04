document.getElementById("signup-form").addEventListener("submit", async function(e) {
    e.preventDefault();  // Stop default form submission

    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    const fName = document.getElementById("fName").value;
    const lName = document.getElementById("lName").value;

    try {
        const response = await fetch("/register", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: new URLSearchParams({
                firstName: fName,
                lastName: lName,
                email: email,
                password: password,
                phone: "",         // default empty (or get from input)
                height: 0,         // you can add more fields later
                weight: 0,
                age: 0,
                gender: ""
            })
            
        });

        if (response.ok) {
            const message = await response.text();
            alert(message)
            window.location.href = "login.html";
        } 
        else {
            const error = await response.text();
            alert("Email already has an account");
        }
    } 
    catch (err) {
        console.error("Signup error:", err);
        alert("An unexpected error occurred.");
    }
});
