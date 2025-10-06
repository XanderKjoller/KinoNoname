// Generic fetch (GET)
async function fetchAnyUrl(url) {
    const response = await fetch(url);
    if (!response.ok) {
        const errorMessage = await response.text();
        throw new Error(errorMessage);
    }
    return await response.json();
}

// Generic POST/PUT/PATCH with JSON body
async function postObjectAsJson(url, object, httpMethod) {
    const response = await fetch(url, {
        method: httpMethod,
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(object)
    });

    if (!response.ok) {
        const errorMessage = await response.text();
        throw new Error(errorMessage);
    }

    const responseJson = await response.json();
    return [responseJson, response.status];
}

// Generic DELETE
async function restDelete(url) {
    const response = await fetch(url, { method: "DELETE" });

    if (!response.ok) {
        const errorMessage = await response.text();
        throw new Error(errorMessage);
    }

    return response.status; // 204 expected
}

// Users
const urlUsers = "http://localhost:8080/users";
let usersMap = new Map();

async function fetchUsers() {
    const users = await fetchAnyUrl(urlUsers);
    users.forEach(user => usersMap.set(user.username, user));
    return usersMap;
}

export { fetchAnyUrl, postObjectAsJson, restDelete, fetchUsers, usersMap };
