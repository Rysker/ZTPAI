import React, {useEffect, useState} from "react";
import '../styles/VehicleKit.css';
import {FaBinoculars, FaCheck, FaEye, FaEyeSlash, FaTrash} from "react-icons/fa";
import RatingInformation from "./RatingInformation";
import Button from "@material-ui/core/Button";
import axios from "axios";

const API_ENDPOINT = process.env.REACT_APP_API_ENDPOINT;

function VehicleKit({ kit, changeObserved, setError, setSuccess, addCollectible, showCollection = false, editable = false, deleteCollectible = null, finishCollectible = null})
{
    const [isClicked, setIsClicked] = useState(false);
    const [isPublic, setIsPublic] = useState(false);
    const [statusColor, setStatusColor] = useState("");
    const [completionDate, setCompletionDate] = useState(kit.completionDate || "");
    const [isEditing, setIsEditing] = useState(false);
    const [tempCompletionDate, setTempCompletionDate] = useState(kit.completionDate || "");
    const endsWithNumber = /^\/(?:[^/]+\/)*\d+$/;
    const isLastCharacterNumber = endsWithNumber.test(window.location.pathname);

    const statusColorMap = {
        Owned: "orange",
        Finished: "green",
        Nonowned: "black"
    };

    useEffect(() =>
    {
        setStatusColor(statusColorMap[kit.status] || "black");
        setIsClicked(kit.onWatchlist);
        setIsPublic(kit.isPublic === "true");
    }, [kit.status]);

    const handleClick = async (event) =>
    {
        event.preventDefault()
        try
        {
            await changeObserved(kit.id);
            setIsClicked(!isClicked);
        }
        catch
        {
            console.log("Error!");
        }
    };

    const handleProgress = async (event) =>
    {
        event.preventDefault()
        try
        {
            await addCollectible(kit.collectibleId);
            setStatusColor(statusColorMap["Owned"] || "black");
        }
        catch
        {
            console.log("Error!");
        }
    };

    const finCollectible = async (event) =>
    {
        event.preventDefault()
        try
        {
            await axios.post(`${API_ENDPOINT}/api/v1/collection/collectible/finish/${kit.collectibleId}`);
            if(finishCollectible !== null)
            {
                finishCollectible(kit.id);
                setCompletionDate(new Date().toISOString().split('T')[0]);
            }
        }
        catch
        {
            console.log("Error!");
        }
    };

    const changeVisible = async (event) =>
    {
        event.preventDefault()
        try
        {
            await axios.post(`${API_ENDPOINT}/api/v1/collection/collectible/visibility/${kit.collectibleId}`);
            setIsPublic(!isPublic);
        }
        catch
        {
            console.log("Error!");
        }
    };

    const removeCollectible = async (event) =>
    {
        event.preventDefault()
        try
        {
            await axios.delete(`${API_ENDPOINT}/api/v1/collection/collectible/delete/${kit.collectibleId}`);
            if(deleteCollectible !== null)
                deleteCollectible(kit.id);
        }
        catch
        {
            console.log("Error!");
        }
    };

    const validateDate = (dateString) =>
    {
        const datePattern = /^\d{4}-\d{2}-\d{2}$/;
        return datePattern.test(dateString);
    };

    const saveCompletionDate = async () =>
    {
        if (!validateDate(tempCompletionDate))
        {
            setError("Invalid date format");
            return;
        }
        try
        {
            await axios.put(`${API_ENDPOINT}/api/v1/collection/collectible/edit/${kit.collectibleId}`, { completionDate: tempCompletionDate });
            setIsEditing(false);
            setCompletionDate(tempCompletionDate);
            setSuccess("Date successfully changed!");
        }
        catch
        {
            setIsEditing(false);
            setError("Date couldn't be changed!")
        }
    };

    const handleEditing = () =>
    {
        setIsEditing(true);
    };

    const handleDateChange = (event) =>
    {
        setTempCompletionDate(event.target.value);
    };

    const handleBlur = () =>
    {
        if (tempCompletionDate.trim() === "")
            setIsEditing(false);
        else
            saveCompletionDate();
    };

    const handleBlurOrEnter = (event) =>
    {
        if (event.type === "blur" || event.key === "Enter")
            handleBlur();
    };

    return (
            <div className="vehicle-kit">
                {isLastCharacterNumber || showCollection ? <img src={kit.photo} alt={kit.name + " photo"}/> : <a href={`${window.location.pathname}/${kit.id}`} className="vehicle-kit-link"> <img src={kit.photo} alt={kit.name + " photo"}/> </a>}
                    <div className="vehicle-kit-description">
                        <div className="vehicle-kit-description-title">
                            {kit.name}
                        </div>
                        <div className="vehicle-kit-description-details">
                            <div className="vehicle-kit-description-details-elements">
                                <p>Scale: {kit.scale}</p>
                                <p>Manufacturer: {kit.manufacturer}</p>
                                <p>Manufacturer Code: {kit.manufacturerCode}</p>
                                <p>Variant: {kit.variant}</p>
                            </div>
                        </div>
                        {isLastCharacterNumber ? <Button id="vehicle-kit-add" onClick={handleProgress}>Add to collection</Button> : null }
                        {(editable && statusColor !== "green") ? <Button id="vehicle-kit-add" onClick={(event) => finCollectible(event, kit.collectibleId)}>Finish</Button> : null }
                    </div>
                    <div className="vehicle-kit-info">
                        <div className="vehicle-kit-info-controls">
                            {showCollection && editable && (
                                <a href="" onClick={(event) => removeCollectible(event)}>
                                     <FaTrash className="info-icon"/>
                                </a>
                            )}
                            {showCollection && editable && (
                                <a href="" onClick={(event) => changeVisible(event)}>
                                    {isPublic ? <FaEye className="info-icon" /> : <FaEyeSlash className="info-icon"/>}
                                </a>
                            )}
                            <FaCheck className="info-icon" style={{ color: statusColor }} />
                            {(!showCollection || editable) && (
                                <a href="" onClick={handleClick}>
                                    <FaBinoculars className={isClicked ? "clickable-icon clicked" : "clickable-icon"} />
                                </a>
                            )}
                        </div>
                        <div className="vehicle-kit-info-reviews">
                            {showCollection && (
                                isEditing ? (
                                    <input
                                        className="collection-date-edit-input"
                                        type="date"
                                        value={tempCompletionDate}
                                        onChange={handleDateChange}
                                        onBlur={handleBlur}
                                        onKeyDown={handleBlurOrEnter}
                                        autoFocus
                                    />
                                ) : (
                                    <p class = {editable ? "editable" : "non-editable"} onClick={editable ? handleEditing : undefined}>{completionDate}</p>
                                )
                            )}
                            {!showCollection && (
                                <>
                                    <RatingInformation value={Number(kit.reviewsAverage)} />
                                    <p>({kit.reviewsCount})</p>
                                </>
                            )}
                        </div>
                    </div>
            </div>

    );
}

export default VehicleKit;