import React from "react";

class Day extends React.Component {
    constructor(props) {
        super(props);
    }
    render() {
        return (
        <div className="jour">{this.props.day}</div>
        );
    }
}

export default Day;