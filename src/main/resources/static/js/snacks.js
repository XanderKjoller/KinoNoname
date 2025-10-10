import {fetchAnyUrl} from "./moduleJSON.js";

console.log("snacks.js loaded");

let isUserEmployee

const snacksContainer = document.getElementById("snacks");
const searchInput = document.querySelector(".filters input");

const SNACK_URL = window.location.origin+"/snacks";

let allSnacks = [];

let booking=0


// Load snacks from backend
async function fetchAnyUrl2(url) {
    const response = await fetch(url, { method: 'POST' });
    if (!response.ok) throw new Error(`HTTP error! status: ${response.status}`);
    isUserEmployee = false
    try {
        const user = await fetchAnyUrl("/me");
        console.log(user.authority)
        isUserEmployee = user.authority === "EMPLOYEE"
    } catch (error) {
        console.log("not logged in or not employee")
    }

    booking= await fetchAnyUrl("/b")

    return await response.json();
}



async function loadSnacks() {


    try {
        const snacks = await fetchAnyUrl2(SNACK_URL);


        allSnacks = snacks;
        console.log("Snacks loaded:", snacks);



        displaySnacks(snacks);
    } catch (err) {
        console.error("Error loading snacks:", err);
    }
}

async function handleFormSubmit(event) {
    event.preventDefault();

    const bookingID = parseInt(event.target.querySelector("#inpBookingID").value);
    const snackID = parseInt(event.target.querySelector("#inpSnackID").value);

    const snackReservation = {
        booking: { bookingID },
        snack: { snackID }
    };


    try {
        //post to database
        const response = await fetch("/addSnackReservation", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(snackReservation),
        });

        if (!response.ok) {
            throw new Error("post snackReservation failed ");
        }

        const data = await response.json();
        console.log(data);
        console.log("Success")

       // alert("new snackReservation to booking"+snackReservation.bookingID+" successful!");
    } catch (err) {

        console.error(err);
        alert(err.message);
    }

}

// Display snack grid
function displaySnacks(snacks) {
    snacksContainer.innerHTML = "";

    if (!snacks || snacks.length === 0) {
        snacksContainer.innerHTML = `<p>No snack found!!</p>`;
        return;
    }

    snacks.forEach((snack, index, array) => {

        const snackDiv = document.createElement("div");
        snackDiv.classList.add("snack");

        // Snack image
        const img = document.createElement('img');
        img.src = snack.image;
        img.alt = snack.name;
        img.style.display = "flex";

        // Snack title
        const titleDiv = document.createElement('div');
        titleDiv.className = "snack-title";
        titleDiv.textContent = snack.name;

        // Append elements to snackDiv
        snackDiv.appendChild(img);
        snackDiv.appendChild(titleDiv);
        if(isUserEmployee){
            snackDiv.addEventListener("click", () => window.location.href = window.location.origin+ "/snack/"+snack.snackID);
        }else {
            let bookingID = booking.bookingID

            // Create the form
            const subConHeader = document.createElement("form");
            subConHeader.className = "subConHeader";
            subConHeader.action = "/addSnackReservation";
            subConHeader.method = "post";
            subConHeader.addEventListener("submit", handleFormSubmit);

            // --- Snack ID field ---
            const snackGroup = document.createElement("div");
            snackGroup.className = "form-group";

            const snackIdElement = document.createElement("input");
            snackIdElement.type = "number";
            snackIdElement.id = "inpSnackID";
            snackIdElement.name = "inpSnackID";
            snackIdElement.value = snack.snackID;
            snackIdElement.hidden=true

            snackGroup.appendChild(snackIdElement);

            // --- Booking ID field ---
            const bookingGroup = document.createElement("div");
            bookingGroup.className = "form-group";

            const bookingIdElement = document.createElement("input");
            bookingIdElement.type = "number";
            bookingIdElement.id = "inpBookingID";
            bookingIdElement.name = "inpBookingID";
            bookingIdElement.value = bookingID;
            bookingIdElement.hidden=true

            bookingGroup.appendChild(bookingIdElement);

            // --- Submit button ---
            const button = document.createElement("button");
            button.type = "submit";
            button.textContent = "x";
            button.hidden = true;

            subConHeader.appendChild(snackGroup);
            subConHeader.appendChild(bookingGroup);
            subConHeader.appendChild(button);

            snackDiv.appendChild(subConHeader);

            //Click on it to create a snackReservation
            snackDiv.addEventListener("click", () => button.click());


        }



        snacksContainer.appendChild(snackDiv);
            // If last element, add a "Load More" snack
            if (index === array.length - 1) {
                if(isUserEmployee){
                    const newSnackDiv = document.createElement("div");
                    newSnackDiv.classList.add("snack"); // same styling as snacks

                    const _img = document.createElement("img");
                    _img.src = "https://plus.unsplash.com/premium_photo-1681400545953-0ba00cfa7926?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTN8fGNyZWF0ZSUyMG5ld3xlbnwwfHwwfHx8MA%3D%3D"; // placeholder or custom image
                    _img.alt = "Add new Snack";
                    _img.style.display = "flex";



                    const _imgTitle = document.createElement("div");
                    _imgTitle.className = "snack-title";
                    _imgTitle.textContent = "Add new Snack";

                    newSnackDiv.appendChild(_img);
                    newSnackDiv.appendChild(_imgTitle);
                    _img.addEventListener("click", ()=>{
                        window.location.href = window.location.origin+ "/snack"
                    })


                    snacksContainer.appendChild(newSnackDiv);
                }
            }

    }


    );
}



function showSnackDetails(snack) {
    const modal = document.createElement("div");
    modal.classList.add("modal");
    modal.innerHTML = `
        <div class="modal-content">
            <span class="close">&times;</span>
            <h2> ${snack.name}</h2>
            <p><strong>Price:</strong> ${snack.price}</p>
        </div>
    `;
    document.body.appendChild(modal);

    // Close modal on click
    modal.querySelector(".close").addEventListener("click", () => {
        modal.remove();
    });
}


// Filtering logic
function filterSnacks() {
    const searchTerm = searchInput.value.toLowerCase();

    const filtered = allSnacks.filter(movie => {
        const matchesSearch = movie.name.toLowerCase().includes(searchTerm);
        return matchesSearch;
    });

    displaySnacks(filtered);
}

// Watch for search changes
searchInput.addEventListener("input", filterSnacks);

// Run on page load
window.addEventListener("DOMContentLoaded", loadSnacks);
