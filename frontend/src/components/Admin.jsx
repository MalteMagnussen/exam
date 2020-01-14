import React, { useState, useEffect } from "react";
import facade from "../apiFacade.jsx";
import { Table, Row, Col, DropdownButton, Dropdown } from "react-bootstrap";

const defaultErrorMessage = "Fill out all fields before submitting.";

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
  const emptyItem = { id: 0, name: "", price_pr_kg: 0 };
  const [item, setItem] = useState({ ...emptyItem });
  const [errorMessage, setErrorMessage] = useState("");
  const [stockItem, setStockItem] = useState();
  const [itemButton, setItemButton] = useState(false);
  const [itemList, setItemList] = useState();
  const [storageList, setStorageList] = useState();

  const handleChange = event => {
    if (item.name === "" || item.price_pr_kg == 0) {
      setErrorMessage(defaultErrorMessage);
    } else {
      setErrorMessage("");
    }
    const target = event.target;
    const id = target.id;
    const value = target.value;
    setItem({ ...item, [id]: value });
  };

  const handleSubmit = event => {
    event.preventDefault();
    if (item.name === "" || item.price_pr_kg == 0) {
      setErrorMessage(defaultErrorMessage);
      return;
    }
    console.log("About to submit item");
    facade.addEditObj("restaurant/item/add", item);
    // Empty out the fields and set new item.
    setItem({ ...emptyItem });
    setErrorMessage("");
  };

  // Updates List of items when refresh button is clicked and on load.
  useEffect(() => {
    // UPDATE ITEMLIST
    facade
      .fetchGetData("restaurant/items")
      .then(res => setItemList(res))
      .catch(err => {
        if (err.status) {
          err.fullError.then(e => console.log(e.code, e.message));
        } else {
          console.log("Network error");
        }
      });

    // UPDATE STORAGELIST
    facade
      .fetchGetData("restaurant/storage")
      .then(res => setStorageList(res))
      .catch(err => {
        if (err.status) {
          err.fullError.then(e => console.log(e.code, e.message));
        } else {
          console.log("Network error");
        }
      });
  }, [itemButton]);

  const buttonName = item.id === 0 ? "Save" : "Edit";

  return (
    <>
      <br />
      <Row>
        <Col>
          <h3>Add an Item.</h3>
          {errorMessage !== "" && errorMessage}
          <br />
          <form className="form-horizontal" onChange={handleChange}>
            <div className="form-group">
              <label className="control-label col-sm-3">Id:</label>
              <div className="col-sm-9">
                <input
                  className="form-control"
                  readOnly={true}
                  id="id"
                  value={item.id}
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
        </Col>
        <Col>
          <StockItem stockItem={stockItem} />
        </Col>
      </Row>

      <br />
      <ItemList
        setItemButton={setItemButton}
        itemButton={itemButton}
        itemList={itemList}
        storageList={storageList}
        setStockItem={setStockItem}
      />
    </>
  );
};

const StockItem = ({ stockItem }) => {
  return (
    <>
      <h3>Stock an Item</h3>
      {/*<DropdownButton id="dropdown-basic-button" title="Add Stock">
            {itemList &&
              itemList.forEach(item => (
                <Dropdown.Item key={item.id} onClick={() => setStockItem(item)}>
                  {item.name}
                </Dropdown.Item>
              ))}
              </DropdownButton>*/}
      How many of {stockItem.name} would you like to Stock?
      <input type="number"></input>
      <br />
      {JSON.stringify(stockItem)}
    </>
  );
};

const ItemList = ({
  setItemButton,
  itemButton,
  itemList,
  storageList,
  setStockItem
}) => {
  return (
    <>
      <button
        className="btn btn-primary"
        type="button"
        onClick={() => setItemButton(!itemButton)}
      >
        Refresh Item List.
      </button>
      <br />
      <Row>
        <Col>
          <h5>Item Table</h5>
          {itemList && (
            <Table>
              <thead>
                <tr>
                  <th>id</th>
                  <th>name</th>
                  <th>Price per KG</th>
                  <th>Stock this item</th>
                </tr>
              </thead>
              <tbody>
                {itemList.map(item => (
                  <tr key={item.id}>
                    <td>{item.id}</td>
                    <td>{item.name}</td>
                    <td>{item.price_pr_kg}</td>
                    <td>
                      <button
                        className="btn btn-success"
                        onClick={() => setStockItem(item)}
                      >
                        Add Stock
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          )}
          <p>ITEM LIST: {JSON.stringify(itemList)}</p>
        </Col>
        <Col>
          <h5>Storage Table</h5>
          {storageList && (
            <Table>
              <thead>
                <tr>
                  <th>id</th>
                  <th>name</th>
                  <th>Amount in Stock</th>
                </tr>
              </thead>
              <tbody>
                {storageList.map(storage => (
                  <tr key={storage.id}>
                    <td>{storage.id}</td>
                    <td>{storage.item.name}</td>
                    <td>{storage.amount}</td>
                  </tr>
                ))}
              </tbody>
            </Table>
          )}
          <p>STORAGE LIST: {JSON.stringify(storageList)}</p>
        </Col>
      </Row>
    </>
  );
};

export default AdminPanel;
