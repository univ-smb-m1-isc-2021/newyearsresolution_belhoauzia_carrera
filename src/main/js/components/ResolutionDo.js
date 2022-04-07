import React from "react";

class ResolutionDo extends React.Component {
    constructor(props) {
        super(props);
    }
    render() {
        return (
            <div className="subtitle has-text-white mt-1 is-centered">
               Date: {this.props.nb_do.show_date} Number : {this.props.nb_do.nb_do}
            </div>
        );
    }
}

export default ResolutionDo;