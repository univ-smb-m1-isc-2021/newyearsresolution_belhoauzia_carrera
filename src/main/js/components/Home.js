import React from "react";
import axios from 'axios';
import Resolution from "./Resolution";
import Legende from "./Legende";

class Home extends React.Component {


    constructor(props) {
        super(props);
        this.state = {
            resolutions:{"resolutions":[]},
            github:[],
            was:false
        }

    }
    componentDidMount() {
        this.fetchResolution()
        if(this.props.username != "") {
            this.setState({was:true})
            this.fetchGithub()
        }
    }
    componentDidUpdate() {
        if(!this.state.was){
            this.setState({was:true})
            this.fetchGithub()
        }
    }

    fetchResolution = ()=>{
        axios.get(`/api/AllResolutions`)
            .then(res => {
                this.setState({resolutions :{"resolutions":res.data }})
            })
    }
    fetchGithub = ()=>{
        axios.get(`/api/github?username=`+this.props.username)
            .then(res => {
                this.setState({github :res.data})

            })
    }
    createResolution =  (resolution) => {
        return <Resolution username={this.props.username} showButton={true} resolution={resolution.resolution} key={resolution.resolution} percent={resolution.percent}/>;
    }
    createResolutions =  (resolutions) => {
        return resolutions.map(this.createResolution)
    }

        render() {
        return (
            <div className="columns is-centered">
                <div className="column p-0 mt-5 is-centered is-8 home is-rounded ">
                    {this.props.username != "" ?
                        <div className="box github">
                            <h1 className="title has-text-white">Your tenacity</h1>
                            <div className="github">
                                <div className="months">
                                    {this.props.createMonth()}
                                </div>
                                <div className='boxGit'>
                                    {this.props.createCases(this.state.github)}
                                    {this.props.createDay()}
                                </div>
                                <Legende/>
                            </div>
                        </div>
                    : ""}
                    <div className="box has-text-centered is-8">
                        <h1 className="title has-text-white">Most Popular Resolutions</h1>
                        <div className="scrollBox">
                        {this.createResolutions(this.state.resolutions.resolutions)}
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default Home;