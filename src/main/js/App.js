import React  from "react";
import Login from "./components/Login.js";
import Home from "./components/Home.js";
import Inscription from "./components/Inscription.js";
import AddResolution from "./components/AddResolution.js";
import MyResolution from "./components/MyResolution.js";
import axios from 'axios';

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            displayedTable:<Home />,
            name : "",
            msg : "",
            components : {
                "Home": <Home />,
                "Login": <Login login={this.login} showButton={this.showButton} />,
                "Inscription": <Inscription createAccount={this.createAccount} showButton={this.showButton}/>,
                "MyResolution": <MyResolution />,
            }
        }

    }
    componentDidMount() {
        axios.get(`/user`)
            .then(res => {
                if(res.data.name != undefined) {
                    this.setState({name: "Logged as " + res.data.name})
                    this.showButton()
                }
            })
        axios.get(`/getError`)
            .then(res => {
                this.setState({ msg : res.data})
            })
    }
    login = (username,password) => {
        axios.get(`/api/login?username=`+username+"&password="+password)
            .then((res) => {
                if(res.data != null) {
                    this.setState({name: "Logged as " + res.data})
                    this.setState({ msg : ""})
                    this.showButton(res)
                }else{
                    this.setState({ msg : "failed to connect"})
                }
            })
    }

    createAccount = (username,password,confirmation) => {
        if(password === confirmation) {
            axios.get(`/api/newUser?username=` + username + "&password=" + password)
                .then((res) => {
                    if (res.data != "Un utilisateur porte déjà ce nom") {
                        this.setState({name: "Logged as " + res.data})
                        this.setState({msg: ""})
                        this.showButton(res)
                    } else {
                        this.setState({msg: res.data})
                    }
                })
        }else{
            this.setState({msg: "password are different"})
        }
    }

    showButton = ()=>{
        document.getElementById("login").classList.add('is-hidden')
        document.getElementById("inscription").classList.add('is-hidden')
        document.getElementById("logout").classList.remove('is-hidden')
        document.getElementById("resolution").classList.remove('is-hidden')
        document.getElementById("addResolution").classList.remove('is-hidden')
        this.showComponent('Home')
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
                    <button id="login" class="button m-4 is-success" onClick={() => this.showComponent('Login')}>Login</button>
                    <button id="inscription" class="button m-4 is-success" onClick={() => this.showComponent('Inscription')}>Inscription</button>
                    <button id="resolution" className="button m-4 is-success is-hidden" onClick={() => this.showComponent('MyResolution')}>MyResolution</button>
                    <button id="addResolution" className="button m-4 is-success is-hidden" onClick={() => document.getElementById("popup-resolution").classList.add('is-active')}>AddResolution</button>
                    <div className="subtitle m-4 p-2 has-text-white">{this.state.name}</div>
                    <div className="subtitle m-4 p-2 has-text-white">{this.state.msg}</div>
                    <button onClick={()=>
                        axios.post(`/logout`)
                        .then(res => {
                            document.getElementById("login").classList.remove('is-hidden')
                            document.getElementById("inscription").classList.remove('is-hidden')
                            document.getElementById("logout").classList.add('is-hidden')
                            document.getElementById("resolution").classList.add('is-hidden')
                            document.getElementById("addResolution").classList.add('is-hidden')
                            this.setState({name : "Succesfully logged out"})
                        })} className="button m-4 is-danger is-hidden" id="logout">Logout</button>
                </div>
                <div id="content m-6">
                    {this.state.components[this.state.displayedTable]}
                    <AddResolution/>
                </div>
            </div>
        );
    }
}
export default App;