import React from "react";
import axios from 'axios';


class AddResolution extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            erreur:"",
            title:"",
            des : "",
            nb_oc : 0,
            freq : 0
        }

    }
    addResolution(){
        axios.get(`/api/newResolution?title=`+this.state.title+"&des="+this.state.des+"&nboc="+this.state.nb_oc+"&freq="+this.state.freq+"&username="+this.props.username)
            .then(res => {
                if(res.data == "ok") {
                    this.setState({erreur:"",title:"",des : "",nb_oc : 0,freq : 0})
                    document.getElementById("popup-resolution").classList.remove('is-active')
                    this.props.refreshComponent()
                }else{this.setState({ erreur : "Something went wrong"})}
            })
            .catch(() =>{
                this.setState({ erreur : "Something went wrong"})
            })
    }
    render() {
        return (
            <div id="popup-resolution" className="modal">
                <div className="modal-background"></div>
                <div className="modal-content">
                    <div class="box">
                        <h1 className="title has-text-centered has-text-white">Add a new resolution</h1>
                        <div className="subtitle has-text-centered has-text-danger">{this.state.erreur}</div>
                        <div id="form" class="has-text-centered">
                            <label className="subtitle has-text-white" htmlFor="name">Name: </label>
                            <input className="input is-link" type="text" id="name" name="name" required value={this.state.title} onChange={event => this.setState({title: event.target.value})}/><br/><br/>
                            <label className="subtitle has-text-white" htmlFor="description">Description: </label>
                            <textarea  className="input is-link" type="text" id="description" name="description" required value={this.state.des} onChange={event => this.setState({des: event.target.value})}></textarea><br/><br/>
                            <label className="subtitle has-text-white" htmlFor="nb_oc">Number of occurrences: </label>
                            <input className="input is-link" type="number"  name="nb_oc" required value={this.state.nb_oc} onChange={event => this.setState({nb_oc: event.target.value})}/><br/><br/>
                            <label className="subtitle has-text-white" htmlFor="freq">Frequency: </label>
                            <input className="input is-link" type="number"  name="freq" required value={this.state.freq} onChange={event => this.setState({freq: event.target.value})}/><br/><br/>
                            <button className="button is-success" onClick={() => this.addResolution()}>Add resolution</button>
                        </div>
                    </div>
                </div>
                <button className="modal-close is-large" aria-label="close" onClick={() => document.getElementById("popup-resolution").classList.remove('is-active')}></button>
            </div>
        );
    }
}

export default AddResolution;