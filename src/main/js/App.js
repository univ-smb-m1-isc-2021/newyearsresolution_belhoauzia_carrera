import React, {Component} from "react";
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import Login from "./components/Login";
import Home from "./components/Home";

class App extends Component {

    render() {
        return (
            <div>
            <Home/>
            </div>
           /* <Router>
                <h1>Welcome in our crazy website ! </h1>
                <div id="content">
                    <Switch>
                        <Route path="/" exact component={Home} />
                        <Route path="/login" exact component={Login} />
                    </Switch>
                </div>
            </Router>*/
        );
    }
}

export default App;