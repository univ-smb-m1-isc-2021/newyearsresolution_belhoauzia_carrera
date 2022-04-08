import React  from "react";
import Login from "./components/Login.js";
import Home from "./components/Home.js";
import Inscription from "./components/Inscription.js";
import AddResolution from "./components/AddResolution.js";
import MyResolution from "./components/MyResolution.js";
import Settings from "./components/Settings.js";
import Footer from "./components/Footer";
import Refresh from "./components/Refresh";
import axios from 'axios';

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            displayedTable:"Home",
            name : "",
            username : "",
            msg : "",
            components : {},
        }

    }
    componentDidMount() {
        this.updateSate()
        let token = this.getWithExpiry("rememberme")
        if(token != null){
            axios.get(`/api/auto_connect?token=`+token)
                .then(res => {
                    if(res.data != null) {
                        this.setState({name:res.data},this.showComponent("Home"))
                        this.setState({username: res.data})
                        this.showButton()
                        this.updateSate()
                    }else{this.setState({ msg : "Something went wrong"})}
                })
        }else{
            axios.get(`/user`)
                .then(res => {
                    if(res.data.name != undefined) {
                        this.setState({name:res.data.name})
                        this.setState({username: res.data.name})
                        this.showButton()
                        this.updateSate()
                    }else if(res.data.erreur != undefined){
                        this.setState({msg:  res.data.erreur})
                    }
                })
            axios.get(`/getError`)
                .then(res => {
                    this.setState({ msg : res.data})
                })
        }
    }
    login = (username,password,remember) => {
        axios.get(`/api/login?username=`+username+"&password="+password+"&remember="+remember)
            .then((res) => {
                if(res.data != "null") {
                    this.setState({name:res.data[0]})
                    this.setState({username: res.data[0]})
                    this.setState({ msg : ""})
                    this.showButton(res)
                    this.updateSate()
                    this.addToken(res.data)
                }else{
                    this.setState({ msg : "Password or Username is wrong"})
                }
            })
    }
    updateSate = () =>{
        this.setState({components :{
                "Home": <Home username={this.state.username} />,
                "Refresh":<Refresh/>,
                "Login": <Login login={this.login} updateSate={this.updateSate} showButton={this.showButton} addToken={this.addToken}/>,
                "Inscription": <Inscription createAccount={this.createAccount} updateSate={this.updateSate} showButton={this.showButton} addToken={this.addToken}/>,
                "MyResolution": <MyResolution username={this.state.username}/>,
                "Settings": <Settings resetHome={this.resetHome}  setWithExpiry={this.setWithExpiry} getWithExpiry={this.getWithExpiry} name={this.state.username}/>
            }
        })
    }
    createAccount = (username,password,confirmation,remember) => {
        if(password === confirmation) {
            axios.get(`/api/newUser?username=` + username + "&password=" + password+"&remember="+remember)
                .then((res) => {
                    if (res.data != "Un utilisateur porte déjà ce nom") {
                        this.setState({name: "Logged as " + res.data[0]})
                        this.setState({username: res.data[0]})
                        this.setState({msg: ""})
                        this.showButton(res)
                        this.updateSate()
                        this.addToken(res.data)
                    } else {
                        this.setState({msg: res.data[0]})
                    }
                })
        }else{
            this.setState({msg: "password are different"})
        }
    }
    addToken = (data) =>{
        if(data.length > 1){
            this.setWithExpiry("rememberme",data[1],1209600000)
        }
    }
    showButton = ()=>{
        document.getElementById("login").classList.add('is-hidden')
        document.getElementById("inscription").classList.add('is-hidden')
        document.getElementById("logout").classList.remove('is-hidden')
        document.getElementById("resolution").classList.remove('is-hidden')
        document.getElementById("addResolution").classList.remove('is-hidden')
        document.getElementById("addResolution").classList.remove('is-hidden')
        document.getElementById("settings").classList.remove('is-hidden')
        this.showComponent('Home')
    }
    hideButton = ()=>{
        this.setState({components :{
                "Home": <Home username={this.state.username} />,
                "Refresh":<Refresh/>,
                "Login": <Login login={this.login} updateSate={this.updateSate} showButton={this.showButton} addToken={this.addToken}/>,
                "Inscription": <Inscription createAccount={this.createAccount} updateSate={this.updateSate} showButton={this.showButton} addToken={this.addToken}/>,
                "MyResolution": <MyResolution username={this.state.username}/>,
                "Settings": <Settings resetHome={this.resetHome}  setWithExpiry={this.setWithExpiry} getWithExpiry={this.getWithExpiry} name={this.state.username}/>
            }
        },()=>{
            document.getElementById("login").classList.remove('is-hidden')
            document.getElementById("inscription").classList.remove('is-hidden')
            document.getElementById("logout").classList.add('is-hidden')
            document.getElementById("resolution").classList.add('is-hidden')
            document.getElementById("addResolution").classList.add('is-hidden')
            document.getElementById("settings").classList.add('is-hidden')
            this.showComponent('Home')
            this.setState({name : "Succesfully logged out"})
        })

    }
    showComponent = (componentName)=> {
        this.setState({displayedTable: componentName});
    }
    refreshComponent= () =>{
        const component = this.state.displayedTable
        this.showComponent("Refresh")
        this.showComponent(component)
    }
    componentWillMount(){
        this.showComponent('Home')
    }
    resetHome = () =>{
        this.setState({username:""},this.hideButton)
    }
    logout(){
        axios.post(`/logout`)
            .then(res => {
                this.resetHome()
                localStorage.removeItem("rememberme")
            })
    }
    render() {
        return (
            <div>
                <div class="navbar">
                    <div className="navbar-start">
                    <figure className="image is-64x64 m-2">
                        <img src="/assets/logo.png" alt="logo"/>
                    </figure>
                    <button class="button m-4 is-success" onClick={() => this.showComponent('Home')}>Home</button>
                    <button id="login" class="button m-4 is-success" onClick={() => this.showComponent('Login')}>Login</button>
                    <button id="inscription" class="button m-4 is-success" onClick={() => this.showComponent('Inscription')}>Inscription</button>
                    <button id="resolution" className="button m-4 is-success is-hidden" onClick={() => this.showComponent('MyResolution')}>MyResolution</button>
                    <button id="addResolution" className="button m-4 is-success is-hidden" onClick={() => document.getElementById("popup-resolution").classList.add('is-active')}>AddResolution</button>
                </div>
                    <div className="navbar-end">
                    <div className="dropdown is-hoverable is-right">
                       <div className="dropdown-trigger">
                        <div className="button m-4 has-text-black " aria-haspopup="true" aria-controls="dropdown-menu">{this.state.name}</div>
                       </div>
                       <div className="dropdown-menu">
                        <div className="dropdown-content is-centered">
                            <button id="settings" className=" button m-4 is-success is-center" onClick={() => this.showComponent('Settings')}>Settings</button>
                            <div className="dropdown-divider"></div>
                            <button onClick={()=>this.logout()} className="button m-4 is-danger is-center" id="logout">Logout</button>
                        </div>
                       </div>
                   </div>
                    <div className="subtitle m-4 p-2 has-text-white">{this.state.msg}</div>
                    </div>
            </div>
                <div id="content">
                    {this.state.components[this.state.displayedTable]}
                    <AddResolution refreshComponent={this.refreshComponent} username={this.state.username}/>
                </div>
                <Footer/>
            </div>
        );
    }
    setWithExpiry = (key, value, ttl) => {
        const now = new Date()

        // `item` is an object which contains the original value
        // as well as the time when it's supposed to expire
        const item = {
            value: value,
            expiry: now.getTime() + ttl,
        }
        localStorage.setItem(key, JSON.stringify(item))
    }
    getWithExpiry = (key) => {
        const itemStr = localStorage.getItem(key)
        // if the item doesn't exist, return null
        if (!itemStr) {
            return null
        }
        const item = JSON.parse(itemStr)
        const now = new Date()
        // compare the expiry time of the item with the current time
        if (now.getTime() > item.expiry) {
            // If the item is expired, delete the item from storage
            // and return null
            localStorage.removeItem(key)
            return null
        }
        return item.value
    }
}
export default App;