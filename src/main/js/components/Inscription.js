import React from "react";

class Inscription extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            username : "",
            password : "",
            confirmation : "",
            remember:false
        }
    }
    render() {
        return (
            <div class="columns is-mobile is-centered">
                <div class="box column field is-one-third is-centered has-text-centered">
                    <h1 class="title has-text-centered has-text-white">Inscription</h1>
                    <label class="subtitle has-text-white" htmlFor="name">Name: </label>
                    <input class="input is-link" type="text" id="name" name="name" required onChange={event => this.setState({username: event.target.value})} /><br/><br/>
                    <label class="subtitle has-text-white" htmlFor="password">Password: </label>
                    <input class="input is-link " type="password" id="password" name="password" required onChange={event => this.setState({password: event.target.value})} /><br/><br/>
                    <label className="subtitle has-text-white" htmlFor="confirm_password">Confirm password: </label>
                    <input className="input is-link" type="password" id="confirm_password" name="confirm_password" required onChange={event => this.setState({confirmation: event.target.value})} /><br/><br/>
                    <input type="checkbox" name="remember-me" onChange={event => this.setState({remember: event.target.value})}/>
                    <label className="subtitle has-text-white" htmlFor="password">Remember me </label><br/>
                    <button class="button is-success" onClick={() => this.props.createAccount(this.state.username,this.state.password,this.state.confirmation,this.state.remember)}>Validate</button>
                </div>
            </div>
        );
    }
}

export default Inscription;