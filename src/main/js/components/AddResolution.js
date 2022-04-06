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
    addResolution(title,des,nb_oc,freq){
        axios.get(`/api/newResolution?title=`+title+"&des="+des+"&nb_oc="+nb_oc+"&freq="+freq+"&username="+this.props.username)
            .then(res => {
                if(res.data == "ok") {
                    document.getElementById("popup-resolution").classList.remove('is-active')
                }else{this.setState({ erreur : "Something went wrong"})}
            })
            .catch(res =>{
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
                            <input className="input is-link" type="text" id="name" name="name" required onChange={event => this.setState({title: event.target.value})}/><br/><br/>
                            <label className="subtitle has-text-white" htmlFor="description">Description: </label>
                            <textarea  className="input is-link" type="text" id="description" name="description" required onChange={event => this.setState({des: event.target.value})}></textarea><br/><br/>
                            <label className="subtitle has-text-white" htmlFor="nb_oc">Number of occurrences: </label>
                            <input className="input is-link" type="number"  name="nb_oc" required onChange={event => this.setState({nb_oc: event.target.value})}/><br/><br/>
                            <label className="subtitle has-text-white" htmlFor="freq">Frequency: </label>
                            <input className="input is-link" type="number"  name="freq" required onChange={event => this.setState({freq: event.target.value})}/><br/><br/>
                            <button className="button is-success" onClick={() => this.addResolution(this.state.title,this.state.des,this.state.nb_oc,this.state.freq)}>Add resolution</button>
                        </div>
                    </div>
                </div>
                <button className="modal-close is-large" aria-label="close" onClick={() => document.getElementById("popup-resolution").classList.remove('is-active')}></button>
            </div>
        );
    }
}

export default AddResolution;