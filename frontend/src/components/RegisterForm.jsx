import {FaUser, FaKey, FaEnvelope} from "react-icons/fa";
import '../styles/LoginForm.css'
export function RegisterForm()
{
    return (
        <form>
            <h1 id="login-title">Create your account</h1>
            <div className="input-box">
                <FaEnvelope className="icon"/>
                <input type="email" placeholder="Email" required></input>
            </div>
            <div className="input-box">
                <FaUser className="icon"/>
                <input type="text" placeholder="Username" required></input>
            </div>
            <div className="input-box">
                <FaKey className="icon"/>
                <input type="password" placeholder="Password" required></input>
            </div>
            <div className="input-box">
                <FaKey className="icon"/>
                <input type="password" placeholder="Confirm Password" required></input>
            </div>
            <div className="input-box">
                <button className="login-button">LOGIN</button>
            </div>
            <div className="no-account-signup">
                <h1 id="no-account">Already have an account?</h1>
                <h1><a href="/login" id="a-register">Sign up!</a></h1>
            </div>
        </form>
    )
}

export default RegisterForm;