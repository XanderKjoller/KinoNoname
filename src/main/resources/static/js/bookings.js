import {fetchAnyUrl, postObjectAsJson} from "./moduleJSON.js"

const bookingSpace = document.getElementById("bookingSpace")

let bookings = [];
//let htmlBookings = []
let snacks = [];

const days = ['Sun','Mon','Tue','Wed','Thu','Fri','Sat'];
const months = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];

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

            let info = document.createElement("div")
            info.classList.add("details")
            bookingPage.appendChild(info)

            let title = document.createElement("div")
            title.innerText = booking.show.movie.name
            title.classList.add("title")
            info.appendChild(title)

            let time = document.createElement("div")
            time.classList.add("infoText")
            let date = new Date(booking.show.period)
            let dateEnd = new Date(date.getTime() + booking.show.movie.duration * 60000);
            time.innerText = addZero(date.getHours()) + ":" + addZero(date.getMinutes()) + " - " + addZero(dateEnd.getHours()) + ":" + addZero(dateEnd.getMinutes())
            info.appendChild(time)

            let dateInfo = document.createElement("div")
            dateInfo.classList.add("infoText")
            dateInfo.innerText = days[ date.getDay() ] + ", " + date.getDate() + " " + months[ date.getMonth() ] + "."
            info.appendChild(dateInfo)

            let seatInfo = document.createElement("div")
            seatInfo.classList.add("infoText")
            seatInfo.innerText = booking.seatRow + String.fromCharCode(96 + booking.seatColumn);
            seatInfo.classList.add("seat")
            info.appendChild(seatInfo)

            let roomInfo = document.createElement("div")
            roomInfo.classList.add("infoText")
            roomInfo.innerText = "Room " + booking.show.room.roomID
            roomInfo.classList.add("room")
            info.appendChild(roomInfo)

            let ticketInfo = document.createElement("div")
            ticketInfo.classList.add("infoText")
            ticketInfo.innerText = "Ticket Code: " + booking.ticketCode
            info.appendChild(ticketInfo)

            let deleteButton = document.createElement("button")
            deleteButton.innerText = "Delete Ticket"
            deleteButton.classList.add("deleteButton")
            //deleteButton.formAction = ""
            deleteButton.addEventListener("click", r => {deleteBooking(booking, bookingPage).then(r => {})})
            bookingPage.appendChild(deleteButton)

            let snackButton = document.createElement("button")
            snackButton.innerText = "Reserve Snack"
            snackButton.classList.add("snackButton")
            snackButton.addEventListener("click", r => {window.location.href = "/snackRedirect?bookingID=" + booking.bookingID;})
            info.appendChild(snackButton)

            let snackDiv = document.createElement("div")
            bookingPage.appendChild(snackDiv)
            fetchSnacks(booking.bookingID).then(r =>{

                for (let i = 0; i < snacks.length; i++){
                    let snackContainer = document.createElement("div")
                    snackContainer.classList.add("snackContainer");
                    snackDiv.appendChild(snackContainer)

                    console.log(snacks[i])
                    let snackText = document.createElement("div")
                    snackText.innerText = snacks[i].snack.name + ", " + snacks[i].snack.price + " kr.   ";
                    console.log("snacks: " + snacks[i].snackReservationID)
                    snackContainer.appendChild(snackText)

                    let snackX = document.createElement("div")
                    snackX.style.paddingLeft = "5%"
                    snackX.style.color = "red"
                    snackX.innerText = "X"
                    snackContainer.appendChild(snackX)

                    let snack = snacks[i];
                    snackX.addEventListener("click", () => {

                        console.log(snack.snackReservationID)
                        deleteSnack(snack.snackReservationID)
                        snackContainer.remove();
                    });

                }
            })

        }
    }
    else {
        console.log("no bookings")
    }
}

function addZero(number){
    if(number < 10)
        number = "0" + number
    return number
}
async function deleteBooking(booking, bookingPage){
    //console.log(JSON.stringify(booking));
    const [body, status] = await postObjectAsJson(window.location.origin + "/DeleteBooking", { bookingID: booking.bookingID }, "DELETE")
    if(status)
        bookingPage.remove()
}
async function fetchSnacks(bookingID){
    snacks = await fetchAnyUrl(window.location.origin + "/snackReservationData?bookingID=" + bookingID);
}
async function deleteSnack(snack){
    const [body, status] = await postObjectAsJson(window.location.origin + "/DeleteSnack", { snackReservationID: snack }, "DELETE")

}

fetchBooking().then(r => {})
