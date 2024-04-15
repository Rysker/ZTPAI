import React, {useState, useEffect} from 'react';
import '../App.css';
import '../styles/ModelKit.css'
import  "../styles/VehicleDetails.css";
import "../styles/Reports.css";
import {DefaultNavbar} from "../components/DefaultNavbar";
import {Avatar, Divider} from "@material-ui/core";
import {FaBan, FaCheck} from "react-icons/fa";

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
            <div className="content-space-reports">
                <div className="reports-place">
                    <ReportEntity review_id="14534" user_name="User4" type="Language" reports_number="491"></ReportEntity>
                    <ReportEntity review_id="14534" user_name="User4" type="Language" reports_number="491"></ReportEntity>
                    <ReportEntity review_id="14534" user_name="User4" type="Language" reports_number="491"></ReportEntity>
                    <ReportEntity review_id="14534" user_name="User4" type="Language" reports_number="491"></ReportEntity>
                    <ReportEntity review_id="14534" user_name="User4" type="Language" reports_number="491"></ReportEntity>
                    <ReportEntity review_id="14534" user_name="User4" type="Language" reports_number="491"></ReportEntity>
                    <ReportEntity review_id="14534" user_name="User4" type="Language" reports_number="491"></ReportEntity>
                    <ReportEntity review_id="14534" user_name="User4" type="Language" reports_number="491"></ReportEntity>
                </div>
            </div>
        </div>
    )
}

function ReportEntity({review_id, user_name, type, reports_number})
{
    return(
        <div className="report-entity">
            <div className="report-entity-desc">
                <p>{review_id}</p>
                <p>{user_name}</p>
                <p>{type}</p>
                <p>{reports_number}</p>
                <div className="report-entity-buttons">
                    <FaBan class="ban-button"></FaBan>
                    <FaCheck class="dismiss-button"></FaCheck>
                </div>
            </div>
            <div className="report-entity-content">
                Forbidden text. Forbidden text. Forbidden text.Forbidden text.
                Forbidden text. Forbidden text.Forbidden text. Forbidden text.
                Forbidden text.Forbidden text. Forbidden text. Forbidden text.
            </div>
        </div>
    )
}
export default Profile;