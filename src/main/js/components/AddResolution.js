import React from "react";


class AddResolution extends React.Component {

    render() {
        return (
            <div id="popup-resolution" className="modal">
                <div className="modal-background"></div>
                <div className="modal-content">
                    <div class="box">
                        <h1 className="title has-text-centered has-text-white">Add a new resolution</h1>
                        <div id="form" class="box has-text-centered">
                            <label className="subtitle has-text-white" htmlFor="name">Name: </label>
                            <input className="input is-link" type="text" id="name" name="name" required/><br/><br/>
                            <label className="subtitle has-text-white" htmlFor="description">Description: </label>
                            <textarea  className="input is-link" type="text" id="description" name="description" required></textarea><br/><br/>
                            <button className="button is-success" onClick={() => document.getElementById("popup-resolution").classList.remove('is-active')}>Add resolution</button>
                        </div>
                    </div>
                </div>
                <button className="modal-close is-large" aria-label="close" onClick={() => document.getElementById("popup-resolution").classList.remove('is-active')}></button>
            </div>
        );
    }
}

export default AddResolution;