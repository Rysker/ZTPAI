import React, {useState, useEffect} from 'react';
import '../styles/LoginPage.css';
import LoginForm from "../components/LoginForm";
import {Alert} from "@mui/material";
import axios from "axios";

const API_ENDPOINT = process.env.REACT_APP_API_ENDPOINT;

function LoginPage ()
{
    const [error, setError] = useState(null);
    const [success, setSuccess] = useState(false);

    useEffect(() =>
    {
        const timeout = setTimeout(() =>
        {
            setError(null);
            setSuccess(false);
        }, 2000);

        return () => clearTimeout(timeout);
    }, [error, success]);

    const handleLogin = async (credentials) =>
    {
        try
        {
                const response = await axios.post(`${API_ENDPOINT}/api/v1/auth/signin`, credentials, { withCredentials: true });
                console.log(response);
                if (response.status === 200)
                {
                    setSuccess(true);
                    setTimeout(() =>
                    {
                        window.location.href = '/home';
                    }, 2000);
                }
                else
                    throw new Error('Invalid credentials. Please try again.');
        }
        catch (error)
        {
            setError('Invalid credentials. Please try again.');
        }
        };

    return (
        <div className="login-webpage">
            <div className="login-navbar">
                <div className="login-title">
                    ModelBase
                </div>
            </div>
            <div className="login-content">
                <LoginForm onLogin={handleLogin}></LoginForm>
            </div>
            {error && <Alert severity="error">{error}</Alert>}
            {success && <Alert severity="success">Login successful!</Alert>}
        </div>
    )
}

export default LoginPage;