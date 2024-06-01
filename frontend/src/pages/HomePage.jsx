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

    useEffect(() =>
    {
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
                                <TbTank/>
                            </a>
                        </div>
                        <div className="vehicle-pick">
                            <a href="/vehicles?type=Planes">
                                <FaFighterJet/>
                            </a>
                        </div>
                        <div className="vehicle-pick">
                            <a href="/vehicles?type=Ships">
                                <GiBattleship/>
                            </a>
                        </div>
                    </div>
                </>
            )}
        </Webpage>
    )
}
export default HomePage;