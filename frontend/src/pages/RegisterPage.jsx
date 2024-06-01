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
    const handleRegister = async (formData, setError, setSuccess) =>
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
