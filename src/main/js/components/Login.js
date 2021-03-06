import React from "react";

class Login extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            username : "",
            password : "",
            remember : false
        }
    }

    render() {
        return (
            <div class="columns mt-1 is-mobile is-centered">
                <div class="column box field is-one-third is-centered has-text-centered">
                    <h1 class="title has-text-centered has-text-white">Connexion</h1>
                    <div className="columns is-centered">
                        <div className="column has-text-centered">
                            <a href="/oauth2/authorization/github">
                                <figure className="image is-inline-block is-48x48">
                                    <img className="is-rounded" src="./assets/github.png"/>
                                </figure>
                            </a>
                        </div>
                        <div className="column has-text-centered">
                            <a href="/oauth2/authorization/google">
                                <figure className="image is-inline-block is-48x48">
                                    <img className="is-rounded" src="./assets/google.png"/>
                                </figure>
                            </a>
                        </div>
                        <div className="column has-text-centered">
                            <a href="/oauth2/authorization/facebook">
                                <figure className="image is-inline-block is-48x48">
                                    <img className="is-rounded" src="./assets/facebook.png"/>
                                </figure>
                            </a>
                        </div>
                    </div>
                    <label class="subtitle has-text-white" htmlFor="name">Name: </label>
                    <input class="input is-link" type="text" id="username" name="username" onChange={event => this.setState({username: event.target.value})} required /><br/><br/>
                    <label class="subtitle has-text-white" htmlFor="password">Password: </label>
                    <input class="input is-link" type="password" id="password" name="password"  onChange={event => this.setState({password: event.target.value})} required/><br/><br/>
                    <input type="checkbox" name="remember-me" onChange={event => this.setState({remember: event.target.value})}/>
                    <label className="subtitle has-text-white" htmlFor="password">Remember me </label>
                    <br/>
                    <button className="button is-success" onClick={() => this.props.login(this.state.username,this.state.password,this.state.remember)}>Log in</button>
                </div>
            </div>
        );
    }

}

export default Login;