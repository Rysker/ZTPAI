import React, {useState, useEffect} from 'react';
import '../App.css';
import '../styles/MainPage.css';
import Button from "@material-ui/core/Button";

function MainPage ()
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
        <div className="main-webpage">
            <div className="navbar">
                <div className="title">
                    <a href="/" id="a-home">ModelBase</a>
                </div>
                <div className ="buttons">
                </div>
            </div>
            <div className="webpage-body">
                <p><a href="/register">Join Us!</a></p>
            </div>
        </div>
    )
}

export default MainPage;