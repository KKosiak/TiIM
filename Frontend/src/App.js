import React from "react";
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';

import MasterLayout from "./layout/admin/MasterLayout";
import Home from './components/frontend/Home';
import Login from './components/frontend/auth/Login';
import Register from './components/frontend/auth/Register';
import Products from './components/frontend/pages/Products';
import EditProduct from './components/frontend/pages/EditProduct';
import EditCategories from './components/frontend/pages/EditCategories';
import axios from "axios";
import Categories from "./components/frontend/pages/Categories";

// axios.defaults.baseURL="http://localhost:8000/";
axios.defaults.headers.post['Accept']= 'application.json';
axios.defaults.headers.post['Content-Type']= 'application.json';
axios.defaults.withCredentials=true;

function App() {
  return (
    <div className="App">
        <Router>
          <Switch>
              <Route exact path="/" component={Home} />
              <Route exact path="/login" component={Login} />
              <Route exact path="/register" component={Register} />
              <Route exact path="/products" component={Products} />
              <Route exact path="/categories" component={Categories} />
              <Route exact path="/products/:id" component={EditProduct} />
              <Route exact path="/categories/:id" component={EditCategories} />
              <Route path="/admin" name="Admin" render={(props) => <MasterLayout  {...props} /> } />
          </Switch>
        </Router>
    </div>
  );
}

export default App;
