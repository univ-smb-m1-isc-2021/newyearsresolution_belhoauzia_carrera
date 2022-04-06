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
    done(id){
        axios.get(`/api/done?username=`+this.props.username+"&id="+id)
            .then(res => {
                this.setState({nb_do :res.data.nb_do })
            })
    }
    render() {
        return (
            <div className="columns mt-1 is-centered">
                <div className="box has-text-centered is-one-third">
                    <h1 className="title has-text-white">{this.props.resolution.title}</h1>
                    <div className="subtitle has-text-white">{this.state.message}</div>
                    { this.props.showButton && this.props.username != "" ? <button className="button is-success" onClick={() => this.addResolution(this.props.resolution.id)}>Add resolution</button> : null }
                    { !this.props.showButton && this.props.username != "" ?
                        <div>
                            <div className="subtitle has-text-white"> Start date : {this.state.date}</div>
                            <div className="subtitle has-text-white"> NB DO : {this.state.nb_do}</div>
                            <button className="button is-success" onClick={() => this.done(this.props.resolution.id)}>Done</button>
                        </div>
                    : null }
                </div>
            </div>
        );
    }
}

export default Resolution;