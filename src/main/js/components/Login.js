import React from "react";
import styled from "styled-components";

const StyledDiv = styled.div`
  background-color:red;
`;

class Login extends React.Component {

    render() {
        return (
            <StyledDiv>
                <h1>Login</h1>
                <input/>
                <input/>
            </StyledDiv>
        );
    }
}

export default Login;