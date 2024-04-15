import React, {useState, useEffect} from 'react';
import '../styles/LoginPage.css';
import LoginForm from "../components/LoginForm";

function LoginPage ()
{
    const [message, setMessage] = useState("");

    useEffect(() => {
        fetch('/hello')
            .then(response => response.text())
            .then(message => {
                setMessage(message);
            });
    },[])
    return (
        <div className="login-webpage">
            <div className="login-navbar">
                <div className="login-title">
                    ModelBase
                </div>
            </div>
            <div className="login-content">
                <LoginForm></LoginForm>
            </div>
        </div>
    )
}

export default LoginPage;