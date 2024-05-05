import React, { useState } from 'react';
import '../styles/LoginPage.css';
import RegisterForm from '../components/RegisterForm';
import {Alert} from "@mui/material";

function RegisterPage()
{
    const [message, setMessage] = useState("");
    const [error, setError] = useState(null);
    const [success, setSuccess] = useState(false);

    const handleRegister = async (formData) =>
    {
        try
        {
            const response = await fetch('/api/v1/auth/signup', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData),
            });

            const responseData = await response.text();

            if (!response.ok || responseData !== "User registered successfully!")
            {
                setError(responseData);
                setTimeout(() => {
                    setError(null);
                }, 3000);
            }
            else
            {
                setSuccess(true);
                setMessage(responseData);
                setTimeout(() => {
                    window.location.href = '/login';
                }, 2000);
            }
        }
        catch (error)
        {
            console.error('Registration failed', error);
            setError('Registration failed. Please try again.');
            setTimeout(() => {
                setError(null);
            }, 3000);
        }
    };

    return(
        <div className="login-webpage">
            <div className="login-navbar">
                <div className="login-title">
                    ModelBase
                </div>
            </div>
            <div className="login-content">
                <RegisterForm onRegister={handleRegister}></RegisterForm>
            </div>
            {error && <Alert severity="error">{error}</Alert>}
            {success && <Alert severity="success">Account created successfully!</Alert>}
        </div>
    )
}

export default RegisterPage;
