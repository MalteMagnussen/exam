import React, { useState, useEffect } from "react";
import facade from "../apiFacade.jsx";
import { Table, Row, Col, DropdownButton, Dropdown } from "react-bootstrap";

const defaultErrorMessage = "Fill out all fields before submitting.";

const Recipes = ({ loggedIn, roles }) => {
  return (
    // <>
    //   {!loggedIn || !roles.includes("admin") ? (
    //     <p>You are not logged in as admin</p>
    //   ) : (
    //     <RecipesPage />
    //   )}
    // </>
    <RecipesPage />
  );
};

const RecipesPage = () => {
  const [recipes, setRecipes] = useState();
  const [itemButton, setItemButton] = useState(false);
  const [msg, setMsg] = useState("");
  const emptyRecipe = {
    directions: "",
    id: 0,
    ingredient_listDTO: [],
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
        <Col>
          <AddEditRecipe
            newRecipe={recipe}
            emptyRecipe={emptyRecipe}
            setMsg={setMsg}
          />
        </Col>
      </Row>
    </>
  );
};

const AddEditRecipe = ({ newRecipe, emptyRecipe, setMsg }) => {
  const [recipe, setRecipe] = useState({ ...newRecipe });

  const handleChange = event => {
    const target = event.target;
    const id = target.id;
    const value = target.value;
    setRecipe({ ...recipe, [id]: value });
  };

  const handleSubmit = event => {
    if (
      recipe.name === "" ||
      recipe.directions === "" ||
      recipe.preparation_time === 0
    ) {
      setMsg("You must fill all fields before editing or submitting.");
      return;
    }

    console.log("About to submit recipe");
    facade.addEditObj(recipe);
    // Empty out the fields and set new recipe.
    setRecipe({ ...emptyRecipe });
    event.preventDefault();
  };

  useEffect(
    () =>
      setRecipe({
        ...newRecipe
      }),
    [newRecipe]
  );

  const buttonName = recipe.id === 0 ? "Save" : "Edit";

  return (
    <div>
      <form className="form-horizontal" onChange={handleChange}>
        <div className="form-group">
          <label className="control-label col-sm-3">Id:</label>
          <div className="col-sm-9">
            <input
              className="form-control"
              readOnly
              id="id"
              value={recipe.id}
            />
          </div>
        </div>
        <div className="form-group">
          <label className="control-label col-sm-3" htmlFor="name">
            Name:
          </label>
          <div className="col-sm-9">
            <input
              className="form-control"
              id="name"
              value={recipe.name}
              placeholder="Enter Name"
            />
          </div>
        </div>
        <div className="form-group">
          <label className="control-label col-sm-3" htmlFor="age">
            Directions:
          </label>
          <div className="col-sm-9">
            <input
              type="text"
              className="form-control"
              id="directions"
              value={recipe.directions}
              placeholder="Enter age"
            />
          </div>
        </div>
        <div className="form-group">
          <label className="control-label col-sm-3" htmlFor="email">
            Preparation Time:
          </label>
          <div className="col-sm-9">
            <input
              type="number"
              className="form-control"
              id="preparation_time"
              value={recipe.preparation_time}
              placeholder="Enter email"
            />
          </div>
        </div>

        <div className="form-group">
          <div className="col-sm-offset-3 col-sm-9">
            <button
              type="submit"
              onClick={handleSubmit}
              className="btn btn-primary"
            >
              {buttonName}
            </button>
            <button
              style={{ marginLeft: 5 }}
              type="button"
              className="btn btn-dark"
              onClick={() => {
                setRecipe({ ...emptyRecipe });
              }}
            >
              Cancel
            </button>
          </div>
        </div>
      </form>
      <p>{JSON.stringify(recipe)}</p>
    </div>
  );
};

export default Recipes;
