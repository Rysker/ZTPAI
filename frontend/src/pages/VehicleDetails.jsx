import React, { useState, useEffect } from 'react';
import '../App.css';
import "../styles/VehicleDetails.css";
import { DefaultNavbar } from "../components/DefaultNavbar";
import Filter from "../components/Filter";
import { useParams } from "react-router-dom";
import VehicleKit from "../components/VehicleKit";
import axios from "axios";
import Webpage from "../components/Webpage";

const API_ENDPOINT = process.env.REACT_APP_API_ENDPOINT;

function VehicleDetails({ setError })
{
    const [vehicleKits, setVehicleKits] = useState([]);
    const [filters, setFilters] = useState([]);
    const [selectedFilters, setSelectedFilters] = useState([]);
    const { vehicle_name } = useParams();

    const changeObserved = async (id) =>
    {
        const response = await axios.post(`${API_ENDPOINT}/api/v1/watchlist/change/${id}`);
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
                    axios.get(`${API_ENDPOINT}/api/v1/vehicle/models/${vehicle_name}`),
                    axios.get(`${API_ENDPOINT}/api/v1/filters/vehicles/${vehicle_name}`)
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
        <Webpage className={"webpage"}>
            {({ setError, setSuccess }) => (
                <>
                    <DefaultNavbar />
                    <div className="content-space">
                        <div className="content-space-info">
                            <div className="content-space-info-filter">
                                <Filter title={vehicle_name} filters={filters} onCheckboxChange={handleCheckboxChange} />
                            </div>
                            <div className="content-space-info-display-row">
                                {filteredKits().map((kit, index) => (
                                    <VehicleKit setError={setError} setSuccess={setSuccess} key={index} kit={kit} changeObserved={changeObserved}/>
                                ))}
                            </div>
                        </div>
                    </div>
                </>
            )}
        </Webpage>
    );
}

export default VehicleDetails;
