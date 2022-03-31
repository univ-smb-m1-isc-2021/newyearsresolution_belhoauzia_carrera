import React from "react";
import Login from "./components/Login.js";
import Home from "./components/Home.js";
import Inscription from "./components/Inscription.js";
import AddResolution from "./components/AddResolution.js";
import MyResolution from "./components/MyResolution.js";
import axios from 'axios';


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
            displayedTable:<Home />,
            name : "default"
        }

    }
    componentDidMount() {
        axios.get(`/user`)
            .then(res => {
                name = "Logged as " + res.data.name
            })
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
                <div class="mx-2 navbar">
                    <figure className="image is-64x64 m-2">
                        <img src="/assets/logo.png" alt="logo"/>
                    </figure>
                    <button class="button m-4 is-success" onClick={() => this.showComponent('Home')}>Home</button>
                    <button class="button m-4 is-success" onClick={() => this.showComponent('Login')}>Login</button>
                    <button class="button m-4 is-success" onClick={() => this.showComponent('Inscription')}>Inscription</button>
                    <button className="button m-4 is-success" onClick={() => this.showComponent('MyResolution')}>MyResolution</button>
                    <button className="button m-4 is-success" onClick={() => document.getElementById("popup-resolution").classList.add('is-active')}>AddResolution</button>
                    <div className="subtitle has-text-white">{name}</div>
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