import React, { useState } from "react";
import facade from "./apiFacade";
import "bootstrap/dist/css/bootstrap.min.css";
import { LogIn, LoggedIn } from "./components/Login.jsx";
import WelcomePage from "./components/Welcome.jsx";
import Admin from "./components/Admin.jsx";
import Chef from "./components/Chef.jsx";
import {
  HashRouter as Router,
  Switch,
  Route,
  NavLink,
  Link
} from "react-router-dom";
import ShowRoles from "./components/ShowRoles.jsx";
import Recipes from "./components/Recipes.jsx";
import IngredientsPage from "./components/Ingredients.jsx";

const App = () => {
  const [loggedIn, setLoggedIn] = useState(false);
  const [roles, setRoles] = useState([]);
  return (
    <>
      <Router>
        <Header
          loggedIn={loggedIn}
          setLoggedIn={setLoggedIn}
          roles={roles}
          setRoles={setRoles}
        />
        <div className="container">
          <Switch>
            <Route exact path="/" component={WelcomePage} />
            <Route
              path="/login"
              render={() => (
                <LogInScreen
                  loggedIn={loggedIn}
                  setLoggedIn={setLoggedIn}
                  roles={roles}
                  setRoles={setRoles}
                />
              )}
            />
            <Route path="/admin">
              <Admin loggedIn={loggedIn} roles={roles} />
            </Route>
            <Route path="/adminrecipes">
              <Recipes loggedIn={loggedIn} roles={roles} />
            </Route>
            <Route path="/chef">
              <Chef loggedIn={loggedIn} />
            </Route>
            <Route path="/ingredients">
              <IngredientsPage loggedIn={loggedIn} roles={roles} />
            </Route>
            <Route component={NoMatch} />
          </Switch>
        </div>
      </Router>
    </>
  );
};

const NoMatch = () => (
  <div>You're trying to access a resource that doesn't exist.</div>
);

const Header = ({ loggedIn, setLoggedIn, roles, setRoles }) => {
  return (
    <ul className="header">
      <li>
        <NavLink exact activeClassName="active" to="/">
          Home
        </NavLink>
      </li>

      {/*Login / logout start*/}
      {!loggedIn ? (
        <li>
          <NavLink activeClassName="active" to="/login">
            Login
          </NavLink>
        </li>
      ) : (
        <li>
          <NavLink
            onClick={() => {
              setLoggedIn(false);
              facade.logout();
              setRoles([]);
            }}
            to="/login"
          >
            Log out
          </NavLink>
        </li>
      )}
      {/*Login / logout end*/}

      {loggedIn && (
        <li>
          <NavLink to="/chef">Chef panel</NavLink>
        </li>
      )}
      {loggedIn && roles.includes("admin") && (
        <li>
          <NavLink to="/admin">Admin - Items</NavLink>
        </li>
      )}
      {loggedIn && roles.includes("admin") && (
        <li>
          <NavLink to="/adminrecipes">Admin - Recipes</NavLink>
        </li>
      )}
      {loggedIn && roles.includes("admin") && (
        <li>
          <NavLink to="/ingredients">Admin - Ingredients</NavLink>
        </li>
      )}
    </ul>
  );
};

const LogInScreen = ({ loggedIn, setLoggedIn, roles, setRoles }) => {
  const [message, setMessage] = useState("");
  const login = (user, pass) => {
    facade
      .login(user, pass, setRoles)
      .then(res => {
        setMessage("");
        setLoggedIn(true);
      })
      .catch(err => {
        if (err.status) {
          setMessage("Failed to log in, check your information");
          err.fullError.then(e => console.log(e.code, e.message));
        } else {
          console.log("Network error");
        }
      });
  };

  return (
    <div>
      {!loggedIn ? (
        <LogIn login={login} message={message} />
      ) : (
        <div>
          {roles.length && (
            <>
              <ShowRoles roles={roles} />
              <LoggedIn />
            </>
          )}
        </div>
      )}
      <br></br>
      <Link to="/">Back to WelcomePage</Link>
    </div>
  );
};

export default App;
