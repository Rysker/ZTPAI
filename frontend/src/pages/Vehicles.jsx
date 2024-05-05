import React, { useState, useEffect } from 'react';
import '../App.css';
import "../styles/Vehicles.css";
import { DefaultNavbar } from "../components/DefaultNavbar";
import Filter from "../components/Filter";
import {useLocation} from "react-router-dom";

function Vehicles()
{
    const [vehicles, setVehicles] = useState([]);
    const [loading, setLoading] = useState(true);
    const [filters, setFilters] = useState([]);
    const location = useLocation();
    const queryParams = new URLSearchParams(location.search);
    const type = queryParams.get('type');
    const [selectedFilters, setSelectedFilters] = useState([]);

    useEffect(() =>
    {
        setLoading(true);
        const token = sessionStorage.getItem('token');

        const fetchVehicles = async () =>
        {
            try
            {
                let response;
                if (!type || type === "Vehicles")
                {
                    response = await fetch(`/api/v1/vehicle/all`, {
                        headers: {
                            'Authorization': `Bearer ${token}`
                        }
                    });
                }
                else
                {
                    const tmp = type.slice(0, -1);
                    response = await fetch(`/api/v1/vehicle/type?type=${tmp}`, {
                        headers: {
                            'Authorization': `Bearer ${token}`
                        }
                    });
                }
                if (!response.ok)
                    throw new Error('Failed to fetch vehicles');
                const data = await response.json();
                setVehicles(data);
            }
            catch (error)
            {
                console.error('Error fetching vehicles:', error);
            }
            finally
            {
                setLoading(false);
            }
        };

        const fetchFilters = async () =>
        {
            try
            {
                const response = await fetch(`/api/v1/filters/vehicles`, {
                    headers: {
                        'Authorization': `Bearer ${token}`
                    }
                });
                if (!response.ok)
                    throw new Error("Failed to fetch filter options");
                const data = await response.json();
                setFilters(data);
            }
            catch (error)
            {
                console.error("Error fetching filter options:", error);
            }
        };

        fetchFilters();
        fetchVehicles();
    }, [type]);

    const handleCheckboxChange = (filterTitle, option) =>
    {
        const isSelected = selectedFilters.some(filter => filter.title === filterTitle && filter.option === option);
        if (isSelected)
            setSelectedFilters(selectedFilters.filter(filter => !(filter.title === filterTitle && filter.option === option)));
        else
            setSelectedFilters([...selectedFilters, { title: filterTitle, option }]);
    };

    const filterVehicles = () =>
    {
        if (selectedFilters.length === 0)
            return vehicles;
        else
        {
            return vehicles.filter(vehicle =>
            {
                const filtersByTitle = {};
                selectedFilters.forEach(filter =>
                {
                    if (!filtersByTitle[filter.title])
                        filtersByTitle[filter.title] = [];
                    filtersByTitle[filter.title].push(filter.option);
                });

                return Object.entries(filtersByTitle).every(([title, options]) =>
                {
                    return options.some(option =>
                    {
                        return vehicle[title.toLowerCase()] === option;
                    });
                });
            });
        }
    };

    return (
        <div className="webpage">
            <DefaultNavbar />
            <div className="content-space">
                <div className="content-space-info">
                    <div className="content-space-info-filter">
                        <Filter title="Filters" filters={filters} onCheckboxChange={handleCheckboxChange} />
                    </div>
                    <div className="content-space-info-display">
                        {loading ? (
                            <p>Loading...</p>
                        ) : vehicles.length === 0 ? (
                            <p>No vehicles found</p>
                        ) : (
                            filterVehicles().map(vehicle => (
                                <Vehicle key={vehicle.id} name={vehicle.name} photo={vehicle.vehiclePhoto} />
                            ))
                        )}
                    </div>
                </div>
            </div>
        </div>
    );
}

function Vehicle({ name, photo }) {
    return (
        <div className="vehicle-representation">
            <a href={`/vehicles/${name}`}>
                <img src={photo} alt={name} />
                <div className="vehicle-representation-bar">
                    <h1>{name}</h1>
                </div>
            </a>
        </div>
    );
}

export default Vehicles;
