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
    createCase = (cases) => {
        let color_case
        if(cases < 1){
            color_case = "lv1"
        }
        else if(cases < 5){
            color_case = "lv2"
        }
        else if(cases < 10){
            color_case = "lv3"
        }
        else if(cases < 15){
            color_case = "lv4"
        }
        else {
            color_case = "lv5"
        }
        return <Case case={color_case}/>;
    }
    createCases =  (cases) => {
        return cases.map ((cases) => {
             return this.createCase(cases)
        })
    }
    createDay = ()=> {
        let weekday = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
        const d = new Date();
        weekday = weekday.slice(0,d.getDay()+1).reverse().concat(weekday.slice(d.getDay()+1).reverse());
        weekday = weekday.reverse();
        return weekday.map((element,key)=>{
                return <div className="jour">{element.substring(0, 3)}</div>;
            }
        )
    }

        render() {
        return (
            <div className="columns is-centered">
                <div className="column p-0 mt-5 is-centered is-8 home is-rounded ">
                    {this.props.username != "" ?
                        <div className="box github">
                            <h1 className="title has-text-white">Your tenacity</h1>
                            <div className="">
                                <div className='boxGit'>
                                    {this.createCases(this.state.github)}
                                    {this.createDay()}
                                </div>
                                <div>
                                    <div className="legende">
                                        <div className="jour">Less</div>
                                        <div className="cooloor">
                                            <div className='case lv1'></div>
                                            <div className='case lv2'></div>
                                            <div className='case lv3'></div>
                                            <div className='case lv4'></div>
                                            <div className='case lv5'></div>
                                        </div>
                                        <div className="jour">More</div>
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