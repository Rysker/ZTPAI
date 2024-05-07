import React, { useState, useEffect } from 'react';
import { Navigate } from 'react-router-dom';
import axios from 'axios';

const PrivateRoute = ({ children }) =>
{
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() =>
    {
        const checkAuthentication = async () =>
        {
            try
            {
                const response = await axios.get('/api/v1/auth/check', { withCredentials: true });
                if (response.data === true)
                    setIsAuthenticated(true);
            }
            catch (error)
            {
                console.error('Error checking authentication:', error);
            }
            finally
            {
                setIsLoading(false);
            }
        };

        checkAuthentication();
    }, []);

    if (isLoading)
        return <div>Loading...</div>;
    return isAuthenticated ? children : <Navigate to="/login" />;
};

export default PrivateRoute;
