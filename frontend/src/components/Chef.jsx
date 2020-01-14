import React, { useState, useEffect } from "react";
import facade from "../apiFacade.jsx";
import { Table, Row, Col, DropdownButton, Dropdown } from "react-bootstrap";

/**
 * DONE HERE:
 * - The Chef should be able to see a list of all recipes
 * - The Chef should be able to choose 7 recipes.
 * - When a recipe is chosen the app should check that all ingredients are available in storage or otherwise give a warning.
 */
const Chef = ({ loggedIn }) => {
  return <>{!loggedIn ? <p>You are not logged in</p> : <ChefPage />}</>;
};

const ChefPage = () => {
  // TODO ADD LOGIN CHECK
  const [recipes, setRecipes] = useState();
  const [itemButton, setItemButton] = useState(false);
  const [menu, setMenu] = useState([]);
  const [msg, setMsg] = useState("");

  // Updates List of items when refresh button is clicked and on load.
  useEffect(() => {
    // UPDATE ITEMLIST
    facade
      .fetchGetData("restaurant/recipe")
      .then(res => setRecipes(res))
      .catch(err => {
        if (err.status) {
          err.fullError.then(e => console.log(e.code, e.message));
        } else {
          console.log("Network error");
        }
      });
  }, [itemButton]);

  const handleMenu = item => {
    if (menu.length >= 7) {
      return;
    }

    facade
      .myPost("restaurant/recipe/check", item.ingredient_listDTO)
      .then(res => setMsg(res.message))
      .catch(err => {
        if (err.status) {
          err.fullError.then(e => {
            console.log(e.code, e.message);
            setMsg(e.message);
          });
        } else {
          console.log("Network error");
        }
      });

    setMenu([...menu, item]);
  };

  return (
    <>
      <h1>Welcome to the Chefs interface.</h1>
      <h2>{msg}</h2>
      <Row>
        <Col>
          <h5>Item Table</h5>
          {recipes && (
            <Table>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Name</th>
                  <th>Directions</th>
                  <th>Prep Time in seconds</th>
                  <th>Put in Menu</th>
                </tr>
              </thead>
              <tbody>
                {recipes.map(item => (
                  <tr key={item.id}>
                    <td>{item.id}</td>
                    <td>{item.name}</td>
                    <td>{item.directions}</td>
                    <td>{item.preparation_time}</td>
                    <td>
                      <button
                        type="button"
                        className="btn btn-success"
                        onClick={() => handleMenu(item)}
                      >
                        Add
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          )}

          <br />

          {JSON.stringify(recipes)}
        </Col>
        <Col>
          <br />
          <Menu menu={menu} setMenu={setMenu} />
        </Col>
      </Row>

      <br />
      <br />
      <br />
      <br />
    </>
  );
};

const Menu = ({ menu, setMenu }) => {
  const week = [
    "Monday",
    "Tuesday",
    "Wednesday",
    "Thursday",
    "Friday",
    "Saturday",
    "Sunday"
  ];

  return (
    <>
      <h3>The Menu for Next Week: </h3>
      <h4>Max 7 items in menu.</h4>
      <button
        type="button"
        className="btn btn-danger"
        onClick={() => setMenu([])}
      >
        Empty Menu List.
      </button>
      <h5>Menu Table</h5>
      {menu && (
        <Table>
          <thead>
            <tr>
              <th>Weekday</th>
              <th>Name</th>
              <th>Directions</th>
              <th>Prep Time in seconds</th>
            </tr>
          </thead>
          <tbody>
            {menu.map((item, index) => (
              <tr key={item.id}>
                <td>{week[index]}</td>
                <td>{item.name}</td>
                <td>{item.directions}</td>
                <td>{item.preparation_time}</td>
              </tr>
            ))}
          </tbody>
        </Table>
      )}

      <br />
      {JSON.stringify(menu)}
    </>
  );
};

export default Chef;
