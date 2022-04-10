import React from "react";

class Legende extends React.Component {

    render() {
        return (
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
        );
    }
}

export default Legende;