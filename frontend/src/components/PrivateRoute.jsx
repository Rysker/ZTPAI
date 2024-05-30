import React, { useState, useEffect } from 'react';
import { Navigate } from 'react-router-dom';
import axios from 'axios';

const API_ENDPOINT = process.env.REACT_APP_API_ENDPOINT;

const PrivateRoute = ({ children, roles = [] }) =>
{
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() =>
    {
        const checkAuthentication = async () =>
        {
            try
            {
                const response = await axios.get(`${API_ENDPOINT}/api/v1/auth/check`, { withCredentials: true });
                if (response.status === 200)
                {
                    console.log(roles);
                    if(roles.length > 0)
                    {
                        const rolesResponse = await axios.get(`${API_ENDPOINT}/api/v1/profile/roles`, { withCredentials: true });
                        const userRoles = rolesResponse.data.roles;
                        console.log(roles);
                        console.log(userRoles);
                        if (roles.some(role => userRoles.includes(role)))
                            setIsAuthenticated(true);
                    }
                    else
                        setIsAuthenticated(true);
                }
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
