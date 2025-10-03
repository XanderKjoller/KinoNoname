const seats = document.getElementById("seats");

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
    if(seat.isChosen === true){
        seat.isChosen = false
        seat.classList.remove("selected")
    }
    else {
        seat.isChosen = true
        seat.classList.add("selected")
    }

}