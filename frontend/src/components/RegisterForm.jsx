import {FaUser, FaKey, FaEnvelope} from "react-icons/fa";
import '../styles/LoginForm.css'
import {useState} from "react";
const RegisterForm = ({ onRegister }) =>
{
    const [formData, setFormData] = useState({
        email: "",
        username: "",
        password: "",
        confirmPassword: "",
    });

    const [error, setError] = useState(null);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try
        {
            await onRegister(formData);
        }
        catch (error)
        {
            setError(error.message);
        }
    };

    return (
        <form onSubmit = {handleSubmit}>
            <h1 id="login-title">Create your account</h1>
            <div className="input-box">
                <FaEnvelope className="icon" />
                <input type="email" name="email" placeholder="Email" value={formData.email} onChange={handleChange} required autoComplete="off" />
            </div>
            <div className="input-box">
                <FaUser className="icon" />
                <input type="text" name="username" placeholder="Username" value={formData.username} onChange={handleChange} required autoComplete="off" />
            </div>
            <div className="input-box">
                <FaKey className="icon" />
                <input type="password" name="password" placeholder="Password" value={formData.password} onChange={handleChange} required />
            </div>
            <div className="input-box">
                <FaKey className="icon" />
                <input type="password" name="confirmPassword" placeholder="Confirm Password" value={formData.confirmPassword} onChange={handleChange} required />
            </div>
            <div className="input-box">
                <button className="login-button">REGISTER</button>
            </div>
            <div className="no-account-signup">
                <h1 id="no-account">Already have an account?</h1>
                <h1><a href="/login" id="a-register">Sign up!</a></h1>
            </div>
        </form>
    )
}

export default RegisterForm;