document.addEventListener("DOMContentLoaded", async () => {
    const storedUser = JSON.parse(localStorage.getItem("user"));

    if (!storedUser || !storedUser.uEmail) {
        window.location.href = "login.html"; // Not logged in, redirect
        return;
    }

    try {
        const response = await fetch(`/user?email=${encodeURIComponent(storedUser.uEmail)}`);
        if (!response.ok) {
            throw new Error("Failed to fetch user info");
        }

        const user = await response.json();

        document.getElementById("user-name").textContent = user.uFirstName + " " + user.uLastName;
        document.getElementById("user-email").textContent = user.uEmail;
        document.getElementById("user-age").textContent = user.uAge;
        document.getElementById("user-gender").textContent = user.uGender;

    } catch (err) {
        console.error("Error loading user data:", err);
        alert("Failed to load user info. Please log in again.");
        localStorage.removeItem("user");
        window.location.href = "login.html";
    }
});
