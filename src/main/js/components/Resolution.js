import React from "react";
import axios from 'axios';
import ResolutionDo from "./ResolutionDo";

class Resolution extends React.Component {
    constructor(props) {
        super(props);

        let date = [];
        date.push(new Date());
        for(let i = 0 ; i < 6 ; i++){
            date.push(new Date(date[i]).getTime() - 24*60*60*1000);
        }
        this.state = {
            message:"",
            date:"",
            nb_do:[],
            haveResolution:false,
            date_list:date,
            date_selected:""
        }
    }
    componentDidMount() {
        if(!this.props.showButton) {
            axios.get(`/api/getUserRes?username=` + this.props.username + "&id=" + this.props.resolution.id)
                .then(res => {
                    if (res.data != null) {
                        this.setState({date: res.data.start_date, nb_do: res.data.liste})
                    }
                })
        }else if(this.props.username != ""){
            axios.get(`/api/haveResolution?username=` + this.props.username + "&id=" + this.props.resolution.id)
                .then(res => {
                    if (res.data != null) {
                        this.setState({haveResolution : !res.data})
                    }
                })
        }
    }

    componentDidUpdate() {
        if(!this.props.showButton) {
            axios.get(`/api/getUserRes?username=` + this.props.username + "&id=" + this.props.resolution.id)
                .then(res => {
                    if (res.data != null) {
                        this.setState({date: res.data.start_date, nb_do: res.data.liste})
                    }
                })
        }
    }

    addResolution(id){
        axios.get(`/api/addResolutionToUser?username=`+this.props.username+"&id="+id)
            .then(res => {
                this.setState({message :res.data,haveResolution:false })
            })
    }
    done(id){
        axios.get(`/api/done?username=`+this.props.username+"&id="+id+"&date="+this.state.date_selected)
            .then(res => {
                this.setState({nb_do :res.data.liste })
            })
    }
    failed(id){
        axios.get(`/api/failed?username=`+this.props.username+"&id="+id+"&date="+this.state.date_selected)
            .then(res => {
                this.setState({nb_do :res.data.liste })
            })
    }
    createNbDo =  (nb_do) => {
        return <ResolutionDo nb_do={nb_do} key={nb_do}/>;
    }
    createNbDos =  (nb_do) => {
        if(nb_do.length > 0) {
            return nb_do.map(this.createNbDo);
        }else{
            return <div className="subtitle has-text-white">No action done</div>
        }
    }

    createSelect(date){
        const d = new Date(date)
        const str = d.getDate()+"/"+(d.getMonth() + 1)+"/"+d.getFullYear()
        return <option value={str}>{str}</option>
    }
    show_date(){
        return this.state.date_list.map(this.createSelect)
    }
    render() {
        return (
            <div className="columns mt-1 is-centered">
                <div className="box has-text-centered is-one-third">
                    <h1 className="title has-text-white">{this.props.resolution.title}</h1>
                    <div className="subtitle has-text-white">{this.state.message}</div>
                    { this.props.showButton && this.props.username != "" && this.state.haveResolution ? <button className="button is-success" onClick={() => this.addResolution(this.props.resolution.id)}>Add resolution</button> : null }
                    { !this.props.showButton && this.props.username != "" ?
                        <div>
                            <div className="subtitle has-text-white"> Start date : {this.state.date}</div>
                            <div className="subtitle has-text-white"> NB DO :
                                {this.createNbDos(this.state.nb_do)}
                            </div>
                            <div className={"subtitle has-text-white"}>Choose a date : </div>
                            <div className="select mb-2 is-info">
                                <select value={this.state.date_selected} onChange={ (event)=>{this.setState({date_selected: event.target.value})}}>
                                    {this.show_date()}
                                </select>
                            </div><br/>
                            <button className="button is-success" onClick={() => this.done(this.props.resolution.id)}>Done</button>
                            <button className="button ml-3 is-danger" onClick={() => this.failed(this.props.resolution.id)}>Failed</button>
                        </div>
                    : null }
                </div>
            </div>
        );
    }
}

export default Resolution;