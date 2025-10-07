import {fetchAnyUrl, postObjectAsJson} from "./moduleJSON.js"

const bookingSpace = document.getElementById("bookingSpace")

let bookings = [];

async function fetchBooking() {
    console.log(window.location.origin + "/bookingData")
    bookings = await fetchAnyUrl(window.location.origin + "/bookingData");
    if (bookings.length > 0) {
        for (let booking of bookings){
            console.log(booking.seatColumn + ", " + booking.seatRow + ", " + booking.bookingID)
            let bookingPage = document.createElement("div");
            bookingPage.classList.add("booking")
            bookingSpace.appendChild(bookingPage)

            let poster = document.createElement("div")
            poster.classList.add("poster")
            bookingPage.appendChild(poster)
            let image = document.createElement("img")
            poster.appendChild(image)
            console.log(booking.show.movie.poster)
            image.src = booking.show.movie.poster
        }
    }
    else {
        console.log("no bookings")
    }
}
fetchBooking().then(r => {})
