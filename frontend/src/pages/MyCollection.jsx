import React, {useState, useEffect} from 'react';
import {FaBinoculars, FaCheck, FaEye} from "react-icons/fa";
import '../App.css';
import  "../styles/VehicleDetails.css";
import {DefaultNavbar} from "../components/DefaultNavbar";
import Filter from "../components/Filter";
import RatingInformation from "../components/RatingInformation";

function MyCollection()
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
                        <Filter title="109"></Filter>
                    </div>
                    <div className="content-space-info-display-row">
                        <Collectible name="Leopard 1" photo={"/images/signupBackground.jpg"} completion={"12-04-2024"}></Collectible>
                        <Collectible name="Challenger 2" photo={"/images/signupBackground.jpg"}></Collectible>
                        <Collectible name="Leopard 2" photo={"/images/signupBackground.jpg"}></Collectible>
                        <Collectible name="Leopard 1" photo={"/images/signupBackground.jpg"}></Collectible>
                        <Collectible name="Challenger 2" photo={"/images/signupBackground.jpg"}></Collectible>
                        <Collectible name="Leopard 2" photo={"/images/signupBackground.jpg"}></Collectible>
                    </div>
                </div>
            </div>
        </div>
    )
}

function Collectible({name, photo, completion=""})
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
                    <FaEye className="info-icon"></FaEye>
                    <FaCheck className="info-icon"></FaCheck>
                    <FaBinoculars className="info-icon"></FaBinoculars>
                </div>
                <div className="vehicle-kit-info-reviews">
                    {completion && <p>Completion date: {completion}</p>}
                </div>
            </div>
        </div>
    )
}
export default MyCollection;