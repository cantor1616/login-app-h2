import React from "react";

const User = props => (
    <div className="user__info">
        {
            props.result && <span className="user__value"> {props.result}</span>
        }
    </div>
    
);

export default User;