import {fetchAnyUrl} from "./moduleJSON.js";

console.log("Movies.js loaded");

const moviesContainer = document.getElementById("movies");
const dateSelect = document.querySelector(".filters select:nth-child(1)");
const categorySelect = document.querySelector(".filters select:nth-child(2)");
const searchInput = document.querySelector(".filters input");

const MOVIE_URL = window.location.origin + "/";

let allMovies = [];

// Load movies from backend
async function fetchAnyUrl2(url) {
    const response = await fetch(url, {method: 'POST'});
    if (!response.ok) throw new Error(`HTTP error! status: ${response.status}`);
    return await response.json();
}

async function loadMovies() {
    try {
        const movies = await fetchAnyUrl2(MOVIE_URL);
        allMovies = movies;
        console.log("Movies loaded:", movies);


        populateCategories();
        populateDates();
        displayMovies(movies);
    } catch (err) {
        console.error("Error loading movies:", err);
    }
}

// Fill category dropdown dynamically
function populateCategories() {
    const categories = [...new Set(allMovies.map(m => m.category))];
    categorySelect.innerHTML = `<option value="">Every Category</option>`;
    categories.forEach(cat => {
        const opt = document.createElement("option");
        opt.value = cat;
        opt.textContent = cat;
        categorySelect.appendChild(opt);
    });
}

// Fill year dropdown dynamically (based on releaseDate)
function populateDates() {
    const years = [...new Set(allMovies.map(m => new Date(m.releaseDate).getFullYear()))];
    dateSelect.innerHTML = `<option value="">Every year</option>`;
    years.sort((a, b) => a - b); // Optional: Sort years in ascending order
    years.forEach(year => {
        const opt = document.createElement("option");
        opt.value = year;
        opt.textContent = year;
        dateSelect.appendChild(opt);
    });
}


// Display movie grid
function displayMovies(movies) {
    moviesContainer.innerHTML = "";

    if (!movies || movies.length === 0) {
        moviesContainer.innerHTML = `<p>No movie Found!!️</p>`;
        return;
    }

    movies.forEach(movie => {
        const movieDiv = document.createElement("div");
        movieDiv.classList.add("movie");

        // Movie image
        const img = document.createElement('img');
        img.src = movie.poster;
        img.alt = movie.name;
        img.style.display = "flex"

        // Movie title
        const titleDiv = document.createElement('div');
        titleDiv.className = 'movie-title';
        titleDiv.textContent = movie.name;

        // Movie category
        const categoryDiv = document.createElement('div');
        categoryDiv.className = 'movie-category';
        categoryDiv.textContent = movie.category;

        // Movie duration
        const durationDiv = document.createElement('div');
        durationDiv.className = 'movie-duration';
        durationDiv.textContent = `${movie.duration} min`;

        // Append elements to movieDiv
        movieDiv.appendChild(img);
        movieDiv.appendChild(titleDiv);
        movieDiv.appendChild(categoryDiv);
        movieDiv.appendChild(durationDiv);

        // Click to show details modal
        movieDiv.addEventListener("click", () => window.location.href = "/showing/" + movie.movieID);

        moviesContainer.appendChild(movieDiv);
    });
}

function showMovieDetails(movie) {
    const modal = document.createElement("div");
    modal.classList.add("modal");
    modal.innerHTML = `
        <div class="modal-content">
            <span class="close">&times;</span>
            <h2> ${movie.name}</h2>
            <p><strong>Kategori:</strong> ${movie.category}</p>
            <p><strong>Varighed:</strong> ${movie.duration} min</p>
            <p><strong>Udgivelsesdato:</strong> ${movie.releaseDate}</p>
        </div>
    `;
    document.body.appendChild(modal);

    // Close modal on click
    modal.querySelector(".close").addEventListener("click", () => {
        modal.remove();
    });
}


// Filtering logic
function filterMovies() {
    const selectedCategory = categorySelect.value;
    const selectedDate = dateSelect.value;
    const searchTerm = searchInput.value.toLowerCase();

    const filtered = allMovies.filter(movie => {
        const matchesCategory = !selectedCategory || movie.category === selectedCategory;
        const movieYear = new Date(movie.releaseDate).getFullYear(); // Convert to Date and get year
        const matchesDate = !selectedDate || movieYear === Number(selectedDate);
        const matchesSearch = movie.name.toLowerCase().includes(searchTerm);
        return matchesCategory && matchesDate && matchesSearch;
    });

    displayMovies(filtered);
}

// Watch for filter/search changes
categorySelect.addEventListener("change", filterMovies);
dateSelect.addEventListener("change", filterMovies);
searchInput.addEventListener("input", filterMovies);

// Run on page load
window.addEventListener("DOMContentLoaded", loadMovies);
