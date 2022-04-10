import React from "react";
import axios from 'axios';
import Resolution from "./Resolution";
import Case from "./Case";

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
    componentDidUpdate(prevProps, prevState, snapshot) {
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
                            <div className="">
                                <div className="months">
                                    {this.props.createMonth()}
                                </div>
                                <div className='boxGit'>
                                    {this.props.createCases(this.state.github)}
                                    {this.props.createDay()}
                                </div>
                                <div>
                                    <div className="legende">
                                        <div className="descLegende">Less</div>
                                        <div className="cooloor">
                                            <div className='case lv1'></div>
                                            <div className='case lv2'></div>
                                            <div className='case lv3'></div>
                                            <div className='case lv4'></div>
                                            <div className='case lv5'></div>
                                        </div>
                                        <div className="descLegende">More</div>
                                    </div>
                                </div>
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