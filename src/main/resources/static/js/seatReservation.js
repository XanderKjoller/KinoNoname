import {fetchAnyUrl, postObjectAsJson} from "./moduleJSON.js"

const seats = document.getElementById("seats");
let reservedSeats = []

const timerText = document.getElementById("timer")
let timer = 0;
const timeOutAmount = 10;

const height = 20
const width = 20

const seatList = [];

const totalSeats = height * width;
seats.style.gridTemplateColumns = `repeat(${width}, 1fr)`;
function fillSeats() {
    for (let i = 0; i < totalSeats; i++) {
        let seat = document.createElement("div");
        seat.className = "seat";
        seats.appendChild(seat);
        seatList.push(seat);
    }
}

fillSeats();

for (let i = 0; i < seatList.length; i++) {
    seatList[i].addEventListener("click", () =>  clickedSeat(seatList[i]));
}
function clickedSeat(seat){
    if (!seat.classList.contains("reserved")){
        //make reservation :) if you're reading this you might be wasting time :(
        const seatNumber = seatList.findIndex(s => s === seat) + 1;
        //change when you have it, showID
        const seatReservation = {
            seatRow: Math.floor(seatNumber / height) + 1,
            seatColumn: seatNumber - (Math.floor(seatNumber / height) * height),
            show: {showID : 1}
        }

        if(seat.isChosen === true){
            seat.isChosen = false
            seat.classList.remove("selected")
            deleteSeatReservation(seatReservation).then(r => {})
        }
        else  {
            seat.isChosen = true
            seat.classList.add("selected")
            saveSeatReservation(seatReservation).then(r => {
                if(timer<= 0){
                    setTimer()
                }
            })
        }
    }
}


function fillInReservedSeats(seat){
    console.log("reserved seat: " + seat.seatColumn)

    const seatNumber = (seat.seatRow -1) * height + seat.seatColumn - 1
    console.log(seatNumber + ", " + seat.seatColumn + ", " + seat.seatRow)
    seatList[seatNumber].classList = "seat reserved"
}

function setTimer() {
    console.log("set Timer" + timer)
    timer = 60 * timeOutAmount; // total seconds
    const intervalId = setInterval(() => {
        if (timer <= 0) {
            clearInterval(intervalId);
            // timer ended, do something here if needed
            fetchReservedSeats().then(r => {console.log("fetched")})
            //userid delete all
            return;
        }

        timer--;

        let minutes = Math.floor(timer / 60);
        let seconds = timer % 60;

        let minutesStr = minutes < 10 ? "0" + minutes : "" + minutes;
        let secondsStr = seconds < 10 ? "0" + seconds : "" + seconds;

        timerText.innerText = minutesStr + ":" + secondsStr;
    }, 1000);
}


async function fetchReservedSeats() {
    console.log(window.location.origin + "/SeatReservationData")
    reservedSeats = await fetchAnyUrl(window.location.origin + "/SeatReservationData");
    if (reservedSeats.length > 0) {
        reservedSeats.forEach(fillInReservedSeats)
    } else {
        console.log("no seat reservations")
    }
}

async function saveSeatReservation(seatReservation) {
    const [body, status] = await postObjectAsJson(window.location.origin + "/ReserveSeat", seatReservation, "POST");
    //alert(JSON.stringify(body));
    console.log(JSON.stringify(body))
}

async function deleteSeatReservation(seatReservation){
    const [body, status] = await postObjectAsJson(window.location.origin + "/DeleteSeat", seatReservation, "DELETE");
    //alert(JSON.stringify(body));
    //console.log(JSON.stringify(body))
}

fetchReservedSeats().then(r => {console.log("worked")})