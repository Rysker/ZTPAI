import React, {useState, useEffect} from 'react';
import { TbTank } from "react-icons/tb";
import { FaFighterJet } from "react-icons/fa";
import { GiBattleship } from "react-icons/gi";
import '../App.css';
import '../styles/HomePage.css';
import  "../styles/VehicleDetails.css";
import "../styles/Profile.css";
import {DefaultNavbar} from "../components/DefaultNavbar";

function Profile()
{
    return (
        <div className="webpage">
            <DefaultNavbar></DefaultNavbar>
            <div className="content-space-home">
                <div className="vehicle-pick">
                    <a href="/vehicles?type=Tanks">
                        <TbTank></TbTank>
                    </a>
                </div>
                <div className="vehicle-pick">
                    <a href="/vehicles?type=Planes">
                        <FaFighterJet></FaFighterJet>
                    </a>
                </div>
                <div className="vehicle-pick">
                    <a href="/vehicles?type=Ships">
                        <GiBattleship></GiBattleship>
                    </a>
                </div>
            </div>
        </div>
    )
}
export default Profile;