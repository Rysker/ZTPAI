import React, { useState, useEffect } from 'react';
import { FaBinoculars, FaCheck } from "react-icons/fa";
import '../App.css';
import "../styles/VehicleDetails.css";
import { DefaultNavbar } from "../components/DefaultNavbar";
import Filter from "../components/Filter";
import RatingInformation from "../components/RatingInformation";
import { useParams } from "react-router-dom";
import axios from "axios";

function VehicleDetails()
{
    const [vehicleKits, setVehicleKits] = useState([]);
    const [filters, setFilters] = useState([]);
    const [selectedFilters, setSelectedFilters] = useState([]);
    const { vehicle_name } = useParams();

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
    }, [vehicle_name]);

    const handleCheckboxChange = (filterTitle, option) =>
    {
        const isSelected = selectedFilters.some(filter => filter.title === filterTitle && filter.option === option);
        if (isSelected)
            setSelectedFilters(selectedFilters.filter(filter => !(filter.title === filterTitle && filter.option === option)));
        else
            setSelectedFilters([...selectedFilters, { title: filterTitle, option }]);
    };

    const filteredKits = () => {
        if (selectedFilters.length === 0) {
            return vehicleKits;
        } else {
            return vehicleKits.filter(kit => {
                const filtersByTitle = {};
                selectedFilters.forEach(filter => {
                    if (!filtersByTitle[filter.title]) {
                        filtersByTitle[filter.title] = [];
                    }
                    filtersByTitle[filter.title].push(filter.option);
                });

                return Object.entries(filtersByTitle).every(([title, options]) => {
                    return options.some(option => {
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
                            <VehicleKit key={index} kit={kit} />
                        ))}
                    </div>
                </div>
            </div>
        </div>
    );
}

function VehicleKit({ kit }) {
    return (
        <div className="vehicle-kit">
            <img src={kit.photo} alt={kit.name + " photo"} />
            <div className="vehicle-kit-description">
                <div className="vehicle-kit-description-title">
                    {kit.name}
                </div>
                <div className="vehicle-kit-description-details">
                    <div className="vehicle-kit-description-details-elements">
                        <p>Scale: {kit.scale}</p>
                        <p>Manufacturer: {kit.manufacturer}</p>
                        <p>Manufacturer code: {kit.manufacturerCode}</p>
                        <p>Variant: {kit.variant}</p>
                    </div>
                </div>
            </div>
            <div className="vehicle-kit-info">
                <div className="vehicle-kit-info-controls">
                    <FaCheck className="info-icon" />
                    <FaBinoculars className="info-icon" />
                </div>
                <div className="vehicle-kit-info-reviews">
                    <RatingInformation value={kit.reviewsAverage} />
                    <p>({kit.reviewsCount})</p>
                </div>
            </div>
        </div>
    );
}

export default VehicleDetails;
