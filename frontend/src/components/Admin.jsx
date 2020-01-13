import React, { useState } from "react";
import facade from "../apiFacade.jsx";
import { Dropdown, Button } from "react-bootstrap";

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
  if (!loggedIn || !roles.includes("admin")) {
    return (
      <>
        <p>You are not logged in as admin</p>
      </>
    );
  } else {
    return (
      <>
        <AdminPage />
      </>
    );
  }
};

const AdminPage = () => {
  const [category, setCategory] = useState();
  const [count, setCount] = useState();

  const submitHandler = () => {
    facade
      .fetchGetData("categoryCount", category)
      .then(res => {
        setCount(res);
        console.log(res);
      })
      .catch(err => {
        if (err.status) {
          err.fullError.then(e => {
            console.log(e.code, e.message);
          });
        } else {
          console.log("Network error");
        }
      });
  };

  return (
    <>
      <br />
      <Dropdown>
        <Dropdown.Toggle variant="success" id="dropdown-basic">
          Select Category.
        </Dropdown.Toggle>
        <Dropdown.Menu>
          {acceptable_categories.map((element, index) => {
            return (
              <Dropdown.Item
                value={element}
                key={index}
                onClick={() => setCategory(element)}
              >
                {element}
              </Dropdown.Item>
            );
          })}
        </Dropdown.Menu>
      </Dropdown>
      <br />
      Click submit to get count of requests made in that category <br />
      <Button type="button" className="btn-primary" onClick={submitHandler}>
        Submit
      </Button>
      <br />
      Amount of requests made for {category} is:{" "}
      {count && count.count && count.count}.
    </>
  );
};

export default AdminPanel;
