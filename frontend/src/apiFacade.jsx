//Change this before deployment (production)
import { localURL as URL } from "./settings.js";
function handleHttpErrors(res) {
  if (!res.ok) {
    return Promise.reject({ status: res.status, fullError: res.json() });
  }
  return res.json();
}

const ApiFacade = () => {
  /**
   * method = PUT / POST / GET / DELETE
   * addToken = TRUE / FALSE (logged in or not)
   * body = Body of the fetch
   */
  function makeOptions(method, addToken, body) {
    var opts = {
      method: method,
      headers: {
        "Content-type": "application/json",
        Accept: "application/json"
      }
    };
    if (addToken && loggedIn()) {
      opts.headers["x-access-token"] = getToken();
    }
    if (body) {
      opts.body = JSON.stringify(body);
    }
    return opts;
  }

  function setToken(token) {
    localStorage.setItem("jwtToken", token);
  }

  function getToken() {
    return localStorage.getItem("jwtToken");
  }

  function loggedIn() {
    const loggedIn = getToken() != null;
    return loggedIn;
  }

  /*
    FETCH EXAMPLES START
  */
  //OBSERVE This returns a promise, NOT the actual data, you must handle asynchronicity by the client
  function getPersons() {
    return fetch(URL).then(handleHttpErrors);
  }

  function addEditPerson(person) {
    //Complete me. A smart version will handle both Add and Edit, but focus on Add (POST) only first

    if (person.id === "") {
      // ADD
      let options = makeOptions("POST", person);
      fetch(URL, options);
      console.log("POST");
    } else {
      // EDIT
      const uri = URL + "/" + person.id;
      const options = makeOptions("PUT", person);
      fetch(uri, options);
      console.log("PUT");
    }
  }

  function deletePerson(id) {
    const uri = URL + "/" + id;
    const options = makeOptions("DELETE");
    fetch(uri, options);
    console.log("DELETE");
    //Complete me
  }
  /*
    FETCH EXAMPLES END
  */

  //   //Remember to always include options from the makeOptions fucntion with >true< as the second parameter
  //   //if you want to access a protected endpoint
  const fetchGetData = (endpoint, value) => {
    const options = makeOptions("GET", true); //True add's the token
    return fetch(URL + `/api/${endpoint}/${value}`, options).then(
      handleHttpErrors
    );
  };

  const getRole = () => {
    let jwt = localStorage.getItem("jwtToken");
    let jwtData = jwt.split(".")[1];
    let decodedJwtJsonData = window.atob(jwtData);
    let decodedJwtData = JSON.parse(decodedJwtJsonData);
    return decodedJwtData.roles;
  };

  const login = (user, pass, setRoles) => {
    const options = makeOptions("POST", true, {
      username: user,
      password: pass
    });
    return fetch(URL + "/api/login", options)
      .then(handleHttpErrors)
      .then(res => {
        setToken(res.token);
        setRoles(getRole());
      });
  };

  const logout = () => {
    localStorage.removeItem("jwtToken");
  };

  return {
    login,
    logout,
    fetchGetData
  };
};

let returnVal = ApiFacade();
export default returnVal;
