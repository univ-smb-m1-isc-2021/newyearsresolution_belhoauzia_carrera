import React from "react";
import styled from "styled-components";

const StyledDiv = styled.div`
text-align:center;
background-color:gray;
padding:15px;
witdh:50%;
`;

class Login extends React.Component {

    render() {
        return (
            <StyledDiv>
                <h1>Connexion</h1>
                <label htmlFor="name">Name: </label>
                <input type="text" id="name" name="name" required /><br/><br/>
                <label htmlFor="password">Password: </label>
                <input type="password" id="password" name="password" required/><br/><br/>
                <button>Log in</button>
            </StyledDiv>
        );
    }
}

export default Login;