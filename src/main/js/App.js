import React, {Component} from "react";
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import Login from "./components/Login";
import Home from "./components/Home";

class App extends Component {

    render() {
        return (
            <div>
                <h1>Welcome in our crazy website ! </h1>
                <div id="content">
                    <Switch>
                        <Route exact path="/app" component={Home} />
                        <Route exact path="/app/login" component={Login} />
                    </Switch>
                </div>
            </div>
        );
    }
}

export default App;