import React from "react";
import axios from "axios";


class Settings extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            password:"",
            confirmation:"",
            message:"",
            password_delete:"",
            remember:false
        }
    }
    componentDidMount() {
        let token = this.props.getWithExpiry("rememberme")
        if(token != null) {
            this.setState({remember:true})
        }

    }

    changeBox = () =>{
        this.setState({remember: !this.state.remember})
        if(!this.state.remember){
            axios.get(`/api/getToken?username=`+this.props.name)
                .then(res => {
                    this.props.setWithExpiry("rememberme",res.data,1209600000)
                })
        }else{
            localStorage.removeItem("rememberme")
        }
    }

    changePassword = (password,confirmation) =>{
        if(password === confirmation) {
            axios.get(`/api/changePassword?username=`+this.props.name+"&password="+password)
                .then(res => {
                    this.setState({message:res.data})
                })
        }else{
            this.setState({message:"Passwords are different"})
        }
    }

    deleteAccount = (password) =>{
        axios.get(`/api/deleteAccount?username=`+this.props.name+"&password="+password)
            .then(res => {
                this.setState({message:res.data})
                this.props.hideButton()
                localStorage.removeItem("rememberme")
            })
    }

    render() {return (
            <div className="columns mt-1 is-mobile is-centered">
                <div className="box column field is-one-third is-centered has-text-centered">
                    <h1 className="title mt-2 has-text-centered has-text-white">Settings</h1>
                    <div id="message" className="m-2 has-text-white">{this.state.message}</div>
                    <input type="checkbox" name="remember-me" className="m-2" checked={this.state.remember} onChange={() =>this.changeBox()}/>
                    <label className="subtitle has-text-white" htmlFor="password">Remember me </label><br/>
                    <h1 className="title has-text-centered has-text-white">Change password</h1>
                    <label className="subtitle has-text-white" htmlFor="password">New password: </label>
                    <input className="input is-link " type="password" id="password" name="password" required
                           onChange={event => this.setState({password: event.target.value})}/><br/><br/>
                    <label className="subtitle has-text-white" htmlFor="confirm_password">Confirm password: </label>
                    <input className="input is-link" type="password" id="confirm_password" name="confirm_password"
                           required onChange={event => this.setState({confirmation: event.target.value})}/><br/><br/>
                    <button className="button is-success"
                            onClick={() => this.changePassword(this.state.password, this.state.confirmation)}>Validate
                    </button>
                    <h1 className="title m-2 has-text-centered has-text-white">Delete Account</h1>
                    <div className="subtitle m-2 has-text-white">(If social login please change your password fisrt)</div>
                    <label className="subtitle has-text-white" htmlFor="password">Password: </label>
                    <input className="input is-link " type="password" id="password_delete" name="password" required
                           onChange={event => this.setState({password_delete: event.target.value})}/><br/><br/>
                    <button className="button is-danger"
                            onClick={() => this.deleteAccount(this.state.password_delete)}>Delete
                    </button>
               </div>
            </div>
        );
    }
}

export default Settings;