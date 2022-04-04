import React from "react";


class Login extends React.Component {

    render() {
        return (
            <div class="columns is-mobile is-centered">
                <div class="column box field is-one-third is-centered has-text-centered">
                    <h1 class="title has-text-centered has-text-white">Connexion</h1>
                    <form name="f" action="perform_login" method="POST">
                        <div className="subtitle has-text-white">With GitHub: <a href="/oauth2/authorization/github">click here</a></div>
                        <div className="subtitle has-text-white">With Google: <a href="/oauth2/authorization/google">click here</a></div>
                        <label class="subtitle has-text-white" htmlFor="name">Name: </label>
                        <input class="input is-link" type="text" id="name" name="name" required /><br/><br/>
                        <label class="subtitle has-text-white" htmlFor="password">Password: </label>
                        <input class="input is-link" type="password" id="password" name="password" required/><br/><br/>
                        <input type="checkbox" name="remember-me" /><label className="subtitle has-text-white" htmlFor="password">Remember me </label>
                        <input className="button is-success" name="submit" type="submit" value="Log in" />
                    </form>
                </div>
            </div>
        );
    }
}

export default Login;