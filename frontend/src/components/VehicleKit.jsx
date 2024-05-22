import React, {useEffect, useState} from "react";
import '../styles/VehicleKit.css';
import {FaBinoculars, FaCheck} from "react-icons/fa";
import RatingInformation from "./RatingInformation";
import Button from "@material-ui/core/Button";

function VehicleKit({ kit, changeObserved, addCollectible, showCollection = false, editable = false})
{
    const [isClicked, setIsClicked] = useState(false);
    const [statusColor, setStatusColor] = useState("");
    const endsWithNumber = /^\/(?:[^/]+\/)*\d+$/;
    const isLastCharacterNumber = endsWithNumber.test(window.location.pathname);
    const statusColorMap = {
        OWNED: "orange",
        FINISHED: "green",
        NONOWNED: "black"
    };

    useEffect(() =>
    {
        setStatusColor(statusColorMap[kit.status] || "black");
        setIsClicked(kit.onWatchlist);
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
            await addCollectible(kit.id);
            setStatusColor(statusColorMap["OWNED"] || "black")
        }
        catch
        {
            console.log("Error!");
        }
    };

    return (
            <div className="vehicle-kit">
                {isLastCharacterNumber ? null : <a href={`${window.location.pathname}/${kit.id}`} className="vehicle-kit-link" />}
                    <img src={kit.photo} alt={kit.name + " photo"}/>
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
                    </div>
                    <div className="vehicle-kit-info">
                        <div className="vehicle-kit-info-controls">
                            <FaCheck className="info-icon" style={{ color: statusColor }} />
                            <a href="" onClick={handleClick}>
                                <FaBinoculars className={isClicked ? "clickable-icon clicked" : "clickable-icon"} />
                            </a>
                        </div>
                        <div className="vehicle-kit-info-reviews">
                            <RatingInformation value={Number(kit.reviewsAverage)} />
                            <p>({kit.reviewsCount})</p>
                        </div>
                    </div>
            </div>

    );
}

export default VehicleKit;