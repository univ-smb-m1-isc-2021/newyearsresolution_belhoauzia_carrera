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
            cases:[],
        }

    }
    componentDidMount() {
        this.fetchResolution()

        this.fetchGithub()
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
                console.log(res.data)

            })
    }
    createResolution =  (resolution) => {
        return <Resolution username={this.props.username} showButton={true} resolution={resolution} key={resolution}/>;
    }
    createResolutions =  (resolutions) => {
        return resolutions.map(this.createResolution)
    }
    createCase = (element, key) => {
        let col = "";
        if (key%7==0 || key ==0){
            col = "column"
        }
        let color_case = "has-background-danger"
        if(element > 2){
            color_case = "has-background-success"
        }
        return <Case case={color_case} col={col} />;
    }
    createCases =  (cases) => {
        return cases.map ((element, key) => {
            this.createCase(element, key)
        })
    }
    render() {
        return (

            <div className="columns is-centered">
                <div className="column mt-1 is-centered is-8">
                    {this.props.username != "" ?
                        <div className="box home github">
                            <h1 className="title has-text-white">Your tenacity</h1>
                            <div className="resolution">
                                <div className='columns'>
                                    {this.createCases(this.state.github)}
                                </div>
                            </div>
                        </div>
                    : ""}
                    <div className="box home has-text-centered is-8">
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