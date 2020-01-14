import React, { useState, useEffect } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "../Welcome.css";
import facade from "../apiFacade.jsx";

const Welcome = () => {
  const [menu, setMenu] = useState();

  useEffect(() => {
    // UPDATE ITEMLIST
    facade
      .fetchGetData("restaurant/week")
      .then(res => setMenu(res))
      .catch(err => {
        if (err.status) {
          err.fullError.then(e => {
            console.log(e.code, e.message);
          });
        } else {
          console.log("Network error");
        }
      });
  }, []);

  return (
    <>
      <h1>Welcome to the restaurant! View the Week Menus Below!</h1>

      {menu && JSON.stringify(menu)}

      {
        // TODO INSERT TABLE WITH WEEK MENUS
      }
    </>
  );
};

export default Welcome;
