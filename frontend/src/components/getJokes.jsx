import React, { useState } from "react";
import { Button, Table } from "react-bootstrap";
import facade from "../apiFacade.jsx";

const acceptable_categories = {
  career: false,
  celebrity: false,
  dev: false,
  explicit: false,
  fashion: false,
  food: false,
  history: false,
  money: false,
  movie: false,
  music: false,
  political: false,
  science: false,
  sport: false,
  travel: false
};
const GetJokes = ({ loggedIn }) => {
  const [categories, setCategories] = useState(acceptable_categories);
  const [jokes, setJokes] = useState({});

  const handleChange = e => {
    const name = e.target.value;
    setCategories({ ...categories, [name]: !categories[name] });
  };

  const handleSubmit = e => {
    e.preventDefault();

    const validCategories = [];

    Object.keys(categories).forEach(category => {
      if (categories[category] == true) validCategories.push(category);
    });

    if (!loggedIn && validCategories.length > 4) {
      return;
    }

    const myCategories = validCategories.map((element, index) => {
      if (index == 0) return element;
      return element;
    });

    let URL = loggedIn => {
      if (loggedIn) {
        return "jokeByCategoryV2";
      } else {
        return "jokeByCategory";
      }
    };

    facade
      .fetchGetData(URL(loggedIn), myCategories)
      .then(res => {
        setJokes(res);
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
      <div>
        <h3> Select jokes </h3>
        <h5> {!loggedIn && "You may only select 4 categories."} </h5>
        <form>
          {Object.keys(categories).map((e, index) => {
            return (
              <div key={index}>
                <input type="checkbox" value={e} onChange={handleChange} />
                {e}
              </div>
            );
          })}
          <Button type="button" className="btn-primary" onClick={handleSubmit}>
            Submit
          </Button>
        </form>
      </div>
      <div>
        <br />
        <Table>
          <thead>
            <tr>
              <th>Category</th>
              <th>Joke</th>
            </tr>
          </thead>
          <tbody>
            {jokes &&
              jokes.jokes &&
              jokes.jokes.map((element, index) => {
                return (
                  <tr key={index}>
                    <td>{element.category}</td>
                    <td>{element.joke}</td>
                  </tr>
                );
              })}
          </tbody>
        </Table>
      </div>
    </>
  );
};

export default GetJokes;
