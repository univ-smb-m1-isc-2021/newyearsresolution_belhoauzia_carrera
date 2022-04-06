import React from "react";

class Resolution extends React.Component {
    constructor(props) {
        super(props);
    }
    render() {
        return (
            <div class="columns mt-1 is-centered">
                <div class="box has-text-centered is-one-third">
                    <h1 class="title has-text-white">Resolution</h1>
                    <div className="subtitle has-text-white">{this.props.resolution.title}</div>
                </div>
            </div>
        );
    }
}

export default Resolution;