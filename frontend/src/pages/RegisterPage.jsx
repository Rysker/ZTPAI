import React, {useEffect} from 'react';
import '../styles/LoginPage.css';
import RegisterForm from '../components/RegisterForm';
import axios from "axios";
import Webpage from "../components/Webpage";

const API_ENDPOINT = process.env.REACT_APP_API_ENDPOINT;

function RegisterPage()
{
    useEffect(() =>
    {
        localStorage.clear();
    })

    const isValidEmail = (email) =>
    {
        const emailRegex = /^[a-zA-Z0-9_+&*-]+(?:\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,7}$/;
        return emailRegex.test(email);
    };

    const isValidPassword = (password) =>
    {
        const passwordRegex = /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^!&+=])(?=\S+$).{8,}$/;
        return passwordRegex.test(password);
    };

    const handleRegister = async (formData, setError, setSuccess) =>
    {
        const { email, username, password, confirmPassword } = formData;
        if (!isValidEmail(email))
        {
            setError("Invalid email format!");
            return;
        }

        if (!isValidPassword(password))
        {
            setError("Password does not meet the requirements!");
            return;
        }

        if (password !== confirmPassword)
        {
            setError("Passwords do not match!");
            return;
        }

        try
        {
            const response = (await axios.post(`${API_ENDPOINT}/api/v1/auth/signup`, formData));
            const responseData = response.data;

            if (response.status === 200)
            {
                if (responseData.message === "User registered successfully!")
                {
                    setSuccess(responseData.message);
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
        <Webpage className={"login-webpage"}>
            {({ setError, setSuccess }) => (
                <>
                    <div className="login-navbar">
                        <div className="login-title">
                            ModelBase
                        </div>
                    </div>
                    <div className="login-content">
                        <RegisterForm onRegister={(formData) =>handleRegister(formData, setError, setSuccess)}></RegisterForm>
                    </div>
                </>
            )}
        </Webpage>
    )
}

export default RegisterPage;
