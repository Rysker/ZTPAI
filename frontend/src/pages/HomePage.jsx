import { TbTank } from "react-icons/tb";
import { FaFighterJet } from "react-icons/fa";
import { GiBattleship } from "react-icons/gi";
import '../App.css';
import '../styles/HomePage.css';
import  "../styles/VehicleDetails.css";
import "../styles/Profile.css";
import {DefaultNavbar} from "../components/DefaultNavbar";
import Webpage from "../components/Webpage";
import React, {useEffect, useState} from "react";
import LoadingScreen from "../components/LoadingScreen";

function HomePage()
{
    const [showLoadingScreen, setShowLoadingScreen] = useState(true);
    const [username, setUsername] = useState(null);

    useEffect(() =>
    {
        setUsername(localStorage.getItem("username"));
        const timer = setTimeout(() => {
            setShowLoadingScreen(false);
        }, 2000);
        return () => clearTimeout(timer);
    })

    if (showLoadingScreen)
        return <LoadingScreen/>;

    return (
        <Webpage className={"webpage"}>
            {({ setError, setSuccess }) => (
                <>
                    <DefaultNavbar/>
                    <div className="content-space-home">
                        <div className="vehicle-pick">
                            <a href="/vehicles?type=Tanks">
                                <img src={'/images/Tanks.jpg'} alt="Tanks" />
                                <div className="homepage-representation-bar">
                                    <h1>Tanks</h1>
                                </div>
                            </a>
                        </div>
                        <div className="vehicle-pick">
                            <a href="/vehicles?type=Planes">
                                <img src={'/images/Planes.jpg'} alt="Planes"/>
                                <div className="homepage-representation-bar">
                                    <h1>Planes</h1>
                                </div>
                            </a>
                        </div>
                        <div className="vehicle-pick">
                            <a href="/vehicles?type=Ships">
                                <img src={'/images/Ships.jpg'} alt="Ships" />
                                <div className="homepage-representation-bar">
                                    <h1>Ships</h1>
                                </div>
                            </a>
                        </div>
                    </div>
                </>
            )}
        </Webpage>
    )
}
export default HomePage;