import {fetchAnyUrl, postObjectAsJson,fetchUser,fetchUser2} from "./moduleJSON.js"

const seats = document.getElementById("seats");
let reservedSeats = []
let user
let show

const timeFrameText = document.getElementById("timeframe")
const durationText = document.getElementById("duration")
const bookButton = document.getElementById("bookButton")

const timerText = document.getElementById("timer")
let timer = 0;
const timeOutAmount = 10;

let height = 20
let width = 20

const seatList = [];

let totalSeats = height * width;

const days = ['Sun','Mon','Tue','Wed','Thu','Fri','Sat'];
const months = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];


function fillSeats() {
    for (let i = 0; i < totalSeats; i++) {
        let seat = document.createElement("div");
        seat.className = "seat";
        seats.appendChild(seat);
        seatList.push(seat);
    }
}




function clickedSeat(seat){
    console.log("in here atleast")
    if (!seat.classList.contains("reserved")){
        //make reservation :) if you're reading this you might be wasting time :(
        //const seatNumber = seatList.findIndex(s => s === seat) + 1;
        //change when you have it, showID
        const seatReservation = seatToJson(seat)

        if(seat.isChosen === true){
            seat.isChosen = false
            seat.classList.remove("selected")
            deleteSeatReservation(seatReservation).then(r => {
                let nomoreseats = true;
                for (let i = 0; i <seatList.length; i++){
                    if(seatList[i].classList.contains("selected")) {
                        nomoreseats = false;
                    }
                }
                if(nomoreseats)
                    timer = 0;
            })


        }
        else  {
            //console.log("this is user:" + user.username)
            if(user == null){
                window.location.href = "/login";
            }
            else {
                console.log("trying to select")
                saveSeatReservation(seatReservation).then(r => {
                    if(timer<= 0){
                        setTimer()
                    }
                    seat.isChosen = true
                    seat.classList.add("selected")
                    console.log("did select")
                })
            }

        }
    }
}


function fillInReservedSeats(seat){
    console.log("reserved seat: " + seat.seatColumn)

    const seatNumber = (seat.seatRow -1) * width + seat.seatColumn - 1
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
            //fetchReservedSeats().then(r => {console.log("fetched")})
            for (let i = 0; i < seatList.length; i++){
                if(seatList[i].classList.contains("selected")){
                    const seatReservation = seatToJson(seatList[i])
                    deleteSeatReservation(seatReservation).then(r => {})
                    seatList[i].classList.remove("selected")
                }
            }
            timerText.innerText = ""
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
    reservedSeats = await fetchAnyUrl(window.location.origin + "/SeatReservationData?showID=" + show.showID);
    if (reservedSeats.length > 0) {
        reservedSeats.forEach(fillInReservedSeats)
    } else {
        console.log("no seat reservations")
    }
}
async function fetchShow(){
    
    show = await fetchAnyUrl(window.location.origin + "/GetShow");
    if ( show == null) {
        console.log("no user")
    }
    else
        console.log(show)
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
function seatToJson(seat){
    let seatNumber = seatList.findIndex(s => s === seat) + 1;
    return {
        seatRow: Math.floor(seatNumber / width) + 1,
        seatColumn: seatNumber - (Math.floor(seatNumber / width) * width),
        show: {showID: show.showID}
    }
}
function addZero(number){
    if(number < 10)
        number = "0" + number
    return number
}
async function fetchUser3(){

    console.log(window.location.origin + "/me")
    user = await fetchAnyUrl(window.location.origin + "/me");
    if ( user == null) {
        console.log("no user")
        return null
    }
    return user
}
async function bookTickets() {
    let seatsChosen = false;
    for (let i = 0; i < seatList.length; i++) {
        if(seatList[i].isChosen){
            seatsChosen = true;
            let booking = seatToJson(seatList[i])
            deleteSeatReservation(booking).then(r => {console.log("Deleted seatR")})
            const [body, status] = await postObjectAsJson(window.location.origin + "/BookTicket", booking, "POST")
            console.log("json of booking", JSON.stringify(booking))
            seatList[i].classList.remove("selected")
            seatList[i].isChosen = false;
            timer = 0;
        }
    }
    if(!seatsChosen){
        //write to user no seats chosen
    }
    else {
        window.location.href = "/Bookings";
    }

}

fetchShow().then(r => {
    console.log("got show")
    console.log("show rows and columns:", show.room.rows, show.room.columns)
    height = show.room.columns
    width = show.room.rows
    totalSeats = height * width;
    seats.style.gridTemplateColumns = `repeat(${width}, 1fr)`;
    fillSeats();
    for (let i = 0; i < seatList.length; i++) {
        seatList[i].addEventListener("click", () =>  clickedSeat(seatList[i]));
    }

    let date = new Date(show.period)
    let dateEnd = new Date(date.getTime() + show.movie.duration * 60000);
    timeFrameText.innerText = addZero(date.getHours()) + ":" + addZero(date.getMinutes()) + " - " + addZero(dateEnd.getHours()) + ":" + addZero(dateEnd.getMinutes())
    timeFrameText.innerText += " " + days[ date.getDay() ] + ", " + date.getDate() + " " + months[ date.getMonth() ] + "."

    durationText.innerText = Math.floor(show.movie.duration / 60) + " Hours " + (show.movie.duration - Math.floor(show.movie.duration / 60)*60) + " Minutes"

    fetchUser3().then(r => {console.log("think i got user")})
    fetchReservedSeats().then(r => {console.log("got seats")})
    bookButton.addEventListener("click", bookTickets)
})

