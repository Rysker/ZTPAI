import React, { useState } from 'react';
import '../styles/LoginPage.css';
import RegisterForm from '../components/RegisterForm';
import {Alert} from "@mui/material";
import axios from "axios";

const API_ENDPOINT = process.env.REACT_APP_API_ENDPOINT;

function RegisterPage()
{
    const [message, setMessage] = useState("");
    const [error, setError] = useState(null);
    const [success, setSuccess] = useState(false);

    const handleRegister = async (formData) =>
    {
        try
        {
            const response = (await axios.post(`${API_ENDPOINT}/api/v1/auth/signup`, formData));
            const responseData = response.data;

            if (response.status === 200)
            {
                if (responseData.message === "User registered successfully!")
                {
                    setSuccess(true);
                    setMessage(responseData.message);
                    setTimeout(() =>
                    {
                        window.location.href = '/login';
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
