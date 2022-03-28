import React from "react";
import Login from "./components/Login.js";
import Home from "./components/Home.js";
import Inscription from "./components/Inscription.js";
import AddResolution from "./components/AddResolution.js";
import MyResolution from "./components/MyResolution.js";


const components = {
    "Home": <Home />,
    "Login": <Login />,
    "Inscription": <Inscription />,
    "MyResolution": <MyResolution />,
}

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            displayedTable:<Home />
        }

    }
    showComponent(componentName) {
        this.setState({displayedTable: componentName});
    }
    componentWillMount(){
        this.showComponent('Home')
    }

    render() {
        return (
            <div>
                <div class="m-2 navbar">
                    <figure className="image is-48x48 m-2">
                        <img src="/assets/logo.png" alt="logo"/>
                    </figure>
                    <button class="button m-2 is-success" onClick={() => this.showComponent('Home')}>Home</button>
                    <button class="button m-2 is-success" onClick={() => this.showComponent('Login')}>Login</button>
                    <button class="button m-2 is-success" onClick={() => this.showComponent('Inscription')}>Inscription</button>
                    <button className="button m-2 is-success" onClick={() => this.showComponent('MyResolution')}>MyResolution</button>
                    <button className="button m-2 is-success" onClick={() => document.getElementById("popup-resolution").classList.add('is-active')}>AddResolution</button>
                </div>
                <div id="content m-6">
                    {components[this.state.displayedTable]}
                    <AddResolution/>
                </div>
            </div>
        );
    }
}
export default App;