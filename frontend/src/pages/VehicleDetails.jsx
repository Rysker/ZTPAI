import React, { useState, useEffect } from 'react';
import { FaBinoculars, FaCheck } from "react-icons/fa";
import '../App.css';
import "../styles/VehicleDetails.css";
import { DefaultNavbar } from "../components/DefaultNavbar";
import Filter from "../components/Filter";
import RatingInformation from "../components/RatingInformation";
import { useParams } from "react-router-dom";
import axios from "axios";
import {Alert} from "@mui/material";
import VehicleKit from "../components/VehicleKit";

function VehicleDetails()
{
    const [vehicleKits, setVehicleKits] = useState([]);
    const [filters, setFilters] = useState([]);
    const [selectedFilters, setSelectedFilters] = useState([]);
    const { vehicle_name } = useParams();
    const [error, setError] = useState(null);

    useEffect(() =>
    {
        const timeout = setTimeout(() =>
        {
            setError(null);
        }, 2000);

        return () => clearTimeout(timeout);
    }, [error]);

    const changeObserved = async (id) =>
    {
        const response = await axios.post(`/api/v1/watchlist/change/${id}`);
        if(response.status !== 200)
            setError('Failed to change observed status.');
    };

    useEffect(() =>
    {
        const fetchData = async () =>
        {
            try
            {
                const [kitsResponse, filtersResponse] = await Promise.all([
                    axios.get(`/api/v1/vehicle/models/${vehicle_name}`),
                    axios.get(`/api/v1/filters/vehicles/${vehicle_name}`)
                ]);
                setVehicleKits(kitsResponse.data);
                setFilters(filtersResponse.data);
            }
            catch (error)
            {
                console.error('Error fetching data:', error);
            }
        };

        fetchData();
    }, [vehicle_name], [vehicleKits]);

    const handleCheckboxChange = (filterTitle, option) =>
    {
        const isSelected = selectedFilters.some(filter => filter.title === filterTitle && filter.option === option);
        if (isSelected)
            setSelectedFilters(selectedFilters.filter(filter => !(filter.title === filterTitle && filter.option === option)));
        else
            setSelectedFilters([...selectedFilters, { title: filterTitle, option }]);
    };

    const filteredKits = () =>
    {
        if (selectedFilters.length === 0)
            return vehicleKits;
        else
        {
            return vehicleKits.filter(kit =>
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
                        return kit[title.toLowerCase()] === option;
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
                        <Filter title={vehicle_name} filters={filters} onCheckboxChange={handleCheckboxChange} />
                    </div>
                    <div className="content-space-info-display-row">
                        {filteredKits().map((kit, index) => (
                            <VehicleKit key={index} kit={kit} changeObserved={changeObserved}/>
                        ))}
                    </div>
                </div>
            </div>
            {error && <Alert severity="error">{error}</Alert>}
        </div>
    );
}

export default VehicleDetails;
