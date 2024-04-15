import {FaKey, FaEnvelope} from "react-icons/fa";
import '../styles/LoginForm.css'
export function LoginForm()
{
    return (
        <form>
            <h1 id="login-title">Sign into your account</h1>
            <div className="input-box">
                <FaEnvelope className="icon"/>
                <input type="email" placeholder="E-mail" required></input>
            </div>
            <div className="input-box">
                <FaKey className="icon"/>
                <input type="password" placeholder="Password" required></input>
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