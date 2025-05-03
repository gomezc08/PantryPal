document.getElementById("login-form").addEventListener("submit", async function(e) {
    e.preventDefault();  // Stop default form submission

    const email = document.getElementById("first").value;
    const password = document.getElementById("password").value;

    try {
        const response = await fetch("/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: new URLSearchParams({
                email: email,
                password: password
            })
        });

        if (response.ok) {
            const user = await response.json();

            // Save user info to localStorage
            localStorage.setItem("user", JSON.stringify(user));

            // Redirect to the home page
            window.location.href = "home.html";
        } 
        else {
            const error = await response.text();
            alert("Login failed: " + error);
        }
    } catch (err) {
        console.error("Login error:", err);
        alert("An unexpected error occurred.");
    }
});
