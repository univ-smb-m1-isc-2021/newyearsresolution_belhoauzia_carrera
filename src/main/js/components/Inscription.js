import React from "react";

class Inscription extends React.Component {

    render() {
        return (
            <div class="columns is-mobile is-centered">
                <div class="column field is-one-third">
                    <h1 class="title is-centered">Inscription</h1>
                    <label class="subtitle" htmlFor="name">Name: </label>
                    <input class="input is-link" type="text" id="name" name="name" required /><br/><br/>
                    <label class="subtitle" htmlFor="password">Password: </label>
                    <input class="input is-link" type="password" id="password" name="password" required/><br/><br/>
                    <label className="subtitle" htmlFor="confirm_password">Confirm password: </label>
                    <input className="input is-link" type="password" id="confirm_password" name="confirm_password" required/><br/><br/>
                    <button class="button is-centered">Validate</button>
                </div>
            </div>
        );
    }
}

export default Inscription;