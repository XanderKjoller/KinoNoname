const pathSegments = window.location.pathname.split("/").filter(Boolean);
const snackId = pathSegments[pathSegments.length - 1];
console.log(snackId); // "5"


import { fetchAnyUrl, fetchUser2, isEmployee } from "./moduleJSON.js";

console.log("snack2.js loaded");

const urlPutSnack = window.location.origin + "/snack/";

document.addEventListener('DOMContentLoaded', function() {

    const formSnack = document.getElementById("snackForm");
    formSnack.addEventListener("submit", handleFormSubmit);
});

async function handleFormSubmit(event) {
    event.preventDefault();

    const snack = {
        snackID: parseInt(snackId),
        name: document.getElementById("inpName").value,
        image: document.getElementById("inpImage").value,
        price: document.getElementById("inpPrice").value,
    };


    try {
        //post to database
        const response = await fetch(urlPutSnack+snack.snackID, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(snack),
        });

        if (!response.ok) {
            throw new Error("update snack failed ");
        }

        const data = await response.json();
        console.log(data);

        alert("new snack successful!");
    } catch (err) {

        console.error(err);
        alert(err.message);
    }

}