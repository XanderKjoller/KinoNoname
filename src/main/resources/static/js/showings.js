let pathSegments = window.location.pathname.split('/');
let movieID = pathSegments[pathSegments.length - 1];
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
    img.style.margin = "auto"
    img.style.width = "30%"
    img.style.height = "flex"
    movieposter.appendChild(img)

}

async function everythingallatonce() {
    const movie = await fetchAnyUrl("/MovieData/" + movieID);
    const showings = await fetchAnyUrl("/ShowingsData/" + movieID);
    displayMovie(movie);
    fillshowings(showings);
}

function fillshowings(showings)
{
    showings.forEach(show => {
            const showDiv = document.createElement("show");
            showDiv.innerHTML =show.showID;
            showContainer.appendChild(showDiv);
        }
    )
}

window.addEventListener("DOMContentLoaded", everythingallatonce);