import React, { useState, useEffect } from 'react';
import { Alert } from "@mui/material";

const Webpage = ({ children, className }) =>
{
    const [error, setError] = useState(null);
    const [success, setSuccess] = useState(null);

    useEffect(() =>
    {
        const timeout = setTimeout(() =>
        {
            setError(null);
            setSuccess(null);
        }, 2000);

        return () => clearTimeout(timeout);
    }, [error, success]);

    return(
        <div className={className || "webpage"}>
            {children({ setError, setSuccess })}
            {error && <Alert severity="error">{error}</Alert>}
            {success && <Alert severity="success">{success}</Alert>}
        </div>
    );
};

export default Webpage;