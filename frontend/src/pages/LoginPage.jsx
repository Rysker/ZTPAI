import React, {useEffect} from 'react';
import '../styles/LoginPage.css';
import LoginForm from "../components/LoginForm";
import axios from "axios";
import Webpage from "../components/Webpage";

const API_ENDPOINT = process.env.REACT_APP_API_ENDPOINT;

function LoginPage()
{
    useEffect(() =>
    {
        localStorage.clear();
    })

    const handleLogin = async (credentials, setError, setSuccess) =>
    {
        try
        {
            const response = await axios.post(`${API_ENDPOINT}/api/v1/auth/signin`, credentials, { withCredentials: true });
            const responseData = response.data;

            if (response.status === 200)
            {
                if (responseData.message === "Success")
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
                {
                    setError(responseData.message);
                    setTimeout(() =>
                    {
                        setError(null);
                    }, 3000);
                }
            }
        }
        catch (error)
        {
            setError("Doesn't work");
            setTimeout(() =>
            {
                setError(null);
            }, 3000);
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