import React from "react";
import axios from 'axios';
import Resolution from "./Resolution";

class MyResolution extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            resolutions:{"resolutions":[]},
            message:""
        }

    }
    componentDidMount() {
        this.fetchResolution()
    }
    fetchResolution(){
        axios.get(`/api/myResolution?username=`+this.props.username)
            .then(res => {
                if(res.data.length != 0) {
                    this.setState({resolutions: {"resolutions": res.data}})
                }else{this.setState({message :"You don't have any resolutions :("})}
            })
    }
    createResolution =  (resolution) => {
        return <Resolution username={this.props.username} showButton={false} resolution={resolution} key={resolution}/>;
    }
    createResolutions =  (resolutions) => {
        return resolutions.map(this.createResolution);
    }
    render() {
        return (
            <div class="columns mt-1 is-mobile is-centered">
                <div class="box resolutions has-text-centered is-one-third">
                    <h1 class="title mb-10 has-text-white">My resolution</h1>
                    <div className="subtitle has-text-white">{this.state.message}</div>
                    <div className="scrollBox">
                        {this.createResolutions(this.state.resolutions.resolutions)}
                    </div>
                </div>
            </div>
        );
    }
}

export default MyResolution;