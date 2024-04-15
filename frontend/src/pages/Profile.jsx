import React, {useState, useEffect} from 'react';
import '../App.css';
import '../styles/ModelKit.css'
import  "../styles/VehicleDetails.css";
import "../styles/Profile.css";
import {DefaultNavbar} from "../components/DefaultNavbar";
import Button from "@material-ui/core/Button";
import {Avatar, Divider} from "@material-ui/core";

function Profile()
{
    const [message, setMessage] = useState("");
    let photo = "/images/signupBackground.jpg";
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
                    <div className="content-space-info-display-row">
                        <div className="profile">
                            <div className="profile-header">
                                <div className="profile-header-avatar">
                                    <div className="profile-header-avatar-container">
                                        <Avatar className="profile-header-avatar-entity">A</Avatar>
                                    </div>
                                </div>
                                <div className="profile-header-description">
                                    <div className="profile-header-description-top">
                                        <h1 id="user-profile-name">User1</h1>
                                        <Button>Follow</Button>
                                    </div>
                                    <div className="profile-header-description-bottom">
                                        <p id="user-profile-description">My name is A. Beginner model maker.</p>
                                    </div>
                                </div>
                            </div>
                            <Divider orientation="horizontal" variant="middle"/>
                            <div className="profile-desc">
                                <div className="profile-desc-collection">
                                    <h1>Collection (109)</h1>
                                    <div className="profile-desc-collection-gallery">
                                        <CollectibleEntity name="Leopard1" photo={photo}></CollectibleEntity>
                                    </div>
                                    <h2>+More</h2>
                                </div>
                                <div className="profile-desc-stats">
                                    <div className="profile-desc-stats-container">
                                        <h1>Reviews</h1>
                                        <h1>Reputation</h1>
                                        <h1>Member since</h1>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

function CollectibleEntity({name, photo})
{
    return(
        <div className="profile-desc-collection-entity">
            <img src={photo} alt={name + " photo"} />
            <div className="profile-desc-collection-entity-bar">
                {name}
            </div>
        </div>
    )
}
export default Profile;