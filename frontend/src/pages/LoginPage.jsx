import React from 'react';
import '../styles/LoginPage.css';
import LoginForm from "../components/LoginForm";
import axios from "axios";
import Webpage from "../components/Webpage";

const API_ENDPOINT = process.env.REACT_APP_API_ENDPOINT;

function LoginPage()
{
    const handleLogin = async (credentials, setError, setSuccess) =>
    {
        try
        {
            const response = await axios.post(`${API_ENDPOINT}/api/v1/auth/signin`, credentials, { withCredentials: true });
            console.log(response);
            if (response.status === 200)
            {
                localStorage.setItem('username', response.data.username);
                localStorage.setItem('roles', response.data.roles);
                setSuccess("Login successful!");
                setTimeout(() =>
                {
                    window.location.href = '/home';
                }, 2000);
            }
            else
                new Error("Invalid credentials. Try again!");
        }
        catch (error)
        {
            setError("Invalid credentials. Try again!");
        }
    };

    return (
        <Webpage className={"login-webpage"}>
            {({ setError, setSuccess }) => (
                <>
                    <div className="login-navbar">
                        <div className="login-title">
                            ModelBase
                        </div>
                    </div>
                    <div className="login-content">
                        <LoginForm onLogin={(credentials) => handleLogin(credentials, setError, setSuccess)} />
                    </div>
                </>
            )}
        </Webpage>
    );
}

export default LoginPage;