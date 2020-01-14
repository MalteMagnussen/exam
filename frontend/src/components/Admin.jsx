import React, { useState } from "react";
import facade from "../apiFacade.jsx";
import { Dropdown, Button } from "react-bootstrap";

const errorMessage = "Fill out all fields before submitting.";

const acceptable_categories = [
  "career",
  "celebrity",
  "dev",
  "explicit",
  "fashion",
  "food",
  "history",
  "money",
  "movie",
  "music",
  "political",
  "science",
  "sport",
  "travel"
];

const AdminPanel = ({ loggedIn, roles }) => {
  return (
    <>
      {!loggedIn || !roles.includes("admin") ? (
        <p>You are not logged in as admin</p>
      ) : (
        <AdminPage />
      )}
    </>
  );
};

const AdminPage = () => {
  return (
    <>
      <CreateItem />
    </>
  );
};

const CreateItem = () => {
  const emptyItem = { id: "", name: "", price_pr_kg: "" };
  const [item, setItem] = useState({ ...emptyItem });
  const [errorMessage, setErrorMessage] = useState("");

  const handleChange = event => {
    if (item.name === "" || item.price_pr_kg === "") {
      setErrorMessage(errorMessage);
      return;
    }
    const target = event.target;
    const id = target.id;
    const value = target.value;
    setItem({ ...item, [id]: value });
  };

  const handleSubmit = event => {
    event.preventDefault();
    if (item.name === "" || item.price_pr_kg === "") {
      setErrorMessage(errorMessage);
      return;
    }
    console.log("About to submit item");
    facade.addEditObj(item);
    // Empty out the fields and set new item.
    setItem({ ...emptyItem });
    setErrorMessage("");
  };

  const buttonName = item.id === "" ? "Save" : "Edit";

  return (
    <>
      <br />
      {errorMessage !== "" && errorMessage}
      <br />
      <form className="form-horizontal" onChange={handleChange}>
        <div className="form-group">
          <label className="control-label col-sm-3">Id:</label>
          <div className="col-sm-9">
            <input className="form-control" readOnly id="id" value={item.id} />
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
              value={item.name}
              placeholder="Enter Name"
            />
          </div>
        </div>
        <div className="form-group">
          <label className="control-label col-sm-3" htmlFor="age">
            Age:
          </label>
          <div className="col-sm-9">
            <input
              type="number"
              className="form-control"
              id="price_pr_kg"
              value={item.price_pr_kg}
              placeholder="Indtast pris pr kg i ører"
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
                setItem({ ...emptyItem });
              }}
            >
              Cancel
            </button>
          </div>
        </div>
      </form>
      <p>{JSON.stringify(item)}</p>
    </>
  );
};

export default AdminPanel;
