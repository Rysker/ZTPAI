import React from "react";
import '../styles/Navbar.css'
import BasicMenu from "./BasicMenu";

export function DefaultNavbar()
{
    const username = sessionStorage.getItem("username");
    return (
        <div className="navbar">
            <div className="title">
                <a href="/home" id="a-home">ModelBase</a>
            </div>
            <div className ="buttons">
                <div className="page-link">
                    <h1><a href="/vehicles" id="a-vehicles">Vehicles</a></h1>
                </div>
                <div className="page-link">
                    <h1><a href={`/profile/${username}/collection`} id="a-collection">Collection</a></h1>
                </div>
                <BasicMenu></BasicMenu>
            </div>
        </div>
    )
}