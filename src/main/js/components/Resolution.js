import React from "react";
import axios from 'axios';

class Resolution extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            message:"",
            date:"",
            nb_do:0
        }
    }
    componentDidMount() {
        if(!this.props.showButton) {
            axios.get(`/api/getUserRes?username=` + this.props.username + "&id=" + this.props.resolution.id)
                .then(res => {
                    console.log(res.data)
                    if (res.data != null) {
                        this.setState({date: res.data.start_date, nb_do: res.data.nb_do})
                    }
                })
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
                    { !this.props.showButton && this.props.username != "" ? <div><div className="subtitle has-text-white"> Start date : {this.state.date}</div><div className="subtitle has-text-white"> NB DO : {this.state.nb_do}</div> </div>  : null }
                </div>
            </div>
        );
    }
}

export default Resolution;