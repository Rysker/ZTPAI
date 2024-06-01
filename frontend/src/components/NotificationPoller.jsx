import React, { useState, useEffect } from 'react';
import axios from 'axios';

const API_ENDPOINT = process.env.REACT_APP_API_ENDPOINT;

const NotificationPoller = ({ setError, setInformation }) =>
{
    useEffect(() =>
    {
        const interval = setInterval(() => {
            axios.get(`${API_ENDPOINT}/api/v1/notifications`)
                .then(response => {
                    setInformation(response.data.message);
                })
                .catch(() => {
                });
        }, 1000 * 15);
        return () => clearInterval(interval);
    }, [setError, setInformation]);

    return null;
};

export default NotificationPoller;
