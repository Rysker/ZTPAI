import React, {useState, useEffect} from 'react';
import '../App.css';
import '../styles/ModelKit.css'
import  "../styles/VehicleDetails.css";
import "../styles/Reports.css";
import {DefaultNavbar} from "../components/DefaultNavbar";
import {FaBan, FaCheck} from "react-icons/fa";
import TooltipExpanded from "../components/TooltipExpanded";
import axios from "axios";
import Webpage from "../components/Webpage";

const API_ENDPOINT = process.env.REACT_APP_API_ENDPOINT;

function Profile({ setError, setSuccess })
{
    const [reports, setReports] = useState([]);

    useEffect(() =>
    {
        fetchReports();
    }, []);

    const fetchReports = () =>
    {
        axios.get(`${API_ENDPOINT}/api/v1/report`)
            .then(response => {
                setReports(response.data);
            })
            .catch(() => {
                setError("Error!");
            });
    };

    const handleAction = (reviewId, action, setError, setSuccess) =>
    {
        axios.post(`${API_ENDPOINT}/api/v1/report/review/verify/${reviewId}`, { message: action })
            .then(response => {
                if (response.status === 200)
                {
                    setReports(reports.filter(report => report.reviewId !== reviewId));
                    setSuccess(response.data.message);
                }
            })
            .catch(() => {
                setError("Error!");
            });
    };

    return (
        <Webpage className={"webpage"}>
            {({ setError, setSuccess }) => (
                <>
                    <DefaultNavbar></DefaultNavbar>
                    <div className="content-space-reports">
                        <div className="reports-place">
                            {reports.length > 0 ? (
                                reports.map((report, index) => (
                                    <ReportEntity
                                        key={report.reviewId}
                                        report={report}
                                        handleAction={handleAction}
                                        setError={setError}
                                        setSuccess={setSuccess}
                                    />
                                ))
                            ) : (
                                <p>No reports found!</p>
                            )}
                        </div>
                    </div>
                </>
            )}
        </Webpage>
    )
}

function ReportEntity({report, handleAction, setError, setSuccess})
{
    return(
        <div className="report-entity">
            <div className="report-entity-desc">
                <div className="report-entity-desc-info">
                    <p>Review id: {report.reviewId}</p>
                    <p>Main reason: {report.mainReason}</p>
                    <p>How many reports: {report.reportCount}</p>
                </div>
                <div className="report-entity-buttons">
                    <div onClick={() => handleAction(report.reviewId, 'Ban', setError, setSuccess)}>
                        <TooltipExpanded title="Ban" className="ban-button" >
                            <FaBan/>
                        </TooltipExpanded>
                    </div>
                    <div onClick={() => handleAction(report.reviewId, 'Reject', setError, setSuccess)}>
                        <TooltipExpanded title="Reject reports" className="dismiss-button">
                            <FaCheck/>
                        </TooltipExpanded>
                    </div>
                </div>
            </div>
            <div className="report-entity-content">
                {report.reviewContent}
            </div>
        </div>
    )
}
export default Profile;