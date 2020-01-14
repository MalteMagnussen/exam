import React, { useState, useEffect } from "react";
import facade from "../apiFacade.jsx";
import { DropdownButton, Dropdown } from "react-bootstrap";

const IngredientsPage = ({ loggedIn, roles }) => {
  return (
    <>
      {!loggedIn || !roles.includes("admin") ? (
        <p>You are not logged in as admin</p>
      ) : (
        <Ingredients />
      )}
    </>
  );
};

const Ingredients = () => {
  const [recipes, setRecipes] = useState();
  const [items, setItems] = useState();
  const [itemButton, setItemButton] = useState(false);
  const emptyRecipe = {
    directions: "",
    id: 0,
    ingredient_listDTO: [],
    name: "",
    preparation_time: 0
  };
  const [recipe, setRecipe] = useState({ ...emptyRecipe });
  const [item, setItem] = useState();
  const [amount, setAmount] = useState();

  const submitIngredient = () => {
    // ingredient/{itemId}/{amount}/{recipeId}
    // POST
    facade.myPost(
      "restaurant/ingredient/" +
        item.id +
        "/" +
        Number(amount) +
        "/" +
        recipe.id
    );
  };

  // Updates Lists of items when refresh button is clicked and on load.
  useEffect(() => {
    // UPDATE ITEMLISTS
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

    facade
      .fetchGetData("restaurant/items")
      .then(res => setItems(res))
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
      <h5>Make ingredient.</h5>
      {recipes && items && (
        <>
          <DropdownButton id="recipes" title="Choose Recipe">
            {recipes.map(recipe => (
              <Dropdown.Item key={recipe.id} onClick={() => setRecipe(recipe)}>
                {recipe.name}
              </Dropdown.Item>
            ))}
          </DropdownButton>{" "}
          Recipe name: {recipe && recipe.name}
          <br />
          <DropdownButton id="items" title="Choose Items">
            {items.map(item => (
              <Dropdown.Item key={item.id} onClick={() => setItem(item)}>
                {item.name}
              </Dropdown.Item>
            ))}
          </DropdownButton>{" "}
          Item name: {item && item.name}
          <br />
          <input
            type="number"
            placeholder="Input amount"
            value={amount}
            onChange={e => setAmount(e.target.value)}
          ></input>
        </>
      )}
      <br />
      <br />
      <button
        type="button"
        className="btn btn-success"
        onClick={submitIngredient}
      >
        Submit Ingredient
      </button>
      <br />
      <br />
      <button
        type="button"
        className="btn btn-primary"
        onClick={() => setItemButton(!itemButton)}
      >
        Refresh list
      </button>
      <br />
      {JSON.stringify(recipe)}
      <br />
      {JSON.stringify(item)}
      <br />
      {JSON.stringify(amount)}
      <br />
    </>
  );
};

export default IngredientsPage;
