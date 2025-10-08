function fetchAnyUrl(url) {
    return fetch(url).then(response => response.json())
}

async function postObjectAsJson(url, object, httpVerbum) {
    const objectAsJsonString = JSON.stringify(object);
    console.log("object here: ", objectAsJsonString);
    const fetchOptions = {
        method: httpVerbum,
        headers: {
            "content-type": "application/json"
        },
        body: objectAsJsonString
    };
    const response = await fetch(url,fetchOptions);
    console.log(response);
    if (!response.ok) {
        const errorMessage = await response.text();
        throw new Error(errorMessage);
    }
    const responseJson = await response.json();
    return [responseJson, response.status];
}

async function fetchUser(){
    let user;
    console.log(window.location.origin + "/me")
    user = await fetchAnyUrl(window.location.origin + "/me");
    if ( user.length < 1) {
        console.log("no user")
    }
    return user
}
export {fetchAnyUrl, postObjectAsJson, fetchUser};