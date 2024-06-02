import {FaKey, FaEnvelope} from "react-icons/fa";
import '../styles/LoginForm.css'
import {useState} from "react";
export function LoginForm({ onLogin })
{
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const handleSubmit = (e) =>
    {
        e.preventDefault();
        onLogin({ email, password });
    };

    return (
        <form onSubmit = {handleSubmit}>
            <h1 id="login-title">Sign into your account</h1>
            <div className="input-box">
                <FaEnvelope className="icon"/>
                <input type="email" placeholder="E-mail" value={email} onChange={(e) => setEmail(e.target.value)} required autoComplete="off"></input>
            </div>
            <div className="input-box">
                <FaKey className="icon"/>
                <input type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} required></input>
            </div>
            <div className="input-box">
                <button className="login-button">LOGIN</button>
            </div>
            <div className="no-account-signup">
                <h1 id="no-account">Don't have an account?</h1>
                <h1><a href="/register" id="a-register">Sign in!</a></h1>
            </div>
        </form>
    )
}

export default LoginForm;