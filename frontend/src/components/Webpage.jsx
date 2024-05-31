import React, { useState, useEffect } from 'react';
import { Alert } from "@mui/material";
import NotificationPoller from "./NotificationPoller";

const Webpage = ({ children, className }) =>
{
    const [error, setError] = useState(null);
    const [success, setSuccess] = useState(null);
    const [information, setInformation] = useState(null);

    const username = localStorage.getItem('username');

    useEffect(() =>
    {
        const timeout = setTimeout(() => {
            setError(null);
            setSuccess(null);
        }, 2000);

        const timeout2 = setTimeout(() => {
            setInformation(null);
        }, 4000);

        return () => {
            clearTimeout(timeout);
            clearTimeout(timeout2);
        };
    }, [error, success, information]);

    return(
        <div className={className || "webpage"}>
            {username && (
                <NotificationPoller username={username} setError={setError} setInformation={setInformation} />
            )}
            {children({ setError, setSuccess })}
            {error && <Alert severity="error">{error}</Alert>}
            {success && <Alert severity="success">{success}</Alert>}
            {information && < Alert severity="info">{information}</Alert>}
        </div>
    );
};

export default Webpage;