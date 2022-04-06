import React from "react";
import axios from 'axios';

class Resolution extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            message:""
        }
    }
    addResolution(id){
        axios.get(`/api/addResolutionToUser?username=`+this.props.username+"&id="+id)
            .then(res => {
                this.setState({message :res.data })
            })
    }
    render() {
        return (
            <div className="columns mt-1 is-centered">
                <div className="box has-text-centered is-one-third">
                    <h1 className="title has-text-white">Resolution</h1>
                    <div className="subtitle has-text-white">{this.state.message}</div>
                    <div className="subtitle has-text-white">{this.props.resolution.title}</div>
                    { this.props.showButton && this.props.username != "" ? <button className="button is-success" onClick={() => this.addResolution(this.props.resolution.id)}>Add resolution</button> : null }
                </div>
            </div>
        );
    }
}

export default Resolution;