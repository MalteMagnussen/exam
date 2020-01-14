import React, { useState, useEffect } from "react";
import facade from "../apiFacade.jsx";
import { Table, Row, Col, DropdownButton, Dropdown } from "react-bootstrap";

/**
 * TASKS HERE:
 * - The Chef should be able to see a list of all recipes
 * - The Chef should be able to search for a specific recipe 
    (you decide how, and which fields can be used in your search (ingredients, price etc)
 * - The Chef should be able to choose 7 recipes.
 * - When a recipe is chosen the app should check that all ingredients are available in storage or otherwise give a warning.
 */
const Chef = () => {
  return (
    <>
      <h1>Welcome to the Chefs interface.</h1>
    </>
  );
};

export default Chef;
