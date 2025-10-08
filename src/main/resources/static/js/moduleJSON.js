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
async function fetchUser2() {
    try{
        console.log(window.location.origin + "/me");
        const user = await fetchAnyUrl(window.location.origin + "/me");

        if (!user) {
            console.log("No user found (null response)");
            return null;
        }

        console.log("Fetched user:", user);
        return user;
    }catch (syntaxError){
    }

}
function isEmployee(user) {
    return user?.authority === "EMPLOYEE";
}

export {fetchAnyUrl, postObjectAsJson, fetchUser,fetchUser2,isEmployee};