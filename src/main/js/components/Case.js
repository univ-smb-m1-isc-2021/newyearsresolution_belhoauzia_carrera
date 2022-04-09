import React from "react";

class Case extends React.Component {
    constructor(props) {
        super(props);
    }
    render() {
        return (
            <div className={'case ' + this.props.case + " " +this.props.col}></div>
        );
    }
}

export default Case;