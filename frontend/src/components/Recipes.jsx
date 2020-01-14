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
  return (
    <>
      <p>youre logged in as admin</p>
    </>
  );
};

export default Recipes;
