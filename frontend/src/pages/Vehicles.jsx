import React, {useState, useEffect} from 'react';
import '../App.css';
import  "../styles/Vehicles.css";
import {DefaultNavbar} from "../components/DefaultNavbar";
import Filter from "../components/Filter";

function Vehicles()
{
    const [message, setMessage] = useState("");
    let type = new URLSearchParams(window.location.search).get('type');
    if(type == null)
        type = "Vehicles"
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
                        <Filter title={type}></Filter>
                    </div>
                    <div className="content-space-info-display">
                        <Vehicle name="Leopard 1" photo={"https://storage.googleapis.com/model-base-images/signupBackground.jpg"}></Vehicle>
                        <Vehicle name="T-80BVM" photo={"https://storage.googleapis.com/model-base-images/signupBackground.jpg"}></Vehicle>
                        <Vehicle name="Object 292" photo={"https://storage.googleapis.com/model-base-images/signupBackground.jpg"}></Vehicle>
                        <Vehicle name="Panther" photo={"https://storage.googleapis.com/model-base-images/signupBackground.jpg"}></Vehicle>
                        <Vehicle name="T-80BVM" photo={"https://storage.googleapis.com/model-base-images/signupBackground.jpg"}></Vehicle>
                        <Vehicle name="M1A2 Abrams" photo={"https://storage.googleapis.com/model-base-images/signupBackground.jpg"}></Vehicle>
                        <Vehicle name="Leopard 1" photo={"https://storage.googleapis.com/model-base-images/signupBackground.jpg"}></Vehicle>
                        <Vehicle name="T-80BVM" photo={"https://storage.googleapis.com/model-base-images/signupBackground.jpg"}></Vehicle>
                        <Vehicle name="Object 292" photo={"https://storage.googleapis.com/model-base-images/signupBackground.jpg"}></Vehicle>
                    </div>
                </div>
            </div>
        </div>
    )
}

function Vehicle({name, photo})
{
    return(
        <div className="vehicle-representation">
            <img src={photo} alt={name + " photo"} />
            <div className="vehicle-representation-bar">
                <h1>{name}</h1>
            </div>
        </div>
    )
}
export default Vehicles;