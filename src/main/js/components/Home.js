import React from "react";
import axios from 'axios';
import Resolution from "./Resolution";

class Home extends React.Component {


    constructor(props) {
        super(props);
        this.state = {
            resolutions:{"resolutions":[]},
            github:{"github":[]},
            cases:{"cases":[]},
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
                let str = ""
                this.setState({github :{"github":res.data }})
                //console.log(this.state.github)

            })
    }
    createResolution =  (resolution) => {
        return <Resolution username={this.props.username} showButton={true} resolution={resolution} key={resolution}/>;
    }
    createResolutions =  (resolutions) => {
        return resolutions.map(this.createResolution)
    }
    render() {
        return (

            <div className="columns is-centered">
                {this.state.github}
                <div className="column mt-1 is-centered is-8">
                    {this.props.username != "" ?
                        <div className="box home github">
                            <h1 className="title has-text-white">Your tenacity</h1>
                            <div className="resolution">
                                {(() =>{
                                    const str = [];
                                    console.log("caca")
                                    for(let i = 0; i< this.state.github.github.lenght; i++){
                                        str.push("<div className='columns'>")
                                        for(let j = 0; j<6; j++){
                                            if (this.state.github.github.lenght[i]>2){
                                                str.push("<div className='column case is-black'></div>")
                                            }
                                            else{
                                                str.push("<div className='column case is-white'></div>")
                                            }
                                        }
                                        str.push("</div>");
                                        console.log(str);
                                        return str;
                                    }})}
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