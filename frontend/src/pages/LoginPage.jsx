import React, {useState, useEffect} from 'react';
import '../styles/LoginPage.css';
import LoginForm from "../components/LoginForm";
import {Alert} from "@mui/material";

function LoginPage ()
{
    const [error, setError] = useState(null);
    const [success, setSuccess] = useState(false);

    useEffect(() => {
        const timeout = setTimeout(() => {
            setError(null);
            setSuccess(false);
        }, 2000);

        return () => clearTimeout(timeout);
    }, [error, success]);

    const handleLogin = async (credentials) =>
    {
        try
        {
            const response = await fetch('/api/v1/auth/signin', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(credentials),
            });

            if (!response.ok)
                throw new Error('Invalid credentials. Please try again.');

            const result = await response.json();
            const token = result.token;
            sessionStorage.setItem('token', token);
            setSuccess(true);
            setTimeout(() => {
                window.location.href = '/home';
            }, 2000);
        }
        catch (error)
        {
            console.error('Login failed', error);
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