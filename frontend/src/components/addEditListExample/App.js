import React, { useState, useEffect } from "react";
import AddEditPerson from "./AddEditPerson";
import AllPersons from "./AllPersons";

function App({ apiFacade }) {
  const emptyPerson = { id: "", age: "", name: "", email: "", gender: "" };
  const [personToAddEdit, setPersonToAddEdit] = useState(emptyPerson);
  const [persons, setPersons] = useState([]);

  const updateList = () => {
    apiFacade.getPersons().then(data => {
      setPersons(data);
    });
  };

  useEffect(() => {
    updateList();
    //This would be a great place to fetch data (all persons) from the backend
    const timer = setInterval(() => {
      updateList();
    }, 3000);

    // Clean-up
    return () => {
      clearInterval(timer);
    };
  }, []);

  const storeAddEditPerson = person => {
    //Call this from the AddEditPerson control with the person to Add or Edit and Add/Edit via the apiFacade
    apiFacade.addEditPerson(person);
    console.log("storeAddEditPerson");
  };

  const deletePerson = id => {
    //Call this from the AllPerson control with the id for the person to delete
    apiFacade.deletePerson(id);
  };

  const editPerson = person => {
    //Call thisfrom the AllPerson control with the  person to edit
    //Set the state variable personToAddEdit with this person (a clone) to make the new value flow down via props
    setPersonToAddEdit(person);
  };

  return (
    <div className="container">
      <div className="row">
        <h3>CRUD Demo </h3>
        <div className="col-md-7">
          <h3>All Persons</h3>
          <AllPersons
            persons={persons}
            editPerson={editPerson}
            deletePerson={deletePerson}
          />
        </div>
        <div className="col-md-5">
          <h3 style={{ textAlign: "center" }}>Add Persons</h3>
          <AddEditPerson
            newPerson={personToAddEdit}
            //  Next two lines, are if you decide to use the pattern introduced in the day-2 exercises
            addEditPerson={storeAddEditPerson}
          />
        </div>
      </div>
    </div>
  );
}
export default App;
