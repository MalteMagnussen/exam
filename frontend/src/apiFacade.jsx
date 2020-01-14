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

  function addEditObj(ekstraURL, obj) {
    if (obj.id === "") {
      // ADD
      const uri = `/api/${ekstraURL}`;
      let options = makeOptions("POST", true, obj);
      fetch(uri, options).then(handleHttpErrors);
      console.log("POST");
    } else {
      // EDIT
      const uri = `/api/${ekstraURL}/${obj.id}`;
      const options = makeOptions("PUT", true, obj);
      fetch(uri, options).then(handleHttpErrors);
      console.log("PUT");
    }
  }

  /**
   * const uri = `/api/${ekstraURL}/${id}`;
   * const options = makeOptions("DELETE");
   * fetch(uri, options).then(handleHttpErrors);
   * @param {*} ekstraURL
   * @param {*} id
   */
  function deleteObj(ekstraURL, id) {
    const uri = `/api/${ekstraURL}/${id}`;
    const options = makeOptions("DELETE", true);
    fetch(uri, options).then(handleHttpErrors);
    console.log("DELETE");
  }

  //   //Remember to always include options from the makeOptions fucntion with >true< as the second parameter
  //   //if you want to access a protected endpoint

  /**
   * const options = makeOptions("GET", true); //True add's the token
   * return fetch(URL + `/api/${endpoint}/${value}`, options).then(handleHttpErrors);
   * @param {*} endpoint
   * @param {*} value
   */
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
    fetchGetData,
    addEditObj,
    deleteObj
  };
};

let returnVal = ApiFacade();
export default returnVal;
