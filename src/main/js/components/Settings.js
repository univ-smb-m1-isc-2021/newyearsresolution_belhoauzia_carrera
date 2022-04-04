import React from "react";
import axios from "axios";


class Settings extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            password:"",
            confirmation:"",
            message:"",
            remember:false
        }
    }
    componentDidMount() {
        let token = this.props.getWithExpiry("remeberme")
        if(token != null) {
            this.setState({remember:true})
        }

    }

    changeBox = () =>{
        this.setState({remember: !this.state.remember})
        if(this.state.remember){
            axios.get(`/api/getToken?username=`+this.props.name)
                .then(res => {
                    this.props.setWithExpiry("remeberme",res.data,1209600000)
                })
        }else{
            localStorage.removeItem("remeberme")
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

    render() {return (
            <div className="columns is-mobile is-centered">
                <div className="box column field is-one-third is-centered has-text-centered">
                    <h1 className="title has-text-centered has-text-white">Settings</h1>
                    <input type="checkbox" name="remember-me" className="m-2" defaultChecked={this.state.remember} onChange={() =>this.changeBox()}/>
                    <label className="subtitle has-text-white" htmlFor="password">Remember me </label><br/>
                    <h1 className="title has-text-centered has-text-white">Change password</h1>
                    <label className="subtitle has-text-white" htmlFor="password">Password: </label>
                    <input className="input is-link " type="password" id="password" name="password" required
                           onChange={event => this.setState({password: event.target.value})}/><br/><br/>
                    <label className="subtitle has-text-white" htmlFor="confirm_password">Confirm password: </label>
                    <input className="input is-link" type="password" id="confirm_password" name="confirm_password"
                           required onChange={event => this.setState({confirmation: event.target.value})}/><br/><br/>
                    <div id="message" className="m-2 has-text-white">{this.state.message}</div>
                    <button className="button is-success"
                            onClick={() => this.changePassword(this.state.password, this.state.confirmation)}>Validate
                    </button>
               </div>
            </div>
        );
    }
}

export default Settings;