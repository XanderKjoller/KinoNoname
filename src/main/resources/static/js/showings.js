const pathSegments = window.location.pathname.split('/');
const movieID = pathSegments[pathSegments.length - 1];
const showContainer = document.getElementById("showings")
const movieposter = document.getElementById("moviePoster")


// Generic fetch (GET)
async function fetchAnyUrl(url) {
    const response = await fetch(url);
    if (!response.ok) {
        const errorMessage = await response.text();
        throw new Error(errorMessage);
    }
    return await response.json();
}

function displayMovie(movie) {
    const img = document.createElement('img');
    img.src = movie.poster;
    img.alt = movie.name;
    img.style.display = "flex"
    img.style.width = "10%"
    movieposter.appendChild(img)

}

async function everythingallatonce() {
    const movie = await fetchAnyUrl("/MovieData/" + movieID);
    const showings = await fetchAnyUrl("/ShowingsData/" + movieID);
    let auth = false
    try {
        const user = await fetchAnyUrl("/me");
        auth = user.authority === "EMPLOYEE"
    } catch (error) {
        console.log("not logged in")
    }
    console.log
    displayMovie(movie);
    fillshowings(showings, auth);

}

function fillshowings(showings, auth) {
    const tempDate = new Date();
    const weekdayElements = [document.createElement("div")];
    const forwardDates = [];
    console.log(auth)
    for (let i = 0; i < 7; i++) {
        forwardDates[i] = tempDate.toJSON().split("T")[0];
        weekdayElements[i] = document.createElement("div");
        const datebox = document.createElement("div");
        datebox.innerHTML = tempDate.toDateString();
        datebox.className = "scheduleHeader";

        weekdayElements[i].className = "scheduleContainer";

        weekdayElements[i].appendChild(datebox)
        showContainer.appendChild(weekdayElements[i]);
        tempDate.setDate(tempDate.getDate() + 1);

    }

    showings.forEach(show => {

            for (let i = 0; i < 7; i++) {
                const showDate = show.period.split("T")[0]

                if (showDate === forwardDates[i]) {
                    const subContainer = document.createElement("div");
                    subContainer.className = "subContainer";
                    subContainer.onclick = () => {
                        window.location.href = "/SeatReservation?showID=" + movieID;
                    };


                    if (auth === true) {
                        const subConHeader = document.createElement("form")
                        subConHeader.className = "subConHeader"
                        subConHeader.action = "/deleteShowing";
                        subConHeader.method = "post";

                        const showIdElement = document.createElement("input")
                        showIdElement.hidden= true;
                        showIdElement.value = show.showID
                        showIdElement.name ="showId"

                        const deleteButton = document.createElement("button")
                        deleteButton.innerHTML = "x";
                        deleteButton.type="submit";
                        deleteButton.className = "deleteButton";


                        subConHeader.appendChild(showIdElement)
                        subConHeader.appendChild(deleteButton)
                        subContainer.appendChild(subConHeader)
                    }


                    const showTime = document.createElement("div");
                    showTime.innerHTML = show.period.split("T")[1].substring(0, 5);

                    const showCinema = document.createElement("div");
                    showCinema.innerHTML = "Cinema: " + show.room;

                    const showPrice = document.createElement("div");
                    showPrice.innerHTML = "Price: " + show.price + " kr";

                    subContainer.appendChild(showTime);
                    subContainer.appendChild(showCinema);
                    subContainer.appendChild(showPrice);

                    weekdayElements[i].appendChild(subContainer);
                }
            }
        }
    )
}

window.addEventListener("DOMContentLoaded", everythingallatonce);