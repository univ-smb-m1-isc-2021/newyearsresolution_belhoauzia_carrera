import React from "react";
import axios from 'axios';
import Resolution from "./Resolution";

class Home extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            resolutions:{"resolutions":[]},
        }

    }
    componentDidMount() {
        this.fetchResolution()
    }
    fetchResolution(){
        axios.get(`/api/AllResolutions`)
            .then(res => {
                this.setState({resolutions :{"resolutions":res.data }})
            })
    }
    createResolution =  (resolution) => {
        return <Resolution resolution={resolution} key={resolution}/>;
    }
    createResolutions =  (resolutions) => {
        return resolutions.map(this.createResolution);
    }
    render() {
        return (
            <div className="columns mt-1 is-centered">
                <div className="box has-text-centered is-one-third">
                    <h1 className="title has-text-white">Home page</h1>
                    <div className="scrollBox">
                    {this.createResolutions(this.state.resolutions.resolutions)}
                    </div>
                </div>
            </div>
        );
    }
}

export default Home;