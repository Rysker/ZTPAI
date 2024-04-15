import React, {useState, useEffect} from 'react';
import { FaBinoculars, FaCheck } from "react-icons/fa";
import '../App.css';
import  "../styles/VehicleDetails.css";
import {DefaultNavbar} from "../components/DefaultNavbar";
import Filter from "../components/Filter";
import RatingInformation from "../components/RatingInformation";

function VehicleDetails()
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
        <div className="webpage">
            <DefaultNavbar></DefaultNavbar>
            <div className="content-space">
                <div className="content-space-info">
                    <div className="content-space-info-filter">
                        <Filter title="Filter by"></Filter>
                    </div>
                    <div className="content-space-info-display-row">
                        <VehicleKit name="Leopard 1" photo={"/images/signupBackground.jpg"}></VehicleKit>
                        <VehicleKit name="Challenger 2" photo={"/images/signupBackground.jpg"}></VehicleKit>
                        <VehicleKit name="Leopard 2" photo={"/images/signupBackground.jpg"}></VehicleKit>
                        <VehicleKit name="T-34-85M" photo={"/images/signupBackground.jpg"}></VehicleKit>
                        <VehicleKit name="T-44" photo={"/images/signupBackground.jpg"}></VehicleKit>
                        <VehicleKit name="Leopard 1" photo={"/images/signupBackground.jpg"}></VehicleKit>
                        <VehicleKit name="Challenger 2" photo={"/images/signupBackground.jpg"}></VehicleKit>
                        <VehicleKit name="Leopard 2" photo={"/images/signupBackground.jpg"}></VehicleKit>
                        <VehicleKit name="T-34-85M" photo={"/images/signupBackground.jpg"}></VehicleKit>
                        <VehicleKit name="T-44" photo={"/images/signupBackground.jpg"}></VehicleKit>
                    </div>
                </div>
            </div>
        </div>
    )
}

function VehicleKit({name, photo})
{
    return(
        <div className="vehicle-kit">
            <img src={photo} alt={name + " photo"} />
            <div className="vehicle-kit-description">
                <div className="vehicle-kit-description-title">
                    German Leopard 1 Main Battle Tank
                </div>
                <div className="vehicle-kit-description-details">
                    <div className="vehicle-kit-description-details-elements">
                        <p>Scale: 1:35</p>
                        <p>Scale: 1:35</p>
                        <p>Scale: 1:35</p>
                        <p>Scale: 1:35</p>
                    </div>
                </div>
            </div>
            <div className="vehicle-kit-info">
                <div className="vehicle-kit-info-controls">
                    <FaCheck className="info-icon"></FaCheck>
                    <FaBinoculars className="info-icon"></FaBinoculars>
                </div>
                <div className="vehicle-kit-info-reviews">
                    <RatingInformation value="4.6"></RatingInformation>
                    <p>(19)</p>
                </div>
            </div>
        </div>
    )
}
export default VehicleDetails;