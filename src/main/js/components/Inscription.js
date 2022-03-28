import React from "react";

class Inscription extends React.Component {

    render() {
        return (
            <div class="columns is-mobile is-centered">
                <div class="box column field is-one-third is-centered has-text-centered">
                    <h1 class="title has-text-centered has-text-white">Inscription</h1>
                    <label class="subtitle has-text-white" htmlFor="name">Name: </label>
                    <input class="input is-link" type="text" id="name" name="name" required /><br/><br/>
                    <label class="subtitle has-text-white" htmlFor="password">Password: </label>
                    <input class="input is-link " type="password" id="password" name="password" required/><br/><br/>
                    <label className="subtitle" htmlFor="confirm_password">Confirm password: </label>
                    <input className="input is-link" type="password" id="confirm_password" name="confirm_password" required/><br/><br/>
                    <button class="button is-success">Validate</button>
                </div>
            </div>
        );
    }
}

export default Inscription;