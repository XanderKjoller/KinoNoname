
import { fetchAnyUrl, fetchUser2, isEmployee } from "./moduleJSON.js";

console.log("snack.js loaded");

const urlPostSnack = window.location.origin + "/addSnack";
const urlPutSnack = window.location.origin + "/snack/";

document.addEventListener('DOMContentLoaded', function() {

    const formSnack = document.getElementById("snackForm");
    formSnack.addEventListener("submit", handleFormSubmit);
});

async function handleFormSubmit(event) {
    event.preventDefault();

    const snack = {
        name: document.getElementById("inpName").value,
        image: document.getElementById("inpImage").value,
        price: document.getElementById("inpPrice").value,
    };

    try {
        //post to database
        const response = await fetch(urlPostSnack, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(snack),
        });

        if (!response.ok) {
            throw new Error("post snack failed ");
        }

        const data = await response.json();
        console.log(data);

        alert("SignUp successful!");
    } catch (err) {

        console.error(err);
        alert(err.message);
    }

}