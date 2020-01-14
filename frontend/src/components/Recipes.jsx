import React, { useState, useEffect } from "react";
import facade from "../apiFacade.jsx";
import { Table, Row, Col, DropdownButton, Dropdown } from "react-bootstrap";

const defaultErrorMessage = "Fill out all fields before submitting.";

const Recipes = ({ loggedIn, roles }) => {
  return (
    <>
      {!loggedIn || !roles.includes("admin") ? (
        <p>You are not logged in as admin</p>
      ) : (
        <RecipesPage />
      )}
    </>
  );
};

const RecipesPage = () => {
  const [recipes, setRecipes] = useState();
  const [itemButton, setItemButton] = useState(false);
  const [msg, setMsg] = useState("");
  const emptyRecipe = {
    directions: "",
    id: 0,
    ingredient_listDTO: [
      {
        amount: 0,
        id: 0,
        itemDTO: { id: 0, name: "", price_pr_kg: "" }
      },
      {
        amount: 0,
        id: 0,
        itemDTO: { id: 0, name: "", price_pr_kg: "" }
      }
    ],
    name: "",
    preparation_time: 0
  };
  const [recipe, setRecipe] = useState({ ...emptyRecipe });

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

  return (
    <>
      <br />
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
                  <th>Edit</th>
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
                        className="btn btn-primary"
                        onClick={() => setRecipe(item)}
                      >
                        EDIT
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          )}
          <br />
          {JSON.stringify(recipe)}
        </Col>
        <Col>Form for editing goes here.</Col>
      </Row>
    </>
  );
};

export default Recipes;
